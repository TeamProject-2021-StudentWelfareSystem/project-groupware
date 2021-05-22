package com.mju.groupware.service;

import java.util.List;

import com.mju.groupware.dto.Class;
import com.mju.groupware.dto.Team;
import com.mju.groupware.dto.TeamUser;
import com.mju.groupware.dto.User;

public interface TeamService {

	public void InsertTeamInfo(Team team);

	public int SelectClassID(Class classInfo);

	public int SelectUserIDForTeamUser(User user);

	public void InsertTeamUserInfo(TeamUser teamUser);

	public List<Class> SelectLectureInfo(String lectureName);

	public int SelectTeamLeaderUserID(String name);

}
