package com.mju.groupware.dao;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.constant.ConstantAdminStudentDao;
import com.mju.groupware.dto.Student;

@Service
@Repository
public class StudentDaoImpl implements StudentDao {
	private ConstantAdminStudentDao Constant;

	@SuppressWarnings("resource")
	public StudentDaoImpl() {
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/StudentDao.xml");
		CTX.refresh();
		this.Constant = (ConstantAdminStudentDao) CTX.getBean("StudentDaoID");
	}

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
		sqlSession.insert(this.Constant.getInsertInformation(), student);
	}

	@Override
	public void UpdateUserID(Student student) {
		sqlSession.update(this.Constant.getUpdateUserID(), student);
	}

	@Override
	public String getGrade(String userId) {
		if (!(userId).equals("")) {
			StudentGrade = this.sqlSession.selectOne(this.Constant.getGetGrade(), userId);
		}
		return StudentGrade;
	}

	@Override
	public void UpdateStudentGender(Student student) {
		// Gender변경
		sqlSession.update(this.Constant.getUpdateStudentGender(), student);
	}

	@Override
	public void UpdateStudentGrade(Student student) {
		// grade
		sqlSession.update(this.Constant.getUpdateStudentGrade(), student);
	}

	@Override
	public void UpdateStudentDobuleMajor(Student student) {
		sqlSession.update(this.Constant.getUpdateStudentDoubleMajor(), student);
	}

	@Override
	public ArrayList<String> SelectStudentProfileInfo(String userID) {
		ArrayList<String> StudentInfo = new ArrayList<String>();
		if (!userID.equals("")) {
			List<Student> Output = this.sqlSession.selectList(this.Constant.getSelectStudentProfileInfo(), userID);
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
	public ArrayList<String> SelectMyPageUserInfo(String userId) {
		ArrayList<String> Info = new ArrayList<String>();
		if (!userId.equals("")) {
			List<Student> Output = this.sqlSession.selectList(this.Constant.getSelectMyPageStudentInfo(), userId);
			if (Output == null) {

			} else {

				for (Student Item : Output) {
					StudentGrade = Item.getStudentGrade();
					if (Item.getStudentDoubleMajor() == null) {
						StudentDoubleMajor = this.Constant.getNoDoubleMajor();
						try {
							StudentDoubleMajor = new String(StudentDoubleMajor.getBytes("iso-8859-1"), "utf-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
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

		List<Student> Output = this.sqlSession.selectList(this.Constant.getSelectMyPageStudentInfoByID(), mysqlID);
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
	public void UpdateStudentColleges(Student student) {
		sqlSession.update(this.Constant.getUpdateStudentColleges(), student);
	}

	@Override
	public void UpdateStudentMajor(Student student) {
		sqlSession.update(this.Constant.getUpdateStudentMajor(), student);
	}

	@Override
	public Student SelectStudentInfo(String userID) {
		Student Output = sqlSession.selectOne(this.Constant.getSelectStudentInfo(), userID);
		return Output;
	}

	@Override
	public void InsertWithdrawalStudent(Student student) {
		sqlSession.insert(this.Constant.getInsertWithdrawalStudent(), student);
	}

	@Override
	public void DeleteWithdrawalStudent(Student student) {
		sqlSession.delete(this.Constant.getDeleteWithdrawalStudent(), student);
	}

	@Override
	public void DeleteWithdrawalStudentList(String userID) {
		sqlSession.delete(this.Constant.getDeleteWithdrawalStudentList(), userID);
	}

	@Override
	public void UpdateStudentLoginDate(Student student) {
		sqlSession.update("UpdateStudentLoginDate", student);
	}

	@Override
	public Student SelectModifyStudentInfo(int userID) {
		Student student = sqlSession.selectOne("SelectModifyStudentInfo", userID);
		return student;
	}
}