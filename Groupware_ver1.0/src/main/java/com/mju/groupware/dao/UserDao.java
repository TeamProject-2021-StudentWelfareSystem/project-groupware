package com.mju.groupware.dao;

import com.mju.groupware.security.MemberDetails;

public interface UserDao {
	
	public MemberDetails selectByLoginID(String username);

}
