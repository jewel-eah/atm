package atm;

public class Account {
	
	public static int LIMIT = 3;
	
	private String userId;
	private String accountNum;
	private int money;
	
	public Account(String userId, String accountNum, int money) {
		this.userId = userId;
		this.accountNum = accountNum;
		this.money = money;
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
	
}
