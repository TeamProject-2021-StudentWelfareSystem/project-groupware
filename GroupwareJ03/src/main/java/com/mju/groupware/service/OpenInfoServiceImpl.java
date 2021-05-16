package com.mju.groupware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mju.groupware.dao.OpenInfoDao;
import com.mju.groupware.dto.UserList;

@Service
public class OpenInfoServiceImpl implements OpenInfoService {

	@Autowired
	private OpenInfoDao openInfoDao;
	
	@Override
	public List<UserList> SelectForOpenInfoAll(String mysqlID) {
		return openInfoDao.SelectForOpenInfoAll(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfoWithoutGrade(String mysqlID) {
		return openInfoDao.SelectForOpenInfoWithoutGrade(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfoWithoutMajor(String mysqlID) {
		return openInfoDao.SelectForOpenInfoWithoutMajor(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfoWithoutPhoneNum(String mysqlID) {
		return openInfoDao.SelectForOpenInfoWithoutPhoneNum(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfoWithoutEmail(String mysqlID) {
		return openInfoDao.SelectForOpenInfoWithoutEmail(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfoWithoutName(String mysqlID) {
		return openInfoDao.SelectForOpenInfoWithoutName(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_E_P(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_N_E_P(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_E_G(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_N_E_G(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_E_M(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_N_E_M(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_M_G(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_N_M_G(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_P_M(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_N_P_M(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_P_G(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_N_P_G(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_P_M(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_E_P_M(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_P_G(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_E_P_G(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_M_G(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_E_M_G(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_P_M_G(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_P_M_G(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_E(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_N_E(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_P(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_N_P(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_M(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_N_M(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_N_G(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_N_G(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_P(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_E_P(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_M(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_E_M(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_E_G(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_E_G(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_P_M(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_P_M(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_P_G(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_P_G(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfo_M_G(String mysqlID) {
		return openInfoDao.SelectForOpenInfo_M_G(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfoName(String mysqlID) {
		return openInfoDao.SelectForOpenInfoName(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfoEmail(String mysqlID) {
		return openInfoDao.SelectForOpenInfoEmail(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfoPhoneNum(String mysqlID) {
		return openInfoDao.SelectForOpenInfoPhoneNum(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfoMajor(String mysqlID) {
		return openInfoDao.SelectForOpenInfoMajor(mysqlID);
	}

	@Override
	public List<UserList> SelectForOpenInfoGrade(String mysqlID) {
		return openInfoDao.SelectForOpenInfoGrade(mysqlID);
	}

}
