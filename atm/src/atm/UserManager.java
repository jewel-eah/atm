package atm;

import java.util.ArrayList;
import java.util.Scanner;


public class UserManager {

	private static ArrayList<User> list = new ArrayList<User>();
	

	// User 에 대한
	
	// Create
	// 외부에서 검증이 완료된 정보들을 넣을 인터페이스만 제공 
	public User addUser(User user) {
		// 검증 후 add
		User check = getUserById(user.getId());
		if(check == null) {
			list.add(user);
			return user;
		}
		return null;
	}
	
	public boolean isCheckPassword(String password, int log) {
		if(list.get(log).getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	// Read
	public User getUser(int index) {
		User user = list.get(index);
		
		User reqQbj=
				new User (
						user.getId(),
						user.getPassword(),
						user.getName(),
						user.getAccountList()
				);
		return reqQbj;
	}	
		
		
	public User getUserById(String id) {
		User user = null;
		
		int index = indexOf(id);
		if(index != -1) {
			user = getUser(index);
		}

		return user;
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
	

	
	// Update
	public void setUser(int index, User user) {
		// 검증 메소드 작성 
		list.set(index, user);
	}
	
	
	public void printAccount(User user, Account account, int log) {
		for(int i=0; i<list.get(log).getAccountSize(); i++) {
			System.out.printf("[%d] %s\n", i+1, list.get(log).getAccount(i).getAcccountNum());
		}
	}
	
	public void setUser(User user, Account account) {
		int index = indexOf(user.getId());
		
		list.get(index).addAcoount(account);
	}
	
	// 로그인 검증 
	public boolean checkLogInfo(String id, String password) {
		for(int i=0; i<list.size(); i++) {
			if(id.equals(list.get(i).getId()) && password.equals(list.get(i).getPassword())) {
				return true;
			}
		}
		return false;
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

	
}
	
	
	
