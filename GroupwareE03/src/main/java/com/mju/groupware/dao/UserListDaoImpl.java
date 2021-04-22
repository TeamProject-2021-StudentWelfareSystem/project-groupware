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
	public List<UserList> SelectUserlist() throws Exception {
		List<UserList> Output = this.sqlSession.selectList("SelectUserList");
		return Output;
	}

	// 휴먼계정 List 호출
	@Override
	public List<UserList> SelectDormantUserList() {
		List<UserList> Output = this.sqlSession.selectList("SelectDormantList");
		return Output;
	}

	// 탈퇴계정 List 호출
	@Override
	public List<UserList> SelectWithdrawalUserList() {
		List<UserList> Output = this.sqlSession.selectList("SelectWithdrawalList");
		return Output;
	}
}
