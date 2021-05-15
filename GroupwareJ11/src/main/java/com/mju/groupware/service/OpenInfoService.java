package com.mju.groupware.service;

import java.util.List;

import com.mju.groupware.dto.UserList;

public interface OpenInfoService {

	public List<UserList> SelectForOpenInfoAll(String mysqlID);

	public List<UserList> SelectForOpenInfoWithoutGrade(String mysqlID);

	public List<UserList> SelectForOpenInfoWithoutMajor(String mysqlID);

	public List<UserList> SelectForOpenInfoWithoutPhoneNum(String mysqlID);

	public List<UserList> SelectForOpenInfoWithoutEmail(String mysqlID);

	public List<UserList> SelectForOpenInfoWithoutName(String mysqlID);

	public List<UserList> SelectForOpenInfo_N_E_P(String mysqlID);

	public List<UserList> SelectForOpenInfo_N_E_G(String mysqlID);

	public List<UserList> SelectForOpenInfo_N_E_M(String mysqlID);

	public List<UserList> SelectForOpenInfo_N_M_G(String mysqlID);

	public List<UserList> SelectForOpenInfo_N_P_M(String mysqlID);

	public List<UserList> SelectForOpenInfo_N_P_G(String mysqlID);

	public List<UserList> SelectForOpenInfo_E_P_M(String mysqlID);

	public List<UserList> SelectForOpenInfo_E_P_G(String mysqlID);

	public List<UserList> SelectForOpenInfo_E_M_G(String mysqlID);

	public List<UserList> SelectForOpenInfo_P_M_G(String mysqlID);

	public List<UserList> SelectForOpenInfo_N_E(String mysqlID);

	public List<UserList> SelectForOpenInfo_N_P(String mysqlID);

	public List<UserList> SelectForOpenInfo_N_M(String mysqlID);

	public List<UserList> SelectForOpenInfo_N_G(String mysqlID);

	public List<UserList> SelectForOpenInfo_E_P(String mysqlID);

	public List<UserList> SelectForOpenInfo_E_M(String mysqlID);

	public List<UserList> SelectForOpenInfo_E_G(String mysqlID);

	public List<UserList> SelectForOpenInfo_P_M(String mysqlID);

	public List<UserList> SelectForOpenInfo_P_G(String mysqlID);

	public List<UserList> SelectForOpenInfo_M_G(String mysqlID);

	public List<UserList> SelectForOpenInfoName(String mysqlID);

	public List<UserList> SelectForOpenInfoEmail(String mysqlID);

	public List<UserList> SelectForOpenInfoPhoneNum(String mysqlID);

	public List<UserList> SelectForOpenInfoMajor(String mysqlID);

	public List<UserList> SelectForOpenInfoGrade(String mysqlID);

}
