package call911;

public class Nine11Call implements PoliceEvent {
    private String address;  //the street address of the emergency
    private String respondent;  //the name of the first responding officer or emergency personnel
    private int urgencyLevel;  //The higher the level, the more urgent, should be 0 - 20
    private boolean needAmbulance;

    public Nine11Call(String addr) {
        this.address = addr;
        this.respondent = "John Blue";
        this.urgencyLevel = 5;
        this.needAmbulance = false;
    }

    public Nine11Call(String addr, boolean ambulance) {
        this.address = addr;
        this.respondent = "John Blue";
        this.urgencyLevel = ambulance ? 17 : 5;
        this.needAmbulance = ambulance;
    }

    public Nine11Call(String addr, int howUrgent, boolean ambulance) {
        this.address = addr;
        this.respondent = "John Blue";
        this.urgencyLevel = howUrgent;
        this.needAmbulance = ambulance;
    }

    public Nine11Call(String addr, String respondent, int howUrgent ) {
        this.address = addr;
        this.respondent = respondent;
        this.urgencyLevel = howUrgent;
        this.needAmbulance = false;
    }

    public Nine11Call(String addr, String respondent, int howUrgent, boolean ambulance ) {
        this.address = addr;
        this.respondent = respondent;
        this.urgencyLevel = howUrgent;
        this.needAmbulance = ambulance;
    }

    // Getters
    public String getAddress() {
        return this.address;
    }

    public String getRespondent() {
        return this.respondent;
    }

    public int getUrgencyLevel() {
        return this.urgencyLevel;
    }

    public boolean isAmbulanceNeeded() {
        return this.needAmbulance;
    }

    // Setters
    public void setAddress(String address) {
        this.address = address;
    }

    public void setRespondent(String respondent) {
        this.respondent = respondent;
    }

    public void setUrgencyLevel(int urgencyLevel) {
        this.urgencyLevel = urgencyLevel <= 20 ? urgencyLevel : 5;
    }

    public void setAmbulance(boolean ambulance) {
        this.needAmbulance = ambulance;
    }
}