/*
 * Processes a customer's payment given a unique customer key and payment amount.  The transaction
 * updates the related warehouse's and distribution station's total sales fields and adjusts the
 * customer's balance, payment count, and payment total accordingly.
 *
 * GIVENS:
 *
 * 		Customer
 *			customer_key = 1
 *		
 *		Payment Amount = $39.34 - This amount is the total amount due on the customer's last two orders.
 */

BEGIN TRANSACTION;

-- ################# DATA SETUP ######################

UPDATE warehouses
SET sales_total = sales_total - 39.34
WHERE ware_key = 1;

UPDATE dist_sts
SET sales_total = sales_total - 39.34
WHERE dist_station_key = 1;

UPDATE customers
SET balance = balance + 39.34, payment_count = payment_count - 1, payment_total = payment_total - 39.34
WHERE customer_key = 1;

-- ####################################################

-- Creates a temporary table that stores all of the info for the new payment
DROP TABLE IF EXISTS new_payment CASCADE;
CREATE TEMP TABLE new_payment AS
SELECT warehouses.ware_key, dist_sts.dist_station_key, customers.customer_key
FROM warehouses
JOIN link_wares_dists ON warehouses.ware_key = link_wares_dists.ware_key
JOIN dist_sts ON link_wares_dists.dist_station_key = dist_sts.dist_station_key
JOIN link_dists_custs ON dist_sts.dist_station_key = link_dists_custs.dist_station_key
JOIN customers ON link_dists_custs.customer_key = customers.customer_key
WHERE customers.customer_key = 1;

-- Updates the given customer's warehouse sales total
UPDATE warehouses
SET sales_total = sales_total + 39.34
FROM new_payment
WHERE warehouses.ware_key = new_payment.ware_key;

-- Updates the given customer's distribution station sales total
UPDATE dist_sts
SET sales_total = sales_total + 39.34
FROM new_payment
WHERE dist_sts.dist_station_key = new_payment.dist_station_key;

-- Updates the given customer's current balance, payment count, and payment total
UPDATE customers
SET balance = balance - 39.34, payment_count = payment_count + 1, payment_total = payment_total + 39.34
FROM new_payment
WHERE customers.customer_key = new_payment.customer_key;

COMMIT;