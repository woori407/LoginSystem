package com.aa.login;

import java.util.LinkedList;

import com.aa.fileio.FileIO;
import com.aa.io.Input;
import com.aa.member.Member;
public class LogIn {
   
//   Member[] memArray;            //링크드만 사용해도 괜찮을 듯 하다.
   Member inputData;            //사용자 입력을 받을 객체
   boolean idExist;            //아이디가 존재하는지 확인
   boolean isLockNeeded;         //count가 3이상이면 true
   boolean isValidPW;            //패스워드가 맞으면 true
   String fileStr;               //파일내용
   
   //idVal 값을 이용해서 정렬시키고 검색해주는 데이터 스트럭쳐
   //파일 데이터
   //1.회원번호   2.id   3.pw   4.mailAddr   5.count   6.locked
   LinkedList<Member> mList;         //전체 회원
   
   public LogIn(){
      mList = new LinkedList<Member>();
      idExist = false;
      isLockNeeded = false;
      isValidPW = false;
   }
   
   public void process() {
      
      FileIO fIO = new FileIO();
      fileStr = fIO.getInitStr();
      
      createDataStructure(fileStr);
      while(true){
         inputData = new Member();      //이전 값에 영향을 받지 않기 위해 수행단위마다 새로운 인스턴스 생성
//   1 로그인
//      -아이디 입력
         
//         TODO 20170426 16:25 동완
//         inputId()로 아이디 입력받아 메소드 내부에서 checkId()실행
//         checkId()내에서 mList 검색해 Id일치값 찾아 inputData에 해당 member 인스턴스 삽입
//         checkId()내부에서 일치값 찾으면 idExist=true, 못찾으면 idExist=false
         
         inputId();               //현정 : inputData 의 아이디 값 set
         if(idExist){
//   1.1 아이디 존재
//      -접속 시도 횟수 조회
            checkCount();   //동완 : 리스트의 해당 아이디 count가 3 이상이면 lockDown(), isLockNeeded;     
            if(isLockNeeded){
//   1.1.2 접속 시도 횟수 3회
//      -잠금 된 아이디 메시지 출력
            	System.out.println("ID locked");
//      -[1번으로 복귀]
            }else{
//   1.1.1 접속시도 횟수 3회 이내
               
//               TODO 20170426 04:24 동완
//               inputPw()로 비밀번호 입력받아 그 내부에서 바로 checkPw 호출
               
//      -비밀번호 입력
               inputPw();         //현정 : inputData 의 패스워드 값 set
//      -비밀번호 확인
               if(isValidPW){
//   1.1.1.1 비밀번호 일치
//      -접속 시도 횟수 초기화
                  resetCount();            //기훈 : 파일의 해당 아이디의 카운트 값 리셋하고 해당 아이디 값  파일참조하여 리스트 업데이트
//      -[로그인 성공]
                  System.out.println("log-in successfully");
                  break;
               } else {
//   1.1.1.2 비밀번호 불일치
//      -비밀번호 불일치 예외 메세지
                  System.out.println("wrong password");
//      -해당 아이디 접속 시도 횟수 증가
                  increCount();            //현정 : 파일의 카운트 증가시키고 해당 아이디 값 파일 참조하여 리스트 업데이트
//      -접속 시도 횟수 확인
                  isLockNeeded = false;      //같은 변수 사용중이므로 만약을 대비해 false로 초기화
//                  isLockNeeded = checkCount();   //현정 : 리스트의 해당 아이디 count가 3 이상이면 true , 아니라면 false 반드시 반환
                  if(isLockNeeded){
//   1.1.1.2.1 접속 시도 횟수 3회 이내
//      -[1번으로 복귀]
                     continue;
                  }else{
//   1.1.1.2.2 접속 시도 횟수 3회
//      -아이디 잠금
                     lockDown();
//      -잠금 메시지 출력
                     System.out.println("id locked");
//      -메일 주소 입력
//      -메일 주소 저장을 메소드 내에서 수행
                     inputMailAddr();
//      -메일 발송(attached: confirm url)
                     sendMail();            // 구현하지 않음
//      -[1번으로 복귀]
                     String id = inputData.getId();
                     for (int i = 0; i < mList.size(); i++) {
                    	 Member tmp = mList.get(i);
            			if(tmp.getId().compareTo(id)==0){
//            				mList <- inputData
            				tmp.setCount(inputData.getCount());
            				tmp.setLocked(inputData.isLocked());
            				tmp.setMailAddr(inputData.getMailAddr());
//            				filestr <- mList
            				findAndSaveStr();
            			      fIO.writeLastStr(fileStr);
            				break;
            			}
            		}
                     System.out.println();
                     continue;
                  }
               }
            }
         } else {
//   1.2 아이디 비존재
//      -아이디가 없습니다 메시지
            System.out.println("no such id");
//      -[1번으로 복귀]
            continue;
         }
      }

//   2 사후처리
//      -confirm url 방문
//      -잠금 해제
//      -접속 시도 횟수 초기화   
      
   }
   
   //동완
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   public void checkCount(){
      if(inputData.getCount()<3){
         isLockNeeded = false;
      } else{
          lockDown();
          isLockNeeded = true;
      }
   }
   public void lockDown(){
      inputData.setLocked(true);
   }
   
