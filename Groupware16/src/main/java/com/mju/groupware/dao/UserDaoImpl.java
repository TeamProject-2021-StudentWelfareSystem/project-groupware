package com.mju.groupware.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.security.UsersDetails;

@Service
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void SignUp(User user) {
		this.sqlSession.insert("InsertUser", user);
	}

	@Override
	public UsersDetails selectByLoginID(String userLoginID) {
		UsersDetails users = this.sqlSession.selectOne("SelectUser", userLoginID);
		return users;
	}

	@Override
	public boolean IdConfirm(User user) {
		System.out.println(user.getUserLoginID());
		User output = this.sqlSession.selectOne("UserIdConfirm", user);

		if (output == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean PwdConfirm(User user) {
		System.out.println(user.getUserLoginID());
		User output = this.sqlSession.selectOne("UserPwdConfirm", user);

		if (output == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public int SelectUserID(Student student) {
		return this.sqlSession.selectOne("SelectUserID", student);
	}

}