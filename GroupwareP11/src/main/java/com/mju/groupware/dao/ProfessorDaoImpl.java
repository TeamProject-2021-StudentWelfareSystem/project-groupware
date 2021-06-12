package com.mju.groupware.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.Professor;

@Service
@Repository
public class ProfessorDaoImpl implements ProfessorDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	// 학년
	private String ProfessorMajor;
	private String ProfessorColleges;
	private String ProfessorRoom;
	private String ProfessorRoomNum;

	@Override
	public void InsertInformation(Professor professor) {
		sqlSession.insert("InsertInformationP", professor);
	}

	@Override
	public void UpdateUserID(Professor professor) {
		sqlSession.update("UpdateUserID", professor);
	}

	@Override
	public void UpdateProfessorColleges(Professor professor) {
		sqlSession.update("UpdateProfessorColleges", professor);
	}

	@Override
	public void UpdateProfessorMajor(Professor professor) {
		sqlSession.update("UpdateProfessorMajor", professor);

	}

	@Override
	public void UpdateProfessorRoom(Professor professor) {
		sqlSession.update("UpdateProfessorRoom", professor);
	}

	@Override
	public void UpdateProfessorRoomNum(Professor professor) {
		sqlSession.update("UpdateProfessorRoomNum", professor);
	}

	@Override
	public ArrayList<String> SelectProfessorProfileInfo(String userID) {
		ArrayList<String> ProfessorInfo = new ArrayList<String>();
		if (!userID.equals("")) {
			List<Professor> Output = this.sqlSession.selectList("SelectProfessorProfileInfo", userID);
			if (Output == null) {

			} else {
				for (Professor Item : Output) {
					ProfessorColleges = Item.getProfessorColleges().toString();
					ProfessorMajor = Item.getProfessorMajor().toString();
					ProfessorRoom = Item.getProfessorRoom().toString();
				}

				ProfessorInfo.add(ProfessorColleges);
				ProfessorInfo.add(ProfessorMajor);
				ProfessorInfo.add(ProfessorRoom);
			}
		}
		return ProfessorInfo;
	}

	@Override
	public ArrayList<String> SelectMyPageUserInfo(String userId) {
		ArrayList<String> Info = new ArrayList<String>();
		if (!userId.equals("")) {
			List<Professor> Output = this.sqlSession.selectList("SelectMyPageProfessorInfo", userId);
			if (Output == null) {

			} else {
				for (Professor Item : Output) {
					ProfessorMajor = Item.getProfessorMajor();
					ProfessorRoom = Item.getProfessorRoom();
					ProfessorRoomNum = Item.getProfessorRoomNum();
				}
				Info.add(ProfessorColleges);
				Info.add(ProfessorMajor);
				Info.add(ProfessorRoom);
				Info.add(ProfessorRoomNum);
			}
		}
		return Info;
	}

	@Override
	public ArrayList<String> SelectMyPageUserInfoByID(String mysqlID) {
		ArrayList<String> Info = new ArrayList<String>();

		List<Professor> Output = this.sqlSession.selectList("SelectMyPageProfessorInfoByID", mysqlID);
		if (Output == null) {

		} else {
			for (Professor Item : Output) {
				ProfessorColleges = Item.getProfessorColleges().toString();
				ProfessorMajor = Item.getProfessorMajor().toString();
				ProfessorRoom = Item.getProfessorRoom().toString();
				ProfessorRoomNum = Item.getProfessorRoomNum().toString();
			}

			Info.add(ProfessorColleges);
			Info.add(ProfessorMajor);
			Info.add(ProfessorRoom);
			Info.add(ProfessorRoomNum);
		}
		return Info;
	}

	@Override
	public Professor SelectProfessorInfo(String userID) {
		Professor Output = sqlSession.selectOne("SelectProfessorInfo", userID);
		return Output;
	}

	@Override
	public void InsertWithdrawalprofessor(Professor professor) {
		sqlSession.insert("InsertWithdrawalProfessor", professor);
	}

	@Override
	public void DeleteWithdrawalprofessor(Professor professor) {
		sqlSession.delete("DeleteWithdrawalProfessor", professor);
	}

	@Override
	public void DeleteWithdrawalprofessorList(String userID) {
		sqlSession.delete("DeleteWithdrawalProfessorList", userID);
	}

	@Override
	public void UpdateProfessorLoginDate(Professor professor) {
		sqlSession.update("UpdateProfessorLoginDate", professor);
	}

	@Override
	public Professor SelectModifyProfessorInfo(int userID) {
		Professor professor = sqlSession.selectOne("SelectModifyProfessorInfo", userID);
		return professor;
	}

}