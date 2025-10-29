package banking03;

public class NormalAccount extends Account {

	public NormalAccount(String account, String name, int balance,
						double interestRate) {
		super(account, name, balance, interestRate);
	}
	
	public void depositMoney(int amount) {
		int interest = (int)(balance * interestRate);
		balance += interest + amount;
		System.out.println("입금이 완료되었습니다.");
	}
}
