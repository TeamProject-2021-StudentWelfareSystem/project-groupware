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
	private String UserName;
	// 아이디
	private int UserID;
	// 로그인 아이디
	private String UserLoginID;
	// 전화번호
	private String UserPhoneNum;

	private String UserEmail;

	private boolean EmailCheck;

	@Override
	public void InsertForSignUp(User user) {
		this.sqlSession.insert("InsertUser", user);
	}

	@Override
	public UsersDetails selectByLoginID(String userLoginID) {
		UsersDetails users = this.sqlSession.selectOne("SelectByLoginID", userLoginID);
		return users;
	}

	@Override
	public boolean SelctForIDConfirm(User user) {
		User Output = this.sqlSession.selectOne("SelctForIDConfirm", user);

		if (Output == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean SelectPwdForConfirmToFindPwd(User user) {
		User Output = this.sqlSession.selectOne("SelectPwdForConfirmToFindPwd", user);

		if (Output == null) {
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
	public boolean SelectForShowPassword(User user) {
		User Output = this.sqlSession.selectOne("SelectForShowPassword", user);
		if (Output == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean SelectForEmailDuplicateCheck(User user) {
		User Output = sqlSession.selectOne("SelectForEmailDuplicateCheck", user.getUserEmail());

		if (Output == null) {
			EmailCheck = false;
		} else {
			EmailCheck = true;
		}

		return EmailCheck;
	}

	@Override
	public void UpdateLoginDate(User user) {
		this.sqlSession.selectOne("UpdateLoginDate", user);
	}

	@Override
	public String SelectCurrentPwd(String id) {
		return this.sqlSession.selectOne("SelectCurrentPwd", id);
	}

	@Override
	public void UpdatePwd(User user) {
		this.sqlSession.update("UpdatePwd", user);
	}

	@Override
	public boolean SelectForPwdCheckBeforeModify(String id, String pwd) {

		// 추후 entity로 이동해야한다.
		BCryptPasswordEncoder Encoder = new BCryptPasswordEncoder();
		String Output = this.sqlSession.selectOne("SelectForPwdCheckBeforeModify", id);
		if (Output == null) {
			return false;
		} else {
			if (Encoder.matches(pwd, Output)) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public ArrayList<String> SelectMyPageUserInfo(String userId) {
		ArrayList<String> Info = new ArrayList<String>();
		List<User> Output = this.sqlSession.selectList("SelectMyPageInfo", userId);
		if (Output == null) {

		} else {
			for (User Item : Output) {
				UserID = Item.getUserID();
				UserLoginID = Item.getUserLoginID();
				UserName = Item.getUserName();
				UserPhoneNum = Item.getUserPhoneNum();
				UserEmail = Item.getUserEmail();
			}
			Info.add(Integer.toString(UserID));
			Info.add(UserLoginID);
			Info.add(UserName);
			Info.add(UserPhoneNum);
			Info.add(UserEmail);
		}
		return Info;
	}

	@Override
	public ArrayList<String> SelectUserProfileInfo(String id) {
		// 정보를 저장하기 위한 ArrayList
		ArrayList<String> Info = new ArrayList<String>();
		// 학생정보를 가져오는 query문 실행
		List<User> Output = this.sqlSession.selectList("SelectUserInfo", id);

		if (Output == null) {

		} else {
			for (User Item : Output) {
				UserID = Item.getUserID();
				UserName = Item.getUserName();
			}

			// 이름 0
			Info.add(UserName);
			// 아이디 1
			Info.add(Integer.toString(UserID));
		}
		return Info;
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
	public ArrayList<String> SelectUserInformation(String userId) {
		ArrayList<String> UserInfo = new ArrayList<String>();
		List<User> Output = sqlSession.selectList("SelectUserInformation", userId);
		if (Output == null) {

		} else {
			for (User Item : Output) {
				UserID = Item.getUserID();
				UserLoginID = Item.getUserLoginID().toString();
			}

			// 아이디
			UserInfo.add(Integer.toString(UserID));
			// 로그인 아이디(학번)
			UserInfo.add(UserLoginID);
		}
		return UserInfo;
	}

	@Override
	public void UpdateTemporaryPwd(User user) {
		sqlSession.update("UpdateTemporaryPwd", user);
	}

	@Override
	public void UpdateWithdrawlUser(String id) {
		sqlSession.update("UpdateWithdrawal", id);
	}

}