package atm;

import java.util.ArrayList;

public class AccountManager {
	
	// 모든 유저의 계좌번호 
	private static ArrayList<Account> list = new ArrayList<Account>();
	
	// Create
	public void createAccount(Account account) {
		list.add(account);
	}
	
	// Read
	public Account getAccount(int index) {
		Account account = list.get(index);
		String id = account.getUserId();
		String accountNum = account.getAcccountNum();
		int money = account.getMoney();
		int size = account.getSize();
		Account reqObj = new Account(id, accountNum, money, size);
		return reqObj;
	}
	
	// Update
	public void setAccount(int index, Account account) {
		list.set(index, account);
	}
	
	// Delete
	public void deleteAccount(int index) {
		list.remove(index);
	}
	
	public ArrayList<Account> getList(){
		return list;
	}
	
}
