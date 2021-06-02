package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.SearchKeyWord;
import com.mju.groupware.dto.User;

public interface SearchDao {

	List<User> SelectKeyWord(SearchKeyWord searchKeyWord);

}
