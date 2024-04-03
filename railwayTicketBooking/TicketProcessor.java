package railwayTicketBooking;

import java.util.*;

public class TicketProcessor {
	static	int availableLowerBerth = 3;
	static	int availableUpperBerth = 3;
	static	int availableMiddleBerth = 3;
	static	int availableRAC = 2;
	static	int availableWaitingList = 1;

	static	Queue<Integer> waitingList = new LinkedList<Integer>();
	static	Queue<Integer> RAC = new LinkedList<Integer>();
	static	List<Integer> bookedList = new ArrayList<Integer>();

	static	List<Integer> lowerBerthPositions = new ArrayList<>(Arrays.asList(1,2,3));
	static	List<Integer> upperBerthPositions = new ArrayList<>(Arrays.asList(1,2,3));
	static	List<Integer> middleBerthPositions = new ArrayList<>(Arrays.asList(1,2,3));
	static	List<Integer> RACPositions = new ArrayList<>(Arrays.asList(1,2));
	static	List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));

	Map<Integer, Passenger> passengerMap = new HashMap<Integer, Passenger>();

	public void bookTicket(Passenger p, Integer berthNo, String allotedBerth) {
		p.number = berthNo;
		p.alloted = allotedBerth;
		bookedList.add(p.passengerID);
		passengerMap.put(p.passengerID, p);
		System.out.println("Ticket booked successfully");

	}

	public void addToRAC(Passenger p, Integer berthNo, String allotedBerth) {
		p.number = berthNo;
		p.alloted = allotedBerth;
		RAC.add(p.passengerID);
		passengerMap.put(p.passengerID, p);
		System.out.println("Added to RAC");

	}

	public void addToWaitingList(Passenger p, Integer berthNo, String allotedBerth) {
		p.number = berthNo;
		p.alloted = allotedBerth;
		waitingList.add(p.passengerID);
		passengerMap.put(p.passengerID, p);
		System.out.println("Added to Waiting List");

	}

	public void cancleTicket(int id) {
		Passenger p = passengerMap.get(id);
		bookedList.remove(id);
		passengerMap.remove(id);
		int num = p.number;
		String allotedString = p.alloted;
		if (allotedString.equals("L")) {
			availableLowerBerth++;
			lowerBerthPositions.add(num);
		}
		else if (allotedString.equals("M")) {
			availableMiddleBerth++;
			middleBerthPositions.add(num);
		}
		else if (allotedString.equals("U")) {
			availableUpperBerth++;
			upperBerthPositions.add(num);
		}
		else if (allotedString.equals("RAC")) {
			availableRAC++;
			RACPositions.add(num);
		}
		else if (allotedString.equals("WL")) {
			availableWaitingList++;
			waitingListPositions.add(num);
		}

		if (RACPositions.size()>0) {
			Passenger racPassenger = passengerMap.get(RAC.poll());
			int racNum = racPassenger.number;
			RAC.remove(racPassenger.passengerID);
			passengerMap.remove(racPassenger.passengerID);
			RACPositions.add(racNum);
			availableRAC++;
			bookTicket(racPassenger, racPassenger.age, racPassenger.berthPreference);

			if (waitingListPositions.size()>0) {
				Passenger WLpassenger = passengerMap.get(waitingList.poll());
				int wlNum = WLpassenger.number;
				waitingList.remove(WLpassenger.passengerID);
				passengerMap.remove(WLpassenger.passengerID);
				waitingListPositions.add(wlNum);


				WLpassenger.number = RACPositions.get(0);
				WLpassenger.alloted = "RAC";
				RAC.add(WLpassenger.passengerID);
				RACPositions.remove(0);
				availableWaitingList++;
				availableRAC--;
			}
		}
	}

	public void printAvailable() {
		System.out.println("Available Lower Berth - " + availableLowerBerth);
		System.out.println("Available Upper Berth - " + availableUpperBerth);
		System.out.println("Available Middle Berth - " + availableMiddleBerth);
		System.out.println("Available RAC  - " + availableRAC);
		System.out.println("Available Waiting List - " + availableWaitingList);

		System.out.println("--------------------------");

	}

	public void printBookedTickets() {
		if (passengerMap.isEmpty()) {
			System.out.println("No booking made");
			return;
		}
		for (Passenger p : passengerMap.values()) {
			System.out.println("Passenger Name : " + p.name);
			System.out.println("Passenger age : " + p.age);
			System.out.println("Passenger num : " + p.number);
			System.out.println("Allotted berth : " + p.alloted);

		}

	}

}
