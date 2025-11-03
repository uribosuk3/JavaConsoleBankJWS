package banking05;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 프로그램의 시작점 (main 함수가 있는 클래스)
 메뉴를 보여주고, 사용자가 선택한 기능(계좌개설, 입금, 출금 등)을 실행하는 역할
 */
public class BankingSystemMain implements ICustomDefine {

	// 프로그램 전체에서 사용할 Scanner
	public static Scanner scan = new Scanner(System.in);
	
	//메뉴를 출력하는 메서드
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
	/*
	main() 메서드 : 프로그램 시작 지점
	무한 반복으로 메뉴를 보여주며 사용자가 입력한 번호에 따라 기능 수행
	 */
	public static void main(String[] args) {
		//계좌 관리 기능을 담당하는 객체 생성
		AccountManager handler = new AccountManager();
		
		//while문을 사용하여 프로그램이 종료될 때까지 반복(6번 종료를 선택할 때까지)
		while(true) {
			//메뉴 출력
			menuShow();
			try {
				int choice = scan.nextInt(); //메뉴 선택 입력
				scan.nextLine();
				
				//1~6사이의 숫자가 아니면 예외 발생시킴
				if(choice < ICustomDefine.MAKE || choice > ICustomDefine.EXIT) {
					throw new MenuSelectException();
				}
				
				//메뉴에 입력한 번호에 따라 각각의 기능 수행
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
				case ICustomDefine.Delete :
					handler.deleteAccount();
					break;
				case ICustomDefine.EXIT :
					System.out.println("프로그램종료");
					return;
				}	
			}
			//메뉴 번호 잘못 입력 시 예외 처리
			catch (MenuSelectException e) {
		        System.out.println(e.getMessage());
		        System.out.println("메뉴는 1~6사이의 정수를 입력하세요.");
		    }
			//문자를 입력했을 때 발생하는 예외 처리
			catch (InputMismatchException e) {
				System.out.println("예외 발생됨");
				System.out.println("숫자만 입력 가능합니다.");
				scan.nextLine();
			}
		}
	}
}

