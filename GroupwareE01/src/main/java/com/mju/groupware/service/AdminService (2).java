package com.mju.groupware.service;

import java.util.List;

import com.mju.groupware.dto.UserList;

public interface AdminService {

	public List<UserList> SelectUserlist() throws Exception;

	public List<UserList> SelectDormantUserList();

	public List<UserList> SelectWithdrawalUserList();

}
