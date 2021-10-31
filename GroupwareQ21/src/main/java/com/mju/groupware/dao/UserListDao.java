package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.Criteria;
import com.mju.groupware.dto.UserList;

public interface UserListDao {

	public List<UserList> SelectUserlist() throws Exception;

	public List<UserList> SelectDormantUserList();

	public List<UserList> SelectWithdrawalUserList();

	public int CountTotalUserList();

	public List<UserList> SelectUserListPN(Criteria cri);
	
	public int CountTotalDormantUserList();

	public List<UserList> SelectDormantUserListPN(Criteria cri);

	public int CountTotalWithdrawalUserList();

	public List<UserList> SelectWithdrawalUserListPN(Criteria cri);

}
