package com.mju.groupware.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mju.groupware.dto.Board;

public interface BoardService {

	public void InsertBoard(Board board, HttpServletRequest request);

	public List<Board> SelectCommunityBoardList();

	public void UpdateHitCount(String boardID);

	public Board SelectOneCommunityContent(String boardID);

	public void UpdateModifiedContent(Board board);

	public String SelectLoginUserID(String loginID);

	public List<Map<String,Object>> SelectFileList(int BNo);
}
