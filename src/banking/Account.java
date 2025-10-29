package banking;

public class Account {

	String account;
	String name;
	int balance;
	
	public Account(String account, String name, int balance) {
		this.account = account;
		this.name = name;
		this.balance = balance;
	}

	public void showAccInfo() {
		System.out.println("계좌번호:"+ account);
		System.out.println("고객이름:"+ name);
		System.out.println("잔고:"+ balance);
	}

}
