package com.mju.groupware.dao;

import java.util.Map;

import com.mju.groupware.dto.Board;

public interface BoardDao {

	void InsertBoardInfo(Board board);

	void insertFile(Map<String, Object> map);

}
