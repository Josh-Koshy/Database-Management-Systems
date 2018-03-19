/*
 * Finds the line item details on the given customer's most recent order
 *
 * GIVENS:
 *
 * 		Customer
 *			customer_key = 6
 *		
 */

BEGIN TRANSACTION;

-- Finds the most recent order
WITH most_recent AS (
	SELECT orders.order_key
	FROM link_custs_orders 
	JOIN orders ON link_custs_orders.order_key = orders.order_key
	WHERE link_custs_orders.customer_key = 6
	ORDER BY order_date DESC
	LIMIT 1 )

-- Selects line items from the most recent order
SELECT line_items.line_item_id AS "Line Item", items.item_id AS "UPC", items.item_name AS "Item", line_items.qty_sold AS "Item Qty", to_char( line_items.line_sales, 'LFM9999999.00') AS "Amount Due", line_items.date_delivered AS "Date Delivered"
FROM most_recent 
JOIN line_items ON most_recent.order_key = line_items.order_key
JOIN items ON line_items.item_key = items.item_key
ORDER BY line_items.line_item_id;

COMMIT;