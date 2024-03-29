package com.mju.groupware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.SearchDao;
import com.mju.groupware.dto.Criteria;
import com.mju.groupware.dto.SearchKeyWord;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserReview;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;

	@Override
	public List<User> SelectKeyWord(SearchKeyWord searchKeyWord) {
		return searchDao.SelectKeyWord(searchKeyWord);
	}

	@Override
	public Student SelectStudentInfo(int userID) {
		return searchDao.SelectStudentInfo(userID);
	}

	@Override
	public List<UserReview> SelectUserReview(String userID) {
		return searchDao.SelectUserReview(userID);
	}

	@Override
	public int CountTotalUserReview(String userID) {
		return searchDao.CountTotalUserReview(userID);
	}

	@Override
	public List<UserReview> SelectUserReviewPN(Criteria cri) {
		return searchDao.SelectUserReviewPN(cri);
	}

}
