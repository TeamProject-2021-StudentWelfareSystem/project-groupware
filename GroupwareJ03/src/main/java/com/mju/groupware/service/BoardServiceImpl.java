package com.mju.groupware.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mju.groupware.dao.BoardDao;
import com.mju.groupware.dto.Board;
import com.mju.groupware.util.BFileUtils;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;

//	@Override
//	public void InsertBoardInfo(Board board, MultipartHttpServletRequest mpRequest) {
//		// 게시판 글 입력 정보 Insert
//		
//
//		BFileUtils BFileUtils = new BFileUtils();
//		try {
//
//			List<Map<String, Object>> list = BFileUtils.InsertFileInfo(board, mpRequest);
//			if (list.isEmpty()) {
//				System.out.println("정보가없습니다.");
//			} else {
//				int size = list.size();
//				// 여러개의 첨부파일을 업로드하기위해서
//				for (int i = 0; i < size; i++) {
//					// 파일정보 insert
//					boardDao.InsertFile(list.get(i));
//				}
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	@Override
	public void InsertBoardInfoOnlyText(Board board) {
		boardDao.InsertBoardInfo(board);
	}

}
