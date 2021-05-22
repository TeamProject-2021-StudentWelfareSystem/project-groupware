package com.mju.groupware.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Class;
import com.mju.groupware.dto.Team;
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
		int Output = sqlSession.selectOne("SelectUserIDForTeamUser", user);
		return Output;
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
}
