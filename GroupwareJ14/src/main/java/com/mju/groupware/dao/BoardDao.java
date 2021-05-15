package com.mju.groupware.dao;

import java.util.List;
import java.util.Map;

import com.mju.groupware.dto.Board;

public interface BoardDao {

	void InsertBoardInfo(Board board);

	void InsertFile(Map<String, Object> map);

	List<Board> SelectCommunityBoardList();

	void UpdateHitCount(String boardID);

	void insertFile(Map<String, Object> map);

	int SelectBoardID(Board board);

	Board SelectOneCommunityContent(String boardID);

	String SelectLoginUserID(String loginID);

	void UpdateModifiedContent(Board board);

	void DeleteCommunity(int boardID);

}
