package com.mju.groupware.dto;

public class User { 
	
	private String loginID;
	private String loginPW;
	private String authority; // ROLE_USER
	private boolean enabled;
	
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getLoginPW() {
		return loginPW;
	}
	public void setLoginPW(String loginPW) {
		this.loginPW = loginPW;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}