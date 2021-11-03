package com.mju.groupware.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.constant.ConstantAdminUserEmailDao;
import com.mju.groupware.dto.UserEmail;

@Service
@Repository

public class UserEmailDaoImpl implements UserEmailDao {
	private ConstantAdminUserEmailDao Constant;

	@SuppressWarnings("resource")
	public UserEmailDaoImpl() {
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/UserEmailDao.xml");
		CTX.refresh();
		this.Constant = (ConstantAdminUserEmailDao) CTX.getBean("UserEmailDaoID");
	}

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void InsertCertification(UserEmail userEmail) {
		this.sqlSession.insert(this.Constant.getInsertCertification(), userEmail);
	}

	@Override
	public boolean SelectForCheckCertification(int authNum) {
		UserEmail Output = this.sqlSession.selectOne(this.Constant.getSelectForCheckCertification(), authNum);
		if (Output == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void DeleteCertification(UserEmail userEmail) {
		this.sqlSession.delete(this.Constant.getDeleteCertification(), userEmail);
	}
}
