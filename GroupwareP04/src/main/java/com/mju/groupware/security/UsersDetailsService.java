package com.mju.groupware.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.UserDao;

@Service
public class UsersDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersDetails Users = userDao.selectByLoginID(username);
		if(Users == null) {
			 throw new UsernameNotFoundException("username " + username + " not found");
		}
		return Users;
	}

}
