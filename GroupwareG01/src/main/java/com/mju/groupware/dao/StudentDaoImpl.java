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
	private String StudentDoubleMajor;
	private String StudentGender;
	private String StudentMajor;
	private String StudentColleges;

	@Override
	public void InsertInformation(Student student) {
		sqlSession.insert("InsertInformation", student);
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
	public ArrayList<String> SelectStudentProfileInfo(String userID) {
		ArrayList<String> StudentInfo = new ArrayList<String>();
		if (!userID.equals("")) {
			List<Student> Output = this.sqlSession.selectList("SelectStudentProfileInfo", userID);
			if (Output == null) {

			} else {
				for (Student Item : Output) {
					StudentColleges = Item.getStudentColleges().toString();
					StudentMajor = Item.getStudentMajor().toString();
					StudentGrade = Item.getStudentGrade().toString();
				}

				StudentInfo.add(StudentColleges);
				StudentInfo.add(StudentMajor);
				StudentInfo.add(StudentGrade);
			}
		}
		return StudentInfo;
	}

	@Override
	public ArrayList<String> SelectMyPageUserInfo(String UserId) {
		ArrayList<String> Info = new ArrayList<String>();
		if (!UserId.equals("")) {
			List<Student> Output = this.sqlSession.selectList("SelectMyPageStudentInfo", UserId);
			if (Output == null) {

			} else {

				for (Student Item : Output) {
					StudentGrade = Item.getStudentGrade();
					if (Item.getStudentDoubleMajor() == null) {
						StudentDoubleMajor = "없음";
					} else {
						StudentDoubleMajor = Item.getStudentDoubleMajor();

					}
					StudentGender = Item.getStudentGender();
				}

				Info.add(StudentColleges);
				Info.add(StudentMajor);
				Info.add(StudentGrade);
				Info.add(StudentDoubleMajor);
				Info.add(StudentGender);

			}
		}
		return Info;
	}

	@Override
	public ArrayList<String> SelectMyPageUserInfoByID(String mysqlID) {
		ArrayList<String> Info = new ArrayList<String>();

		List<Student> Output = this.sqlSession.selectList("SelectMyPageStudentInfoByID", mysqlID);
		if (Output == null) {

		} else {
			for (Student Item : Output) {
				StudentColleges = Item.getStudentColleges().toString();
				StudentMajor = Item.getStudentMajor().toString();
				StudentGrade = Item.getStudentGrade().toString();
				StudentDoubleMajor = Item.getStudentDoubleMajor().toString();
				StudentGender = Item.getStudentGender();
			}

			Info.add(StudentColleges);
			Info.add(StudentMajor);
			Info.add(StudentGrade);
			Info.add(StudentDoubleMajor);
			Info.add(StudentGender);
		}
		return Info;
	}

	@Override
	public void updateUserGrade(Student student) {
		sqlSession.update("UpdateUserGrade", student);	
	}
}