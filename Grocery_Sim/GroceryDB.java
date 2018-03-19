import java.io.*;
import java.util.*;

public class GroceryDB {

    public static final int MAX_WAREHOUSES = 1;
    public static final int MAX_DIST_STATIONS = 8;
    public static final int MAX_CUSTOMERS = 400;
    public static final int MAX_ITEMS = 500;
    public static final int MAX_ORDERS = 4000;
    public static final int MAX_LINE_ITEMS = 20000;


    private class Warehouse {

        private int ware_key;
        private int ware_id;
        private String ware_name;
        private String street_addr;
        private String city;
        private String state_abbr;
        private String zip;
        private double sales_total;
        private double sales_tax;

        private Warehouse() {

        }

        private Warehouse( int new_ware_id, String new_ware_name, String new_street_addr, String new_city, String new_state_abbr,
                           String new_zip, double new_sales_total, double new_sales_tax ) {

            ware_key = ware_key_gen;
            ware_id = new_ware_id;
            ware_name = new_ware_name;
            street_addr = new_street_addr;
            city = new_city;
            state_abbr = new_state_abbr;
            zip = new_zip;
            sales_total = new_sales_total;
            sales_tax = new_sales_tax;

            ware_key_gen++;
        }


        private void printInsert() {
            System.out.println( "INSERT INTO warehouses VALUES" );

            System.out.print( "( DEFAULT, " );
            System.out.print( ware_id + ", " );
            System.out.print( "'" + ware_name + "', " );
            System.out.print( "'" + street_addr + "', " );
            System.out.print( "'" + city + "', " );
            System.out.print( "'" + state_abbr + "', " );
            System.out.print( "'" + zip + "', " );
            System.out.print( sales_total + ", " );
            System.out.print( sales_tax + " ); " );

            System.out.println();
            System.out.println();
        }


    } // End of Warehouses

    private class Dist_Station {

        private int dist_station_key;
        private int dist_station_id;
        private String dist_station_name;
        private String street_addr;
        private String city;
        private String state_abbr;
        private String zip;
        private double sales_total;
        private double sales_tax;

        private Dist_Station() {
        }

        private Dist_Station( int new_dist_station_id, String new_dist_station_name, String new_street_addr, String new_city,
                              String new_state_abbr, String new_zip, double new_sales_tax ) {

            dist_station_key = dist_station_key_gen;
            dist_station_id = new_dist_station_id;
            dist_station_name = new_dist_station_name;
            street_addr = new_street_addr;
            city = new_city;
            state_abbr = new_state_abbr;
            zip = new_zip;
            sales_total = 0.0;
            sales_tax = new_sales_tax;

            dist_station_key_gen++;
        }

        private void printInsert() {
            System.out.println( "INSERT INTO dist_stations VALUES" );

            System.out.print( "( DEFAULT, " );
            System.out.print( dist_station_id + ", " );
            System.out.print( "'" + dist_station_name + "', " );
            System.out.print( "'" + street_addr + "', " );
            System.out.print( "'" + city + "', " );
            System.out.print( "'" + state_abbr + "', " );
            System.out.print( "'" + zip + "', " );
            System.out.print( sales_total + ", " );
            System.out.print( sales_tax + " ); " );

            System.out.println();
            System.out.println();
        }

    } // End of Dist_Stations

    private class Customer {

        private int customer_key;
        private int customer_id;
        private String first_name;
        private String mid_init;
        private String last_name;
        private String street_addr;
        private String city;
        private String state_abbr;
        private String zip;
        private String phone;
        private double balance;
        private int payment_count;
        private double payment_total;
        private int delivery_count;
        private double discount;
        private String start_date;
        private int cust_dist_station;

        private Customer() {
        }

        private Customer( int new_customer_id, String new_first_name, String new_mid_init, String new_last_name, String new_street_addr,
                          String new_city, String new_state_abbr, String new_zip, String new_phone, double new_balance,
                          int new_payment_count, int new_payment_total, int new_delivery_count, double new_discount,
                          String new_start_date, int new_cust_dist_station ) {

            customer_key = customer_key_gen;
            customer_id = new_customer_id;
            first_name = new_first_name;
            mid_init = new_mid_init;
            last_name = new_last_name;
            street_addr = new_street_addr;
            city = new_city;
            state_abbr = new_state_abbr;
            zip = new_zip;
            phone = new_phone;
            balance = new_balance;
            payment_count = new_payment_count;
            payment_total = new_payment_total;
            delivery_count = new_delivery_count;
            discount = new_discount;
            start_date = new_start_date;
            cust_dist_station = new_cust_dist_station;

            customer_key = customer_key_gen++;
        }

