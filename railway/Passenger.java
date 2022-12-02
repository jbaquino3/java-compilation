package railway;

class Passenger {
	private int civil_id;
	private String name;
	private int trip_number;
	private boolean active;
	
	public Passenger(String name) {
		setName(name);
	}
	
	public Passenger(int id, String name) {
		setCivilId(id);
		setName(name);
		setTripNumber(-1);
		System.out.println("Passenger " + getName() + " created.");
	}
	
	public int getCivilId() {
		return civil_id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTripNumber() {
		return trip_number;
	}
	
	public boolean getActive() {
		return active;
	}
	
	public void setCivilId(int id) {
		this.civil_id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTripNumber(int number) {
		trip_number = number;
	}
	
	public void setActive(boolean b) {
		active = b;
	}
	
	public String toString() {
		return "Driver " + getCivilId() + " " + getName();
	}
	
	public boolean equals(Passenger d) {
		return d.getCivilId() == getCivilId();
	}
}
