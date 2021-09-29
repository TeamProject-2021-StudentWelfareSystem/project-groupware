package com.mju.groupware.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.ProfessorDao;
import com.mju.groupware.dto.Professor;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorDao professorDao;

	@Override
	public void InsertInformation(Professor professor) {
		professorDao.InsertInformation(professor);
	}

	@Override
	public void UpdateUserID(Professor professor) {
		professorDao.UpdateUserID(professor);
	}

	@Override
	public void UpdateProfessorColleges(Professor professor) {
		professorDao.UpdateProfessorColleges(professor);
	}

	@Override
	public void UpdateProfessorMajor(Professor professor) {
		professorDao.UpdateProfessorMajor(professor);
	}

	@Override
	public void UpdateProfessorRoom(Professor professor) {
		professorDao.UpdateProfessorRoom(professor);
	}

	@Override
	public void UpdateProfessorRoomNum(Professor professor) {
		professorDao.UpdateProfessorRoomNum(professor);
	}

	@Override
	public ArrayList<String> SelectProfessorProfileInfo(String userID) {
		ArrayList<String> ProfessorInfo = new ArrayList<String>();
		ProfessorInfo = professorDao.SelectProfessorProfileInfo(userID);
		return ProfessorInfo;
	}

	@Override
	public Professor SelectProfessorInfo(String userID) {
		return professorDao.SelectProfessorInfo(userID);
	}

	@Override
	public void InsertWithdrawalProfessor(Professor professor) {
		professorDao.InsertWithdrawalprofessor(professor);
	}

	@Override
	public void DeleteWithdrawalProfessor(Professor professor) {
		professorDao.DeleteWithdrawalprofessor(professor);
	}

	@Override
	public void DeleteWithdrawalProfessorList(String string) {
		professorDao.DeleteWithdrawalprofessorList(string);
	}

	@Override
	public void UpdateProfessorLoginDate(Professor professor) {
		professorDao.UpdateProfessorLoginDate(professor);
	}

	@Override
	public Professor SelectModifyProfessorInfo(int userID) {
		return professorDao.SelectModifyProfessorInfo(userID);
	}
}