        private void printInsert() {
            System.out.println( "INSERT INTO customers VALUES" );

            System.out.print( "( DEFAULT, " );
            System.out.print( customer_id + ", " );
            System.out.print( "'" + first_name + "', " );
            System.out.print( "'" + mid_init + "', " );
            System.out.print( "'" + last_name + "', " );
            System.out.print( "'" + street_addr + "', " );
            System.out.print( "'" + city + "', " );
            System.out.print( "'" + state_abbr + "', " );
            System.out.print( "'" + zip + "', " );
            System.out.print( "'" + phone + "', " );
            System.out.print( balance + ", " );
            System.out.print( payment_count + ", " );
            System.out.print( payment_total + ", " );
            System.out.print( delivery_count + ", " );
            System.out.print( discount + ", " );
            System.out.print( "'" + start_date + "' );" );

            System.out.println();
            System.out.println();
        }

    } // End of Customers

    private class Order {

        private int order_key;
        private int order_id;
        private String order_date;
        private boolean is_complete;
        private int num_line_items;


        private Order( int new_order_id, String new_order_date, boolean new_is_complete, int new_num_line_items) {
            order_key = order_key_gen;
            order_id = new_order_id;
            order_date = new_order_date;
            is_complete = new_is_complete;
            num_line_items = new_num_line_items;

            order_key_gen++;
        }



        private void printInsert() {
            System.out.println( "INSERT INTO orders VALUES" );

            System.out.print( "( DEFAULT, " );
            System.out.print( order_id + ", " );
            System.out.print( "'" + order_date + "'," );
            System.out.print( "'" + is_complete + "', " );
            System.out.print( num_line_items + " );" );

            System.out.println();
            System.out.println();
        }

    } // End of Orders

    private class Item {

        private int item_key;
        private int item_id;
        private String item_name;
        private double item_price;


        private Item( int new_item_id, String new_item_name, double new_item_price ) {
            item_key = item_key_gen;
            item_id = new_item_id;
            item_name = new_item_name;
            item_price = new_item_price;

            item_key_gen++;
        }

        private void printInsert() {
            System.out.println( "INSERT INTO items VALUES" );

            System.out.print( "( DEFAULT, " );
            System.out.print( item_id + ", " );
            System.out.print( "'" + item_name + "', " );
            System.out.print( item_price + " ); " );

            System.out.println();
            System.out.println();
        }

    } // End of Item

    private class Warehouse_Stock {

        private int ware_key;
        private int item_key;
        private int item_qty;
        private int items_sold;
        private double item_sales;


        private Warehouse_Stock( int new_ware_key, int new_item_key, int new_item_qty, int new_items_sold, double new_item_sales ) {
            ware_key = new_ware_key;
            item_key = new_item_key;
            item_qty = new_item_qty;
            items_sold = new_items_sold;
            item_sales = new_item_sales;
        }

        private void printInsert() {
            System.out.println( "INSERT INTO warehouse_stock VALUES" );

            System.out.print( "( " + ware_key + ", " );
            System.out.print( item_key + ", " );
            System.out.print( item_qty + ", " );
            System.out.print( items_sold + ", " );
            System.out.print( item_sales + " ); " );

            System.out.println();
            System.out.println();
        }

    } // End of Item

    private class Line_Item {

        private int line_item_key;
        private int order_key;
        private int item_key;
        private int line_item_id;
        private int qty_sold;
        private double line_sales;
        private String date_delivered;


        private Line_Item( int new_order_key, int new_item_key, int new_line_item_id, int new_qty_sold, double new_line_sales, String new_date_delivered ) {

            line_item_key = line_item_key_gen;
            order_key = new_order_key;
            item_key = new_item_key;
            line_item_id = new_line_item_id;
            qty_sold = new_qty_sold;
            line_sales = new_line_sales;
            date_delivered = new_date_delivered;

            line_item_key_gen++;
        }

