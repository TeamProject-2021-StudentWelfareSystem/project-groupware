drop database GroupWare;
show databases;
create database GroupWare;
use GroupWare;
show tables;

# 로그인 날짜 입력
update User set LoginDate = date_format(NOW(), '%Y%m%d') where UserName = "박지수";

# insert 모음
insert into UserEmail(UserEmail, UserCertificationNum, UserCertificationTime) values ("123@mju.ac.kr", "123456", date_format(NOW(), '%Y%m%d%H%m%s'));
insert into User(UserName, UserPhoneNum, UserEmail, UserLoginID, UserLoginPwd) values ("정민","01045018711","happy6021004@mju.ac.kr","60181666","wjdals0426@");
insert into Student(StudentGender, StudentGrade, StudentColleges, StudentMajor, StudentDoubleMajor, UserID) values ("여자", "4학년", "ICT융합대학", "융합소프트웨어학부", "없음", 1);

# alter 모음
alter table User add Dormant boolean not null default 0;

# select 모음
select userLoginID, userName from user where userloginID = "학번" and userName = "이름";
select * from User where Dormant = 1;
select * from Student;
select * from UserEmail;
select StudentGrade,StudentGender,StudentDoubleMajor from Student where StudentID = '1';

# drop 모음
drop table Professor;
drop table Student;
drop table User;
drop table UserEmail;

# update 모음
update User set UserLoginPwd = '바꿀 비밀번호' where UserLoginID = 'UserLoginID';
update Student set StudentGender = '여' where UserID = '해당UserID';
update User set UserPhoneNum = '바꿀 번호' where UserLoginID = 'UserLoginID';
update Student set StudentGrade = '바꿀 학년' where UserLoginID = 'UserLoginID';
update User set UserColleges = '바꿀 단과대학' where UserLoginID = 'UserLoginID';
update User set UserMajor = '바꿀 학과' where UserLoginID = 'UserLoginID';
update Student set StudentDoubleMajor = '바꿀 복수전공' where UserLoginID = 'UserLoginID'; 
update User set Authority = "ROLE_ADMIN" where UserID = 7;
update User set LoginDate = "2020-1-30" where UserName = "배트맨";
update User set Dormant = 0 where UserName = "배트맨";
update User set Enabled = 1 where UserName = "배트맨";

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
Dormant boolean not null default 0, # 휴먼계정아니면 0, 휴면계정이면 1
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
StudentColleges ENUM ('인문대학', '사회과학대학', '경영대학', '법과대학', 'ICT융합대학', '미래융합대학') not null, #단과대학
StudentMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부', '사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과') not null, #전공
StudentDoubleMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부','사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과', '없음') default '없음', #복수전공
UserID int, foreign key (StudentID) references user(UserID) on delete cascade on update cascade
);

create table Professor(
ProfessorID int auto_increment not null primary key,
ProfessorRoom varchar(10), #교수실
ProfessorRoomNum varchar(30), #교수실전화번호 
ProfessorColleges ENUM ('인문대학', '사회과학대학', '경영대학', '법과대학', 'ICT융합대학', '미래융합대학') not null, #단과대학
ProfessorMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부', '사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과') not null, #전공
UserID int, foreign key (ProfessorID) references user(UserID) on delete cascade on update cascade
);

create table WithDrawalUser(
UserID int auto_increment not null primary key,
UserName varchar(20) not null,
UserPhoneNum varchar(30) not null,
UserEmail varchar(100) not null unique key,
UserLoginID varchar(30) binary not null unique key,
UserLoginPwd varchar(300) binary not null,
UserRole ENUM ('STUDENT', 'PROFESSOR', 'ADMINISTRATOR'),
Authority varchar(20) not null default 'ROLE_USER', # ROLE_USER, ROLE_ADMIN
Enabled boolean not null default 1, # 활성화:1 비활성화:0
LoginDate date #로그인날짜
);

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
    
# 하루 한 번 6개월 이상 로그인 안한 유저 Dormant 1 (휴먼계정) 로 업데이트
CREATE
   Event Dormant_Scheduler ON SCHEDULE EVERY 1 minute STARTS '2021-04-16'
    DO
    UPDATE User set Dormant = 1 WHERE LoginDate <= DATE_SUB(NOW(), INTERVAL 6 month);
    
# Withdrawal 업데이트 테스트하기 위한 스케쥴러
CREATE
   Event withdrawal_Scheduler_test ON SCHEDULE EVERY 1 day STARTS '2021-04-09'
    DO
    UPDATE User set Dormant = 1 WHERE LoginDate <= DATE_SUB(NOW(), INTERVAL 6 month);

DROP EVENT email_validation_Scheduler;
DROP EVENT email_validation_Scheduler_test;
DROP EVENT Dormant_Scheduler;
DROP EVENT Dormant_Scheduler_test;

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