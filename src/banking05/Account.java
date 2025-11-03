package banking05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//모든 계좌(기본/신용)의 기본 형태(추상 클래스)
abstract public class Account {

	//모든 계좌가 가지는 기본 정보
	String accountNumber;
	String name;
	int balance;
	double interestRate;
	
	public Account(String accountNumber, String name, int balance,
					double interestRate) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.interestRate = (interestRate / 100.0);
	}

	//계좌 정보 출력
	public void showAccInfo() {
		System.out.print("계좌번호>"+ accountNumber);
		System.out.print(", 고객이름>"+ name);
		System.out.print(", 잔고>"+ balance);
		System.out.println(", 기본이자>" + (int)(interestRate * 100) + "%");
	}

	//하위 클래스(보통/신용)에서 반드시 구현해야 할 메서드
	public abstract void depositMoney(int amount);
	public abstract void deleteAccount();
	
	//중복 계좌번호 방지를 위한 equals()와 hashcode() 오버라이드
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof Account)) return false;
		Account acc = (Account) obj;
		return accountNumber != null && accountNumber.equals(acc.accountNumber);
	}
	
	@Override
	public int hashCode() {
		return accountNumber.hashCode();
	}
}