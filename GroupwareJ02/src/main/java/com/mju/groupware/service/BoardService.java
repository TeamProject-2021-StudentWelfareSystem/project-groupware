package com.mju.groupware.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mju.groupware.dto.Board;

public interface BoardService {

	void InsertBoardInfo(Board board, MultipartHttpServletRequest mpRequest);

}
