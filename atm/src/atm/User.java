package atm;

import java.util.ArrayList;

public class User {

	private String id;
	private String password;
	private String name;
	private ArrayList<Account> accs;

	public ArrayList<Account> getAccs(){
		return this.accs;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
	}
	
}
