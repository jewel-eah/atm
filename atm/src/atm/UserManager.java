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
		
		// 사본 제공 
		User reqQbj = new User();
		return reqQbj;
	}
	
	public User getUserById(String id) {
		int index = -1 ; // ?
		return getUser(index);
		
	}
	
	
	// Update
	public void setUser(int index, User user) {
		// 검증 메소드 작성 
		this.list.set(index, user);
	}
	// Delete
	public void deleteUser(int index) {
		this.list.remove(index);
	}	
	
	
	public void deleteUserById(String id) {
		
	}
	
	public ArrayList<User> getUserList(){
		return this.list;
	}
	
	
	
}
	
	
	
//	public static void dataSetting(String data) {
//		String[] lines = data.split("\n");
//		for (int i = 0; i < lines.length; i++) {
//			String[] info = lines[i].split("/");
//
//			String userId = info[0];
//			String acc = info[1];
//			int money = Integer.parseInt(info[2]);
//
//			AccountT account = new AccountT(userId, acc, money);
//			list.add(account);
//		}
//	}
//
//	public ArrayList<AccountT> getList() {
//		return list;
//	}
//	
