package banking05;

//메뉴번호, 이자율 정의 : 프로그램 전체에서 사용할 상수(고정값)들을 한 곳에 모아둔 파일
public interface ICustomDefine {

	//신용등급에 따른 추가 이자율
	int A=7, B=4, C=2;
	
	//메뉴 선택 번호 정의(가독성 목적)
	int MAKE = 1;
	int DEPOSIT = 2;
	int WITHDRAW = 3;
	int INQUIRE = 4;
	int Delete = 5;
	int EXIT = 6;
	
}
