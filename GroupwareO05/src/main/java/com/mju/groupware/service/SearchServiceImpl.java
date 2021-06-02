package com.mju.groupware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.SearchDao;
import com.mju.groupware.dto.Professor;
import com.mju.groupware.dto.SearchKeyWord;
import com.mju.groupware.dto.Student;
import com.mju.groupware.dto.User;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;

	@Override
	public List<User> SelectKeyWord(SearchKeyWord searchKeyWord) {
		// TODO Auto-generated method stub
		return searchDao.SelectKeyWord(searchKeyWord);
	}

	@Override
	public Student SelectStudentInfo(int userID) {
	
		return searchDao.SelectStudentInfo(userID);
	}

	@Override
	public Professor SelectProfessorInfo(int userID) {
		// TODO Auto-generated method stub
		return searchDao.SelectProfessorInfo(userID);
	}

}
