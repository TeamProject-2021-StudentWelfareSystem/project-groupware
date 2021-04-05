package com.mju.groupware.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class UsersDetails implements UserDetails {
	
//	public enum userRole {STUDENT, PROFESSOR, ADMINISTRATOR}
	
	private int userID;
	private String userName; 
	private String userPhoneNum;
	private String userEmail;
	private String userLoginID;
	private String userLoginPwd; 
//	private userRole userRole;
	private String authority; // ROLE_USER
	private boolean enabled; // true : 怨꾩æ �솢�꽦�솕, false : 鍮꾪솢�꽦�솕

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		//alluser, admin, user
		
		authList.add(new SimpleGrantedAuthority(authority));
		return authList;
	}
	
	@Override
	public String getPassword() {
		return userLoginPwd;
	}
	@Override
	public String getUsername() {
		return userLoginID;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return enabled? true:false;
	}

}