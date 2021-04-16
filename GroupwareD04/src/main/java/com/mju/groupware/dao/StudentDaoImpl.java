package com.mju.groupware.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Student;

@Service
@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	// 학년
	private String StudentGrade;
	private String DoubleMajor;
	private String StudentGender;
	private String StudentMajor;
	private String StudentColleges;

	@Override
	public void SaveInformation(Student student) {
		sqlSession.insert("StudentInsert", student);
	}

	@Override
	public void UpdateUserID(Student student) {
		sqlSession.update("UpdateUserID", student);
	}

	@Override
	public String getGrade(String UserId) {
		if (!(UserId).equals("")) {
			StudentGrade = this.sqlSession.selectOne("GetGrade", UserId);

		}
		return StudentGrade;
	}

	@Override
	public ArrayList<String> GetMyPageUserInfo(String UserId) {
		ArrayList<String> info = new ArrayList<String>();
		if (!UserId.equals("")) {
			List<Student> output = this.sqlSession.selectList("GetMyPageUserInfo", UserId);
			if (output == null) {

			} else {

				for (Student item : output) {
					StudentGrade = item.getStudentGrade();
					if (item.getStudentDoubleMajor() == null) {
						DoubleMajor = "없음";
					} else {
						DoubleMajor = item.getStudentDoubleMajor();

					}
					StudentGender = item.getStudentGender();
				}

				info.add(StudentColleges);
				info.add(StudentMajor);
				info.add(StudentGrade);
				info.add(DoubleMajor);
				info.add(StudentGender);

			}
		}
		return info;
	}

	@Override
	public void UpdateStudentGender(Student student) {
		// Gender변경
		sqlSession.update("UpdateStudentGender", student);
	}

	@Override
	public void UpdateStudentGrade(Student student) {
		sqlSession.update("UpdateStudentGrade", student);
	}

	@Override
	public void UpdateStudentDobuleMajor(Student student) {
		sqlSession.update("UpdateStudentDoubleMajor", student);
	}

	@Override
	public ArrayList<String> GetProfileStudentInfo(String userID) {
		ArrayList<String> studentInfo = new ArrayList<String>();
		if (!userID.equals("")) {
			List<Student> output = this.sqlSession.selectList("GetProfileStudentInfo", userID);
			if (output == null) {

			} else {
				for (Student item : output) {
					StudentColleges = item.getStudentColleges().toString();
					StudentMajor = item.getStudentMajor().toString();
					StudentGrade = item.getStudentGrade().toString();
				}

				studentInfo.add(StudentColleges);
				studentInfo.add(StudentMajor);
				studentInfo.add(StudentGrade);
			}
		}
		return studentInfo;
	}


}
