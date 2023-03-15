package atm;

import java.util.Scanner;

public class Bank {

	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOG_IN = 3;
	private final int LOG_OUT = 4;
	private final int BANKING = 5;
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
		System.out.println("=============================");
		System.out.println("--- " + this.name + " ATM ---");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 로그인");
		System.out.println("4. 로그아웃");
		System.out.println("5. 뱅킹 서비스");
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
		if (!isLogged(this.log)) {
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
		} else {
			System.out.println("[ 로그아웃 후 이용가능합니다. ]");
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

	// 로그인
	private void logIn() {
		if (!isLogged(this.log)) {
			System.out.print("id : ");
			String id = inputText();
			System.out.print("password : ");
			String password = inputText();

			int index = -1;
			if (this.um.checkLogInfo(id, password)) {
				index = um.indexOf(id);
				log = index;
				System.out.printf("[ %s 님 로그인 성공 ]\n", id);
			} else {
				System.out.println("[ 일치하는 정보가 없습니다. ]");
			}
		} else {
			System.out.println("[ 이미 로그인 되었습니다. ]");
		}
	}

	// 로그아웃
	private void logOut() {
		if (isLogged(this.log)) {
			this.log = -1;
			System.out.println("[ 로그아웃 완료 ]");
		} else {
			System.out.println("[ 로그인 먼저 해주세요. ]");
		}
	}

	// 계좌개설
	private void createAccount() {
		if (isLogged(this.log)) {
			User user = this.um.getUser(this.log);
			String id = user.getId();

			if (user.getAccountSize() < Account.LIMIT) {
				Account account = this.am.createAccount(new Account(id));
				this.um.setUser(user, account, Account.ADD);
				System.out.println("----- 보유 계좌 목록 ------");
				this.um.printAccount(user, account, this.log);
				System.out.println("[ 계좌생성 완료 ]");
			} else {
				System.out.println("[ 개설 가능한 갯수 초과 ]");
			}
		} else {
			System.out.println("[ 로그인 후 이용가능한 서비스입니다. ]");
		}
	}

	// 계좌 철회
	private void deleteAccount() {
		if (isLogged(this.log)) {
			User user = this.um.getUser(this.log);
			Account account = this.am.getAccount(this.log);
			this.um.printAccount(user, account, this.log);

			System.out.print("철회할 계좌번호 : ");
			int delAccount = inputNumber() - 1;

			if (am.isExsist(delAccount)) {
				System.out.printf("[ %s ]\n", am.getAccount(delAccount).getAccountNum());
				Account delAcc = user.getAccount(delAccount);
				am.deleteAccount(delAcc);
				this.um.setUser(user, delAcc, Account.DELETE);

				System.out.println("[ 계좌 철회 완료 ]");
			} else {
				System.out.println("[ 다시 확인해주세요. ]");
			}
		} else {
			System.out.println("[ 로그인 후 이용가능한 서비스입니다. ]");
		}
	}

	// 메인 런
	public void run() {
		while (true) {
			System.out.printf("[ log : %d ]\n", this.log);
			printMainMenu();
			int select = inputNumber();
			if (select == JOIN) {
				join();
			} else if (select == LEAVE) {
				leave();
			} else if (select == LOG_IN) {
				logIn();
			} else if (select == LOG_OUT) {
				logOut();
			} else if (select == BANKING) {
				bankingRun();
			} else if (select == QUIT)
				break;
		}
	}

	private void addMoney() {
		am.printAccountNumber(this.log, Account.ALL);

		System.out.print("입금할 계좌 :");
		int pickAccount = inputNumber() - 1;

		if (am.isExsist(pickAccount)) {
			am.printAccountNumber(pickAccount, Account.ONE);
			System.out.print("[ 입금진행 yes : 1 / no : 2]");
			int select = inputNumber();
			if (select == 1) {
				System.out.print("입금할 금액 : ");
				int inputMoney = inputNumber();

				if (inputMoney > 0) {
					am.setMoney(pickAccount, inputMoney);
					Account account = am.getAccount(pickAccount);

					System.out.println("[ 입금 완료 ]");
					System.out.printf("[ 현재잔액 : %d원 ]\n", account.getMoney());
				} else {
					System.out.println("[ 0원 이상 입금 가능  ]");
				}
			} else {
				System.out.println("[ 입금 취소 ]");
			}
		}

		else {
			System.out.println("[ 계좌를 다시 확인해주세요 ]");
		}
	}

	// 뱅킹서비스 메뉴
	private void printBankingMenu() {
		System.out.println();
		System.out.println("--- BANKING SERVICE ---");
		System.out.println("1. 계좌신청");
		System.out.println("2. 계좌철회");
		System.out.println("3. 입금");
		System.out.println("4. 출금");
		System.out.println("5. 조회");
		System.out.println("6. 이체");
		System.out.println("7. 파일");
		System.out.println("0. 뒤로가기");
		System.out.println("----------------------");
		System.out.print("메뉴 : ");
	}

	// 뱅킹서비스 런
	public void bankingRun() {
		while (true) {
			if (isLogged(this.log)) {
				printBankingMenu();
				int select = inputNumber();
				if (select == 1)
					createAccount();
				else if (select == 2)
					deleteAccount();
				else if (select == 3)
					addMoney();
//		else if(select == 4) outMoney();
//		else if(select == 5) accountInfo();
//		else if(select == 6) sendMoney();
//		else if(select == 7) file();
				else if (select == 0)
					break;
			} else {
				System.out.println("[ 로그인 후 이용가능한 메뉴입니다. ]");
				break;
			}
		}
	}

}
