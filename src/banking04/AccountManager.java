package banking04;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class AccountManager implements ICustomDefine {

	private Set<Account> accounts = new HashSet<>();
	private void handleDuplicateAccount(banking04.Account newAccount, Scanner scan) {
	}
	
	String Account, name;
	int balance;
	double interestRate;
	
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
				
				if(choice != 1&& choice !=2) {
					System.out.println("잘못된 메뉴입니다. 1 또는 2를 입력하세요.\n");
		            continue;
		        }
				
				System.out.print("계좌번호:"); String Account = scan.nextLine();
				System.out.print("고객이름:"); String name = scan.nextLine();
				System.out.print("잔고:"); int balance = scan.nextInt();
				System.out.print("기본이자%(정수형태로입력): ");
				double interestRate = scan.nextInt();
				scan.nextLine();
				
			if(choice == 1) {
				Account newAccount = new NormalAccount(Account, name, balance,
													interestRate);
				handleDuplicateAccount(newAccount, scan);
				if(!accounts.add(newAccount)) {
					System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
					String answer = scan.nextLine();
						
					if (answer.equalsIgnoreCase("y")) {
						accounts.remove(newAccount);
						accounts.add(newAccount);
						System.out.println("기존 계좌를 덮어썼습니다.");
						System.out.println("계좌개설의 완료되었습니다.");
					}
					else if (answer.equalsIgnoreCase("n")) {
						System.out.println("신규 등록이 취소되었습니다.");
					}
					else if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
						System.out.println("y 혹은 n으로 입력하세요.");
					}
				}	else {
					System.out.println("계좌개설이 완료되었습니다.");
					}
				break;
			}		
				else if(choice == 2) {
					System.out.print("신용등급(A/B/C): ");
					char creditGrade = scan.nextLine().charAt(0);
					
					HighCreditAccount newHAccount = new HighCreditAccount
							(Account, name, balance, interestRate, 0.0, creditGrade);
					handleDuplicateAccount(newHAccount, scan);
					if(!accounts.add(newHAccount)) {
						System.out.println("중복계좌발견됨. 덮어쓸까요?(y or n)");
						String answer = scan.nextLine();
							
						if (answer.equalsIgnoreCase("y")) {
							accounts.remove(newHAccount);
							accounts.add(newHAccount);
							System.out.println("기존 계좌를 덮어썼습니다.");
							System.out.println("계좌개설의 완료되었습니다.");
						}
						else if (answer.equalsIgnoreCase("n")) {
							System.out.println("신규 등록이 취소되었습니다.");
						}
						else if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
							System.out.println("y 혹은 n으로 입력하세요.");
						}
					}	else {
							System.out.println("계좌개설이 완료되었습니다.");
					
						}
					break;
				} 
			}
	}
		
	public void depositMoney() {
		System.out.println("***입   금***");
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		
		System.out.print("계좌번호: ");
		String Account = scan.nextLine();
		
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
		}
		 	catch(InputMismatchException e) {
				System.out.println("숫자를 입력해야 합니다.");
				scan.nextLine();
				return;
			}
		//챗지피티 활용
		 Account temp = new NormalAccount(Account, "", 0, 0);

		    if (accounts.contains(temp)) { 
		        Account target = null;
		        for (Account acc : accounts) {
		            if (acc.equals(temp)) {
		                target = acc;
		                break;
		            }
		        }

		        if (target != null) {
		            accounts.remove(target); 
		            target.depositMoney(amount);
		            accounts.add(target);
		        }
		    } else {
		        System.out.println("해당 계좌가 존재하지 않습니다.");
		
		    }
	}
	
	public void withdrawMoney() {
		System.out.println("***출   금***");
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호: ");
		String Account = scan.nextLine();
		
		System.out.print("출금액: ");
		int amount = scan.nextInt();
		scan.nextLine();
		Scanner sc = new Scanner(System.in);
		
		try {
			if(amount <= 0) {
				System.out.println("음수는 출금불가");
			}	
			if(amount % 1000 != 0) {
				System.out.println("1000원 단위로만 출금가능함");
			}
					if(amount > balance) {
						System.out.println("잔고가 부족합니다 금액 전체를 출금할까요? (y or n)");
						String answer = sc.nextLine();
						
						if(answer.equalsIgnoreCase("y")) {
							System.out.println("잔액 전체 출금이 완료");
							balance = 0;
						}
						else if (answer.equalsIgnoreCase("n")) {
							System.out.println("출금 요청이 취소되었습니다.");
						}
						else {
							System.out.println("에러발생됨.");
						}
					}	else {
							System.out.println("잔액이 부족합니다.");
						}	
		}	catch(InputMismatchException e) {
				System.out.println("숫자를 입력해야 합니다.");
				scan.nextLine();
				return;
			}	
	}
		
	public void showAccInfo() {
	    for(Account acc : accounts) {
	    	acc.showAccInfo();
		System.out.println("-----------------");
		System.out.println("전체계좌정보가 출력이 완료되었습니다");
		}
	}
	
	
}

