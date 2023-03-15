package atm;

public class Account {
	
	public static int ADD = 1;
	public static int DELETE = 2;
	public static int LIMIT = 3;
	
	private String userId;
	private String accountNum;
	private int money;
	private int size;
	
	public Account(String userId, String accountNum) {
		this.userId = userId;
		this.accountNum = accountNum;
//		this.money = money;
	}
	

	public Account(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return this.userId;
	}

	public String getAcccountNum() {
		return this.accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	
	
	public int getMoney() {
		return this.money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getSize() {
		return this.size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
