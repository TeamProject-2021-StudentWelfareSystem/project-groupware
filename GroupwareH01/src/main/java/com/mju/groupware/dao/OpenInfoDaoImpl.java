package com.mju.groupware.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.UserList;

@Service
@Repository
public class OpenInfoDaoImpl implements OpenInfoDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<UserList> SelectForOpenInfoAll(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfoAll", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfoWithoutGrade(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfoWithoutGrade", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfoWithoutMajor(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfoWithoutMajor", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfoWithoutPhoneNum(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfoWithoutPhoneNum", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfoWithoutEmail(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfoWithoutEmail", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfoWithoutName(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfoWithoutName", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_E_P(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_N_E_P", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_E_G(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_N_E_G", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_E_M(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_N_E_M", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_M_G(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_N_M_G", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_P_M(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_N_P_M", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_P_G(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_N_P_G", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_P_M(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_E_P_M", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_P_G(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_E_P_G", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_M_G(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_E_M_G", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_P_M_G(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_P_M_G", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_E(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_N_E", mysqlID);
		System.out.println(Output.toString());
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_P(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_N_P", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_M(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_N_M", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_P(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_E_P", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_G(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_N_G", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_M(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_E_M", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_G(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_E_G", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_P_M(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_P_M", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_P_G(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_P_G", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfo_M_G(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfo_M_G", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfoName(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfoName", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfoEmail(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfoEmail", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfoPhoneNum(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfoPhoneNum", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfoMajor(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfoMajor", mysqlID);
		return Output;
	}

	@Override
	public List<UserList> SelectForOpenInfoGrade(String mysqlID) {
		List<UserList> Output = sqlSession.selectList("SelectForOpenInfoGrade", mysqlID);
		return Output;
	}

}
