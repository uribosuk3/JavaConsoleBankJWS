package banking03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {

	Account[] accounts;
	int numOfAccounts;
 
	public AccountManager() {
	    accounts = new Account[50];
	    numOfAccounts = 0;
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
		String newAccountNum;
		
		if(choice == 1) {
			System.out.print("계좌번호:");Account = scan.nextLine();
			System.out.print("고객이름:");name = scan.nextLine();
			System.out.print("잔고:");balance = scan.nextInt();
			System.out.print("기본이자%(정수형태로입력): ");
			int interestRate = scan.nextInt();
			scan.nextLine();
			
			Account newAccount = new NormalAccount(Account, name, balance,
											interestRate);
			accounts[numOfAccounts++] = newAccount;
			System.out.println("계좌개설이 완료되었습니다.");
			return;
		}
		else if(choice == 2) {
			System.out.print("계좌번호:");Account = scan.nextLine();
			System.out.print("고객이름:");name = scan.nextLine();
			System.out.print("잔고:");balance = scan.nextInt();
			System.out.print("기본이자%(정수형태로입력): ");
			int interestRate = scan.nextInt();
			scan.nextLine();
			System.out.print("신용등급(A/B/C): ");
			char creditGrade = scan.nextLine().charAt(0);
			
			HighCreditAccount newHAccount = new HighCreditAccount
					(Account, name, balance, interestRate, 0.0, creditGrade);
			accounts[numOfAccounts++] = newHAccount;
			
			System.out.println("계좌개설이 완료되었습니다.");
			return;
		} 
		else {
			System.out.println("예외발생됨.");
			continue;
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
		
		boolean found = false;
		for(int i=0 ; i<numOfAccounts; i++) {
			if(Account.equals(accounts[i].account)) {
				found = true;				
				accounts[i].depositMoney(amount);
				break;
			}
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
			boolean found = false;
		    for (int i = 0; i < numOfAccounts; i++) {
		        if (accounts[i].account.equals(Account)) {
		            found = true;
		            
			if(amount > balance) {
				System.out.println("잔고가 부족합니다 금액 전체를 출금할까요? (y or n)");
				String answer = sc.nextLine();
				
				if(answer.equalsIgnoreCase("y")) {
					System.out.println("출금이 완료되었습니다.");
					accounts[i].balance = 0;
				}
				else if (answer.equalsIgnoreCase("n")) {
					System.out.println("출금 요청이 취소되었습니다.");
				}
				else {
					System.out.println("에러발생됨.");
				}
			}
		        }
				return;
			}
		}
		       	catch(InputMismatchException e) {
				System.out.println("숫자를 입력해야 합니다.");
				scan.nextLine();
				return;
		}
		  
			for(int i=0 ; i<numOfAccounts ; i++) {
				if(amount > 0 && amount <= accounts[i].balance) {
					accounts[i].balance -= amount;
				}
				else {
					System.out.println("잔액이 부족합니다.");
				}	
			}
	}
		
	
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		Scanner scan = new Scanner(System.in);
		
		for(int i=0 ; i<numOfAccounts ; i++) {
			System.out.println("-----------------");
			System.out.println("계좌번호>" + accounts[i].account);
	        System.out.println("고객이름>" + accounts[i].name);
	        System.out.println("잔고>" + accounts[i].balance);
	        System.out.println("기본이자>" + (int)(accounts[i].interestRate * 100) + "%");
		
		
		if(accounts[i] instanceof HighCreditAccount) {
			HighCreditAccount hca = (HighCreditAccount)accounts[i];
			System.out.println("신용등급>" + hca.creditGrade);
		}
		
		System.out.println("-----------------");
	}
		System.out.println("전체계좌정보가 출력이 완료되었습니다");
	}
}
