package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.Criteria;
import com.mju.groupware.dto.SearchKeyWord;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserReview;

public interface SearchDao {

	List<User> SelectKeyWord(SearchKeyWord searchKeyWord);

	Student SelectStudentInfo(int userID);

	List<UserReview> SelectUserReview(String userID);

	int CountTotalUserReview(String userID);

	List<UserReview> SelectUserReviewPN(Criteria cri);

}
