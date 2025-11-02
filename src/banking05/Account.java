package banking05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//모든 계좌의 기본 형태(추상 클래스)
abstract public class Account implements Serializable {

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

	public void showAccInfo() {
		System.out.print("계좌번호>"+ accountNumber);
		System.out.print(", 고객이름>"+ name);
		System.out.print(", 잔고>"+ balance);
		System.out.println(", 기본이자>" + (int)(interestRate * 100) + "%");
	}

	public abstract void depositMoney(int amount);
	public abstract void deleteAccount();
	
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