package atm;

import java.util.ArrayList;
import java.util.Scanner;


public class UserManager {

	private static ArrayList<User> list = new ArrayList<User>();
	

	// User �� ����
	
	// Create
	// �ܺο��� ������ �Ϸ�� �������� ���� �������̽��� ���� 
	public void addUser(User user) {
		list.add(user);
	}
	
	// Read
	public User getUser(int index) {
		User user = list.get(index);
		// index ���ϴ� �޼ҵ� �ۼ�
		
		String id = user.getId();
		String password = user.getPassword();
		String name = user.getName();
		// �纻 ���� 
		User reqQbj = new User(id, password, name);
		return reqQbj;
	}
	
	public User getUserById(String id) {
		int index = -1 ; // ?
		return getUser(index);
		
	}
	
	
	// Update
	public void setUser(int index, User user) {
		// ���� �޼ҵ� �ۼ� 
		list.set(index, user);
	}
	// Delete
	public void deleteUser(int index) {
		list.remove(index);
	}	
	
	
	public void deleteUserById(String id) {
		
	}
	
	public ArrayList<User> getUserList(){
		return list;
	}

	public int indexOf(String id) {
		int index = -1;
		for(int i=0; i<list.size(); i++) {
			if(id.equals(list.get(i).getId())){
				index = i;
			}
		}
		return index;
	}
	
}
	
	
	