   public void inputMailAddr(){
      String email="";
      String verify = "";
      Input input = Input.getInstance();
      while (true) {
         email = input.inputEmail();
         System.out.printf("재확인 : ");
         verify = input.inputEmail();
         if (0==email.compareTo(verify)){
            saveMailAddr(email);
            break;
         }
         else{
            System.out.println("메일 주소가 일치하지 않습니다. 다시 입력해주세요");
            continue;
         }
      }
   }
   
   public void saveMailAddr(String email){
      inputData.setMailAddr(email);
   }
   public void resetCount(){
      inputData.setCount(0);
   }
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   //기훈
   public void loadMembers(){}
   ///////////////////////////////////createDataStructure///////////////////////////////////////////////////////////
   public void createDataStructure(String fileStr) {            //추후 예외처리 변경
      
      int ch;
      int choice = 0;
//         FileInputStream fIn = new FileInputStream(".\\resources\\index.txt");
      
      Member temp = new Member();
      String token = "";
      int i = 0;
      
//         while(true){
      for (int j = 0; j < fileStr.length(); j++) {
         ch = fileStr.charAt(i++);
//            if(ch == -1)
//               break;
//            System.err.print((char)ch);
         if(ch == '\t'){
            setTempData(temp , token , choice++%6);
//               System.out.println(token);
            token="";
//         }else if(ch == '\r'){
//            continue;
         }else if(ch == ':'){
            listSortedAdd (mList , temp);
            temp = new Member();
            continue;
         }else{
            token += (char)ch;
         }
      }
      
   }
   
   private void setTempData(Member temp , String token , int n){
      
      switch(n++%6){
      case 0:
         temp.setIdNum(token);
//            System.out.println(temp.getIdNum());
         break;
      case 1:
         temp.setId(token);
//            System.out.println(temp.getId());
         break;
      case 2:
         temp.setPw(token);
//            System.out.println(temp.getPw());
         break;
      case 3:
         temp.setMailAddr(token);
//            System.out.println(temp.getMailAddr());
         break;
      case 4:
         temp.setCount(Integer.parseInt(token));
//            System.out.println(temp.getCount());
         break;
      case 5:
         temp.setLocked(Boolean.parseBoolean(token));
//            System.out.println(temp.isLocked());
         break;
      default:
         System.out.println("System something wrong at switch() in createDataStructure()");
         break;
      
      }
   
   }
   
   private void listSortedAdd (LinkedList<Member> mList , Member temp) {
      
      if(mList.size() == 0){
         mList.add(temp);
      }else{
         String firstStr = mList.get(0).getId();         //리스트의 첫번째 요소의 아이디
         String newStr = temp.getId();            //새롭게 추가될 요소의 아이디
         if(isFirstBigger(firstStr , newStr)){      //firstStr이 클 경우 temp를 가장 앞에 배치
            mList.addFirst(temp);
         }
         else{
            for (int i = 1; i < mList.size(); i++) {
               if(isFirstBigger(mList.get(i).getId() , newStr)){
                  mList.add(i, temp);
                  return;
               }
            }
            mList.addLast(temp);
         }
         
      }
      
   }
   public boolean isFirstBigger (String first , String second){      //true이면 first가 크고 false이면 second가 크다. 만약 같은경우라도 false
      boolean isc = false;
      int fLength = first.length();
      int sLength = second.length();
      int length;
      
      if(fLength < sLength) 
         length = fLength;
      else
         length = sLength;
      
      for (int i = 0; i < length; i++) {
         if(first.charAt(i) < second.charAt(i)){      
            return isc;
         }else if(first.charAt(i) > second.charAt(i)){
            isc = true;
            return isc;
         }else
            continue;
      }
      return isc;
   }
   
   public void findAndSaveStr(){
      
      String [] aStr = null;
      aStr = fileStr.split(":");
      
      int row = Integer.parseInt(inputData.getIdNum()) - 100000;
      
      aStr[row] = "";
      aStr[row] += inputData.getIdNum();
      aStr[row] +='\t';   
      aStr[row] += inputData.getId();
      aStr[row] +='\t';
      aStr[row] += inputData.getPw();
      aStr[row] +='\t';
      aStr[row] += inputData.getMailAddr();
      aStr[row] +='\t';
      aStr[row] += inputData.getCount();
      aStr[row] +='\t';
      if(inputData.isLocked())
         aStr[row] += "1";
      else
         aStr[row] += "0";
//      aStr[row] +=":";
      
      fileStr = "";
      
      for (int i = 0; i < aStr.length; i++) {
         fileStr += aStr[i] + ":";
//    	  System.out.println(aStr[i]);
      }
      
   }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   //현정
   
   public void inputId(){
      String id;
      Input input = Input.getInstance();
      id = input.inputId();
      checkId(id);
   }
   
   public void checkId(String id){
        for(int i=0; i<mList.size();i++){
            if(mList.get(i).getId().compareTo(id)==0){
               inputData = mList.get(i);
               idExist=true;
               break;
            }
        }
    }
   
   
   public void inputPw(){
      String Pw;
      if(inputData.getCount()<3){
          Input input = Input.getInstance();
          Pw = input.inputPassword();
         checkPw(Pw);
      } else {
         lockDown();
      }
   }
   
   public void checkPw(String Pw){
        if(inputData.getPw().compareTo(Pw)==0){
           isValidPW = true;
        } else{
           isValidPW = false;
        }
    }

   public void increCount(){
         
      inputData.setCount(inputData.getCount()+1);
      checkCount();
      
   }
   public void printExceptMsg(){}

   public void sendMail(){}
   public void listUpdate(){}
   

}