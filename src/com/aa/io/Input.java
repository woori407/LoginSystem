package com.aa.io;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class Input {

	private static Input input;
	private Scanner sc = new Scanner(System.in);
	private Console cnsl = null;
	private Input(){}
	public static Input getInstance(){
		if(input==null)
			input = new Input();
		return input;
	}
	public String inputId() {
		String tmp = "";
		System.out.printf("아이디를 입력하세요 : ");
		tmp = sc.nextLine();
		return tmp.trim();
	}

	public String inputPassword() {
		String tmp = "";
//		while (true) {
//			try {
//				cnsl = System.console();
//				if (cnsl != null) {
//					char[] pwd = cnsl.readPassword("패스워드를 입력하세요 : ");
//					for (int i = 0; i < pwd.length; i++) {
//						tmp = tmp+pwd[i];
//					}
//					break;
//				}
//				else{
//					System.out.println("패스워드 입력 메소드 오류");
//					break;
//				}
//			} catch (Exception e) {
//				System.out.println("다시 시도하세요");
//				e.printStackTrace();
//			} 
//		}
		System.out.printf("비밀번호를 입력하세요 : ");
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
