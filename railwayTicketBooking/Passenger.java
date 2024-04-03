package railwayTicketBooking;

public class Passenger {
	String name;
	String berthPreference;
	int age;
	static int passengerID = 1;
	String alloted;
	int number;
	
	public Passenger(String passengerName, int agePassenger, String berthPreferred ) {
		this.name = passengerName;
		this.age = agePassenger;
		this.berthPreference = berthPreferred;
		this.number =-1;
		this.alloted = "";
		this.passengerID = passengerID++;
	}
}
