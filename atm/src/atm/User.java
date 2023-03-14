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
	}

	// new 객체가 아님 -> AccountManager.list 안에 있는 인스턴스 (static)
	
	public ArrayList<Account> getAccs(){
		return this.accs;
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
	
	
}
