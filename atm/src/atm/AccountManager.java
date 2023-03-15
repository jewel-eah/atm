package atm;

import java.util.ArrayList;
import java.util.Random;

public class AccountManager {
	
	// 모든 유저의 계좌번호 
	private static ArrayList<Account> list = new ArrayList<Account>();
	
	// Create
	public Account createAccount(Account account) {
		String number = setAccount();
		account.setAccountNum(number);
		System.out.printf("[ %s ]\n", number);
		list.add(account);
		return account;
	}
	
	// Read
	public Account getAccount(int index) {
		Account account = list.get(index);
		
		Account reqObj = new Account(account.getUserId(), account.getAcccountNum());
		return reqObj;
	}
	
	
	public Account getAccountByNum(String accountNum) {
		Account account = null;
		
		for(Account object : list) {
			if(object.getAcccountNum().equals(accountNum)) {
				account = object;
			}
		}
		return account;
	}
	
	private String setAccount() {
		Random ran = new Random();
		String number = "";
		while(true) {
			int num1 = ran.nextInt(8999) + 1000;
			int num2 = ran.nextInt(8999) + 1000;
			
			number = num1 + "-" + num2;
			
			Account account = getAccountByNum(number);
			
			if(account == null) {
				break;
			}
		}
		return number;
	}
	
	// Update
	public void setAccount(int index, Account account) {
		list.set(index, account);
	}
	
	// Delete
	public boolean isExsist(int delAccount) {
		if(delAccount <= list.size()) {
			return true;
		}
		return false;
	}
	
	public void deleteAccount(Account account) {
		Account delAcc = getAccountByNum(account.getAcccountNum());
		list.remove(delAcc);
	}
	
	public ArrayList<Account> getList(){
		return list;
	}
	
	// printAccountList
	public void printAccountNumber(int index) {
		for (int i = 0; i <list.size(); i++) {
			System.out.printf("[%d] %s/\n", i+1, list.get(i).getAcccountNum());
		}
	}

	
	
}
