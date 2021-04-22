package com.mju.groupware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.UserListDao;
import com.mju.groupware.dto.UserList;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserListDao UserListDao;

	@Override
	public List<UserList> SelectUserlist() throws Exception {
		return UserListDao.SelectUserlist();
	}

	@Override
	public List<UserList> SelectDormantUserList() {
		return UserListDao.SelectDormantUserList();
	}

	@Override
	public List<UserList> SelectWithdrawalUserList() {
		return UserListDao.SelectWithdrawalUserList();
	}
}
