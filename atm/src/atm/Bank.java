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

	// ��ũ ������
	public Bank(String name) {
		this.name = name;
		this.log = -1;
		this.scan = new Scanner(System.in);
		um = new UserManager();
		am = new AccountManager();
	}

	private void printMainMenu() {
		System.out.println("--- " + this.name + " ATM ---");
		System.out.println("1. ȸ������");
		System.out.println("2. ȸ��Ż��");
		System.out.println("3. ���½�û");
		System.out.println("4. ����öȸ");
		System.out.println("5. �α���");
		System.out.println("0. ����");
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


	// ȸ������
	private void join() {
		System.out.print("id : ");
		String id = inputText();
		System.out.println("name : ");
		String name = inputText();
		System.out.print("password : ");
		String password = inputText();

		User user = new User(id, password, name);
		if (this.um.addUser(user) != null) {
			System.out.println("[ ȸ������ ���� ]");
			System.out.printf("[ %s�� ȯ���մϴ�. ]\n", id);
		} else {
			System.out.println("[ �ߺ��� ���̵� �����մϴ�. ]");
		}
	}

	// �α� üũ 
	private boolean isLogged(int log) {
		if (log != -1) {
			return true;
		}
		return false;
	}

	// ȸ��Ż��
	private void leave() {
		if (isLogged(this.log)) {
			System.out.print("password : ");
			String password = inputText();
			if (this.um.isCheckPassword(password, log)) {
				this.um.deleteUser(this.log);
				this.log = -1;
				System.out.println("[ ȸ��Ż�� �Ϸ� ]");

			} else {
				System.out.println("[ ��й�ȣ ��Ȯ�� ]");
			}
		} else {
			System.out.println("[ �α��� �� �̿밡���� �����Դϴ�. ]");
		}
	}

//
//	private int findIndex(String id) {
//		return this.um.indexOf(id);
//	}
//
//	
	// �α���
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
				System.out.printf("[ %s �� �α��� ���� ]\n", id);				
			}
			else {
				System.out.println("[ ��ġ�ϴ� ������ �����ϴ�. ]");
			}
		} else {
			System.out.println("[ �̹� �α��� �Ǿ����ϴ�. ]");
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

	// ���°���
	private void createAccount() {
		if (isLogged(this.log)) {
			User user = this.um.getUser(this.log);
			String id = user.getId();
			if(user.getAccountSize() < Account.LIMIT) {
				Account account = this.am.createAccount(new Account(id));
				this.um.setUser(user, account);
				this.um.printAccount(user, account, this.log);
//				am.printAccountNumber(this.log);
				
				System.out.println("[ ���»��� �Ϸ� ]");
			}
			else {
				System.out.println("[ ���� ������ ���� �ʰ� ]");
			}
		}
		else {
			System.out.println("[ �α��� �� �̿밡���� �����Դϴ�. ]");
		}
	}


	// ���� öȸ
	private void delete() {
		if (isLogged(this.log)) {
			User user = this.um.getUser(this.log);
			Account account = this.am.getAccount(this.log);
			this.um.printAccount(user, account, this.log);
			System.out.print("öȸ�� ���¹�ȣ : ");
			int delAccount = inputNumber() -1;
			if(am.isExsist(delAccount)) {
				System.out.println(am.getAccount(delAccount).getAcccountNum());
				am.deleteAccount(delAccount);
				am.setAccount(delAccount, account);
				System.out.println("[ ���� öȸ �Ϸ� ]");
			}
			else {
				System.out.println("[ �ٽ� Ȯ�����ּ���. ]");
			}
		} else {
			System.out.println("[ �α��� �� �̿밡���� �����Դϴ�. ]");
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
