package taxi;

public class Passenger {
    
    Place origin;

	public Passenger(Place pos) {
	    origin = pos;
	}
	
	public Place getPlace() {
		return origin;
	}

    void setLocation(Place dest) {
        origin=dest;
    }

}
