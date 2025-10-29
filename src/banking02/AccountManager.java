package banking02;

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
		System.out.println("-----계좌선택-----");
		Scanner scan = new Scanner(System.in);
		System.out.println("1. 보통계좌");
		System.out.println("2. 신용신뢰계좌");
		System.out.print("선택:");
		
		int choice = scan.nextInt();
		scan.nextLine();
		
		if(choice == 1) {
			System.out.print("계좌번호:");Account = scan.nextLine();
			System.out.print("고객이름:");name = scan.nextLine();
			System.out.print("잔고:");balance = scan.nextInt();
			System.out.print("기본이자%(정수형태로입력): ");
			int interestRate = scan.nextInt();
			scan.nextLine();
			
			Account newAccount = new Account(Account, name, balance,
											interestRate);
			accounts[numOfAccounts++] = newAccount;
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
		}
		
		System.out.println("계좌개설이 완료되었습니다.");
	}
	
	public void depositMoney() {
		System.out.println("***입   금***");
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		
		System.out.print("계좌번호: ");
		String Account = scan.nextLine();
		
		System.out.print("입금액: ");
		int amount = scan.nextInt();
		
		for(int i=0 ; i<numOfAccounts ; i++) {
			if(Account.equals(accounts[i].account)) {
				if(accounts[i] instanceof HighCreditAccount) {
					((HighCreditAccount) accounts[i]).depositMoney(amount);
				}
				else if (accounts[i] instanceof NormalAccount) {
					((NormalAccount) accounts[i]).depositMoney(amount);
				}
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
		
		for(int i=0 ; i<numOfAccounts ; i++) {
			if(amount > 0 && amount <= accounts[i].balance) {
				accounts[i].balance -= amount;
			}
			else {
				System.out.println("잔액이 부족합니다.");
			}	
		}
		
		System.out.println("출금이 완료되었습니다.");
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
