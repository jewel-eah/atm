package atm;

public class Account {
	
	public static int ADD = 1;
	public static int DELETE = 2;
	public static int LIMIT = 3;
	public static int ALL = 4;
	public static int ONE = 5;
	
	private String userId;
	private String accountNum;
	private int money;
	private int size;
	
	public Account(String userId, String accountNum, int money) {
		this.userId = userId;
		this.accountNum = accountNum;
		this.money = money;
	}
	

	public Account(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return this.userId;
	}

	public String getAccountNum() {
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
