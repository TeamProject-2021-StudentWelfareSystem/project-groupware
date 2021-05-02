package com.mju.groupware.dao;

import java.util.List;

import com.mju.groupware.dto.UserList;

public interface OpenInfoDao {

	List<UserList> SelectForOpenInfoAll(String mysqlID);

	List<UserList> SelectForOpenInfoWithoutGrade(String mysqlID);

	List<UserList> SelectForOpenInfoWithoutMajor(String mysqlID);

	List<UserList> SelectForOpenInfoWithoutPhoneNum(String mysqlID);

	List<UserList> SelectForOpenInfoWithoutEmail(String mysqlID);

	List<UserList> SelectForOpenInfoWithoutName(String mysqlID);

	List<UserList> SelectForOpenInfo_N_E_P(String mysqlID);

	List<UserList> SelectForOpenInfo_N_E_G(String mysqlID);

	List<UserList> SelectForOpenInfo_N_E_M(String mysqlID);

	List<UserList> SelectForOpenInfo_N_M_G(String mysqlID);

	List<UserList> SelectForOpenInfo_N_P_M(String mysqlID);

	List<UserList> SelectForOpenInfo_N_P_G(String mysqlID);

	List<UserList> SelectForOpenInfo_E_P_M(String mysqlID);

	List<UserList> SelectForOpenInfo_E_P_G(String mysqlID);

	List<UserList> SelectForOpenInfo_E_M_G(String mysqlID);

	List<UserList> SelectForOpenInfo_P_M_G(String mysqlID);

	List<UserList> SelectForOpenInfo_N_E(String mysqlID);

	List<UserList> SelectForOpenInfo_N_P(String mysqlID);

	List<UserList> SelectForOpenInfo_N_M(String mysqlID);

	List<UserList> SelectForOpenInfo_E_P(String mysqlID);

	List<UserList> SelectForOpenInfo_N_G(String mysqlID);

	List<UserList> SelectForOpenInfo_E_M(String mysqlID);

	List<UserList> SelectForOpenInfo_E_G(String mysqlID);

	List<UserList> SelectForOpenInfo_P_M(String mysqlID);

	List<UserList> SelectForOpenInfo_P_G(String mysqlID);

	List<UserList> SelectForOpenInfo_M_G(String mysqlID);

	List<UserList> SelectForOpenInfoName(String mysqlID);

	List<UserList> SelectForOpenInfoEmail(String mysqlID);

	List<UserList> SelectForOpenInfoPhoneNum(String mysqlID);

	List<UserList> SelectForOpenInfoMajor(String mysqlID);

	List<UserList> SelectForOpenInfoGrade(String mysqlID);

}
