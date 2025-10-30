package banking04;

abstract public class Account {

	String account;
	String name;
	int balance;
	double interestRate;
	
	public Account(String account, String name, int balance,
					double interestRate) {
		this.account = account;
		this.name = name;
		this.balance = balance;
		this.interestRate = (interestRate / 100.0);
	}

	public void showAccInfo() {
		System.out.println("계좌번호>"+ account);
		System.out.println("고객이름>"+ name);
		System.out.println("잔고>"+ balance);
		System.out.println("기본이자>" + (int)(interestRate * 100) + "%");
	}

	public abstract void depositMoney(int amount);
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof Account)) return false;
		Account acc = (Account) obj;
		return account != null && account.equals(acc.account);
	}
	
	@Override
	public int hashCode() {
		return account.hashCode();
	}
}
