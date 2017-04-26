package com.aa.member;

public class Member {
	
	String idNum;
	String id;
	String pw;
	String mailAddr;
	int count;
	int idVal;
	boolean locked;				//true = 열림 false = 잠김
	
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMailAddr() {
		return mailAddr;
	}
	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getIdVal() {
		return idVal;
	}
	public void setIdVal(int idVal) {
		this.idVal = idVal;
	}

}
