package railwayTicketBooking;

import java.util.Scanner;

public class portal {

	public static void main(String[] args) {
		System.out.println("Welcome to booking portal \nPlease select any of the below option");
		Scanner scan = new Scanner(System.in);
		boolean loop = true;

		while (loop) {
			System.out.println("1.Book Tickets \n2. Cancel Tickets \n3. Available tickets \n4. print booked tickets \n5. exit");
			int option = scan.nextInt();
			switch (option) {
			case 1:
				System.out.println("Pleasse enter the passenger name");
				String name = scan.next();
				System.out.println("Pleasse enter the passenger age");
				int age = scan.nextInt();
				System.out.println("Pleasse enter the BerthPreference - L/M/U");
				String berth = scan.next();

				Passenger p = new Passenger(name, age, berth);
				bookticket(p);

				break;

			case 2:{
				System.out.println("Please enter the passenger ID");
				int id = scan.nextInt();
				cancleTicket(id);
			}
			break;
			case 3:
			{
				TicketProcessor processor = new TicketProcessor();
				processor.printAvailable();
			}
			break;
			case 4:
			{
				TicketProcessor processor = new TicketProcessor();
				processor.printBookedTickets();
			}
			break;
			case 5:
			{
				loop = false;
			}
			default:
				break;
			}
		}



	}

	private static void cancleTicket(int id) {
		TicketProcessor processor = new TicketProcessor();
		
		if (processor.passengerMap.containsKey(id)) {
			processor.cancleTicket(id);
		}
		else {
			System.out.println("Passenger details not found");
		}

	}

	private static void bookticket(Passenger p) {
		TicketProcessor processor = new TicketProcessor();

		if (processor.availableWaitingList==0) {
			System.out.println("No  tickets available");
			return;
		}
		if ((p.berthPreference.equals("L")||p.berthPreference.equals("l")) && processor.availableLowerBerth>0 || (p.berthPreference.equals("M")||p.berthPreference.equals("m")) && processor.availableMiddleBerth>0 || (p.berthPreference.equals("U")||p.berthPreference.equals("u")) && processor.availableUpperBerth>0  ) {

			System.out.println("Preferred Berth Available");
			if (p.berthPreference.equals("L")||p.berthPreference.equals("l")) {
				System.out.println("Lower Berth Alloted");
				processor.bookTicket(p,(TicketProcessor.lowerBerthPositions.get(0)), "L");

				TicketProcessor.lowerBerthPositions.remove(0);
				processor.availableLowerBerth--;
			}
			else if (p.berthPreference.equals("M")||p.berthPreference.equals("m")) {
				System.out.println("Middle Berth Alloted");
				processor.bookTicket(p,(TicketProcessor.middleBerthPositions.get(0)), "M");

				TicketProcessor.middleBerthPositions.remove(0);
				processor.availableMiddleBerth--;
			}
			else if (p.berthPreference.equals("U")||p.berthPreference.equals("u")) {
				System.out.println("Upper Berth Alloted");
				processor.bookTicket(p,(TicketProcessor.upperBerthPositions.get(0)), "U");

				TicketProcessor.upperBerthPositions.remove(0);
				processor.availableUpperBerth--;
			}
		}
		else if (processor.availableLowerBerth>0) {
			System.out.println("Lower berth alloted");
			processor.bookTicket(p,(TicketProcessor.lowerBerthPositions.get(0)), "L");

			TicketProcessor.lowerBerthPositions.remove(0);
			processor.availableLowerBerth--;
		}
		else if (processor.availableMiddleBerth>0) {
			System.out.println("Middle berth alloted");
			processor.bookTicket(p,(TicketProcessor.middleBerthPositions.get(0)), "M");

			TicketProcessor.middleBerthPositions.remove(0);
			processor.availableMiddleBerth--;
		}
		else if (processor.availableLowerBerth>0) {
			System.out.println("Upper berth alloted");
			processor.bookTicket(p,(TicketProcessor.upperBerthPositions.get(0)), "U");

			TicketProcessor.upperBerthPositions.remove(0);
			processor.availableUpperBerth--;
		}
		else if (processor.availableRAC>0) {
			System.out.println("RAC Available");
			processor.addToRAC(p,(TicketProcessor.upperBerthPositions.get(0)), "RAC");

			TicketProcessor.RACPositions.remove(0);
			processor.availableRAC--;
		}
		else if (processor.availableWaitingList>0) {
			System.out.println("Already in Waiting List");
			processor.addToWaitingList(p,(TicketProcessor.waitingListPositions.get(0)), "WL");

			TicketProcessor.waitingListPositions.remove(0);
			processor.availableWaitingList--;
		}

	}

}
