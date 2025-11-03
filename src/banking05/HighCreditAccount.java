package banking05;

//신용계좌 : 기본 이자 + 신용등급별 추가 이자율 적용
public class HighCreditAccount extends Account {
	
	char creditGrade;
	double additionalinterestRate;

	//계좌정보 출력(추가 이자율 + 등급 표시)
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.print("추가이자율> "+(int)(additionalinterestRate) + "%");
		System.out.println(", 신용등급> "+ creditGrade);
	}
	
	public HighCreditAccount(String account, String name, int balance,
						double interestRate, double additionalinterestRate, 
						char creditGrade) {
		super(account, name, balance, interestRate);
		this.creditGrade = creditGrade;
		
		//등급별 추가 이자율 설정
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

	//입금 시 기본이자 + 추가이자 모두 적용
	public void depositMoney(int amount) {
		int interest = (int)(balance * interestRate);
		int additionalinterest = (int)(balance * additionalinterestRate / 100);
		balance += interest + additionalinterest + amount;
		
		System.out.println("입금이 완료되었습니다.");
	}
	// 삭제는 AccountManager에서 처리하므로 내용 없음
	@Override
	public void deleteAccount() {
		
	}
}
