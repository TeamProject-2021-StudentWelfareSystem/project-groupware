package com.mju.groupware.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.constant.ConstantTeamDao;
import com.mju.groupware.dto.Class;
import com.mju.groupware.dto.Team;
import com.mju.groupware.dto.TeamBoard;
import com.mju.groupware.dto.TeamUser;
import com.mju.groupware.dto.User;
import com.mju.groupware.dto.UserReview;

@Service
@Repository
public class TeamDaoImpl implements TeamDao {

	private ConstantTeamDao Constant;

	@SuppressWarnings("resource")
	public TeamDaoImpl() {
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/TeamDao.xml");
		CTX.refresh();
		this.Constant = (ConstantTeamDao) CTX.getBean("TeamID");
	}

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void InsertTeamInfo(Team team) {
		sqlSession.insert(this.Constant.getInsertTeamInfo(), team);
	}

	@Override
	public int SelectClassID(Class classInfo) {
		int Output = sqlSession.selectOne(this.Constant.getSelectClassID(), classInfo);
		return Output;
	}

	@Override
	public int SelectUserIDForTeamUser(User user) {
		Integer Output = sqlSession.selectOne(this.Constant.getSelectUserIDForTeamUser(), user);
		if (Output == null) {
			return 0;
		} else {
			return Output;

		}
	}

	@Override
	public void InsertTeamUserInfo(TeamUser teamUser) {
		sqlSession.insert(this.Constant.getInsertTeamUserInfo(), teamUser);
	}

	@Override
	public List<Class> SelectLectureInfo(String lectureName) {
		List<Class> LectureInfo = sqlSession.selectList(this.Constant.getSelectLectureInformation(), lectureName);
		return LectureInfo;
	}

	@Override
	public int SelectTeamLeaderUserID(String name) {
		return sqlSession.selectOne(this.Constant.getSelectTeamLeaderUserID(), name);
	}

	@Override
	public List<Team> SelectTeamList() {
		List<Team> SelectTeamList = sqlSession.selectList(this.Constant.getSelectTeamList());
		return SelectTeamList;
	}

	@Override
	public Class SelectClassList(int classID) {
		Class SelectClassList = sqlSession.selectOne(this.Constant.getSelectClassList(), classID);
		return SelectClassList;
	}

	@Override
	public int SelectClassIDForCheckTeam(int teamID) {
		return sqlSession.selectOne(this.Constant.getSelectClassIDForCheckTeam(), teamID);
	}

	@Override
	public List<Class> SelectClassInfoForCheckTeam(int classID) {
		return sqlSession.selectList(this.Constant.getSelectClassInfoForCheckTeam(), classID);
	}

	@Override
	public String SelectTeamName(int teamID) {
		return sqlSession.selectOne(this.Constant.getSelectTeamName(), teamID);
	}

	@Override
	public List<TeamUser> SelectTeamMemberInfo(int teamID) {
		return sqlSession.selectList(this.Constant.getSelectTeamMemberInfo(), teamID);
	}

	@Override
	public String SelectLeaderName(int userID) {
		return sqlSession.selectOne(this.Constant.getSelectLeaderName(), userID);
	}

	@Override
	public String SelectLeaderLoginID(int userID) {
		return sqlSession.selectOne(this.Constant.getSelectLeaderLoginID(), userID);
	}

	@Override
	public List<TeamUser> SelectMyTeamList(String loginID) {
		List<TeamUser> SelectMyTeamList = sqlSession.selectList(this.Constant.getSelectMyTeamList(), loginID);
		return SelectMyTeamList;
	}

	@Override
	public void DeleteTeamMemberInfo(int teamID) {
		sqlSession.delete(this.Constant.getDeleteTeamMemberInfo(), teamID);
	}

	@Override
	public List<Team> SelectMyTeamInfo(int teamID) {
		List<Team> TeamInfo = sqlSession.selectList(this.Constant.getSelectMyTeamInfo(), teamID);
		return TeamInfo;
	}

	@Override
	public List<Class> SelectClassInfo(int classID) {
		List<Class> SelectClassInfo = sqlSession.selectList(this.Constant.getSelectClassInfo(), classID);
		return SelectClassInfo;
	}

	@Override
	public List<TeamBoard> SelectTeamBoardListInfo(String TeamID) {
		List<TeamBoard> SelectTeamBoardInfo = sqlSession.selectList(this.Constant.getSelectTeamBoardListInfo(), TeamID);
		return SelectTeamBoardInfo;
	}

	@Override
	public String SelectTeamIDForDocument(String userID) {
		String Output = sqlSession.selectOne(this.Constant.getSelectTeamIDForDocument(), userID);
		return Output;
	}

	@Override
	public String SelectTeamIDForDelete(String tUserID) {
		return sqlSession.selectOne(this.Constant.getSelectTeamIDForDelete(), tUserID);
	}

	@Override
	public List<Integer> SelectTeamNameWithLoginUser(String name) {
		return sqlSession.selectList(this.Constant.getSelectTeamNameWithLoginUser(), name);

	}

	@Override
	public String SelectTeamIDForReview(String teamName) {
		return sqlSession.selectOne(this.Constant.getSelectTeamIDForReview(), teamName);
	}

	@Override
	public List<TeamUser> SelectTeamMember(String teamID) {
		return sqlSession.selectList(this.Constant.getSelectTeamMember(), teamID);
	}

	@Override
	public String SelectTeamUserID(String userLoginID) {
		return sqlSession.selectOne(this.Constant.getSelectTeamUserID(), userLoginID);
	}

	@Override
	public void InsertUserReview(UserReview userReview) {
		sqlSession.insert(this.Constant.getInsertUserReview(), userReview);
	}

	@Override
	public String SelectTeamLeaderLoginID(String teamID) {
		return sqlSession.selectOne("SelectTeamLeaderLoginID", teamID);
	}

	@Override
	public void DeleteTeam(String teamID) {
		sqlSession.delete("DeleteTeam", teamID);
	}

	@Override
	public String SelectWriterUserID(String name) {
		return sqlSession.selectOne("SelectWriterUserID", name);
	}

	@Override
	public int SelectColumnCount(UserReview userReview) {
		return sqlSession.selectOne("SelectColumnCount", userReview);
	}

	@Override
	public String SelectTeamNameWithTeamID(int teamID) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("SelectTeamNameWithTeamID", teamID);
	}

	@Override
	public Integer SelectClassIDFromTeam(Integer teamID) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("SelectClassIDFromTeam", teamID);
	}

}