import java.io.*;
import java.util.*;

public class GrocerySim {


    public static void main( String[] args ) throws Exception {

        GroceryDB db = new GroceryDB();

        if( args.length == 1 && args[0].compareTo( "-create" ) == 0 ) {
            db.dropTables();
            db.createTables();


        } else if( args.length == 1 && args[0].compareTo( "-insert" ) == 0 ) {
        	db.insertData();

        } else {
            System.out.println();
            System.out.println( "Usage: java GroceryDB [-options]" );
            System.out.println();
            System.out.println( "where options include:" );
            System.out.println( "    -create        generates postgres code to create tables" );
            System.out.println( "    -insert       generates data and postgres code to populate tables" );
            System.out.println();
        }






    }


}