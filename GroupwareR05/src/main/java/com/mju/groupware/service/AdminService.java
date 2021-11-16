package com.mju.groupware.service;

import java.util.List;

import com.mju.groupware.dto.Criteria;
import com.mju.groupware.dto.UserList;

public interface AdminService {

	public List<UserList> SelectUserlist() throws Exception;

	public List<UserList> SelectDormantUserList();

	public List<UserList> SelectWithdrawalUserList();

	public int CountTotalUserList(Criteria cri);

	public List<UserList> SelectUserListPN(Criteria cri);

	public int CountTotalDormantUserList(Criteria cri);

	public List<UserList> SelectDormantUserListPN(Criteria cri);

	public int CountTotalWithdrawalUserList(Criteria cri);

	public List<UserList> SelectWithdrawalUserListPN(Criteria cri);



}
