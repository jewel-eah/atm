package atm;

import java.util.ArrayList;
import java.util.Scanner;


public class UserManager {

	private static ArrayList<User> list = new ArrayList<User>();
	

	// User 에 대한
	
	// Create
	// 외부에서 검증이 완료된 정보들을 넣을 인터페이스만 제공 
	public void addUser(User user) {
		list.add(user);
	}
	
	// Read
	public User getUser(int index) {
		User user = list.get(index);
		// index 구하는 메소드 작성
		
		String id = user.getId();
		String password = user.getPassword();
		String name = user.getName();
		// 사본 제공 
		User reqQbj = new User(id, password, name);
		return reqQbj;
	}
	
	public User getUserById(String id) {
		int index = -1 ; // ?
		return getUser(index);
		
	}
	
	
	// Update
	public void setUser(int index, User user) {
		// 검증 메소드 작성 
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
	
	
	