        private void printInsert() {
            System.out.println( "INSERT INTO line_items VALUES" );

            System.out.print( "( DEFAULT, " );
            System.out.print( order_key + ", " );
            System.out.print( item_key + ", " );
            System.out.print( line_item_id + ", " );
            System.out.print( qty_sold + ", " );
            System.out.print( line_sales + ", " );
            System.out.print( "'" + date_delivered + "' );" );

            System.out.println();
            System.out.println();
        }

    } // End of Line_Item

    // Unique key identifiers
    private int ware_key_gen;
    private int dist_station_key_gen;
    private int customer_key_gen;
    private int order_key_gen;
    private int line_item_key_gen;
    private int item_key_gen;

    // Database members
    private Warehouse[] ware_list;
    private Dist_Station[] dist_station_list;
    private Customer[] customer_list;
    private Item[] item_list;
    private Warehouse_Stock[] stock_list;
    private Order[] order_list;
    private Line_Item[] line_item_list;


    // ====================================================================================================================================
    // ====================================================================================================================================
    // ====================================================================================================================================


    public GroceryDB() {
        ware_key_gen = 0;
        dist_station_key_gen = 0;
        customer_key_gen = 0;
        order_key_gen = 0;
        line_item_key_gen = 0;
        item_key_gen = 0;

        ware_list = new Warehouse[100];
        dist_station_list = new Dist_Station[200];
        customer_list = new Customer[1000];
        item_list = new Item[5000];
        stock_list = new Warehouse_Stock[5000];

        order_list = new Order[10000];
        line_item_list = new Line_Item[100000];

    }

    public void dropTables() {
        System.out.println( "DROP TABLE" );
        System.out.println( "IF EXISTS warehouses CASCADE;" );
        System.out.println();

        System.out.println( "DROP TABLE" );
        System.out.println( "IF EXISTS dist_stations CASCADE;" );
        System.out.println();

        System.out.println( "DROP TABLE" );
        System.out.println( "IF EXISTS link_wares_dist_stations CASCADE;" );
        System.out.println();

        System.out.println( "DROP TABLE" );
        System.out.println( "IF EXISTS customers CASCADE;" );
        System.out.println();

        System.out.println( "DROP TABLE" );
        System.out.println( "IF EXISTS link_dist_stations_customers CASCADE;" );
        System.out.println();

        System.out.println( "DROP TABLE" );
        System.out.println( "IF EXISTS orders CASCADE;" );
        System.out.println();

        System.out.println( "DROP TABLE" );
        System.out.println( "IF EXISTS link_custs_orders CASCADE;" );
        System.out.println();

        System.out.println( "DROP TABLE" );
        System.out.println( "IF EXISTS items CASCADE;" );
        System.out.println();

        System.out.println( "DROP TABLE" );
        System.out.println( "IF EXISTS warehouse_stock CASCADE;" );
        System.out.println();

        System.out.println( "DROP TABLE" );
        System.out.println( "IF EXISTS line_items CASCADE;" );
        System.out.println();
    }

