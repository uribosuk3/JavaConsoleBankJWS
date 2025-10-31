package banking04;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {

	public static Scanner scan = new Scanner(System.in);
	
	public static void menuShow() {
		System.out.println("------------------Menu------------------");
		System.out.print("1.계좌개설, ");
		System.out.print("2.입  금, ");
		System.out.print("3.출  금, ");
		System.out.println("4.계좌정보출력 ");
		System.out.print("5.계좌정보삭제, ");
		System.out.println("6.프로그램종료");
		System.out.println("----------------------------------------");
		System.out.print("선택:");
	}
	
	public static void main(String[] args) {
		AccountManager handler = new AccountManager();
				
		while(true) {
			menuShow();
			try {
				int choice = scan.nextInt();
				scan.nextLine();
				
				if(choice < ICustomDefine.MAKE || choice > ICustomDefine.EXIT) {
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
				case ICustomDefine.delete :
					handler.deleteAccount();
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

