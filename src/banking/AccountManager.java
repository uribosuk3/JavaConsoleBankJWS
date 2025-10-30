package banking;

import java.util.Scanner;

public class AccountManager {

	Account[] accounts;
	int numOfAccounts;
 
	public AccountManager() {
	    accounts = new Account[50];
	    numOfAccounts = 0;
}
	
	public void makeAccount(int choice) {
		System.out.println("***신규계좌개설***");
		Scanner scan = new Scanner(System.in);
		String Account, name;
		int balance;
		System.out.print("계좌번호:");Account = scan.nextLine();
		System.out.print("고객이름:");name = scan.nextLine();
		System.out.print("잔고:");balance = scan.nextInt();
		
		Account newAccount = new Account(Account, name, balance);
		accounts[numOfAccounts++] = newAccount;
		
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
				accounts[i].balance += amount;
			}
		}
		System.out.println("입금이 완료되었습니다.");
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
		System.out.println("------------------------");
		System.out.println("계좌번호 : " + accounts[i].account);
        System.out.println("고객이름 : " + accounts[i].name);
        System.out.println("잔고 : " + accounts[i].balance);
		}
		System.out.println("------------------------");
		System.out.println("전체계좌정보가 출력이 완료되었습니다");
	}
}

	

