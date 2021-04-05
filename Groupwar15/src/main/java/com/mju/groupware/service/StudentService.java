package com.mju.groupware.service;

import com.mju.groupware.dto.Student;

public interface StudentService {
	
	// 정보 저장
	public void SaveInformation(Student student);
	
	// 회원가입 후 userID(foreign key) 업데이트
	public void UpdateUserID(Student student);

}
