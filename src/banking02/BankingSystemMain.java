package banking02;

import java.util.Scanner;

public class BankingSystemMain {

	public static void menuShow() {
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입  금");
		System.out.println("3.출  금");
		System.out.println("4.계좌정보출력 ");
		System.out.println("5.프로그램종료");
		System.out.print("선택:");
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		AccountManager handler = new AccountManager();
				
		while(true) {
			menuShow();
			int choice = scan.nextInt();
			scan.nextLine();
			switch(choice) {
			case 1:
				handler.makeAccount();
				break;
			case 2:
				handler.depositMoney();
				break;
			case 3:
				handler.withdrawMoney();
				break;
			case 4:
				handler.showAccInfo();
				break;
			case 5:
				System.out.println("프로그램종료");
				return;
		}
	}
	}
}

