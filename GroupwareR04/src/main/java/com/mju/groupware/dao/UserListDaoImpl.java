package com.mju.groupware.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mju.groupware.constant.ConstantAdminUserListDao;
import com.mju.groupware.dto.Criteria;
import com.mju.groupware.dto.UserList;

@Service
@Repository
public class UserListDaoImpl implements UserListDao {
   private ConstantAdminUserListDao Constant;

   @SuppressWarnings("resource")
   public UserListDaoImpl() {
      GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
      CTX.load("classpath:/xmlForProperties/UserListDao.xml");
      CTX.refresh();
      this.Constant = (ConstantAdminUserListDao) CTX.getBean("UserListDaoID");
   }

   @Autowired
   private SqlSession sqlSession;

   // userList를 호출하기 위한 dao
   @Override
   public List<UserList> SelectUserlist() throws Exception {
      List<UserList> Output = this.sqlSession.selectList(this.Constant.getSelectUserList());
      return Output;
   }

   // 휴먼계정 List 호출
   @Override
   public List<UserList> SelectDormantUserList() {
      List<UserList> Output = this.sqlSession.selectList(this.Constant.getSelectDormantList());
      return Output;
   }

   // 탈퇴계정 List 호출
   @Override
   public List<UserList> SelectWithdrawalUserList() {
      List<UserList> Output = this.sqlSession.selectList(this.Constant.getSelectWithdrawalList());
      return Output;
   }

   @Override
   public int CountTotalUserList(Criteria cri) {
      // TODO Auto-generated method stub
      return sqlSession.selectOne("CountTotalUserList", cri);
   }

   @Override
   public List<UserList> SelectUserListPN(Criteria cri) {
      // TODO Auto-generated method stub
      return sqlSession.selectList("SelectUserListPN", cri);
   }

   @Override
   public int CountTotalDormantUserList(Criteria cri) {
      // TODO Auto-generated method stub
      return sqlSession.selectOne("CountTotalDormantUserList", cri);
   }

   @Override
   public List<UserList> SelectDormantUserListPN(Criteria cri) {
      // TODO Auto-generated method stub
      return sqlSession.selectList("SelectDormantUserListPN", cri);
   }

   @Override
   public int CountTotalWithdrawalUserList(Criteria cri) {
      // TODO Auto-generated method stub
      return sqlSession.selectOne("CountTotalWithdrawalUserList", cri);
   }

   @Override
   public List<UserList> SelectWithdrawalUserListPN(Criteria cri) {
      // TODO Auto-generated method stub
      return sqlSession.selectList("SelectWithdrawalUserListPN", cri);
   }
}