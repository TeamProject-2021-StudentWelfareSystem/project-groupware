package com.mju.groupware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.UserListDao;
import com.mju.groupware.dto.Criteria;
import com.mju.groupware.dto.UserList;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserListDao userListDao;

	@Override
	public List<UserList> SelectUserlist() throws Exception {
		return userListDao.SelectUserlist();
	}

	@Override
	public List<UserList> SelectDormantUserList() {
		return userListDao.SelectDormantUserList();
	}

	@Override
	public List<UserList> SelectWithdrawalUserList() {
		return userListDao.SelectWithdrawalUserList();
	}

	@Override
	public int CountTotalUserList(Criteria cri) {
		// TODO Auto-generated method stub
		return userListDao.CountTotalUserList(cri);
	}

	@Override
	public List<UserList> SelectUserListPN(Criteria cri) {
		// TODO Auto-generated method stub
		return userListDao.SelectUserListPN(cri);
	}

	@Override
	public int CountTotalDormantUserList(Criteria cri) {
		// TODO Auto-generated method stub
		return userListDao.CountTotalDormantUserList(cri);
	}

	@Override
	public List<UserList> SelectDormantUserListPN(Criteria cri) {
		// TODO Auto-generated method stub
		return userListDao.SelectDormantUserListPN(cri);
	}

	@Override
	public int CountTotalWithdrawalUserList(Criteria cri) {
		// TODO Auto-generated method stub
		return userListDao.CountTotalWithdrawalUserList(cri);
	}

	@Override
	public List<UserList> SelectWithdrawalUserListPN(Criteria cri) {
		// TODO Auto-generated method stub
		return userListDao.SelectWithdrawalUserListPN(cri);
	}

	
	

}
