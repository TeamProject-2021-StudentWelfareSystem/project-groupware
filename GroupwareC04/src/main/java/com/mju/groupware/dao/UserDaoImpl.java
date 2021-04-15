package com.mju.groupware.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.security.UsersDetails;

@Service
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	// 이름
	private String Name;
	// 소속
	private String SC;
	// 아이디
	private int UserId;
	// 로그인 아이디
	private String Id;
	// 전화번호
	private String Tel;

	private String email;

	private boolean emailCheck;
	// 전공
	private String UserMajor;
	
	private String UserLoginID;

	@Override
	public void SignUp(User user) {
		System.out.println(user.getStudentColleges());
		System.out.println(user.getStudentMajor());
		this.sqlSession.insert("InsertUser", user);
	}

	@Override
	public UsersDetails selectByLoginID(String userLoginID) {
		UsersDetails users = this.sqlSession.selectOne("SelectUser", userLoginID);
		return users;
	}

	@Override
	public boolean IdConfirm(User user) {

		User output = this.sqlSession.selectOne("UserIdConfirm", user);

		if (output == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean PwdConfirm(User user) {
		User output = this.sqlSession.selectOne("UserPwdConfirm", user);

		if (output == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public int SelectUserID(Student student) {
		return this.sqlSession.selectOne("SelectUserID", student);
	}

	@Override
	public boolean ShowPassword(User user) {
		User output = this.sqlSession.selectOne("UserPwdShow", user);
		System.out.println("비밀번호 : " + output);
		if (output == null) {
			return false;
		} else {

			return true;
		}
	}

	@Override
	public boolean EmailDuplicateCheck(User user) {
		User output = sqlSession.selectOne("EmailCheck", user.getUserEmail());

		if (output == null) {
			emailCheck = false;
		} else {
			emailCheck = true;
		}

		return emailCheck;
	}

	@Override
	public void DateUpdate(User user) {
		this.sqlSession.selectOne("UpdateLoginDate", user);
	}

	@Override
	public ArrayList<String> getUserInfo(String id) {
		ArrayList<String> info = new ArrayList<String>();
		List<User> output = this.sqlSession.selectList("GetInfo", id);

		if (output == null) {

		} else {
			for (User item : output) {
				UserId = item.getUserID();
				Name = item.getUserName();
		//		UserMajor = item.getUserMajor().name().toString();
		//		SC = item.getUserColleges().name().toString();
			}

			// 이름 0
			info.add(Name);
			// 단과대 1
			info.add(SC);
			// 학과 2
			info.add(UserMajor);
			// 아이디 3
			info.add(Integer.toString(UserId));
		}
		return info;
	}

	@Override
	public String currentPW(String id) {
		return this.sqlSession.selectOne("CurrentPW", id);
	}

	@Override
	public void modifyPW(User user) {
		this.sqlSession.selectOne("ModifyPW", user);
	}

	@Override
	public boolean pwCheckBeforeModify(String pw) {
		String output = this.sqlSession.selectOne("pwCheckBeforeModify", pw);
		if (output == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public ArrayList<String> GetMyPageUserInfo(String userId) {
		ArrayList<String> info = new ArrayList<String>();
		List<User> output = this.sqlSession.selectList("GetMyPageInfo", userId);
		if (output == null) {

		} else {
			for (User item : output) {
				UserId = item.getUserID();
				Id = item.getUserLoginID();
				Name = item.getUserName();
				Tel = item.getUserPhoneNum();
		//		SC = item.getUserColleges().name().toString();
		//		UserMajor = item.getUserMajor().name().toString();
				email = item.getUserEmail();
			}
			info.add(Integer.toString(UserId));
			info.add(Id);
			info.add(Name);
			info.add(Tel);
			info.add(SC);
			info.add(UserMajor);
			info.add(email);
		}
		return info;
	}

	@Override
	public ArrayList<String> GetProfileUserInfo(String id) {
		// 정보를 저장하기 위한 ArrayList
		ArrayList<String> info = new ArrayList<String>();
		// 학생정보를 가져오는 query문 실행
		List<User> output = this.sqlSession.selectList("GetProfileUserInfo", id);

		if (output == null) {

		} else {
			for (User item : output) {
				UserId = item.getUserID();
				Name = item.getUserName();
	//			UserMajor = item.getUserMajor().name().toString();
	//			SC = item.getUserColleges().name().toString();
			}

			// 이름 0
			info.add(Name);
			// 단과대 1
			info.add(SC);
			// 학과 2
			info.add(UserMajor);
			// 아이디 3
			info.add(Integer.toString(UserId));
		}
		return info;
	}

	@Override
	public void updateUserPhoneNumber(User user) {
		sqlSession.update("UpdateUserPhoneNum", user);
	}

	@Override
	public void updateUserMajor(User user) {
		sqlSession.update("UpdateUserMajor", user);
	}

	@Override
	public void updateUserColleges(User user) {
		sqlSession.update("UpdateUserColleges", user);
	}

	@Override
	public ArrayList<String> GetUser(String userId) {
		ArrayList<String> userInfo = new ArrayList<String>();
		List<User> output = sqlSession.selectList("GetUser", userId);
		if (output == null) {

		} else {
			for (User item : output) {
				UserId = item.getUserID();
				UserLoginID = item.getUserLoginID().toString();
			}

			// 아이디
			userInfo.add(Integer.toString(UserId));
			// 로그인 아이디(학번)
			userInfo.add(UserLoginID);
		}
		return userInfo;
	}

}