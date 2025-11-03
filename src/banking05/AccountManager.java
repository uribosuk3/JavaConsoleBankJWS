package banking05;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/*
계좌를 관리하는 핵심 클래스
계좌 생성, 입금, 출금, 조회, 삭제 등 모든 기능 담당
HashSet을 이용해 중복 계좌를 막는다.
 */
public class AccountManager implements ICustomDefine {

	// HashSet을 이용해 중복 계좌 방지 (계좌번호가 같으면 추가되지 않음)
	private Set<Account> accounts = new HashSet<>();

	/*
	- 계좌개설
	사용자가 보통계좌 or 신용계좌 중 하나를 선택하고 새 계좌 개설
	 */
	public void makeAccount() {
		System.out.println("***신규계좌개설***");
		while (true) {
			System.out.println("-----계좌선택-----");
			Scanner scan = new Scanner(System.in);
			System.out.println("1. 보통계좌");
			System.out.println("2. 신용신뢰계좌");
			System.out.print("선택:");

			int choice = scan.nextInt();
			scan.nextLine();

			// 입력이 잘못된 경우 안내 후 반복
			if (choice != 1 && choice != 2) {
				System.out.println("잘못된 입력입니다. 1 또는 2를 입력하세요.\n");
			}

			System.out.print("계좌번호:");
			String Account = scan.nextLine();
			System.out.print("고객이름:");
			String name = scan.nextLine();
			System.out.print("잔고:");
			int balance = scan.nextInt();
			System.out.print("기본이자%(정수형태로입력): ");
			double interestRate = scan.nextInt();
			scan.nextLine();

			// 보통계좌 개설
			if (choice == 1) {
				Account newAccount = new NormalAccount(Account, name, balance, interestRate);

				// 이미 같은 계좌가 있으면 add 실패 -> 중복 확인
				if (!accounts.add(newAccount)) {
					System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
					String answer = scan.nextLine();

					if (answer.equalsIgnoreCase("y")) {
						// 기존 계좌 삭제
						accounts.remove(newAccount);
						// 새 계좌로 덮어쓰기
						accounts.add(newAccount);
						System.out.println("기존 계좌를 덮어썼습니다.");
						System.out.println("계좌개설의 완료되었습니다.");
					} else if (answer.equalsIgnoreCase("n")) {
						System.out.println("신규 등록이 취소되었습니다.");
					} else if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
						System.out.println("y 혹은 n으로 입력하세요.");
					}
				} 
				else {
					System.out.println("계좌개설이 완료되었습니다.");
				}
				break;
			}

			// 신용계좌 개설
			else if (choice == 2) {
				System.out.print("신용등급(A/B/C): ");
				char creditGrade = scan.nextLine().charAt(0);

				HighCreditAccount newHAccount = new HighCreditAccount(Account, name, balance, interestRate, 0.0,
						creditGrade);
				if (!accounts.add(newHAccount)) {
					System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
					String answer = scan.nextLine();

					if (answer.equalsIgnoreCase("y")) {
						accounts.remove(newHAccount);
						accounts.add(newHAccount);
						System.out.println("기존 계좌를 덮어썼습니다.");
						System.out.println("계좌개설의 완료되었습니다.");
					} else if (answer.equalsIgnoreCase("n")) {
						System.out.println("신규 등록이 취소되었습니다.");
					} else if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
						System.out.println("y 혹은 n으로 입력하세요.");
					}
				}
			} 	
				else {
					System.out.println("계좌개설이 완료되었습니다.");

				}
				break;
		}
	}

	/*
	- 입금
	계좌번호를 찾아 입금 처리 + 이자 적용
	 */
	public void depositMoney() {
		System.out.println("***입   금***");
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호와 입금할 금액을 입력하세요");

		System.out.print("계좌번호: ");
		String accountNumber = scan.nextLine();

		System.out.print("입금액: ");
		int amount = scan.nextInt();
		try {
			if (amount <= 0) {
				System.out.println("음수는 입금불가");
				return;
			}
			if (amount % 500 != 0) {
				System.out.println("500원 단위로 입금가능함");
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("숫자를 입력해야 합니다.");
			scan.nextLine();
			return;
		}
		// 확장for문 이용해서 Set 안의 계좌를 찾은 후 입금처리 시도
		for (Account acc : accounts) {
			// 입금할 계좌 찾기
			if (accountNumber.equals(acc.accountNumber)) {
				// 입금처리
				acc.depositMoney(amount);
			}
			System.out.println("입금이 완료되었습니다.");
		}
	}

	/*
	- 출금
	출금할 계좌를 찾아 금액 차감
	 */
	public void withdrawMoney() {
		System.out.println("***출   금***");
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호: ");
		String accountNumber = scan.nextLine();

		System.out.print("출금액: ");
		int amount = scan.nextInt();
		scan.nextLine();
		Scanner sc = new Scanner(System.in);

		try {
			if (amount <= 0) {
				System.out.println("음수는 출금불가");
				return;// 해당 함수의 실행종료
			}
			if (amount % 1000 != 0) {
				System.out.println("1000원 단위로만 출금가능함");
				return;// 해당 함수의 실행종료
			}

			// 반복문을 통해서 Set컬렉션에 저장된 인스턴스와 비교해야함.
			for (Account acc : accounts) {
				// 출금할 계좌 찾기
				if (accountNumber.equals(acc.accountNumber)) {
					// 잔고가 충분한지 확인
					if (acc.balance >= amount) {
						// 출금처리
						acc.balance -= amount;
					} 
					else {
						System.out.println("잔액이 부족합니다. 금액 전체를 출금할까요? (y or n)");
						String answer = scan.nextLine();

						if (answer.equalsIgnoreCase("y")) {
							acc.balance = 0;
						} 
						else {
							System.out.println("출금이 취소되었습니다.");
						}
					}
					System.out.println("출금이 완료되었습니다.");
					return;
				}
			}
			System.out.println("해당 계좌가 존재하지 않습니다.");
		} 
		catch (InputMismatchException e) {
			System.out.println("숫자를 입력해야 합니다.");
			scan.nextLine();
			return;
		}
	}

	// 전체 계좌정보 출력 기능
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for (Account acc : accounts) {
			System.out.println("----------------------------------------");
			acc.showAccInfo();
			System.out.println("----------------------------------------");
		}
		System.out.println("전체계좌정보가 출력이 완료되었습니다");
	}

	// 계좌 삭제 기능
	public void deleteAccount() {
		System.out.println("***계좌정보삭제***");
		System.out.println("삭제할 계좌번호를 입력하세요.");
		System.out.print("계좌번호 : ");
		Scanner scan = new Scanner(System.in);
		String deleteAccountNumber = scan.nextLine();

		boolean isdelete = false;
		for (Account acc : accounts) {
			if (deleteAccountNumber.equals(acc.accountNumber)) {
				accounts.remove(acc);
				System.out.println("계좌정보가 삭제되었습니다.");
				isdelete = true;
				break;
			}
		}
		if (isdelete == false)
			System.out.println("----------------------------------------");

	}
}
