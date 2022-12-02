package railway;
import java.util.Date;
import java.util.ArrayList;

class Trip {
	private int number;
	private Driver driver;
	private Date date;
	private String tripName;
	private int max_passengers;
	private ArrayList<Passenger> passengers;
	
	public Trip(int number, String name, Driver d, Date date) {
		setNumber(number);
		setName(name);
		setDriver(d);
		setDate(date);
		setMaxPassengers(5);
		passengers = new ArrayList<Passenger>();
		System.out.printf("New trip created #" + getNumber() + " " + getName()
				+ ", Date: %tB %<te, %<tY"
				+ ", Driver: " + d.getName() + "\n", date);
	}
	
	public int getNumber() {
		return number;
	}
	
	public Driver getDriver() {
		return driver;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getName() {
		return tripName;
	}
	
	public int getMaxPassengers() {
		return max_passengers;
	}
	
	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setName(String name) {
		tripName = name;
	}
	
	public void setMaxPassengers(int max_passengers) {
		this.max_passengers = max_passengers;
	}
	
	public void removeAllPassengers() {
		passengers.clear();
	}
	
	public Passenger addPassenger(Passenger p) {
		if(p.getTripNumber() == getNumber()) {
			System.out.println("Passenger is already on this trip!");
		} else if(p.getTripNumber() > 0) {
			System.out.println("Cannot add passenger! "
					+ "Passenger is reserved on Trip #"
					+ p.getTripNumber());
		} else if(getPassengers().size() == getMaxPassengers()) {
			System.out.println("Trip is full! Only "
					+ getPassengers().size()
					+ " passengers are allowed.");
		} else {
			p.setTripNumber(getNumber());
			passengers.add(p);
			System.out.println("Passenger " + p.getName() + " added to Trip #" + getNumber());
		}
		
		return p;
	}
	
	public Passenger removePassenger(Passenger p) {
		ArrayList<Passenger> passengers = getPassengers();
		
		for(int i=0; i<passengers.size(); i++) {
			if(passengers.get(i).equals(p)) {
				passengers.remove(i);
				p.setTripNumber(-1);
				System.out.println("Passenger " + p.getName() + " is removed from Trip #" + getNumber());
				return p;
			}
		}
		
		System.out.println("Passenger " + p.getName() + " is not on this trip!");
		return p;
	}
	
	public String toString() {
		return "Trip #" + getNumber()
			+ "\nName: " + getName()
			+ "\n" + getDriver()
			+ "\nDate: " + getDate()
			+ "\nPassengers: " + getPassengers().size()
			+ "\nCapacity: " + getMaxPassengers();
	}
	
	public boolean equals(Trip d) {
		return d.getNumber() == getNumber();
	}
}
