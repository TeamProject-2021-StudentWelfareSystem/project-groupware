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
	public void SaveCertification(UserEmail userEmail) {
		userEmailDao.SaveCertification(userEmail);
	}

	@Override
	public boolean CheckCertification(String authNum) {
		boolean checker = userEmailDao.CheckCertificationNum(Integer.parseInt(authNum));
		return checker;
	}

	@Override
	public void DeleteInfo(UserEmail userEmail) {
		userEmailDao.DeleteCertification(userEmail);
	}

}
