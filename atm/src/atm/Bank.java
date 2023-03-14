package atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Bank {

	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int CREATE = 3;
	private final int DELETE = 4;
	private final int LOG_IN = 5;
	private final int QUIT = 0;

	private UserManager um;
	private AccountManager am;

	private String name;
	private int log;
	private Scanner scan;
	private Random ran;

	// 뱅크 생성자
	public Bank(String name) {
		this.name = name;
		this.log = -1;
		this.scan = new Scanner(System.in);
		this.ran = new Random();
		um = new UserManager();
		am = new AccountManager();
	}

	private void printMainMenu() {
		System.out.println("--- " + this.name + " ATM ---");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 계좌신청");
		System.out.println("4. 계좌철회");
		System.out.println("5. 로그인");
		System.out.println("0. 종료");
		System.out.println("메뉴 : ");
	}

	private int inputNumber() {
		int input = this.scan.nextInt();
		return input;
	}

	private String inputText() {
		String input = this.scan.next();
		return input;
	}

	// 아이디 중복확인
	private boolean isDupl(String id) {
		ArrayList<User> userList = um.getUserList();
		for (User UserManager : userList) {
			if (id.equals(UserManager.getId())) {
				return true;
			}
		}
		return false;
	}

	// 회원가입
	private void join() {
		System.out.print("id : ");
		String id = inputText();
		if (!isDupl(id)) {
			System.out.println("name : ");
			String name = inputText();
			System.out.print("password : ");
			String password = inputText();

			User user = new User(id, name, password);
			um.addUser(user);
			System.out.println("[ 회원 가입 완료 ]");
			System.out.printf("[ %s님 환영합니다. ]\n", id);
		} else {
			System.out.println("[ 이미 가입된 아이디입니다. ]");
		}
	}

	private boolean isLogged(int log) {
		if (log != -1) {
			return true;
		}
		return false;
	}

	// 비밀번호 확인
	private boolean isCheckPassword(String password) {
		ArrayList<User> userList = um.getUserList();
		for (User UserManager : userList) {
			if (password.equals(UserManager.getPassword())) {
				return true;
			}
		}
		return false;
	}

	// 회원탈퇴
	private void leave() {
		if (isLogged(this.log)) {
			System.out.print("password : ");
			String password = inputText();
			if (isCheckPassword(password)) {
				um.deleteUser(this.log);
				System.out.println("[ 회원탈퇴 완료 ]");

			} else {
				System.out.println("[ 비밀번호 재확인 ]");
			}
		} else {
			System.out.println("[ 로그인 후 이용가능한 서비스입니다. ]");
		}
	}

	private int findIndex(String id) {
		return this.um.indexOf(id);
	}

	// 로그인
	private void login() {
		if (!isLogged(this.log)) {
			System.out.print("id : ");
			String id = inputText();
			System.out.print("password : ");
			String password = inputText();

			ArrayList<User> userList = um.getUserList();
			int index = -1;
			for (User userManager : userList) {
				if (id.equals(userManager.getId()) && password.equals(userManager.getPassword())) {
					index = findIndex(id);
					log = index;
					System.out.printf("[ %s 님 로그인 성공 ]\n", id);
				}
			}

		} else {
			System.out.println("[ 이미 로그인 되었습니다. ]");
		}
	}

	private String setAccount() {
		int num1 = ran.nextInt(8999) + 1000;
		int num2 = ran.nextInt(8999) + 1000;

		String number = num1 + "-" + num2;
		return number;

	}

	private boolean isAccountDupl(String accountNum) {
		ArrayList<Account> accountList = am.getList();
		for (Account accountManager : accountList) {
			if (accountNum.equals(accountManager.getAcccountNum())) {
				return true;
			}
		}
		return false;
	}

	// 계좌개설
	private void create() {
		if(isLogged(this.log)) {
//			int count = am.getAccount(this.log).getSize();
			int count = 0;
			ArrayList<User> userList = um.getUserList();
			ArrayList<Account> accountList = am.getList();
			if (accountList.size() < Account.LIMIT) {
				String accountNum = setAccount();
				System.out.println(accountNum);
				if (!isAccountDupl(accountNum)) {
					Account account = new Account(userList.get(this.log).getId(), accountNum, 0, count);
					am.createAccount(account);
					count ++;
					am.getAccount(this.log).setSize(count);
					System.out.println("[ 개설 완료 ]");
				}
			} else {
				System.out.println("[ 개설 가능 계좌갯수 초과 ]");
			}
		} else {
			System.out.println("[ 로그인 후 이용가능한 서비스입니다. ]");
		}
	}
	
	private void printAccountNumber() {
		ArrayList<User> userList = um.getUserList();
		ArrayList<Account> accountList = am.getList();
		for(int i=0; i<accountList.size(); i++) {
			System.out.printf("[%d] %s\n", i+1, am.getAccount(this.log).getAcccountNum());
		}
	}
	
	// 계좌 철회 
	private void delete() {
		if(isLogged(this.log)) {
			printAccountNumber();
		}
		else {
			System.out.println("[ 로그인 후 이용가능한 서비스입니다. ]");
		}
	}
	

	public void run() {
		while (true) {
			System.out.println(this.log);
			printMainMenu();
			int select = inputNumber();
			if (select == JOIN) {
				join();
			} else if (select == LEAVE) {
				leave();
			} else if (select == CREATE) {
				create();
			}
		 else if(select == DELETE) {
			 delete();
		 }
			else if (select == LOG_IN) {
				login();
			} else if (select == QUIT)
				break;
		}
	}

}
