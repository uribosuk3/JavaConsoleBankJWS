package banking05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ex20io.Circle;

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
	
	public static void main(String[] args) {

		try {
			//인스턴스를 파일로 저장하기 위해 출력스트림 생성
			ObjectOutputStream out =
					new ObjectOutputStream(
							new FileOutputStream("AccountInfo.obj")
					);
			
			////여기까지 실행하면 obj 파일이 생성됨////
			
			/*
			인스턴스의 복원(역직렬화)를 위한 스트림을 생성하고 메서드를
			통해 복원한다. */
			ObjectInputStream in =
				new ObjectInputStream(
					new FileInputStream("AccountInfo.obj")
				);
			
			out.writeObject(acc);
			/*
			저장시 Object 기반으로 저장되므로 복원시에는 원래의
			자료형으로 다운캐스팅(강제형변환) 해야한다. */
			Account acc = (Account)in.readObject();
			in.close();
			
			/*
			개발자가 직접 정의한 클래스는 멤버메서드를 통해 정보를 출력
			할 수 있다. */
			acc.showAccInfo();
		}
		catch(ClassNotFoundException e) {
			System.out.println("[예외]클래스없음");
		}
		catch(FileNotFoundException e) {
			System.out.println("[예외]파일없음");
		}
		catch(IOException e) {
			System.out.println("[Exception]뭔가없음");
		}
	}
}