    public void createTables() {
        System.out.println( "CREATE TABLE warehouses (" );
        System.out.println( "    ware_key SERIAL," );
        System.out.println( "    ware_id INTEGER," );
        System.out.println( "    ware_name VARCHAR," );
        System.out.println( "    street_addr VARCHAR," );
        System.out.println( "    city VARCHAR," );
        System.out.println( "    state_abbr VARCHAR," );
        System.out.println( "    zip VARCHAR," );
        System.out.println( "    sales_total NUMERIC( 10, 2 ) DEFAULT 0.0," );
        System.out.println( "    sales_tax NUMERIC( 3, 2 ) DEFAULT 0.0," );
        System.out.println();
        System.out.println( "    PRIMARY KEY( ware_key )" );
        System.out.println( ");" );
        System.out.println();

        System.out.println( "CREATE TABLE dist_stations (" );
        System.out.println( "    dist_station_key SERIAL," );
        System.out.println( "    dist_station_id INTEGER," );
        System.out.println( "    dist_station_name VARCHAR," );
        System.out.println( "    street_addr VARCHAR," );
        System.out.println( "    city VARCHAR," );
        System.out.println( "    state_abbr VARCHAR," );
        System.out.println( "    zip VARCHAR," );
        System.out.println( "    sales_total NUMERIC( 10, 2 ) DEFAULT 0.0," );
        System.out.println( "    sales_tax NUMERIC( 3, 2 ) DEFAULT 0.0," );
        System.out.println();
        System.out.println( "    PRIMARY KEY( dist_station_key )" );
        System.out.println( ");" );
        System.out.println();

        System.out.println( "CREATE TABLE link_wares_dist_stations (" );
        System.out.println( "    ware_key INTEGER," );
        System.out.println( "    dist_station_key INTEGER," );
        System.out.println();
        System.out.println( "    PRIMARY KEY( ware_key, dist_station_key )," );
        System.out.println( "    FOREIGN KEY( ware_key ) REFERENCES warehouses( ware_key ) ON UPDATE CASCADE ON DELETE CASCADE," );
        System.out.println( "    FOREIGN KEY( dist_station_key ) REFERENCES dist_stations( dist_station_key ) ON UPDATE CASCADE ON DELETE CASCADE" );
        System.out.println( ");" );

        System.out.println( "CREATE TABLE customers (" );
        System.out.println( "    customer_key SERIAL," );
        System.out.println( "    customer_id INTEGER," );
        System.out.println( "    first_name VARCHAR," );
        System.out.println( "    mid_init VARCHAR," );
        System.out.println( "    last_name VARCHAR," );
        System.out.println( "    street_addr VARCHAR," );
        System.out.println( "    city VARCHAR(32)," );
        System.out.println( "    state_abbr VARCHAR," );
        System.out.println( "    zip VARCHAR," );
        System.out.println( "    phone VARCHAR," );
        System.out.println( "    balance NUMERIC( 10, 2 ) DEFAULT 0.0," );
        System.out.println( "    payment_count INTEGER DEFAULT 0," );
        System.out.println( "    payment_total NUMERIC( 10, 2 ) DEFAULT 0.0," );
        System.out.println( "    delivery_total INTEGER DEFAULT 0," );
        System.out.println( "    discounts NUMERIC( 10, 2 ) DEFAULT 0.0," );
        System.out.println( "    start_date DATE DEFAULT CURRENT_DATE, " );
        System.out.println();
        System.out.println( "    PRIMARY KEY( customer_key )" );
        System.out.println( ");" );
        System.out.println();

        System.out.println( "CREATE TABLE link_dist_stations_customers (" );
        System.out.println( "    dist_station_key INTEGER," );
        System.out.println( "    customer_key INTEGER," );
        System.out.println();
        System.out.println( "    PRIMARY KEY( dist_station_key, customer_key )," );
        System.out.println( "    FOREIGN KEY( dist_station_key ) REFERENCES dist_stations( dist_station_key ) ON UPDATE CASCADE ON DELETE CASCADE," );
        System.out.println( "    FOREIGN KEY( customer_key ) REFERENCES customers( customer_key ) ON UPDATE CASCADE ON DELETE CASCADE" );
        System.out.println( ");" );

        System.out.println( "CREATE TABLE orders (" );
        System.out.println( "    order_key SERIAL," );
        System.out.println( "    order_id INTEGER," );
        System.out.println( "    order_date DATE DEFAULT CURRENT_DATE," );
        System.out.println( "    is_complete BOOLEAN," );
        System.out.println( "    num_line_items INTEGER," );
        System.out.println();
        System.out.println( "    PRIMARY KEY( order_key )" );
        System.out.println( ");" );
        System.out.println();

        System.out.println( "CREATE TABLE link_custs_orders (" );
        System.out.println( "    order_key INTEGER," );
        System.out.println( "    customer_key INTEGER," );
        System.out.println();
        System.out.println( "    PRIMARY KEY( order_key, customer_key )," );
        System.out.println( "    FOREIGN KEY( order_key ) REFERENCES orders( order_key ) ON UPDATE CASCADE ON DELETE CASCADE," );
        System.out.println( "    FOREIGN KEY( customer_key ) REFERENCES customers( customer_key ) ON UPDATE CASCADE ON DELETE CASCADE" );
        System.out.println( ");" );

        System.out.println( "CREATE TABLE items (" );
        System.out.println( "    item_key SERIAL," );
        System.out.println( "    item_id INTEGER," );
        System.out.println( "    item_name VARCHAR," );
        System.out.println( "    item_price NUMERIC( 10, 2 ) DEFAULT 0.0," );
        System.out.println();
        System.out.println( "    PRIMARY KEY( item_key )" );
        System.out.println( ");" );
        System.out.println();

        System.out.println( "CREATE TABLE warehouse_stock (" );
        System.out.println( "    ware_key INTEGER," );
        System.out.println( "    item_key INTEGER," );
        System.out.println( "    item_qty INTEGER DEFAULT 0," );
        System.out.println( "    items_sold INTEGER DEFAULT 0," );
        System.out.println( "    item_sales NUMERIC( 10, 2 ) DEFAULT 0.0," );
        System.out.println();
        System.out.println( "    PRIMARY KEY( ware_key, item_key )," );
        System.out.println( "    FOREIGN KEY( ware_key ) REFERENCES warehouses( ware_key ) ON UPDATE CASCADE ON DELETE CASCADE," );
        System.out.println( "    FOREIGN KEY( item_key ) REFERENCES items( item_key ) ON UPDATE CASCADE ON DELETE CASCADE" );
        System.out.println( ");" );

        System.out.println( "CREATE TABLE line_items (" );
        System.out.println( "    line_item_key SERIAL," );
        System.out.println( "    order_key INTEGER,");
        System.out.println( "    item_key INTEGER," );
        System.out.println( "    line_item_id INTEGER, " );
        System.out.println( "    qty_sold INTEGER DEFAULT 0," );
        System.out.println( "    line_sales NUMERIC( 10, 2 ) DEFAULT 0.0," );
        System.out.println( "    date_delivered DATE DEFAULT CURRENT_DATE," );

        System.out.println();
        System.out.println( "    PRIMARY KEY( line_item_key )," );
        System.out.println( "    FOREIGN KEY( order_key ) REFERENCES orders( order_key ) ON UPDATE CASCADE ON DELETE CASCADE," );
        System.out.println( "    FOREIGN KEY( item_key ) REFERENCES items( item_key ) ON UPDATE CASCADE ON DELETE CASCADE" );
        System.out.println( ");" );
    }

