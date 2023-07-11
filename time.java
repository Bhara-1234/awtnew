package interests;
import java.io.*;
import java.time.*;
import java.util.*;
class Account{
	private  int accountnumber;
	private String accounttype;
	private Date openingdate;
	private int openingbalance;
	Account(int an,String at, Date od, int ob){
		accountnumber=an;
		accounttype=at;
		openingdate=od;
		openingbalance=ob;
	}
 public void setAccountNumber(int an) {
	 accountnumber=an;
 }
 public int getAccountNumber() {
	 return accountnumber;
 }
 public void setAccountType(String at) {
	 accounttype=at;
 }
 public String getAccounttype() {
	 return accounttype;
 }
 public void setOpeningDate(Date od) {
	 openingdate=od;
 }
 public Date getOpeningDate() {
	 return openingdate;
 }
 public void setOpeningBalanace(int ob) {
	 openingbalance=ob;
 }
 public int getOpeningBalance() {
	 return openingbalance;
 }
 
}
class Transaction{
	private int transactionid;
	private int accountnumber;
	private LocalDate transactiondate;
	private double transactionamount;
	Transaction(int a, int b, LocalDate d,double t){
		transactionid=a;
		accountnumber=b;
		transactiondate=d;
		transactionamount=t;
	}

	public int getTransactionId() {
		 return transactionid;
	 }
	 public void setTransactionId(int tid) {
		 transactionid=tid;
	 }
	 public void setAccountNumber(int an) {
		 accountnumber=an;
	 }
	 public int getAccountNumber() {
		 return accountnumber;
	 }
	 public void setTransactionDate(LocalDate d) {
		 transactiondate =d;
	 }
	 public LocalDate getTransactionDate() {
		 return transactiondate;
	 }
	 public void setTransactionAmount(double amount) {
		 transactionamount =amount;
	 }
	 public    double  getTransactionAmount() {
		 return transactionamount;
	 }
}
class Main{
	public static void main(String[] args) throws IOException {
		ArrayList<Account> a;
		ArrayList<Transaction> t = new ArrayList<>();
		
			FileReader fr =  new FileReader("D:\\transactionData.txt");
			BufferedReader br = new BufferedReader(fr);
			String s;
			while((s=br.readLine())!=null) {
				String[] sr=s.split(",");
				int a1= Integer.parseInt(sr[0]);
				int a2 = Integer.parseInt(sr[1]);
				LocalDate a3 = LocalDate.parse(sr[2]);
				double a4 = Double.parseDouble(sr[3]);
				Transaction tt = new Transaction(a1,a2,a3,a4);
				t.add(tt);}
				for(Transaction tt1: t ) {
				System.out.println(tt1.getTransactionId());
				}
				
				
				
		
			
		
			}
	}
