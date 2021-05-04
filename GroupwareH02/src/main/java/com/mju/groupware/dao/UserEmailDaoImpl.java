package com.mju.groupware.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.UserEmail;

@Service
@Repository

public class UserEmailDaoImpl implements UserEmailDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void InsertCertification(UserEmail userEmail) {
		this.sqlSession.insert("InsertCertification", userEmail);
	}

	@Override
	public boolean SelectForCheckCertification(int authNum) {
		UserEmail Output = this.sqlSession.selectOne("SelectForCheckCertification", authNum);
		if (Output == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void DeleteCertification(UserEmail userEmail) {
		this.sqlSession.delete("DeleteCertification", userEmail);
	}
}