    public void insertData() throws Exception {
        BufferedReader iFile;
        int i;
        Random rand = new Random();

        // Fill Warehouse Data
        iFile = new BufferedReader( new FileReader( "warehouses.csv") );
        i = 0;

        while( iFile.ready() ) {
            String textLine = iFile.readLine();
            String[] splitText = textLine.split( "," );

            ware_list[i] = new Warehouse( ware_key_gen + 1, new String( splitText[0] ), new String( splitText[1] ), new String( splitText[2] ),
                                          new String( splitText[3] ), new String( splitText[4] ), 0.0, 0.07 );

            i++;
        }

        // Fills Dist. Station Data
        iFile = new BufferedReader( new FileReader( "dist_stations.csv") );
        i = 0;

        while( iFile.ready() ) {
            String textLine = iFile.readLine();
            String[] splitText = textLine.split( "," );

            dist_station_list[i] = new Dist_Station( dist_station_key_gen + 1, new String( splitText[0] ), new String( splitText[1] ),
                                   new String( splitText[2] ), new String( splitText[3] ), new String( splitText[4] ), 0.07  );

            i++;
        }

        // Fills Customer Data
        iFile = new BufferedReader( new FileReader( "customers.csv") );
        i = 0;

        int cust_id_gen = 1;
        int cust_dist_station = 1;

        while( iFile.ready() ) {
            String textLine = iFile.readLine();
            String[] splitText = textLine.split( "," );



            customer_list[i] = new Customer( cust_id_gen, new String( splitText[0] ), new String( splitText[1] ), new String( splitText[2] ),
                                             new String( splitText[3] ), new String( splitText[4] ), new String( splitText[5] ),
                                             new String( splitText[6] ), new String( splitText[7] ), 0.0, 0, 0, 0, 0.0,
                                             new String( "2017-01-01" ), cust_dist_station );

            i++;
            if( cust_id_gen == 50 ) {
                cust_id_gen = 1;
                cust_dist_station++;
            }
            else cust_id_gen++;
        }

        // Fills Item Data
        iFile = new BufferedReader( new FileReader( "items.csv") );
        i = 0;

        while( iFile.ready() ) {
            String textLine = iFile.readLine();
            String[] splitText = textLine.split( "," );

            item_list[i] = new Item( item_key_gen + 1, new String( splitText[0] ), Double.parseDouble( splitText[1] ) );

            i++;
        }

        // Fills Order Data
        int temp_day = 1;
        int temp_order_id = 1;

        for( i = 0; i < 4000; i++ ) {
            order_list[i] = new Order( temp_order_id, new String( "2017-01-" + temp_day ), true, 5 );

            if( temp_day == 10 ) temp_day = 1;
            else temp_day++;

            if( temp_order_id == 10 ) temp_order_id = 1;
            else temp_order_id++;


        }

        // Initializes & Fills Warehouse_Stock
        for( i = 0; i < 500; i++ ) {
            stock_list[i] = new Warehouse_Stock( 1, i + 1, rand.nextInt(100) + 50, 0, 0.0 );
        }


        // ########################### SALES SIMULATION ####################################
        
        int line_item_index = 0;
        int temp_order_key = 1;

        // Cycles through all customers
        for( int cust_index = 0; cust_index < 400; cust_index++ ) {

            // Generates 10 orders per customer           
            for( int order_count = 0; order_count < 10; order_count++ ) {

                // Generates 5 line items per order
                for( int temp_line_index = 0; temp_line_index < 5; temp_line_index++ ) {
                    
                    // Picks random item from inventory
                    int rand_item_index = rand.nextInt(500);
                    Item temp_item = item_list[rand_item_index];

                    // Creates actual line item...
                    line_item_list[line_item_index] = new Line_Item( temp_order_key, rand_item_index + 1, temp_line_index + 1, 1, temp_item.item_price, "2017-01-" + (order_count + 2) );

                    // Updates Warehouse Stock...
                    stock_list[rand_item_index].items_sold++;
                    stock_list[rand_item_index].item_sales+= temp_item.item_price;

                    // Updates Dist_Station Sales...
                    Customer temp_cust = customer_list[cust_index];
                    int temp_dist = temp_cust.cust_dist_station;
                    dist_station_list[temp_dist - 1].sales_total+= temp_item.item_price;

                    // Updates Warehouse Sales...
                    ware_list[0].sales_total+= temp_item.item_price;

                    // Updates Customers Payment Total...
                    customer_list[cust_index].payment_total+= temp_item.item_price;

                    line_item_index++;
                }

                temp_order_key++;

                customer_list[cust_index].payment_count++;
                customer_list[cust_index].delivery_count++;
            }



        }


        // ########################### PRINT MAIN TABLES ####################################
        
        
        // Prints Warehouses inserts...
        for( i = 0; i < MAX_WAREHOUSES; i++ ) ware_list[i].printInsert();

        // Prints Dist_Stations inserts...
        for( i = 0; i < MAX_DIST_STATIONS; i++ ) dist_station_list[i].printInsert();

        // Prints Customers inserts...
        for( i = 0; i < MAX_CUSTOMERS; i++ ) customer_list[i].printInsert();

        // Prints Items inserts...
        for( i = 0; i < MAX_ITEMS; i++ ) item_list[i].printInsert();

        // Prints Stocks
        for( i = 0; i < MAX_ITEMS; i++ ) stock_list[i].printInsert();

        // Prints Orders inserts...
        for( i = 0; i < MAX_ORDERS; i++ ) order_list[i].printInsert();

        // Prints Line Items
        for( i = 0; i < MAX_LINE_ITEMS; i++ ) line_item_list[i].printInsert();
        

        // ########################### PRINT LINK TABLES ####################################

        // Prints [Wareshouse -- Dist_Stations] link table
        for( i = 0; i < 8; i++ ) {

            System.out.println( "INSERT INTO link_wares_dist_stations VALUES" );

            System.out.print( "( 1, " );
            System.out.print( (i+1) + " );" );

            System.out.println();
            System.out.println();
        }

        // Prints [Dist_Stations -- Customers] link table
        int temp_dist = 1;
        int temp_cust = 1;
        for( i = 0; i < 400; i++ ) {

            System.out.println( "INSERT INTO link_dist_stations_customers VALUES" );

            System.out.print( "( " + temp_dist + ", " );
            System.out.print( i + 1 + " );" );

            System.out.println();
            System.out.println();

            if( temp_cust == 50 ) {
                temp_cust = 1;
                temp_dist++;
            } else temp_cust++;
        }
        
        // Prints [Customers -- Orders] link table
        int temp_cust_key = 1;
        int temp_order_count = 1;

        for( i = 0; i < MAX_ORDERS; i++ ) {

            System.out.println( "INSERT INTO link_custs_orders VALUES" );

            System.out.print( "( " + (i+1) + ", " );
            System.out.print( temp_cust_key + " );" );

            System.out.println();
            System.out.println();

            if( temp_order_count == 10 ) {
                temp_order_count =1;
                temp_cust_key++;
            } else temp_order_count++;

        }
    }
}