package railway;
import java.text.*;
import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<Trip> trips;
	static SimpleDateFormat ft;
	static int nextTripNumber = 1;
	static int nextDriverId = 1;
	static int nextPassengerId = 1;
	
	public static void main(String[] args) {
		try {
			// Create a collection of trips and make 7 trips
			trips = new ArrayList<Trip>();
			createTrip("Genesis", "Robert", "2022-12-01");
			createTrip("Orion", "Bong", "2022-12-03");
			createTrip("Jade", "Ubug", "2022-12-02");
			createTrip("Stellar", "Pusit", "2022-12-01");
			createTrip("Cosmo", "Insiong", "2022-12-03");
			createTrip("Mars", "Iming", "2022-12-01");
			createTrip("Planet", "Georgie", "2022-12-02");
			
			// Add a passenger
			Passenger p = createPassenger("Juno");
			Object[] res = addPassenger(trips.get(0), p);
			trips.set(0, (Trip) res[0]);
			p = (Passenger) res[1];
			
			// Attempt to add the same passenger to the same trip
			addPassenger(trips.get(0), p); // Should print an error
			
			// Attempt to add the same passenger to different trip
			addPassenger(trips.get(1), p); // Should print an error
			
			// Attempt to remove passenger from another trip
			removePassenger(trips.get(2), p); // Should print an error
			
			// Get number of average passenger from a date
			System.out.println("Average of Dec 1, 2022: " + averagePassenger("2022-12-01"));
			
			// Correctly remove the passenger
			Object[] res2 = removePassenger(trips.get(0), p);
			trips.set(0, (Trip) res2[0]);
			p = (Passenger) res2[1];
			
			// Display trips
			String data = displayTrips();
			System.out.println(data);
			
			File file = new File("output.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter("output.txt");
			writer.write(data);
			writer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void createTrip(String name, String driverName, String date) {
		try {
			ft = new SimpleDateFormat("yyyy-MM-dd");
			Trip trip = new Trip(
					nextTripNumber++,
					name,
					new Driver(nextDriverId++, driverName),
					ft.parse(date));
			
			if(trips.size() == 0) {
				trips.add(trip);
			} else {
				for(int i=0; i<trips.size(); i++) {
					if(trips.get(i).getDate().compareTo(trip.getDate()) > 0) {
						trips.add(i, trip);
						return;
					}
				}
				
				trips.add(trip);
			}
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
	
	static Passenger createPassenger(String name) {
		return new Passenger(nextPassengerId++, name);
	}

	static Object[] addPassenger(Trip t, Passenger p) {
		Object[] data = {t, t.addPassenger(p)};
		
		return data;
	}
	
	static Object[] removePassenger(Trip t, Passenger p) {
		Object[] data = {t, t.removePassenger(p)};
		
		return data;
	}
	
	static float averagePassenger(String d_str) {
		try {
			ft = new SimpleDateFormat("yyyy-MM-dd");
			Date d = ft.parse(d_str);
			float sum = 0, count = 0;
			for(int i=0; i<trips.size(); i++) {
				if(trips.get(i).getDate().equals(d)) {
					count++;
					sum += trips.get(i).getPassengers().size();
				}
			}
			return sum/count;
		} catch (ParseException pe) {
			pe.printStackTrace();
			return 0;
		}
	}
	
	static String displayTrips() {
		Date currentDate = null;
		String str = "";
		
		for(int i=0; i<trips.size(); i++) {
			if(trips.get(i).getDate().equals(currentDate) == false) {
				currentDate = trips.get(i).getDate();
				str += "\n" + currentDate + ":";
			}
			str += " tripNo" + trips.get(i).getNumber();
		}
		
		str += "\n";
		
		return str;
	}
}
