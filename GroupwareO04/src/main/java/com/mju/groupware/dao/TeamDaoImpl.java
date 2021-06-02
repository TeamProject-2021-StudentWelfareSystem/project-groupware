package com.mju.groupware.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Class;
import com.mju.groupware.dto.Team;
import com.mju.groupware.dto.TeamBoard;
import com.mju.groupware.dto.TeamUser;
import com.mju.groupware.dto.User;

@Service
@Repository
public class TeamDaoImpl implements TeamDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void InsertTeamInfo(Team team) {
		sqlSession.insert("InsertTeamInfo", team);
	}

	@Override
	public int SelectClassID(Class classInfo) {
		int Output = sqlSession.selectOne("SelectClassID", classInfo);
		return Output;
	}

	@Override
	public int SelectUserIDForTeamUser(User user) {
		Integer Output = sqlSession.selectOne("SelectUserIDForTeamUser", user);
		if (Output == null) {
			return 0;
		} else {
			return Output;

		}
	}

	@Override
	public void InsertTeamUserInfo(TeamUser teamUser) {
		sqlSession.insert("InsertTeamUserInfo", teamUser);
	}

	@Override
	public List<Class> SelectLectureInfo(String lectureName) {
		List<Class> LectureInfo = sqlSession.selectList("SelectLectureInformation", lectureName);
		return LectureInfo;
	}

	@Override
	public int SelectTeamLeaderUserID(String name) {
		return sqlSession.selectOne("SelectTeamLeaderUserID", name);
	}

	@Override
	public List<Team> SelectTeamList() {

		List<Team> SelectTeamList = sqlSession.selectList("SelectTeamList");

		return SelectTeamList;
	}

	@Override
	public Class SelectClassList(int classID) {
		Class SelectClassList = sqlSession.selectOne("SelectClassList", classID);
		return SelectClassList;
	}

	@Override
	public int SelectClassIDForCheckTeam(int teamID) {
		return sqlSession.selectOne("SelectClassIDForCheckTeam", teamID);
	}

	@Override
	public List<Class> SelectClassInfoForCheckTeam(int classID) {
		return sqlSession.selectList("SelectClassInfoForCheckTeam", classID);
	}

	@Override
	public String SelectTeamName(int teamID) {
		return sqlSession.selectOne("SelectTeamName", teamID);
	}

	@Override
	public List<TeamUser> SelectTeamMemberInfo(int teamID) {
		return sqlSession.selectList("SelectTeamMemberInfo", teamID);
	}

	@Override
	public String SelectLeaderName(int userID) {
		return sqlSession.selectOne("SelectLeaderName", userID);
	}

	@Override
	public String SelectLeaderLoginID(int userID) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("SelectLeaderLoginID", userID);
	}

	@Override
	public List<TeamUser> SelectMyTeamList(String loginID) {
		List<TeamUser> SelectMyTeamList = sqlSession.selectList("SelectMyTeamList", loginID);

		return SelectMyTeamList;
	}

	@Override
	public void DeleteTeamMemberInfo(int teamID) {
		sqlSession.delete("DeleteTeamMemberInfo", teamID);
	}

//	@Override
//	public String SelectUserID(String loginID) {
//		return sqlSession.selectOne("SelectUserIDInTeam", loginID);
//	}

	@Override
	public List<Team> SelectMyTeamInfo(int teamID) {
		List<Team> teamInfo = sqlSession.selectList("SelectMyTeamInfo", teamID);
		return teamInfo;
	}

	@Override
	public List<Class> SelectClassInfo(int classID) {
		List<Class> SelectClassInfo = sqlSession.selectList("SelectClassInfo", classID);
		return SelectClassInfo;
	}

//	@Override
//	public List<TeamBoard> SelectTeamFileInfo(String TBoardSubject) {
//		List<TeamBoard> SelectTeamFileInfo = sqlSession.selectList("SelectTeamFileInfo", TBoardSubject);
//		return SelectTeamFileInfo;
//	}

	@Override
	public List<TeamBoard> SelectTeamBoardListInfo(String TeamID) {
		List<TeamBoard> SelectTeamBoardInfo = sqlSession.selectList("SelectTeamBoardListInfo", TeamID);
		return SelectTeamBoardInfo;
	}

	@Override
	public String SelectTeamIDForDocument(String userID) {
		String Output = sqlSession.selectOne("SelectTeamIDForDocument", userID);
		return Output;
	}

	@Override
	public String SelectTeamIDForDelete(String tUserID) {
		return sqlSession.selectOne("SelectTeamIDForDelete", tUserID);
	}

}