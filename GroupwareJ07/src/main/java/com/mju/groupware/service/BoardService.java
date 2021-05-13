package com.mju.groupware.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mju.groupware.dto.Board;

public interface BoardService {

//	void InsertBoardInfo(Board board, MultipartHttpServletRequest mpRequest);

	void InsertBoard(Board board, HttpServletRequest request);

	List<Board> SelectCommunityBoardList();

	void UpdateHitCount(String boardID);

}
