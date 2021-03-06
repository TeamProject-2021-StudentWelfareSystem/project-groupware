package com.mju.groupware.dao;

import com.mju.groupware.dto.UserEmail;

public interface UserEmailDao {

	public void SaveCertification(UserEmail userEmail);

	// 인증번호 비교(디비)
	public boolean CheckCertificationNum(int authNum);

	// 일정 시간 후 인증번호 삭제
	public void DeleteCertification(UserEmail userEmail);

}
