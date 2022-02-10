package taxi;

public class Place {

    String address;
    String district;
	
	public Place(String ind, String quart) {
	    address = ind;
	    district = quart;
	}
	
	public String toString(){
		return address;
	}
	
	String getDistrict(){
	    return district;
	}

}
