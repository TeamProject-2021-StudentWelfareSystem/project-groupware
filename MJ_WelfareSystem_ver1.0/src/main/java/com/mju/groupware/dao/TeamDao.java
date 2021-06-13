package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.Class;
import com.mju.groupware.dto.Team;
import com.mju.groupware.dto.TeamBoard;
import com.mju.groupware.dto.TeamUser;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserReview;

public interface TeamDao {

	public void InsertTeamInfo(Team team);

	public int SelectClassID(Class classInfo);

	public int SelectUserIDForTeamUser(User user);

	public void InsertTeamUserInfo(TeamUser teamUser);

	public List<Class> SelectLectureInfo(String lectureName);

	public int SelectTeamLeaderUserID(String name);

	public List<Team> SelectTeamList();

	public Class SelectClassList(int classID);

	public int SelectClassIDForCheckTeam(int teamID);

	public List<Class> SelectClassInfoForCheckTeam(int classID);

	public String SelectTeamName(int teamID);

	public List<TeamUser> SelectTeamMemberInfo(int teamID);

	public String SelectLeaderName(int userID);

	public String SelectLeaderLoginID(int userID);

	public List<TeamUser> SelectMyTeamList(String loginID);

	public void DeleteTeamMemberInfo(int teamID);

	public List<Team> SelectMyTeamInfo(int teamID);

	public List<Class> SelectClassInfo(int classID);

	public List<TeamBoard> SelectTeamBoardListInfo(String teamID);

	public String SelectTeamIDForDocument(String userID);

	public String SelectTeamIDForDelete(String tUserID);

	public Integer SelectClassIDFromTeam(Integer teamID);

	public List<Integer> SelectTeamNameWithLoginUser(String name);

	public String SelectTeamIDForReview(String teamName);

	public List<TeamUser> SelectTeamMember(String teamID);

	public String SelectTeamUserID(String userLoginID);

	public void InsertUserReview(UserReview userReview);

	public String SelectTeamLeaderLoginID(String teamID);

	public void DeleteTeam(String teamID);

	public String SelectWriterUserID(String name);

	public int SelectColumnCount(UserReview userReview);

	public String SelectTeamNameWithTeamID(int teamID);

}
