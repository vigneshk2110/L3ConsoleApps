package BankApp;

import java.util.Scanner;

public class BankCustomerUI extends Bank{

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Welcome to the CRM Bank");
		Scanner sc = new Scanner(System.in);
		int option = 0;
		boolean flag = true;
		while (flag) {

			System.out.println("Please select the desired option "
					+ "\n1. Banker "
					+ "\n2. Customer");
			option = sc.nextInt();

			if (option == 1 ) {
				if(adminLogin()) {
					bankerUI();
				}
				else {
					System.out.println("Please enter the Right Credentials");
				}
				
			}
			else if (option == 2) {
				Customer cus = customerLogin();
				if (cus!=null) {
					customerUI(cus);
				}
				else {
					System.out.println("Please enter the Right Credentials");
				}
				
			}
			
		}
	}

	private static boolean adminLogin() {
		System.out.println("Enter the UserName");
		Scanner sc = new Scanner(System.in);
		String uNameString = sc.next();
		System.out.println("Enter the Password");
		String passWord = sc.next();
		if (uNameString.equals("admin") && passWord.equals("admin")) {
			return true;
		}
		return false;
	}

	private static void customerUI(Customer cus) throws InterruptedException {
		System.out.println("Redirecting to Customer's Page, Please wait!!!");
		Thread.sleep(1500);
		System.out.println("Welcome to the Customer's Portal");
		Scanner sc = new Scanner(System.in);
		int option = 0;
		do {
			System.out.println("Please select the desired option "
					+ "\n1.Check Balance"
					+ "\n2.Deposit"
					+ "\n3.Withdraw"
					+ "\n4.Money Transfer"
					+ "\n5.View Transactions"
					+ "\n6.Change Account Password");
			option = sc.nextInt();

			switch (option) {
			case 1:
				System.out.println("Account Balance - Rs." + cus.getAccBalance() + "\n");
				break;

			case 2:
				Bank.deposit(cus);
				System.out.println("New Balance - Rs." + cus.getAccBalance() + "\n" );
				break;

			case 3:
				Bank.withDraw(cus);
				System.out.println("New Balance - Rs." + cus.getAccBalance() + "\n" );
				break;

			case 4:
				Bank.transfer(cus);
				System.out.println("New Balance - Rs." + cus.getAccBalance() + "\n" );
				break;
				
			case 5:
				Bank.viewTransaction(cus);
				System.out.println("View Transactions");
				break;
				
			case 6:
				Bank.changePassWord(cus);
				break;
			default:
				break;
			}
		}
		while (option >= 1 && option<=6 );
		System.out.println("Thanks, Visit Again!!!");
	}

	private static void bankerUI() throws InterruptedException{
		System.out.println("Redirecting to Banker's Page, Please wait!!!");
		Thread.sleep(1500);
		System.out.println("Welcome to the Banker's Portal");
		Scanner sc = new Scanner(System.in);
		int option = 0;
		do {
			System.out.println("\nPlease select the desired option "
					+ "\n1.Add New Customer"
					+ "\n2.View Top 10 Customers");
			option = sc.nextInt();


			switch (option) {
			case 1:
				addCustomer();
				break;

			case 2:
				viewTopCustomers();
				break;

			default:
				break;
			}

		} while (option >= 1 && option<=2);
		System.out.println("Thanks, Visit Again!!!");

	}

}
