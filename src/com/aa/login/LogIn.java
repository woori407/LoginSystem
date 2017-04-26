package com.aa.login;

import java.util.LinkedList;
import java.util.Scanner;

import com.aa.member.Member;

public class LogIn {
	
//	Member[] memArray;				//링크드만 사용해도 괜찮을 듯 하다.
	Member inputData;				//사용자 입력을 받을 객체
	boolean idExist;				//아이디가 존재하는지 확인
	boolean isLockNeeded;			//count가 3이상이면 true
	boolean isValidPW;				//패스워드가 맞으면 true
	
	//idVal 값을 이용해서 정렬시키고 검색해주는 데이터 스트럭쳐
	//파일 데이터
	//1.회원번호	2.id	3.pw	4.mailAddr	5.count	6.locked
	LinkedList<Member> mList;			//전체 회원
	
	LogIn(){
		mList = new LinkedList<Member>();
		idExist = false;
		isLockNeeded = false;
		isValidPW = false;
	}
	
	public void process(){
		
		//loadMembers();				//기훈 : 파일로부터 데이터 객체에 매핑
		createDataStructure();		//기훈 : mList 생성 
		
		while(true){
			inputData = new Member();		//이전 값에 영향을 받지 않기 위해 수행단위마다 새로운 인스턴스 생성
//	1 로그인
//		-아이디 입력
			
//			TODO 20170426 16:25 동완
//			inputId()로 아이디 입력받아 메소드 내부에서 checkId()실행
//			checkId()내에서 mList 검색해 Id일치값 찾아 inputData에 해당 member 인스턴스 삽입
//			checkId()내부에서 일치값 찾으면 idExist=true, 못찾으면 idExist=false
			
			inputId();					//현정 : inputData 의 아이디 값 set
			if(idExist){
//	1.1 아이디 존재
//		-접속 시도 횟수 조회
				isLockNeeded = checkCount();	//동완 : 리스트의 해당 아이디 count가 3 이상이면 true		
				if(isLockNeeded){
//	1.1.2 접속 시도 횟수 3회
//		-잠금 된 아이디 메시지 출력
//		-[1번으로 복귀]
				}else{
//	1.1.1 접속시도 횟수 3회 이내
					
//					TODO 20170426 04:24 동완
//					inputPw()로 비밀번호 입력받아 그 내부에서 바로 checkPw 호출
					
//		-비밀번호 입력
					inputPw();			//동완 : inputData 의 패스워드 값 set
//		-비밀번호 확인
					isValidPW = checkPw();			//기훈 : 패스워드가 맞으면 true 반환
					if(isValidPW){
//	1.1.1.1 비밀번호 일치
//		-접속 시도 횟수 초기화
						resetCount();				//기훈 : 파일의 해당 아이디의 카운트 값 리셋하고 해당 아이디 값  파일참조하여 리스트 업데이트
//		-[로그인 성공]
						System.out.println("log-in successfully");
						break;
					} else {
//	1.1.1.2 비밀번호 불일치
//		-비밀번호 불일치 예외 메세지
						System.out.println("wrong password");
//		-해당 아이디 접속 시도 횟수 증가
						increCount();				//현정 : 파일의 카운트 증가시키고 해당 아이디 값 파일 참조하여 리스트 업데이트
//		-접속 시도 횟수 확인
						isLockNeeded = false;		//같은 변수 사용중이므로 만약을 대비해 false로 초기화
						isLockNeeded = checkCount();	//현정 : 리스트의 해당 아이디 count가 3 이상이면 true , 아니라면 false 반드시 반환
						if(isLockNeeded){
//	1.1.1.2.1 접속 시도 횟수 3회 이내
//		-[1번으로 복귀]
							continue;
						}else{
//	1.1.1.2.2 접속 시도 횟수 3회
//		-아이디 잠금
							lockDown();
//		-잠금 메시지 출력
							System.out.println("id locked");
//		-메일 주소 입력
							inputMailAddr();
//		-메일 주소 저장
							saveMailAddr();			// 메일 주소를 파일에 쓰고 리스트 업데이트
//		-메일 발송(attached: confirm url)
							sendMail();				// 구현하지 않음
//		-[1번으로 복귀]
							continue;
						}
					}
				}
			} else {
//	1.2 아이디 비존재
//		-아이디가 없습니다 메시지
				System.out.println("no such id");
//		-[1번으로 복귀]
				continue;
			}
		}

//	2 사후처리
//		-confirm url 방문
//		-잠금 해제
//		-접속 시도 횟수 초기화	
		
	}
	
	//동완
	
	//기훈
	public void loadMembers(){}
	public void createDataStructure(){}
	
	//현정
	
	public void inputId(){
		String id;
		System.out.println("아이디 입력:");
		Scanner scan = new Scanner(System.in);
		id = scan.next();
		checkId(id);
	}
	
	public void checkId(String id){
        for(int i=0; i<mList.size();i++){
            if(mList.get(i).getId() == id){
            	inputData = mList.get(i);
            	idExist=true;
            	break;
            }
        }
    }
	public boolean checkCount(){return false;}
	public void printExceptMsg(){}
	public void inputPw(){}
	public boolean checkPw(){return false;}
	public void resetCount(){}
	public void increCount(){}
	public void inputMailAddr(){}
	public void saveMailAddr(){}
	public void sendMail(){}
	public void lockDown(){}
	public void listUpdate(){}
	

}
