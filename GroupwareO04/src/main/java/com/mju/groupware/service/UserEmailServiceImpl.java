package com.mju.groupware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.UserEmailDao;
import com.mju.groupware.dto.UserEmail;

@Service
public class UserEmailServiceImpl implements UserEmailService {

	@Autowired
	UserEmailDao userEmailDao;
	
	@Override
	public void InsertCertification(UserEmail userEmail) {
		userEmailDao.InsertCertification(userEmail);
	}

	@Override
	public boolean SelectForCheckCertification(String authNum) {
		boolean Checker = userEmailDao.SelectForCheckCertification(Integer.parseInt(authNum));
		return Checker;
	}

	@Override
	public void DeleteInfo(UserEmail userEmail) {
		userEmailDao.DeleteCertification(userEmail);
	}

}
