package com.mju.groupware.dao;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.dto.ConstantAdminProfessorDao;
import com.mju.groupware.dto.Professor;

@Service
@Repository
public class ProfessorDaoImpl implements ProfessorDao {
	private ConstantAdminProfessorDao Constant;

	public ProfessorDaoImpl() {
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/ProfessorDao.xml");
		CTX.refresh();
		this.Constant = (ConstantAdminProfessorDao) CTX.getBean("ProfessorDaoID");
	}

	@Autowired
	private SqlSessionTemplate sqlSession;
	// 학년
	private String ProfessorMajor;
	private String ProfessorColleges;
	private String ProfessorRoom;
	private String ProfessorRoomNum;

	@Override
	public void InsertInformation(Professor professor) {
		sqlSession.insert(this.Constant.getInsertInformation(), professor);
	}

	@Override
	public void UpdateUserID(Professor professor) {
		sqlSession.update(this.Constant.getUpdateUserID(), professor);
	}

	@Override
	public void UpdateProfessorColleges(Professor professor) {
		sqlSession.update(this.Constant.getUpdateProfessorColleges(), professor);
	}

	@Override
	public void UpdateProfessorMajor(Professor professor) {
		sqlSession.update(this.Constant.getUpdateProfessorMajor(), professor);

	}

	@Override
	public void UpdateProfessorRoom(Professor professor) {
		sqlSession.update(this.Constant.getUpdateProfessorRoom(), professor);
	}

	@Override
	public void UpdateProfessorRoomNum(Professor professor) {
		sqlSession.update(this.Constant.getUpdateProfessorRoomNum(), professor);
	}

	@Override
	public ArrayList<String> SelectProfessorProfileInfo(String userID) {
		ArrayList<String> ProfessorInfo = new ArrayList<String>();
		if (!userID.equals("")) {
			List<Professor> Output = this.sqlSession.selectList(this.Constant.getSelectProfessorProfileInfo(), userID);
			if (Output == null) {

			} else {
				for (Professor Item : Output) {
					ProfessorColleges = Item.getProfessorColleges().toString();
					ProfessorMajor = Item.getProfessorMajor().toString();
				}

				ProfessorInfo.add(ProfessorColleges);
				ProfessorInfo.add(ProfessorMajor);
			}
		}
		return ProfessorInfo;
	}

	@Override
	public ArrayList<String> SelectMyPageUserInfo(String UserId) {
		ArrayList<String> Info = new ArrayList<String>();
		if (!UserId.equals("")) {
			List<Professor> Output = this.sqlSession.selectList(this.Constant.getSelectMyPageProfessorInfo(), UserId);
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

		List<Professor> Output = this.sqlSession.selectList(this.Constant.getSelectMyPageProfessorInfoByID(), mysqlID);
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
		Professor Output = sqlSession.selectOne(this.Constant.getSelectProfessorInfo(), userID);
		return Output;
	}

	@Override
	public void InsertWithdrawalprofessor(Professor professor) {
		sqlSession.insert(this.Constant.getInsertWithdrawalProfessor(), professor);

	}

	@Override
	public void DeleteWithdrawalprofessor(Professor professor) {
		sqlSession.delete(this.Constant.getDeleteWithdrawalProfessor(), professor);

	}

	@Override
	public void DeleteWithdrawalprofessorList(String userID) {
		sqlSession.delete(this.Constant.getDeleteWithdrawalProfessorList(), userID);

	}

}