package BankApp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Bank {

	public static ArrayList<Customer> customerList = new ArrayList<>();
	//	Date now = 

	public static Customer addCustomer() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the Name of the Account Holder");
		String accountHolderName  = sc.nextLine();

		System.out.println("Please enter the Password for your Account");
		String password  = sc.nextLine();
		while (!validPwd(password)) {
			System.out.println("Please enter the Password in Right format.(atleast 2 Block letters, atleast 2 small Letters, atleast 2 Numerals && minimum of 6 character)");
			password  = sc.nextLine();
		}

		password = passwordEncrypt(password);

		Customer newCustomer = new Customer(accountHolderName, password);
		customerList.add(newCustomer);
		return newCustomer;
	}


	private static boolean validPwd(String acPwd1) {

		String pattern = "^(?=.*[0-9]){2,}(?=.*[a-z]){2,}(?=.*[A-Z])"
				+ "{2,}(?=\\S+$).{6,}$";
		if (acPwd1.matches(pattern)) {
			return true;
		}
		else {
			return false;
		}
	}

	private static String passwordEncrypt(String acPwd1) {

		String encryptedPass = "";

		for (int i = 0; i < acPwd1.length(); i++) {
			if (Character.isDigit(acPwd1.charAt(i))) {
				if (acPwd1.charAt(i) == '9') {
					encryptedPass+= '1';
				}
				else {
					encryptedPass+= (char)(acPwd1.charAt(i) +1);
				}
			}
			else if (Character.isLowerCase(acPwd1.charAt(i))) {
				if (acPwd1.charAt(i) == 'z') {
					encryptedPass+= 'a';
				}
				else {
					encryptedPass+= (char)(acPwd1.charAt(i) +1);
				}

			}
			else if (Character.isUpperCase(acPwd1.charAt(i))) {
				if (acPwd1.charAt(i) == 'Z') {
					encryptedPass+= 'A';
				}
				else {
					encryptedPass+= (char)(acPwd1.charAt(i) +1);
				}
			}

		}
		return encryptedPass;
	}

	protected static void viewTopCustomers() {

		System.out.println("\nTop 10 Customers");
		System.out.println("------------------------------------------------------");
		System.out.print("Name" + "\t");
		System.out.print("CustID" + "\t");
		System.out.print("Balance" + "\t");
		System.out.print("Pass\t" + "\t");
		System.out.println("accNo");
		System.out.println("------------------------------------------------------");		

		Comparator<Customer> cm1=Comparator.comparingInt(Customer::getAccBalance);  

		Collections.sort(customerList,cm1);  

		System.out.println();

		int i = 0;

		forLoop:
			for (Customer customer : customerList) {
				customer.printCustomer();
				i++;
				if (i==10) {
					break forLoop;
				}
			}
	}


	public static Customer customerLogin() {
		System.out.println("Enter Your Cust ID");
		Scanner sc = new Scanner(System.in);
		int custID = sc.nextInt();
		System.out.println("Enter Your Password");
		String passWord = sc.next();
		while (!validPwd(passWord)) {
			System.out.println("Enter the RIGHT Password");
			passWord = sc.next();
		}

		passWord = passwordEncrypt(passWord);

		for (Customer customer : customerList) {
			if (customer.getCustId()  == custID && passWord.equals(customer.getPassword()) ) {
				return customer;
			}

		}
		return null;
	}

	public static void transfer(Customer cus) {
		System.out.println("Please enter the Beneficiary CustID");
		Scanner sc = new Scanner(System.in);
		int beneficiaryID = sc.nextInt();

		for (Customer customer : customerList) {
			if (beneficiaryID == customer.getCustId()) {
				System.out.println("Please enter the Amount to be Transfered to " + customer.getAcName());
				int transferAmt = sc.nextInt();
				while ((cus.getAccBalance()-transferAmt)<=1000) {
					System.out.println("Please transfer Lesser Amount, Your Balance - " + cus.getAccBalance());
					transferAmt = sc.nextInt();
				}
				String timeStamp = getTime();
				cus.setTranscation(""+timeStamp + " Amount Transfered  to "+ beneficiaryID+" - "+ transferAmt + "\n");
				cus.setAccBalance(cus.getAccBalance() - transferAmt);
				customer.setAccBalance(customer.getAccBalance() + transferAmt);
				System.out.println("your Amount Debited & Credited to the Beneficiary");
				return;
			}
		}
		System.out.println("Beneficiary not Found");
		return;
	}


	public static void withDraw(Customer cus) {
		System.out.println("Please enter the Amount to Withdrawn");
		Scanner sc = new Scanner(System.in);
		int amountWithDraw = sc.nextInt();

		while ((cus.getAccBalance()-amountWithDraw)<=1000) {
			System.out.println("Please withdraw Lesser Amount, Your Balance - " + cus.getAccBalance());
			amountWithDraw = sc.nextInt();
		}
		String timeStamp = getTime();
		cus.setTranscation(""+timeStamp + " Amount Withdrawn  - " + amountWithDraw + "\n");
		cus.setAccBalance(cus.getAccBalance() -amountWithDraw );
		System.out.println("Please Collect Your Money from the Dispatcher");

	}


	public static void deposit(Customer cus) {
		System.out.println("Please enter the Amount to Deposit");
		Scanner sc = new Scanner(System.in);
		int amountDep = sc.nextInt();
		String timeStamp = getTime();
		cus.setTranscation(""+timeStamp + " Amount Deposited - " + amountDep + "\n");
		cus.setAccBalance(amountDep + cus.getAccBalance());
		System.out.println("Deposited SuccessFully!!!");

	}


	public static void changePassWord(Customer cus) {
		System.out.println("Please enter your passWord");
		Scanner sc = new Scanner(System.in);
		String password  = sc.nextLine();
		password = passwordEncrypt(password);
		while (!cus.getPassword().equals(password) ) {
			System.out.println("Please enter the RIGHT passWord");
			password  = sc.nextLine();
			password = passwordEncrypt(password);
		}
		System.out.println("Please enter your NEW passWord");
		String newPassword  = sc.nextLine();
		while (!validPwd(newPassword)) {
			System.out.println("Please enter the Password in Right format.(atleast 2 Block letters, atleast 2 small Letters, atleast 2 Numerals && minimum of 6 character)");
			password  = sc.nextLine();
		}

		newPassword = passwordEncrypt(newPassword);

		cus.updatePassword(newPassword);	
		String timeStamp = getTime();
		cus.setTranscation(""+ timeStamp + " Password Updated " + "\n");

	}


	public static void viewTransaction(Customer cus) {
		System.out.println(cus.getTranscation());		
	}

	public static String getTime() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);  
	}
}
