package com.mju.groupware.dto;

public class Student extends User {

   public enum studentDoubleMajor {
      국어국문학과, 영어영문학과, 중어중문학과, 일어일문학과, 사학과, 문헌정보학과, 아랍지역학과, 미술사학과, 철학과, 문예창작학과, 행정학과, 경제학과, 정치외교학과, 디지털미디어학과, 아동학과,
      청소년지도학과, 경영정보학과, 국제통상학과, 법학과, 융합소프트웨어학부, 디지털콘텐츠디자인학과, 창의융합인재학부, 사회복지학과, 부동산학과, 법무행정학과, 심리치료학과, 미래융합경영학과,
      멀티디자인학과, 계약학과, 없음
   }

   private int StudentID;
   private String StudentGrade;
   private String StudentGender;
   private studentDoubleMajor StudentDoubleMajor;
   private int UserID; // foreign key

   public int getStudentID() {
      return StudentID;
   }

   public void setStudentID(int StudentID) {
      this.StudentID = StudentID;
   }

   public String getStudentGrade() {
      return StudentGrade;
   }

   public void setStudentGrade(String StudentGrade) {
      this.StudentGrade = StudentGrade;
   }

   public String getStudentGender() {
      return StudentGender;
   }

   public void setStudentGender(String StudentGender) {
      this.StudentGender = StudentGender;
   }

   public studentDoubleMajor getStudentDoubleMajor() {
      return StudentDoubleMajor;
   }

   public void setStudentDoubleMajor(studentDoubleMajor StudentDoubleMajor) {
      this.StudentDoubleMajor = StudentDoubleMajor;
   }

   public int getUserID() {
      return UserID;
   }

   public void setUserID(int UserID) {
      this.UserID = UserID;
   }

}