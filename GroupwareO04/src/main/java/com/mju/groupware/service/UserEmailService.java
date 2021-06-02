package com.mju.groupware.service;

import com.mju.groupware.dto.UserEmail;

public interface UserEmailService {

	// 메일 인증 정보 저장
	public void InsertCertification(UserEmail userEmail);

	// 디비에 저장된 인증번호랑 비교
	public boolean SelectForCheckCertification(String authNum);

	// 일정시간 후 인증번호 삭제
	public void DeleteInfo(UserEmail userEmail);

}
