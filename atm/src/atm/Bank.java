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

	// 뱅크 생성자
	public Bank(String name) {
		this.name = name;
		this.log = -1;
		this.scan = new Scanner(System.in);
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


	// 회원가입
	private void join() {
		System.out.print("id : ");
		String id = inputText();
		System.out.println("name : ");
		String name = inputText();
		System.out.print("password : ");
		String password = inputText();

		User user = new User(id, password, name);
		if (this.um.addUser(user) != null) {
			System.out.println("[ 회원가입 성공 ]");
			System.out.printf("[ %s님 환영합니다. ]\n", id);
		} else {
			System.out.println("[ 중복된 아이디가 존재합니다. ]");
		}
	}

	// 로그 체크 
	private boolean isLogged(int log) {
		if (log != -1) {
			return true;
		}
		return false;
	}

	// 회원탈퇴
	private void leave() {
		if (isLogged(this.log)) {
			System.out.print("password : ");
			String password = inputText();
			if (this.um.isCheckPassword(password, log)) {
				this.um.deleteUser(this.log);
				this.log = -1;
				System.out.println("[ 회원탈퇴 완료 ]");

			} else {
				System.out.println("[ 비밀번호 재확인 ]");
			}
		} else {
			System.out.println("[ 로그인 후 이용가능한 서비스입니다. ]");
		}
	}

//
//	private int findIndex(String id) {
//		return this.um.indexOf(id);
//	}
//
//	
	// 로그인
	private void login() {
		if (!isLogged(this.log)) {
			System.out.print("id : ");
			String id = inputText();
			System.out.print("password : ");
			String password = inputText();

			int index = -1;
			if(this.um.checkLogInfo(id, password)) {
				index = um.indexOf(id);
				log =index;
				System.out.printf("[ %s 님 로그인 성공 ]\n", id);				
			}
			else {
				System.out.println("[ 일치하는 정보가 없습니다. ]");
			}
		} else {
			System.out.println("[ 이미 로그인 되었습니다. ]");
		}
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
	private void createAccount() {
		if (isLogged(this.log)) {
			User user = this.um.getUser(this.log);
			String id = user.getId();
			if(user.getAccountSize() < Account.LIMIT) {
				Account account = this.am.createAccount(new Account(id));
				this.um.setUser(user, account);
				this.um.printAccount(user, account, this.log);
//				am.printAccountNumber(this.log);
				
				System.out.println("[ 계좌생성 완료 ]");
			}
			else {
				System.out.println("[ 개설 가능한 갯수 초과 ]");
			}
		}
		else {
			System.out.println("[ 로그인 후 이용가능한 서비스입니다. ]");
		}
	}


	// 계좌 철회
	private void delete() {
		if (isLogged(this.log)) {
			User user = this.um.getUser(this.log);
			Account account = this.am.getAccount(this.log);
			this.um.printAccount(user, account, this.log);
			System.out.print("철회할 계좌번호 : ");
			int delAccount = inputNumber() -1;
			if(am.isExsist(delAccount)) {
				System.out.println(am.getAccount(delAccount).getAcccountNum());
				am.deleteAccount(delAccount);
				am.setAccount(delAccount, account);
				System.out.println("[ 계좌 철회 완료 ]");
			}
			else {
				System.out.println("[ 다시 확인해주세요. ]");
			}
		} else {
			System.out.println("[ 로그인 후 이용가능한 서비스입니다. ]");
		}
	}

	public void run() {
		while (true) {
			System.out.printf("[ log : %d ]\n",this.log);
			printMainMenu();
			int select = inputNumber();
			if (select == JOIN) {
				join();
			} else if (select == LEAVE) {
				leave();
			}
				else if (select == CREATE) {
					createAccount();
			}
				else if (select == DELETE) {
				delete();
			}
				else if (select == LOG_IN) {
				login();
			} else if (select == QUIT)
				break;
		}
	}

}
