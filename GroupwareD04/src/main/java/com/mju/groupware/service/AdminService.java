package com.mju.groupware.service;

import java.util.List;

import com.mju.groupware.dto.UserList;

public interface AdminService {

	public List<UserList> list() throws Exception;

	public List<UserList> DormantList();

}
