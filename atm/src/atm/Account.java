package atm;

public class Account {
	
	public static int LIMIT = 3;
	
	private String userId;
	private String accountNum;
	private int money;
	private int size;
	
	public Account(String userId, String accountNum, int money, int size) {
		this.userId = userId;
		this.accountNum = accountNum;
		this.money = money;
		this.size = 0;
	}
	

	public String getUserId() {
		return this.userId;
	}

	public String getAcccountNum() {
		return this.accountNum;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public int getSize() {
		return this.size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
