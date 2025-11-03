package banking05;

//보통계좌 : 기본 이자율만 적용되는 계좌
public class NormalAccount extends Account {

	//부모 생성자 호출로 기본 정보 초기화
	public NormalAccount(String account, String name, int balance,
						double interestRate) {
		super(account, name, balance, interestRate);
	}
	
	//입금 시 기본이자 적용
	public void depositMoney(int amount) {
		int interest = (int)(balance * interestRate);
		balance += interest + amount;
		System.out.println("입금이 완료되었습니다.");
	}
	// 삭제는 AccountManager에서 처리하므로 내용 없음
	@Override
	public void deleteAccount() {
	}
}
