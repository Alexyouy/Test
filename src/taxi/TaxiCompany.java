package taxi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TaxiCompany {
    
	private Map<String,Taxi> taxies = new HashMap<>();
	private LinkedList<Taxi> available = new LinkedList<>();
	private List<Taxi> occupied = new LinkedList<>();
    private int lostTrips;
	
	public void addTaxi(String id) throws InvalidTaxiName {
	    Taxi t = new Taxi(id);
	    if(available.contains(t)) throw new InvalidTaxiName();
	    t.setCompany(this);
        taxies.put(id, t);
	    available.add(t);
	}
	
	public Collection<Taxi> getAvailable() {
		return available;
	}

	public Taxi callTaxi(Passenger p) {
	    Taxi t = available.poll();//!!!!//
	    if(t!=null){
	        occupied.add(t);
	        t.setPassenger(p);
	    }else{
	        lostTrips++;
	    }
		return t;
	}
	
	public List<Trip> getTrips(String id) throws InvalidTaxiName{
	    if(!taxies.containsKey(id)) throw new InvalidTaxiName();
	    Taxi t = taxies.get(id);
		return t.getTrips();
	}
	
	public int getLostTrips(){
		return lostTrips;
	}
	
	public ArrayList<InfoI> statsTaxi() {
		return taxies.values().stream()
		        .sorted(Comparator.comparingInt(InfoI::getValue).reversed().thenComparing(Comparator.comparing(InfoI::getId)))
		        .collect(Collectors.toCollection(ArrayList::new));/***!!!!!!!!!!!!!*/
		        
	}
	
	
	class Info implements InfoI{
	    private String id;
	    private int value;
	    Info(String id, int value){
	        this.id=id;
	        this.value = value;
	    }
        @Override
        public int compareTo(InfoI o) {
            return this.value - o.getValue();
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public int getValue() {
            return value;
        }
	    
	}
	public ArrayList<InfoI> statsDistricts() {
	    return taxies.values().stream()
	    .flatMap( t -> t.getTrips().stream())
	    .map(Trip::getDestination)
	   // .map(Place::getDistrict)
	    .collect(Collectors.groupingBy(/*Function.identity()*/s->s.getDistrict(),Collectors.counting()))/**************************************/
	    .entrySet().stream()
	    .map( entry -> new Info(entry.getKey(),entry.getValue().intValue()) )
	    .sorted(Comparator.comparingInt(InfoI::getValue).reversed().thenComparing(Comparator.comparing(InfoI::getId)))
	    .collect(Collectors.toCollection(ArrayList::new))
	    ;
	}

    void endTrip(Taxi taxi) {
        occupied.remove(taxi);
        available.add(taxi);
    }
}
