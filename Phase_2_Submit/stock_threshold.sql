/*
 * Of the items purchased in the last 20 orders, returns a count of any one of those item's
 * whose current stock is below the stock threshold for a given distribution station.
 *
 * GIVENS:
 *
 *		Distribution Station
 * 	    	dist_station_key = 1
 *
 * 		Stock Threshold = 100
 */

BEGIN TRANSACTION;

WITH stock_query AS (
	WITH most_recent_orders AS( 
		SELECT orders.order_key, orders.order_date
		FROM dist_sts
		JOIN link_dists_custs ON dist_sts.dist_station_key = link_dists_custs.dist_station_key
		JOIN customers ON link_dists_custs.customer_key = customers.customer_key
		JOIN link_custs_orders ON customers.customer_key = link_custs_orders.customer_key
		JOIN orders ON link_custs_orders.orader_key = orders.order_key
		WHERE dist_sts.dist_station_key = 1
		ORDER BY orders.order_date DESC, orders.order_key
		LIMIT 20 )
	SELECT DISTINCT line_items.item_key, items.item_name, warehouse_stock.item_qty
	FROM most_recent_orders
	JOIN line_items ON most_recent_orders.order_key = line_items.order_key
	JOIN items ON line_items.item_key = items.item_key
	JOIN warehouse_stock ON items.item_key = warehouse_stock.item_key
	WHERE warehouse_stock.item_qty < 100
	ORDER BY item_key )
SELECT COUNT(stock_query.item_key) AS "Items Under Stock"
FROM stock_query;

COMMIT;