package atm;

import java.util.ArrayList;
import java.util.Random;

public class AccountManager {
	
	// 모든 유저의 계좌번호 
	private static ArrayList<Account> list = new ArrayList<Account>();
	
	// Create
	public Account createAccount(Account account) {
		String number = makeAccount();
		account.setAccountNum(number);
		System.out.printf("[ %s ]\n", number);
		list.add(account);
		return account;
	}
	
	// Read
	public Account getAccount(int index) {
		Account account = list.get(index);
		
		Account reqObj = new Account(account.getUserId(), account.getAccountNum(), account.getMoney());
		return reqObj;
	}
	
	public Account setAccount(User user, Account account) {
		return account;
	}
	
	public Account getAccountByNum(String accountNum) {
		Account account = null;
		
		for(Account object : list) {
			if(object.getAccountNum().equals(accountNum)) {
				account = object;
			}
		}
		return account;
	}
	
	private String makeAccount() {
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
		Account delAcc = getAccountByNum(account.getAccountNum());
		list.remove(delAcc);
	}
	
	public ArrayList<Account> getList(){
		return list;
	}
	
	// printAccountList
	public void printAccountNumber(int index, int order) {
		if(order == Account.ALL) {
			for (int i = 0; i <list.size(); i++) {
				System.out.printf("[%d] %s\n", i+1, list.get(i).getAccountNum());
			}
		}
		else if(order == Account.ONE) {
			System.out.printf("[%d] %s\n", index+1, list.get(index).getAccountNum());
		}
	}

	public String findAccountNumber(int index) {
		String accountNumber = list.get(index).getAccountNum();
		return accountNumber;
	}
	
	public void setMoney(int pickAccount, int money) {
		String findAcc = findAccountNumber(pickAccount);
		Account user = list.get(pickAccount);
		int currentMoney = user.getMoney();
		int totalMoney = currentMoney + money;
		
		Account account = new Account(user.getUserId(), findAcc, totalMoney);
		list.set(pickAccount, account);
	}
	
}
