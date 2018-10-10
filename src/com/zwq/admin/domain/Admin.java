package com.zwq.admin.domain;

public class Admin {
	
	private int adminId;
	private String adminname;
	private String adminpassword;
	private String verifyCode;
	private String newadminpassword;
	private String readminpassword;
	
	
	
	
	
	public String getReadminpassword() {
		return readminpassword;
	}
	public void setReadminpassword(String readminpassword) {
		this.readminpassword = readminpassword;
	}
	public String getNewadminpassword() {
		return newadminpassword;
	}
	public void setNewadminpassword(String newadminpassword) {
		this.newadminpassword = newadminpassword;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getAdminpassword() {
		return adminpassword;
	}
	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	
	

}
