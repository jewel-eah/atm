package atm;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {

	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int CREATE = 3;
	private final int DELETE = 4;
	private final int LOG_IN = 5;
	
	
	private UserManager um;
	private AccountManager am;
	
	// Banking ���� �޼ҵ� 
	private String name;
	private Scanner scan;
	
	// ��ũ ������ 
	public Bank(String name){
		this.name = name;
		this.scan = new Scanner(System.in);
		um = new UserManager();
		am = new AccountManager();
	}
	
	private void printMainMenu() {
		System.out.println("--- " +this.name + " ATM ---");
		System.out.println("1. ȸ������");
		System.out.println("2. ȸ��Ż��");
		System.out.println("3. ���½�û");
		System.out.println("4. ����öȸ");
		System.out.println("5. �α���");
		System.out.println("�޴� : ");
	} 
	
	private int inputNumber() {
		int input = this.scan.nextInt();
		return input;
	}
	
	private String inputText() {
		String input = this.scan.next();
		return input;
	}

	// ���̵� �ߺ�Ȯ�� 
	private boolean isDupl(String id) {
		ArrayList<User> userList = um.getUserList();
		for(User UserManager : userList) {
			if(id.equals(UserManager.getId())) {
				return true;
			}
		}
		return false;
	}
	
	
	// ȸ������ 
	private void join() {
		System.out.print("id : ");
		String id = inputText();
		if(!isDupl(id)) {
			System.out.print("password : ");
			String password = inputText();
			
		}
		else {
			System.out.println("[ �̹� ���Ե� ���̵��Դϴ�. ]");
		}
		
	}
	
	
	public void run() {
		 printMainMenu();
		 int select = inputNumber();
		 if(select == JOIN) {
			 join();
		 }
//		 else if(select == LEAVE) {
//			 leave();
//		 }
//		 else if(select == CREATE) {
//			 create();
//		 }
//		 else if(select == DELETE) {
//			 delete();
//		 }
//		 else if(select == LOG_IN) {
//			 login();
//		 }
	}
	
}
