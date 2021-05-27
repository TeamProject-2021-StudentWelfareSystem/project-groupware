package com.mju.groupware.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.ConstantAdminUserListDao;
import com.mju.groupware.dto.UserList;
import com.mju.groupware.dto.WithdrawalProfessor;
import com.mju.groupware.dto.WithdrawalStudent;
import com.mju.groupware.dto.WithdrawalUser;

@Service
@Repository
public class UserListDaoImpl implements UserListDao {
	private ConstantAdminUserListDao Constant;
	public UserListDaoImpl() {
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/UserListDao.xml");
		CTX.refresh();
		this.Constant = (ConstantAdminUserListDao) CTX.getBean("UserListDaoID");
	}

	@Autowired
	private SqlSession sqlSession;

	// userList를 호출하기 위한 dao
	@Override
	public List<UserList> SelectUserlist() throws Exception {
		List<UserList> Output = this.sqlSession.selectList(this.Constant.getSelectUserList());
		return Output;
	}

	// 휴먼계정 List 호출
	@Override
	public List<UserList> SelectDormantUserList() {
		List<UserList> Output = this.sqlSession.selectList(this.Constant.getSelectDormantList());
		return Output;
	}

	// 탈퇴계정 List 호출
	@Override
	public List<WithdrawalUser> SelectWithdrawalUserList() {
		List<WithdrawalUser> Output = this.sqlSession.selectList(this.Constant.getSelectWithdrawalList());
		return Output;
	}

	@Override
	public WithdrawalUser SelectWithdrawalUserListForRecovery(String userLoginID) {
		WithdrawalUser Output = sqlSession.selectOne(this.Constant.getSelectWithdrawalUserListForRecovery(), userLoginID);
		return Output;
	}

	@Override
	public WithdrawalStudent SelectWithdrawalStudentListForRecovery(String wuserID) {
		WithdrawalStudent Output = sqlSession.selectOne(this.Constant.getSelectWithdrawalStudentListForRecovery(), wuserID);
		return Output;
	}
	
	@Override
	public WithdrawalProfessor SelectWithdrawalProfessorListForRecovery(String wuserID) {
		WithdrawalProfessor Output = sqlSession.selectOne(this.Constant.getSelectWithdrawalProfessorListForRecovery(), wuserID);
		return Output;
	}

	
	@Override
	public void InsertUserForRecovery(WithdrawalUser withdrawalUser) {
		sqlSession.insert(this.Constant.getInsertUserForRecovery(), withdrawalUser);
	}

	@Override
	public void InsertStudentForRecovery(WithdrawalStudent withdrawalStudent) {
		System.out.println(withdrawalStudent.getWUserID());
		sqlSession.insert(this.Constant.getInsertStudentForRecovery(), withdrawalStudent);
	}

	@Override
	public void InsertProfessorForRecovery(WithdrawalProfessor withdrawalProfessor) {
		System.out.println(withdrawalProfessor.getWUserID());
		sqlSession.insert(this.Constant.getInsertProfessorForRecovery(), withdrawalProfessor);
		
	}
}
