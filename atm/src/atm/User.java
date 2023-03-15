package atm;

import java.util.ArrayList;

public class User {

	private String id;
	private String name;
	private String password;
	private ArrayList<Account> accs;

	public User(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.accs = new ArrayList<Account>();
	}
	
	public User(String id, String password, String name, ArrayList<Account> accs) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.accs = accs;
	}

	// new 객체가 아님 -> AccountManager.list 안에 있는 인스턴스 (static)
	
	// 비밀번호확인용 
	public User(String password) {
		this.password = password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Account> getAccountList(){
		
		return (ArrayList<Account>) this.accs.clone();
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password;
	}
	
	public int getAccountSize() {
		return this.accs.size();
	}

	public void addAcoount(Account account) {
		this.accs.add(account);
	}
	
	public Account getAccount(int index) {
		return this.accs.get(index);
	}

}
