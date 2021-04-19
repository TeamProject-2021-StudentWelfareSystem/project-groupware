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
	public List<UserList> list() throws Exception {
		return UserListDao.list();
	}

	@Override
	public List<UserList> DormantList() {
		return UserListDao.DormantList();
	}

	@Override
	public List<UserList> WithdrawalList() {
		return UserListDao.WithdrawalList();
	}
}
