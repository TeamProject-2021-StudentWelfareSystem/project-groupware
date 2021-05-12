package com.mju.groupware.dto;

public class ConstantDoEmail {
	private String EmailAdress;
	private String DateFormat;
	private String AuthUrl;
	private String AgreeUrl;
	private String AuthNum;
	private String EPwd;

	public String getEPwd() {
		return EPwd;
	}

	public void setEPwd(String ePwd) {
		EPwd = ePwd;
	}

	public String getAuthNum() {
		return AuthNum;
	}

	public void setAuthNum(String authNum) {
		AuthNum = authNum;
	}

	public String getEmailAdress() {
		return EmailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		EmailAdress = emailAdress;
	}

	public String getAgreeUrl() {
		return AgreeUrl;
	}

	public void setAgreeUrl(String agreeUrl) {
		AgreeUrl = agreeUrl;
	}

	public String getAuthUrl() {
		return AuthUrl;
	}

	public void setAuthUrl(String authUrl) {
		AuthUrl = authUrl;
	}

	public String getDateFormat() {
		return DateFormat;
	}

	public void setDateFormat(String dateFormat) {
		DateFormat = dateFormat;
	}

}
