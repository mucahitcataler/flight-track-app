package flightTrackApp;

import java.io.*;
import java.util.LinkedList;


public class Main {
@SuppressWarnings("unchecked")
	public static void main(String[] args) {
				
		LinkedList<Capital> capitals = new LinkedList<Capital>();
        
        Capital aCapital1 = new Capital( "Istanbul" , 3 , 4 );
        capitals.add(aCapital1);
            
        Capital aCapital2 = new Capital( "New York" , -7 , 4 );
        capitals.add(aCapital2);
            
        Capital aCapital3 = new Capital( "Sydney" , -15 , -3 );
        capitals.add(aCapital3);
            
        Capital aCapital4 = new Capital( "Paris" , -2 , 5 );
        capitals.add(aCapital4);
            
        Capital aCapital5 = new Capital( "Tokyo" , -13 , 3 );           
        capitals.add(aCapital5);
            
            
        try {
        	ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("Capitals.txt"));
        	writer.writeObject(capitals);
        	writer.close();
        	System.out.println("The information you have entered has "
					+ "been successfully saved in file " + "Capitals.txt");
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
        
        LinkedList<Flight> flights = new LinkedList<Flight>();
		
		Flight aFlight1 = new Flight(1, aCapital4, aCapital2, 45, 23, 17, 05, 2020);
		flights.add(aFlight1);
		Flight aFlight2 = new Flight(2, aCapital2, aCapital4, 50, 20, 16, 05, 2020);
		flights.add(aFlight2);
		Flight aFlight3 = new Flight(3, aCapital5, aCapital1, 30, 12, 15, 05, 2020);
		flights.add(aFlight3);
		Flight aFlight4	= new Flight(4, aCapital4, aCapital3, 10, 22, 15, 05, 2020);
		flights.add(aFlight4);
		Flight aFlight5 = new Flight(5, aCapital2, aCapital5, 30, 23, 17, 05, 2020);
		flights.add(aFlight5);
		Flight aFlight6 = new Flight(6, aCapital1, aCapital5, 45, 20, 15, 05, 2020);
		flights.add(aFlight6);
		Flight aFlight7 = new Flight(7, aCapital3, aCapital4, 20, 17, 16, 05, 2020);
		flights.add(aFlight7);
		Flight aFlight8 = new Flight(8, aCapital4, aCapital1, 50, 19, 12, 05, 2020);
		flights.add(aFlight8);
		Flight aFlight9 = new Flight(9, aCapital5, aCapital2, 20, 13, 18, 05, 2020);
		flights.add(aFlight9);
		Flight aFlight10 = new Flight(10, aCapital1, aCapital3, 40, 15, 20, 05, 2020);
		flights.add(aFlight10);
		Flight aFlight11 = new Flight(11, aCapital1, aCapital3, 10, 0, 14, 05, 2020);
		flights.add(aFlight11);
		
		try {
			ObjectOutputStream writer = new ObjectOutputStream (new FileOutputStream("Flights.txt"));
			writer.writeObject(flights);
			writer.close();
			System.out.println("The information you have entered has "
					+ "been successfully saved in file " + "Flights.txt");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		try {
			ObjectInputStream reader = new ObjectInputStream( new FileInputStream( "Flights.txt" ) );
			LinkedList<Flight> flightss = (LinkedList<Flight>)reader.readObject();
			for( Flight aFlightt : flightss ) {
					if (aFlightt.ID == 3) {
						aFlightt.setDelay(20);
					}
					if (aFlightt.ID == 2) {
						aFlightt.setDelay(30);
					}
					if (aFlightt.ID == 7) {
						aFlightt.setDelay(10);
					}
					if (aFlightt.ID == 10) {
						aFlightt.setDelay(80);
					}
					if (aFlightt.ID == 5) {
						aFlightt.setDelay(40);
					}
			}
			try {
				ObjectOutputStream writer = new ObjectOutputStream (new FileOutputStream("Flights.txt"));
				writer.writeObject(flightss);
				writer.close();
				System.out.println("The information you have entered has "
						+ "been successfully saved in file " + "Flights.txt");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			reader.close();
		} 
		catch( IOException e ) {
			System.out.println("An exception has occured during file reading.");
			e.printStackTrace();
		} 
		catch( ClassNotFoundException e ) {
			System.out.println("An exception has occured while processing read records.");
			e.printStackTrace();
		}
		
		LinkedList<FlightReport> flightreports = new LinkedList<FlightReport>();
		try {
			ObjectOutputStream writer = new ObjectOutputStream (new FileOutputStream("FlightReports.txt"));
			writer.writeObject(flightreports);
			writer.close();
			System.out.println("The information you have entered has "
					+ "been successfully saved in file " + "FlightReports.txt");
		}
		catch (IOException e2) {
			e2.printStackTrace();
		}
		
		new AppMenu().setVisible(true);

	}

}
