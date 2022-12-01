package call911;

import java.util.*;

public class Main
{
    public static void main(String[] args) {
		Nine11Call cat = new Nine11Call("123 Maple Grove", "C. Simon", 0, false );
        Nine11Call whiteCollar = new Nine11Call("8 Wall Street" );

        //Do not change this list
        List<Nine11Call> calls=Arrays.asList( new Nine11Call("200 Campus Way", "Ofc. Mary Kelly", 4),
            new Nine11Call("1782 20th Street", 19, true),
            new Nine11Call("302 29th Ave", 15, false),
            new Nine11Call("4 Box Way", 2, true),
            new Nine11Call("822 Jackson Street", true),
            new Nine11Call("1602 PA Ave")
        );

        calls.stream().forEach(elem -> notify911(elem));
	}

    public static void notify911(Nine11Call nine11call) {
        if(nine11call.getUrgencyLevel() <= 10) {
            System.out.print(nine11call.getRespondent() + " is responding to " + nine11call.getAddress() + ". ");
            System.out.println("An ambulance " + (nine11call.isAmbulanceNeeded() ? "is" : "is not") + " needed.");
        } else if(nine11call.getUrgencyLevel() <=15) {
            System.out.print(nine11call.getRespondent() + " is responding to " + nine11call.getAddress() + " on an urgent call. ");
            System.out.println("An ambulance " + (nine11call.isAmbulanceNeeded() ? "is" : "is not") + " needed.");
        } else {
            System.out.println("Extremely urgent! Emergency at " + nine11call.getAddress() + ". Notify the Mayor!");
        }
    }
}