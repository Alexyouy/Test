package taxi;

public class Trip {
    private Place source;
    private Place destination;
	
	public Trip(Place place, Place dest) {
        source = place;
        destination = dest;
    }
	
	Place getDestination(){
	    return destination;
	}

    public String toString() {
		return source + " , " + destination;
	}
	
}
