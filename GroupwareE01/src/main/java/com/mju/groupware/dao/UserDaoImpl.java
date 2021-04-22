package com.mju.groupware.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private int UserID;
	// 로그인 아이디
	private String ID;
	// 전화번호
	private String Tel;

	private String email;

	private boolean emailCheck;
	
	private String UserLoginID;

	@Override
	public void SignUp(User user) {
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
	public String currentPW(String id) {
		return this.sqlSession.selectOne("CurrentPW", id);
	}

	@Override
	public void modifyPW(User user) {
		this.sqlSession.selectOne("ModifyPW", user);
	}

	@Override
	public boolean pwCheckBeforeModify(String id, String pw) {

		// 추후 entity로 이동해야한다.
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String output = this.sqlSession.selectOne("pwCheckBeforeModify", id);
		if (output == null) {
			return false;
		} else {
			if (encoder.matches(pw, output)) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public ArrayList<String> SelectMyPageUserInformationList(String userId) {
		ArrayList<String> info = new ArrayList<String>();
		List<User> output = this.sqlSession.selectList("SelectMyPageUserInformationList", userId);
		if (output == null) {

		} else {
			for (User item : output) {
				UserID = item.getUserID();
				ID = item.getUserLoginID();
				Name = item.getUserName();
				Tel = item.getUserPhoneNum();
				email = item.getUserEmail();
			}
			info.add(Integer.toString(UserID));
			info.add(ID);
			info.add(Name);
			info.add(Tel);
			info.add(email);
		}
		return info;
	}

	@Override
	public ArrayList<String> SelectProfileUserInformationList(String id) {
		// 정보를 저장하기 위한 ArrayList
		ArrayList<String> ProfileUserInformation = new ArrayList<String>();
		// 학생정보를 가져오는 query문 실행
		List<User> output = this.sqlSession.selectList("SelectProfileUserInformationList", id);

		if (output == null) {

		} else {
			for (User item : output) {
				UserID = item.getUserID();
				Name = item.getUserName();
			}

			// 이름 0
			ProfileUserInformation.add(Name);
			// 아이디 1
			ProfileUserInformation.add(Integer.toString(UserID));
		}
		return ProfileUserInformation;
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
	public ArrayList<String> SelectUserID(String userID) {
		ArrayList<String> userInfo = new ArrayList<String>();
		List<User> output = sqlSession.selectList("SelectUserID", userID);
		if (output == null) {

		} else {
			for (User item : output) {
				UserID = item.getUserID();
				UserLoginID = item.getUserLoginID().toString();
			}

			// 아이디
			userInfo.add(Integer.toString(UserID));
			// 로그인 아이디(학번)
			userInfo.add(UserLoginID);
		}
		return userInfo;
	}

	@Override
	public void TemporaryPW(User user) {
		sqlSession.update("UpdateTempPW", user);
	}

	@Override
	public void withdrawl(String id) {
		sqlSession.update("UpdateWithdrawal", id);
	}

}