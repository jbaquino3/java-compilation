package railway;

class Driver extends Passenger {
	private int driver_id;
	private int exp; // years of experience
	
	public Driver(int id, String name) {
		super(name);
		setDriverId(id);
		setExp(0);
		setActive(true);
	}
	
	public int getDriverId() {
		return driver_id;
	}
	
	public int getExp() {
		return exp;
	}
	
	public void setDriverId(int id) {
		this.driver_id = id;
	}
	
	public void setExp(int years) {
		exp = years;
	}
	
	public String toString() {
		return "Driver: ID#" + getDriverId() + " " + getName();
	}
	
	public boolean equals(Driver d) {
		return d.getDriverId() == getDriverId();
	}
}
