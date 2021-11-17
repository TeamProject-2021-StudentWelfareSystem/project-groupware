package com.mju.groupware.service;

import java.util.List;

import com.mju.groupware.dto.Criteria;
import com.mju.groupware.dto.SearchKeyWord;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserReview;

public interface SearchService {

	public List<User> SelectKeyWord(SearchKeyWord searchKeyWord);

	public Student SelectStudentInfo(int i);

	public List<UserReview> SelectUserReview(String userID);

	public int CountTotalUserReview(String userID);

	public List<UserReview> SelectUserReviewPN(Criteria cri);

}
