package banking03;

public class HighCreditAccount extends Account {
	
	char creditGrade;
	double additionalinterestRate;

	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("신용등급> "+ creditGrade);
	}
	
	public HighCreditAccount(String account, String name, int balance,
						double interestRate, double additionalinterestRate, 
						char creditGrade) {
		super(account, name, balance, interestRate);
		this.creditGrade = creditGrade;
		
		if(creditGrade == 'A') {
			this.additionalinterestRate = ICustomDefine.A;
		}
		else if (creditGrade == 'B') {
			this.additionalinterestRate= ICustomDefine.B;
		}
		else if (creditGrade == 'C') {
			this.additionalinterestRate= ICustomDefine.C;
		}
		else {
			this.additionalinterestRate = 0.0;
		}
	}

	public void depositMoney(int amount) {
		int interest = (int)(balance * interestRate);
		int additionalinterest = (int)(balance * additionalinterestRate);
		balance += interest + additionalinterest + amount;
		
		System.out.println("입금이 완료되었습니다.");
	}
}
