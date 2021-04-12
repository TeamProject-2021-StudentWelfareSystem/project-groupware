drop database GroupWare;
show databases;
create database GroupWare;
use GroupWare;
show tables;
# 회원가입sql문
insert into student(studentName, studentRR, studentNum, studentCampus, studentColleges, studentMajor, studentDoubleMajor, studentAddress, 
studentPhoneNum, studentEmail, studentLoginID, studentLoginPwd, studentGrade) values (?,?,?,?,?,?,?,?,?,?,?,?,?);
insert into professor(professorName, professorRR, professorCampus, professorColleges, professorMajor, professorAddress, 
professorPhoneNum, professorEmail, professorLoginID, professorLoginPwd) values (?,?,?,?,?,?,?,?,?,?);
insert into manager(managerName, managerRR, managerPhoneNum, managerEmail, managerLoginID, managerLoginPwd, managerDepartment) 
values (?,?,?,?,?,?,?);
# 로그인 날짜 입력
update User set LoginDate = date_format(NOW(), '%Y%m%d') where UserName = "박지수";
# insert 모음
insert into UserEmail(UserEmail, UserCertificationNum, UserCertificationTime) values ("123@mju.ac.kr", "123456", date_format(NOW(), '%Y%m%d%H%m%s'));
insert into User(UserName, UserPhoneNum, UserEmail, UserLoginID, UserLoginPwd) values ("박지수","010-3501-8711","happy6021005@naver.com","132","1234");
# select 모음
select userLoginID, userName from user where userloginID = "학번" and userName = "이름";
select * from User;
select * from Student;
select * from UserEmail;
# drop 모음
drop table Professor;
drop table Student;
drop table User;
drop table UserEmail;
# 하루 한 번 인증번호 삭제
CREATE
   EVENT email_validation_Scheduler ON SCHEDULE EVERY 1 DAY STARTS '2021-04-09 00:00:00'
    DO
   DELETE from UserEmail WHERE userCertificationTime <= NOW();
    
# 인증번호 삭제 되는지 테스트하기 위한 스케쥴러
CREATE
   EVENT email_validation_Scheduler_test ON SCHEDULE EVERY 1 minute STARTS '2021-04-09 00:00:00'
    DO
   DELETE from UserEmail WHERE userCertificationTime <= DATE_SUB(NOW(), INTERVAL 1 minute);
    
# 하루 한 번 6개월 이상 로그인 안한 유저 Withdrawal 1 (탈퇴) 로 업데이트
CREATE
   Event withdrawal_Scheduler ON SCHEDULE EVERY 1 day STARTS '2021-04-09'
    DO
    UPDATE User set Withdrawal = 1 WHERE LoginDate <= DATE_SUB(NOW(), INTERVAL 6 month);
    
# Withdrawal 업데이트 테스트하기 위한 스케쥴러
CREATE
   Event withdrawal_Scheduler_test ON SCHEDULE EVERY 1 day STARTS '2021-04-09'
    DO
    UPDATE User set Withdrawal = 1 WHERE LoginDate <= DATE_SUB(NOW(), INTERVAL 6 month);
DROP EVENT email_validation_Scheduler;
DROP EVENT email_validation_Scheduler_test;
DROP EVENT withdrawal_Scheduler;
DROP EVENT withdrawal_Scheduler_test;

select * from user;
UPDATE User
set Authority = 'ROLE_ADMIN'
where UserName = '박지수';
create table User(
UserID int auto_increment not null primary key,
UserName varchar(20) not null,
UserPhoneNum varchar(30) not null,
UserEmail varchar(100) not null unique key,
UserLoginID varchar(30) binary not null unique key,
UserLoginPwd varchar(300) binary not null,
UserRole ENUM ('STUDENT', 'PROFESSOR', 'ADMINISTRATOR'),
Authority varchar(20) not null default 'ROLE_USER', # ROLE_USER, ROLE_ADMIN
Enabled boolean not null default 1, # 활성화:1 비활성화:0
LoginDate date, #로그인날짜
UserColleges ENUM ('인문대학', '사회과학대학', '경영대학', '법과대학', 'ICT융합대학', '미래융합대학') not null, #단과대학
UserMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부', '사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과') not null, #전공
Withdrawal boolean not null default 0 # 가입:0 탈퇴:1 
);
# 회원가입 전 인증메일
create table UserEmail(
UserEmailID int auto_increment not null primary key,
UserEmail varchar(100) not null, 
UserCertificationNum int, #인증번호
UserCertificationTime Datetime #인증번호 받은 시간
);
create table Student(
StudentID int auto_increment not null primary key,
StudentGrade ENUM ('1학년', '2학년', '3학년', '4학년') not null, #학년
StudentGender varchar(20) not null, # male / female
StudentDoubleMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부','사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과','없음') Default '없음', #복수전공
UserID int, foreign key (UserID) references user(UserID) on delete cascade on update cascade
);
create table Professor(
ProfessorID int auto_increment not null primary key,
ProfessorRoom varchar(10), #교수실
ProfessorRoomNum varchar(30), #교수실전화번호 
UserID int, foreign key (UserID) references user(UserID) on delete cascade on update cascade
);
/*
* ON DELETE SET NULL
* ON UPDATE SET NULL
옵션 SET NULL -> 부모테이블에서 primary 값이 수정 또는 삭제될 경우
하위테이블의 reference값은 존재할 수 없습니다. 옵션이 없을 경우는 에러가 발생하고 옵션 SET NULL 로 정의되면 하위테이블의 reference값이  NULL 값으로 변경되면서 참조무결성을 유지합니다.
* ON UPDATE CASCADE
옵션 CASCADE -> 부모테이블에서 primary 값이 수정될 경우
옵션 CASCADE 로 정의되면 하위테이블의 reference값은 변경된 상위테이블의 수정된 값을 가지면서 참조무결성을 유지합니다.
* ON DELETE CASCADE
옵션 CASCADE -> 부모테이블에서 primary 값이 삭제될 경우
옵션 CASCADE 로 정의되면 하위테이블의 reference값은 삭제되면서 참조무결성을 유지합니다.
*/