package com.mju.groupware.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mju.groupware.dao.BoardDao;
import com.mju.groupware.dto.Board;
import com.mju.groupware.util.BFileUtils;

@Service
public class BoardServiceImpl implements BoardService {
	@Resource(name = "fileUtils")
	private BFileUtils BfileUtils;
	@Autowired
	BoardDao boardDao;

	@Override
	public List<Board> SelectCommunityBoardList() {
		return boardDao.SelectCommunityBoardList();
	}

	@Override
	public void UpdateHitCount(String boardID) {
		boardDao.UpdateHitCount(boardID);
	}

	@Override
	public void InsertBoard(Board board, HttpServletRequest request) {
		boardDao.InsertBoardInfo(board);
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

		try {
			
			int BNo = boardDao.SelectBoardID(board);
			board.setBno(BNo);
			List<Map<String, Object>> list = BfileUtils.InsertFileInfo(board, multipartHttpServletRequest);
			for (int i = 0, size = list.size(); i < size; i++) {
				boardDao.insertFile(list.get(i));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
