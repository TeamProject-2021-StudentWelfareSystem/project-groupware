package com.mju.groupware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.TeamDao;
import com.mju.groupware.dto.Class;
import com.mju.groupware.dto.Team;
import com.mju.groupware.dto.TeamUser;
import com.mju.groupware.dto.User;

@Service
public class TeamServiceImpl implements TeamService {
   @Autowired
   TeamDao teamDao;

   @Override
   public void InsertTeamInfo(Team team) {
      teamDao.InsertTeamInfo(team);
   }

   @Override
   public int SelectClassID(Class classInfo) {
      return teamDao.SelectClassID(classInfo);
   }

   @Override
   public int SelectUserIDForTeamUser(User user) {
      return teamDao.SelectUserIDForTeamUser(user);
   }

   @Override
   public void InsertTeamUserInfo(TeamUser teamUser) {
      teamDao.InsertTeamUserInfo(teamUser);
   }

   @Override
   public List<Class> SelectLectureInfo(String lectureName) {
      List<Class> LectureInfo = teamDao.SelectLectureInfo(lectureName);
      return LectureInfo;
   }

   @Override
   public int SelectTeamLeaderUserID(String name) {
      return teamDao.SelectTeamLeaderUserID(name);
   }

   @Override
   public List<Team> SelectTeamList() {
      List<Team> SelectTeamList = teamDao.SelectTeamList();
      return SelectTeamList;
   }

   @Override
   public Class SelectClassList(int classID) {
      Class SelectClassList = teamDao.SelectClassList(classID);
      return SelectClassList;
   }

   @Override
   public int SelectClassIDForCheckTeam(int teamID) {
      return teamDao.SelectClassIDForCheckTeam(teamID);
   }

   @Override
   public List<Class> SelectClassInfoForCheckTeam(int classID) {
      return teamDao.SelectClassInfoForCheckTeam(classID);
   }

   @Override
   public String SelectTeamName(int teamID) {
      return teamDao.SelectTeamName(teamID);
   }

   @Override
   public List<TeamUser> SelectTeamMemberInfo(int teamID) {
      return teamDao.SelectTeamMemberInfo(teamID);
   }

   @Override
   public String SelectLeaderName(int userID) {
      return teamDao.SelectLeaderName(userID);
   }

   @Override
   public String SelectLeaderLoginID(int userID) {
      return teamDao.SelectLeaderLoginID(userID);
   }

   @Override
   public List<Team> SelectMyTeamList(String loginID) {

      return teamDao.SelectMyTeamList(loginID);
   }

}