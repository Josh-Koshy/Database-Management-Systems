/*
 * Finds all undelivered line item's in a given warehouse and sets them as delivered. 
 *
 * GIVENS:
 *
 * 		Warehouses
 *			ware_key = 1
 *		
 * Notes: Before the query runs, the last two orders of customer 1 and 2 are flagged as
 * undelivered given that our Phase 1 test data was to assume that all orders had already
 * been delivered.
 */

BEGIN TRANSACTION;

-- ################# DATA SETUP ######################

UPDATE customers
SET delivery_total = delivery_total - 2
WHERE customer_key = 1 OR customer_key = 2;

UPDATE line_items
SET date_delivered = NULL
WHERE line_item_key > 40 AND line_item_key < 51;

UPDATE line_items
SET date_delivered = NULL
WHERE line_item_key > 90 AND line_item_key < 101;

-- ####################################################

-- Creates temporary table that finds the all line items within a warehouse that have not been delivered and updates
-- line_items and sets all undelivered lines's delivered date to the current date
DROP TABLE IF EXISTS undelivered_items CASCADE;
CREATE TEMP TABLE undelivered_items AS
SELECT 
	customers.customer_key, customers.balance AS temp_balance, customers.payment_count, customers.payment_total, customers.delivery_total,
	orders.order_key, orders.order_date, orders.is_complete,
	line_items.line_item_key, line_items.line_item_id, line_items.line_sales, line_items.date_delivered

FROM warehouses
JOIN link_wares_dists ON warehouses.ware_key = link_wares_dists.ware_key
JOIN dist_sts ON link_wares_dists.dist_station_key = dist_sts.dist_station_key
JOIN link_dists_custs ON dist_sts.dist_station_key = link_dists_custs.dist_station_key
JOIN customers ON link_dists_custs.customer_key = customers.customer_key
JOIN link_custs_orders ON customers.customer_key = link_custs_orders.customer_key
JOIN orders ON link_custs_orders.order_key = orders.order_key
JOIN line_items ON orders.order_key = line_items.order_key
WHERE warehouses.ware_key = 1 AND line_items.date_delivered ISNULL
ORDER BY orders.order_key ASC;

UPDATE line_items
SET date_delivered = CURRENT_DATE
FROM undelivered_items
WHERE line_items.line_item_key = undelivered_items.line_item_key;

-- Updates a customer's balance with all of the items that were delivered
DROP TABLE IF EXISTS new_balance CASCADE;
CREATE TEMP TABLE new_balance AS
SELECT customer_key, SUM( line_sales ) AS delivery_total
FROM undelivered_items
GROUP BY customer_key;

UPDATE customers
SET balance = balance + new_balance.delivery_total
FROM new_balance
WHERE customers.customer_key = new_balance.customer_key;

-- Updates a customer's delivery count, counting a single orders as a delivery
DROP TABLE IF EXISTS new_delivery_count CASCADE;
CREATE TEMP TABLE new_delivery_count AS
WITH order_count AS (
	SELECT DISTINCT customer_key, order_key
	FROM undelivered_items )
SELECT customer_key, COUNT( order_key ) AS new_count
FROM order_count
GROUP BY customer_key;

UPDATE customers
SET delivery_total = delivery_total + new_delivery_count.new_count
FROM new_delivery_count
WHERE customers.customer_key = new_delivery_count.customer_key;

-- Drops temporary tables
DROP TABLE IF EXISTS undelivered_items CASCADE;
DROP TABLE IF EXISTS new_balance CASCADE;
DROP TABLE IF EXISTS new_delivery_count CASCADE;

COMMIT;