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

	// ��ũ ������
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
		System.out.println("1. ȸ������");
		System.out.println("2. ȸ��Ż��");
		System.out.println("3. �α���");
		System.out.println("4. �α׾ƿ�");
		System.out.println("5. ��ŷ ����");
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
		if (!isLogged(this.log)) {
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
		} else {
			System.out.println("[ �α׾ƿ� �� �̿밡���մϴ�. ]");
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

	// �α���
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
				System.out.printf("[ %s �� �α��� ���� ]\n", id);
			} else {
				System.out.println("[ ��ġ�ϴ� ������ �����ϴ�. ]");
			}
		} else {
			System.out.println("[ �̹� �α��� �Ǿ����ϴ�. ]");
		}
	}

	// �α׾ƿ�
	private void logOut() {
		if (isLogged(this.log)) {
			this.log = -1;
			System.out.println("[ �α׾ƿ� �Ϸ� ]");
		} else {
			System.out.println("[ �α��� ���� ���ּ���. ]");
		}
	}

	// ���°���
	private void createAccount() {
		if (isLogged(this.log)) {
			User user = this.um.getUser(this.log);
			String id = user.getId();

			if (user.getAccountSize() < Account.LIMIT) {
				Account account = this.am.createAccount(new Account(id));
				this.um.setUser(user, account, Account.ADD);
				System.out.println("----- ���� ���� ��� ------");
				this.um.printAccount(user, account, this.log);
				System.out.println("[ ���»��� �Ϸ� ]");
			} else {
				System.out.println("[ ���� ������ ���� �ʰ� ]");
			}
		} else {
			System.out.println("[ �α��� �� �̿밡���� �����Դϴ�. ]");
		}
	}

	// ���� öȸ
	private void deleteAccount() {
		if (isLogged(this.log)) {
			User user = this.um.getUser(this.log);
			Account account = this.am.getAccount(this.log);
			this.um.printAccount(user, account, this.log);

			System.out.print("öȸ�� ���¹�ȣ : ");
			int delAccount = inputNumber() - 1;

			if (am.isExsist(delAccount)) {
				System.out.printf("[ %s ]\n", am.getAccount(delAccount).getAccountNum());
				Account delAcc = user.getAccount(delAccount);
				am.deleteAccount(delAcc);
				this.um.setUser(user, delAcc, Account.DELETE);

				System.out.println("[ ���� öȸ �Ϸ� ]");
			} else {
				System.out.println("[ �ٽ� Ȯ�����ּ���. ]");
			}
		} else {
			System.out.println("[ �α��� �� �̿밡���� �����Դϴ�. ]");
		}
	}

	// ���� ��
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

		System.out.print("�Ա��� ���� :");
		int pickAccount = inputNumber() - 1;

		if (am.isExsist(pickAccount)) {
			am.printAccountNumber(pickAccount, Account.ONE);
			System.out.print("[ �Ա����� yes : 1 / no : 2]");
			int select = inputNumber();
			if (select == 1) {
				System.out.print("�Ա��� �ݾ� : ");
				int inputMoney = inputNumber();

				if (inputMoney > 0) {
					am.setMoney(pickAccount, inputMoney);
					Account account = am.getAccount(pickAccount);

					System.out.println("[ �Ա� �Ϸ� ]");
					System.out.printf("[ �����ܾ� : %d�� ]\n", account.getMoney());
				} else {
					System.out.println("[ 0�� �̻� �Ա� ����  ]");
				}
			} else {
				System.out.println("[ �Ա� ��� ]");
			}
		}

		else {
			System.out.println("[ ���¸� �ٽ� Ȯ�����ּ��� ]");
		}
	}

	// ��ŷ���� �޴�
	private void printBankingMenu() {
		System.out.println();
		System.out.println("--- BANKING SERVICE ---");
		System.out.println("1. ���½�û");
		System.out.println("2. ����öȸ");
		System.out.println("3. �Ա�");
		System.out.println("4. ���");
		System.out.println("5. ��ȸ");
		System.out.println("6. ��ü");
		System.out.println("7. ����");
		System.out.println("0. �ڷΰ���");
		System.out.println("----------------------");
		System.out.print("�޴� : ");
	}

	// ��ŷ���� ��
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
				System.out.println("[ �α��� �� �̿밡���� �޴��Դϴ�. ]");
				break;
			}
		}
	}

}
