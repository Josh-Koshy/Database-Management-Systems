import java.sql.*;
import java.util.Properties;
import java.util.Random;

/*
 * Driver program that spawns 3 threads per the five sql transactions from Phase 2.
 * 
 * Authors: Michael Thomson - mrt49
 *          Matt Duing - mad242
 *          Joshua Koshy - jdk 77
 */
public class GroceryDriver extends Thread 
{
	private static int NUM_OF_THREADS = 15;
	private static Random rand = new Random();

	int id;
	
	public static void main (String args [])
	{
		try {
			// Create the threads
			Thread[] threadList = new Thread[NUM_OF_THREADS];

			// Spawn threads
			for (int i = 0; i < NUM_OF_THREADS; i++) {
				threadList[i] = new GroceryDriver(i);
				threadList[i].start();
			}
		
			// Start everyone at the same time
			setGreenLight ();

			// wait for all threads to end
			for (int i = 0; i < NUM_OF_THREADS; i++) {
				threadList[i].join();
			}

		}
		catch (Exception e) {
			 e.printStackTrace();
		}
	
	}	

	public GroceryDriver(int i) throws  SQLException {
		 super();
		 id = i;
	}

	
	public void run() {
		try {

			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost/mrt49";
			Properties props = new Properties();
			props.setProperty("user","mrt49");
			props.setProperty("password","cs1555");
			Connection conn = DriverManager.getConnection(url, props);

			Statement st = conn.createStatement();

			// wait for the go-ahead
			while (!getGreenLight())
				yield();
			
			if(id <= 2) {
				 st.executeQuery( "SELECT func_payment_transaction( 1, 100.00 )" );
				 System.out.println( "Payment Transaction Thread" );
			}
			else if(id <= 5)
			 {
				 st.executeQuery( "SELECT func_order_status( 1 )" );
				 System.out.println( "Order Status Thread" );
			 }
			else if(id <= 8)
			 {
			 	 st.executeQuery( "SELECT func_delivery_transaction( 1 )" );
				 System.out.println( "Delivery Transaction Thread" );
				 
			 }
			else if(id <= 11)
			 {
			 	 st.executeQuery( "SELECT func_stock_level_transaction( 1, 100 )" );
			 	 System.out.println( "Stock Level Transaction Thread" );
			 }
			else if(id <= 14)
			 {
			 	 st.executeQuery( "SELECT func_new_order( 1, '{1, 2, 3, 4, 5}', '{1, 2, 3, 4, 5}' )" );
			 	 System.out.println( "New Order Thread" );
			 }


			int c = 0;
			for (int i = 0; i < 15; i++) {
				//System.out.println(id);
				
				c = rand.nextInt();
				if (c < 33)
					yield();
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("!! Exception: " + e + "!!");
			e.printStackTrace();
			return;
		}
	}

	static boolean greenLight = false;
	static synchronized void setGreenLight () { greenLight = true; }
	synchronized boolean getGreenLight () { return greenLight; }

}