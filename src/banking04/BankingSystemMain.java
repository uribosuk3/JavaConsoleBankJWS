package banking04;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {

	public static Scanner scan = new Scanner(System.in);
	
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
		//Scanner scan = new Scanner(System.in);
		AccountManager handler = new AccountManager();
				
		while(true) {
			menuShow();
			try {
				int choice = scan.nextInt();
				scan.nextLine();
				
				if(choice < 1 || choice > 5) {
					throw new MenuSelectException();
				}
				
				switch(choice) {
				case ICustomDefine.MAKE :
					handler.makeAccount();
					break;
				case ICustomDefine.DEPOSIT :
					handler.depositMoney();
					break;
				case ICustomDefine.WITHDRAW :
					handler.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE :
					handler.showAccInfo();
					break;
				case ICustomDefine.EXIT :
					System.out.println("프로그램종료");
					return;
				}	
			}
			catch (MenuSelectException e) {
		        System.out.println(e.getMessage());
		        System.out.println("메뉴는 1~5사이의 정수를 입력하세요.");
		    }
		}
	}
}

