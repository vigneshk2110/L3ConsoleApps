package BankApp;


public class Customer {	

	private int custId = 0;
	private int accNo = 0;
	private int accBalance;

	private String acName;
	private String acPwdNo1;
	private String transcation;

	public int getCustId() {
		return custId;
	}
	public int getAccNo() {
		return accNo;
	}
	public int getAccBalance() {
		return accBalance;
	}
	public String getAcName() {
		return acName;
	}
	public void setAccBalance(int accBalance) {
		this.accBalance = accBalance;
	}


	public String getPassword() {
			return this.acPwdNo1;
		
	}

	public String getTranscation() {
		return transcation;
	}
	
	public void setTranscation(String transcation) {
		this.transcation += transcation;
	}
	
	public Customer(String acName, String pass) {
		this.acName = acName;
		this.acPwdNo1 = pass;
		this.accBalance = 10000;
		String timeStamp = Bank.getTime();
		this.transcation = ""+timeStamp + " Customer added to the Bank \n";
		if (Bank.customerList.size() != 0) {
			Customer last =  Bank.customerList.get(Bank.customerList.size()-1);
			this.accNo = last.getAccNo()+11011;
			this.custId  = last.getCustId()+11;
		}else {
			this.accNo = 11011;
			this.custId  = 11;
		}
	}

	public void printCustomer() {
		System.out.print(this.getAcName() + "\t");
		System.out.print( this.getCustId() + "\t");
		System.out.print(this.getAccBalance() + "\t");
		System.out.print( this.getPassword() + "\t");
		System.out.println( this.getAccNo() + "\t");
	}
	public void updatePassword(String newPassword) {
		this.acPwdNo1 = newPassword;
		System.out.println("Password Updated Succefully");
	}
	

}
