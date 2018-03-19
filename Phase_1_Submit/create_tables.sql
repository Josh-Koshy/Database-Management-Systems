DROP TABLE
IF EXISTS warehouses CASCADE;

DROP TABLE
IF EXISTS dist_stations CASCADE;

DROP TABLE
IF EXISTS link_wares_dist_stations CASCADE;

DROP TABLE
IF EXISTS customers CASCADE;

DROP TABLE
IF EXISTS link_dist_stations_customers CASCADE;

DROP TABLE
IF EXISTS orders CASCADE;

DROP TABLE
IF EXISTS link_custs_orders CASCADE;

DROP TABLE
IF EXISTS items CASCADE;

DROP TABLE
IF EXISTS warehouse_stock CASCADE;

DROP TABLE
IF EXISTS line_items CASCADE;

CREATE TABLE warehouses (
    ware_key SERIAL,
    ware_id INTEGER,
    ware_name VARCHAR,
    street_addr VARCHAR,
    city VARCHAR,
    state_abbr VARCHAR,
    zip VARCHAR,
    sales_total NUMERIC( 10, 2 ) DEFAULT 0.0,
    sales_tax NUMERIC( 3, 2 ) DEFAULT 0.0,

    PRIMARY KEY( ware_key )
);

CREATE TABLE dist_stations (
    dist_station_key SERIAL,
    dist_station_id INTEGER,
    dist_station_name VARCHAR,
    street_addr VARCHAR,
    city VARCHAR,
    state_abbr VARCHAR,
    zip VARCHAR,
    sales_total NUMERIC( 10, 2 ) DEFAULT 0.0,
    sales_tax NUMERIC( 3, 2 ) DEFAULT 0.0,

    PRIMARY KEY( dist_station_key )
);

CREATE TABLE link_wares_dist_stations (
    ware_key INTEGER,
    dist_station_key INTEGER,

    PRIMARY KEY( ware_key, dist_station_key ),
    FOREIGN KEY( ware_key ) REFERENCES warehouses( ware_key ) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY( dist_station_key ) REFERENCES dist_stations( dist_station_key ) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE customers (
    customer_key SERIAL,
    customer_id INTEGER,
    first_name VARCHAR,
    mid_init VARCHAR,
    last_name VARCHAR,
    street_addr VARCHAR,
    city VARCHAR(32),
    state_abbr VARCHAR,
    zip VARCHAR,
    phone VARCHAR,
    balance NUMERIC( 10, 2 ) DEFAULT 0.0,
    payment_count INTEGER DEFAULT 0,
    payment_total NUMERIC( 10, 2 ) DEFAULT 0.0,
    delivery_total INTEGER DEFAULT 0,
    discounts NUMERIC( 10, 2 ) DEFAULT 0.0,
    start_date DATE DEFAULT CURRENT_DATE, 

    PRIMARY KEY( customer_key )
);

CREATE TABLE link_dist_stations_customers (
    dist_station_key INTEGER,
    customer_key INTEGER,

    PRIMARY KEY( dist_station_key, customer_key ),
    FOREIGN KEY( dist_station_key ) REFERENCES dist_stations( dist_station_key ) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY( customer_key ) REFERENCES customers( customer_key ) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE orders (
    order_key SERIAL,
    order_id INTEGER,
    order_date DATE DEFAULT CURRENT_DATE,
    is_complete BOOLEAN,
    num_line_items INTEGER,

    PRIMARY KEY( order_key )
);

CREATE TABLE link_custs_orders (
    order_key INTEGER,
    customer_key INTEGER,

    PRIMARY KEY( order_key, customer_key ),
    FOREIGN KEY( order_key ) REFERENCES orders( order_key ) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY( customer_key ) REFERENCES customers( customer_key ) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE items (
    item_key SERIAL,
    item_id INTEGER,
    item_name VARCHAR,
    item_price NUMERIC( 10, 2 ) DEFAULT 0.0,

    PRIMARY KEY( item_key )
);

CREATE TABLE warehouse_stock (
    ware_key INTEGER,
    item_key INTEGER,
    item_qty INTEGER DEFAULT 0,
    items_sold INTEGER DEFAULT 0,
    item_sales NUMERIC( 10, 2 ) DEFAULT 0.0,

    PRIMARY KEY( ware_key, item_key ),
    FOREIGN KEY( ware_key ) REFERENCES warehouses( ware_key ) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY( item_key ) REFERENCES items( item_key ) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE line_items (
    line_item_key SERIAL,
    order_key INTEGER,
    item_key INTEGER,
    line_item_id INTEGER, 
    qty_sold INTEGER DEFAULT 0,
    line_sales NUMERIC( 10, 2 ) DEFAULT 0.0,
    date_delivered DATE DEFAULT CURRENT_DATE,

    PRIMARY KEY( line_item_key ),
    FOREIGN KEY( order_key ) REFERENCES orders( order_key ) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY( item_key ) REFERENCES items( item_key ) ON UPDATE CASCADE ON DELETE CASCADE
);
