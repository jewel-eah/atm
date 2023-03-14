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
