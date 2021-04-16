package com.mju.groupware.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.UserList;
@Service
@Repository
public class UserListDaoImpl implements UserListDao {

	@Autowired
	private SqlSession sqlSession;

	// userList를 호출하기 위한 dao
	@Override
	public List<UserList> list() throws Exception {

		List<UserList> output = this.sqlSession.selectList("GetUserList");

		return output;
	}

}
