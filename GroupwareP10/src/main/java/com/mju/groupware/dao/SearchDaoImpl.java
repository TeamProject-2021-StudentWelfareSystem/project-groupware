package com.mju.groupware.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.SearchKeyWord;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserReview;

@Service
@Repository
public class SearchDaoImpl implements SearchDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<User> SelectKeyWord(SearchKeyWord searchKeyWord) {
		return sqlSession.selectList("SelectKeyWord", searchKeyWord);
	}

	@Override
	public Student SelectStudentInfo(int userID) {
		return sqlSession.selectOne("SelectStudentInfoForSearch", userID);
	}

	@Override
	public Professor SelectProfessorInfo(int userID) {
		return sqlSession.selectOne("SelectProfessorInfoForSearch",userID);
	}

	@Override
	public List<UserReview> SelectUserReview(String userID) {
		return sqlSession.selectList("SelectUserReview", userID);
	}

}
