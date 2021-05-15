package com.mju.groupware.dao;

import java.util.List;
import java.util.Map;

import com.mju.groupware.dto.Board;

public interface BoardDao {

	public void InsertBoardInfo(Board board);

	public void InsertFile(Map<String, Object> map);

	public List<Board> SelectCommunityBoardList();

	public void UpdateHitCount(String boardID);

	public void insertFile(Map<String, Object> map);

	public int SelectBoardID(Board board);

	public Board SelectOneCommunityContent(String boardID);

	public String SelectLoginUserID(String loginID);

	public void UpdateModifiedContent(Board board);

	public List<Map<String, Object>> SelectFileList(int BNo);

	public Map<String, Object> SelectFileInfo(Map<String, Object> map);

	

}
