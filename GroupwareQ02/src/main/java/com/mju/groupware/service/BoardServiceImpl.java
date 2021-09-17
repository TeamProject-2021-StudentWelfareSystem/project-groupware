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
import com.mju.groupware.dto.TeamBoard;
import com.mju.groupware.util.BFileUtils;
import com.mju.groupware.util.TeamFileUtils;

@Service
public class BoardServiceImpl implements BoardService {
	@Resource(name = "fileUtils")
	private BFileUtils BfileUtils;

	@Resource(name = "TfileUtils")
	private TeamFileUtils TeamFileUtils;

	@Autowired
	BoardDao boardDao;

	@Override
	public List<Board> SelectCommunityBoardList() {
		return boardDao.SelectCommunityBoardList();
	}

	@Override
	public List<Board> SelectNoticeBoardList() {
		return boardDao.SelectNoticeBoardList();
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

			int BNo = board.getBoardID();
			board.setBno(BNo);
			List<Map<String, Object>> List = BfileUtils.InsertFileInfo(board, multipartHttpServletRequest);
			for (int i = 0, size = List.size(); i < size; i++) {
				boardDao.insertFile(List.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void InsertTeamDocument(TeamBoard teamBoard, HttpServletRequest request) {
		boardDao.InsertTeamDocument(teamBoard);
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		try {

			int TeamBoardID = teamBoard.getTBoardID();
			teamBoard.setTBno(TeamBoardID);
			List<Map<String, Object>> List = TeamFileUtils.InsertTeamFileInfo(teamBoard, multipartHttpServletRequest);
			for (int i = 0, Size = List.size(); i < Size; i++) {
				boardDao.InsertTeamFileInfo(List.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Board SelectOneCommunityContent(String boardID) {
		return boardDao.SelectOneCommunityContent(boardID);
	}

	@Override
	public Board SelectOneNoticeContent(String boardID) {
		return boardDao.SelectOneCommunityContent(boardID);
	}

	@Override
	public String SelectLoginUserID(String loginID) {
		return boardDao.SelectLoginUserID(loginID);
	}

	@Override
	public void DeleteCommunity(int boardID) {
		boardDao.DeleteCommunity(boardID);
	}

	@Override
	public void DeleteNotice(int boardID) {
		boardDao.DeleteNotice(boardID);
	}

	@Override
	public void UpdateModifiedContent(Board board, String[] FileList, String[] fileNameList,
			HttpServletRequest request) {
		boardDao.UpdateModifiedContent(board);
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

		List<Map<String, Object>> List;
		try {
			List = BfileUtils.UpdateFileInfo(board, FileList, fileNameList, multipartHttpServletRequest);
			Map<String, Object> TempMap = null;
			int Size = List.size();
			for (int i = 0; i < Size; i++) {
				TempMap = List.get(i);
				// 여기일단조심
				if (TempMap.get("IsNew").equals("1")) {
					boardDao.InsertFile(TempMap);
				} else {
					boardDao.UpdateFile(TempMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void UpdateTeamBoardModifiedContent(TeamBoard teamBoard, String[] fileList, String[] fileNameList,
			HttpServletRequest request) {

		boardDao.UpdateTeamBoardModifiedContent(teamBoard);

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

		List<Map<String, Object>> List;
		try {
			List = TeamFileUtils.UpdateTeamBoardFileInfo(teamBoard, fileList, fileNameList,
					multipartHttpServletRequest);
			Map<String, Object> TempMap = null;
			int Size = List.size();
			for (int i = 0; i < Size; i++) {
				TempMap = List.get(i);
				// 여기일단조심
				if (TempMap.get("IsNew").equals("1")) {
					boardDao.InsertTeamFile(TempMap);
				} else {
					boardDao.UpdateTeamFile(TempMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Map<String, Object>> SelectCommunityFileList(int BNo) {
		List<Map<String, Object>> SelectCommunityFileList = boardDao.SelectCommunityFileList(BNo);
		return SelectCommunityFileList;
	}

	@Override
	public List<Map<String, Object>> SelectTeamBoardFileList(int BNo) {
		List<Map<String, Object>> SelectTeamBoardFileList = boardDao.SelectTeamBoardFileList(BNo);
		return SelectTeamBoardFileList;
	}

	@Override
	public Map<String, Object> SelectCommunityFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectCommunityFileInfo = boardDao.SelectCommunityFileInfo(map);
		return SelectCommunityFileInfo;
	}

	@Override
	public List<Map<String, Object>> SelectNoticeFileList(int BNo) {
		List<Map<String, Object>> SelectNoticeFileList = boardDao.SelectNoticeFileList(BNo);
		return SelectNoticeFileList;
	}

	@Override
	public Map<String, Object> SelectNoticeFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectNoticeFileInfo = boardDao.SelectNoticeFileInfo(map);
		return SelectNoticeFileInfo;
	}

	@Override
	public void UpdateBoardDelete(int boardID) {
		boardDao.UpdateBoardDelete(boardID);
	}

	@Override
	public List<TeamBoard> SelectTeamBoardList() {
		return boardDao.SelectTeamBoardList();
	}

	@Override
	public TeamBoard SelectTeamBoardContent(String tBoardID) {
		return boardDao.SelectTeamBoardContent(tBoardID);
	}

	@Override
	public void UpdateTBoardDelete(int tBoardID) {
		boardDao.UpdateTBoardDelete(tBoardID);
	}

	@Override
	public String SelectWriterID(TeamBoard teamBoard) {
		return boardDao.SelectWriterID(teamBoard);
	}

	@Override
	public Map<String, Object> SelectTeamBoardFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectCommunityFileInfo = boardDao.SelectTeamBoardFileInfo(map);
		return SelectCommunityFileInfo;
	}

	@Override
	public List<Board> SelectMyBoardList(String login) {
		return boardDao.SelectMyBoardList(login);
	}

}
