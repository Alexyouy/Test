package taxi;

import java.util.LinkedList;
import java.util.List;

public class Taxi implements InfoI {
    private String id;
    private Passenger pass;
    private Place dest;
    TaxiCompany company;
    private List<Trip> trips = new LinkedList<>();
    
	public Taxi(String id) {
	    this.id=id;
	}
	
	void setCompany(TaxiCompany c){
        company = c;
	}
	
	public String toString(){
		return id;
	}
	
	public void beginTrip(Place dest) {
	    this.dest = dest;
	}
	
	public void terminateTrip(){
	    Trip t = new Trip(pass.getPlace(),dest);
	    trips.add(t);
	    pass.setLocation(dest);
	    company.endTrip(this);
	}
	
	@Override
	public int hashCode(){
	    return id.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
	    if(o==null) return false;
	    if(! (o instanceof Taxi)) return false;
	    return this.id.equals(((Taxi)o).id);
	}

    public void setPassenger(Passenger p) {
        pass = p;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    @Override
    public int compareTo(InfoI o) {
        return trips.size() - o.getValue();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getValue() {
        return trips.size();
    }
}
