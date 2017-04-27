package com.aa.io;

import java.util.Scanner;

public class Input {

	private static Input input;
	private Scanner sc = new Scanner(System.in);
	private Input(){}
	public static Input getInstance(){
		if(input==null)
			input = new Input();
		return input;
	}
	public String inputId() {
		String tmp = "";
		System.out.println("아이디를 입력하세요");
		tmp = sc.nextLine();
		return tmp.trim();
	}

	public String inputPassword() {
		String tmp = "";
		System.out.println("패스워드를 입력하세요");
		tmp = sc.nextLine();
		return tmp.trim();
	}

	public String inputEmail() {
		String tmp = "";
		System.out.println("이메일을 입력하세요");
		tmp = sc.nextLine();
		return tmp.trim();
	}

}
