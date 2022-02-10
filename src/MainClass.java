import taxi.Trip;
import taxi.InfoI;
import taxi.InvalidTaxiName;
import taxi.Place;
import taxi.Passenger;
import taxi.Taxi;
import taxi.TaxiCompany;



public class MainClass {


	public static void main(String[] args) {
        TaxiCompany company = new TaxiCompany();
        Place luogo1 = new Place("Via Roma 10" , "centro");
        Place luogo2 = new Place("Corso Francia 105", "cit turin");
        Place luogo3 = new Place("Corso Duca Abruzzi 24", "crocetta");
        Passenger passeggero1 = new Passenger(luogo1);
        Passenger passeggero2 = new Passenger(luogo1);
        Passenger passeggero3 = new Passenger(luogo3);
        try{
        	company.addTaxi("Taxi1");
        	company.addTaxi("Taxi2");
        	company.addTaxi("Taxi1");
        }
        catch (InvalidTaxiName itn){
        	System.out.println(itn);
        }
        Taxi t = company.callTaxi(passeggero1);
        if (t != null){
        	System.out.println("Assegnato " + t);						// Assegnato Taxi1
        }
        else
        	System.out.println("Richiesta  rifiutata");
        t.beginTrip(luogo2);
        System.out.println( t + ": partito da " + passeggero1.getPlace() + ", destinazione " + luogo2);
        																// Taxi1: partito da Via Roma 10, destinazione Corso Francia 105
        t.terminateTrip();
        System.out.println(t + ": arrivato in " + passeggero1.getPlace());	// Taxi1: arrivato in Corso Francia 105
        
        t = company.callTaxi(passeggero2);
        if (t != null){
        	System.out.println("Assegnato " + t);						// Assegnato Taxi2
        }
        else
        	System.out.println("Richiesta  rifiutata");
        
        t.beginTrip(luogo3);
        System.out.println( t + ": partito da " + passeggero2.getPlace() + ", destinazione " + luogo3);
        																// Taxi2: partito da Via Roma 10, destinazione Corso Duca Abruzzi 24
        t.terminateTrip();
        System.out.println(t + ": arrivato in " + passeggero2.getPlace());	// Taxi2: arrivato in Corso Duca Abruzzi 24
        
        t = company.callTaxi(passeggero1);
        if (t != null){
        	System.out.println("Assegnato " + t);						// Assegnato Taxi1
        }
        else
        	System.out.println("Richiesta  rifiutata");
        t.beginTrip(luogo3);
        System.out.println( t + ": partito da " + passeggero1.getPlace() + ", destinazione " + luogo3);
        t.terminateTrip();
        System.out.println(t + ": arrivato in " + passeggero1.getPlace());
        
        t = company.callTaxi(passeggero1);
        if (t != null){
        	System.out.println("Assegnato " + t);						// Assegnato Taxi2
        }
        else
        	System.out.println("Richiesta  rifiutata");
        
        t = company.callTaxi(passeggero2);
        if (t != null){
        	System.out.println("Assegnato " + t);						// Assegnato Taxi1
        }
        else
        	System.out.println("Richiesta  rifiutata");
        
        t = company.callTaxi(passeggero3);
        if (t != null){
        	System.out.println("Assegnato " + t);
        }
        else
        	System.out.println("Richiesta  rifiutata");					// Richiesta  rifiutata
        
        System.out.println("Corse effettuate da Taxi1");
        try{
        for(Trip c : company.getTrips("Taxi1"))
        	System.out.println("  " + c);								//   Via Roma 10,Corso Francia 105
        }																//   Corso Francia 105,Corso Duca Abruzzi 24
        catch (InvalidTaxiName itn){
        	System.out.println(itn);									
        }
                
        System.out.println("Corse effettuate da Taxi2");
        try{
        for(Trip c : company.getTrips("Taxi2"))
        	System.out.println("  " + c);								//   Via Roma 10,Corso Duca Abruzzi 24
        }
        catch (InvalidTaxiName itn){
        	System.out.println(itn);									
        } 
        System.out.println("Corse perse: " + company.getLostTrips());	// Corse perse: 1
   	
        System.out.println("Statistiche Taxi:");
        for(InfoI info : company.statsTaxi())
        	System.out.println(info.getId() + " " + info.getValue());	//  Taxi1 2
        																//  Taxi2 1
        
        System.out.println("Statistiche Quartieri:");
        for(InfoI info : company.statsDistricts())
        	System.out.println(info.getId() + " " + info.getValue());	//  crocetta 2
																		//  cit turin 1
	}
} 
