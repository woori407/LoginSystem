package com.aa.main;

import java.util.Scanner;

import com.aa.main.Login;

public class LoginMain {
	public static void main(String[] args) {
			
			Login log = new Login();
			Scanner scan = new Scanner(System.in);
			System.out.println("ID 입력");
			String id =scan.next();
			log.getId();
			
			System.out.println("비밀번호 입력");
			String password = scan.next();
			log.getPassword();
			

	}
}
