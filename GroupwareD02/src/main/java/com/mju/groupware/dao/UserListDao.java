package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.UserList;

public interface UserListDao {

	public List<UserList> list() throws Exception;

}
