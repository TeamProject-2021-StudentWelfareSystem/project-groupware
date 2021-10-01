package com.mju.groupware.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class UsersDetails implements UserDetails {
	
	private String UserLoginID;
	private String UserLoginPwd; 
	private String Authority; // ROLE_USER
	private boolean Enabled; // true : 활성화, false : 비활성화

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> AuthList = new ArrayList<GrantedAuthority>();		
		AuthList.add(new SimpleGrantedAuthority(Authority));
		return AuthList;
	}
	
	@Override
	public String getPassword() {
		return UserLoginPwd;
	}
	@Override
	public String getUsername() {
		return UserLoginID;
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
		return Enabled? true:false;
	}

}