package atm;

import java.util.ArrayList;

public class AccountManager {
	
	private static ArrayList<Account> list = new ArrayList<Account>();
	
	// Create
	public void createAccount(Account account) {
		list.add(account);
	}
	
	
	// Read
	public Account getAccount(int index) {
		Account account = list.get(index);
		
		Account reqObj = new Account(null, null, index);
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
	
}
