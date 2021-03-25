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

	private UserDetails users;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (userDao.selectByLoginID(username)!=null) {
			users = (MemberDetails)userDao.selectByLoginID(username);
		} else {
			throw new UsernameNotFoundException("username " + username + " not found");
		}
		System.out.println("**************Found user***************");
		System.out.println("id : " + users.getUsername());
		System.out.println("pw : " + users.getPassword());
		System.out.println("pw : " + users.getAuthorities());
		
		return users;
	}

}
