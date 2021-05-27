drop database GroupWare;
show databases;
create database GroupWare;
use GroupWare;
show tables;

# 로그인 날짜 입력
update User set LoginDate = date_format(NOW(), '%Y%m%d') where UserName = "박지수";
update User set LoginDate = '2021-05-05' where UserName = "정민";

# delete 모음
delete from WithdrawalUser where WUserName = "탈퇴";
delete from WithdrawalUser where WUserName = "자퇴";
delete from User where UserID = "3";

# insert 모음
insert into UserReservation(ReservationDate,ReservationStartTime,ReservationEndTime,ReservationNumOfPeople,LectureRoomNo,UserID) values ('2021-05-12','17:00:00','19:00:00',10,1135,14);
insert into UserEmail(UserEmail, UserCertificationNum, UserCertificationTime) values ("123@mju.ac.kr", "123456", date_format(NOW(), '%Y%m%d%H%m%s'));
insert into User(UserName, UserPhoneNum, UserEmail, UserLoginID, UserLoginPwd) values ("정민","01045018711","happy6021004@mju.ac.kr","60181666","wjdals0426@");
insert into Student(StudentGender, StudentGrade, StudentColleges, StudentMajor, StudentDoubleMajor, UserID) values ("여자", "4학년", "ICT융합대학", "융합소프트웨어학부", "없음", 25);
insert into WithdrawalUser(WUserName, WUserPhoneNum, WUserEmail, WUserLoginID, WUserRole) values ("정민","01045018711","happy6021004@mju.ac.kr","60181666","STUDENT");
insert into WithdrawalStudent(WStudentGender, WStudentGrade, WStudentColleges, WStudentMajor, WStudentDoubleMajor, WUserID) values ("여자", "4학년", "ICT융합대학", "융합소프트웨어학부", "없음", 1);
insert into Board(BoardSubject, BoardContent, BoardWriter, BoardDate, UserID) values ("1","2","정민","2021-05-12 00:00:00", 1);
insert into BoardFile(BOriginalFileName, BStoredFileName, BFileSize, BoardID) values (2, 2, 1, 1);
insert into professor (ProfessorColleges, professorMajor, UserID) values ("ICT융합대학", "디지털콘텐츠디자인학과", 3);
# alter 모음
alter table User add Dormant boolean not null default 0;
alter table Professor add ProfessorRoomNum varchar(30) default '입력해주세요';
alter table Student drop column ProfessorRoom;
alter table Team add TeamLeaderID varchar(30) not null;

# select 모음
select userLoginID, userName from user where userloginID = "학번" and userName = "이름";
select * from User where Dormant = 1;
select * from Student;
select * from Professor;
select * from User;
select * from Board;
select * from BoardFile;
select * from UserEmail;
select * from WithdrawalUser;
select * from Class;
select * from WithdrawalStudent;
select * from Team;
select StudentGrade,StudentGender,StudentDoubleMajor from Student where StudentID = '1';
SELECT OpenName, OpenPhoneNum FROM User WHERE UserLoginID = '';
select WUserID from WithdrawalUser where WUserLoginID = '60212222';
select * from UserReservation;
select * from TeamUser;
select * from LectureRoom;
# 예약 가능한 강의실 검색하기 조건문 생각해보기
select * from UserReservation where ReservationStartTime >= '11:00:00' and ReservationEndTime <= '13:00:00' and ReservationDate = '2021-5-12';
select * from UserReservation where ReservationDate = '2021-05-12' and 
(ReservationStartTime >= '09:00:00' and ReservationEndTime <= '11:00:00') or 
(ReservationStartTime >= '11:00:00' and ReservationEndTime <= '13:00:00') or 
(ReservationStartTime >= '13:00:00' and ReservationEndTime <= '15:00:00') or 
(ReservationStartTime >= '15:00:00' and ReservationEndTime <= '17:00:00') or 
(ReservationStartTime >= '17:00:00' and ReservationEndTime <= '19:00:00') or
(ReservationStartTime >= '19:00:00' and ReservationEndTime <= '21:00:00');
select ReservationNo, RoomLocation, ReservationDate, ReservationStartTime, ReservationStartTime
from LectureRoom join UserReservation on LectureRoom.LectureRoomNo = UserReservation.ReservationNo;
select * from LectureRoom join UserReservation on LectureRoom.LectureRoomNo = UserReservation.ReservationNo;

# 유저가 예약하고자 하는 강의실을 선택 -> 해당 강의실을 선택하면 날짜 선택 -> 날짜 선택하면 해당 강의실No의 예약된 시간들을 select해옴
# -> select해서 startTime, endTime의 시간들을 비교해서 없으면 예약 가능

# drop 모음
drop table Professor;
drop table Student;
drop table User;
drop table UserEmail;
drop table WithdrawalUser;
drop table WithdrawalStudent;
drop table WithdrawalProfessor;
drop table Team;
drop table TeamFile;
drop table Class;
drop table TeamUser;
drop table Board;
drop table BoardFile;
drop table UserReservation;

# update 모음
update User set UserLoginPwd = '바꿀 비밀번호' where UserLoginID = 'UserLoginID';
update Student set StudentGender = '여' where UserID = '해당UserID';
update User set UserPhoneNum = '바꿀 번호' where UserLoginID = 'UserLoginID';
update Student set StudentGrade = '바꿀 학년' where UserLoginID = 'UserLoginID';
update User set UserColleges = '바꿀 단과대학' where UserLoginID = 'UserLoginID';
update User set UserMajor = '바꿀 학과' where UserLoginID = 'UserLoginID';
update Student set StudentDoubleMajor = '바꿀 복수전공' where UserLoginID = 'UserLoginID'; 
update User set Authority = "ROLE_ADMIN" where UserID = 1;
update User set UserRole = "PROFESSOR" where UserName = "교수";
update User set UserRole = "STUDENT" where UserName = "생성";
update User set LoginDate = "2020-1-30" where UserName = "배트맨";
update User set Dormant = 0 where UserName = "유저이름";
update User set Enabled = 1 where UserName = "유저이름";
update User set Authority = "ROLE_ADMIN" , UserRole = "ADMINISTRATOR" where UserName="정민";
update User set Authority = "ROLE_USER" where UserName = "탈퇴";
update User set OpenInfo = '이름', OpenInfo = '이메일' where UserLoginID = '60181664';
update User set OpenPhoneNum = "비공개";
update User set UserName = '월,수 13:30-14:45' where UserName = "확인용";

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
OpenName varchar(20) not null default '비공개', # 정보공개여부
OpenEmail varchar(20) not null default '비공개',
OpenMajor varchar(20) not null default '비공개',
OpenGrade varchar(20) not null default '비공개',
OpenPhoneNum varchar(20) not null default '비공개',
Dormant boolean not null default 0, # 휴먼계정아니면 0, 휴면계정이면 1
Withdrawal boolean not null default 0 # 가입:0 탈퇴:1 
);

create table Board(
BoardID int auto_increment not null primary key,
BoardSubject varchar(100) not null,
BoardContent varchar(10000) not null,
BoardWriter varchar(20) not null,
BoardDate dateTime not null,
UserID int not null,
BoardHit int default 0,
BoardType varchar(100) not null,
foreign key (UserID) references User(UserID) on delete cascade on update cascade
);
alter table Board add BoardType varchar(100) not null;
select * from Board;
select * from BoardFile;
drop table Board;

create table BoardFile(
BFileID int auto_increment not null primary key,
BOriginalFileName varchar(200) not null,
BStoredFileName varchar(200) not null,
BFileSize int not null,
BoardID int not null,
foreign key (BoardID) references Board(BoardID) on delete cascade on update cascade
);

create table Class(
ClassID int not null primary key, 
ClassName varchar(50) not null, #강의이름
ClassProfessorName varchar(50) not null, #교수	
ClassType varchar(30) not null #강의종류(전필, 교양 etc)
);

create table Team(
TeamID int auto_increment not null primary key,
TeamName varchar(50) not null,
TeamLeaderID varchar(30) not null,
TeamLeaderName varchar(20) not null,
TeamCreationDate Date not null,
ClassID int not null,
foreign key (ClassID) references Class(ClassID) on delete cascade on update cascade
);

create table TeamUser(
UserID int not null,
TeamID int not null,
UserName varchar(20) not null,
UserLoginID varchar(30) not null,
foreign key (UserID) references User(UserID) on delete cascade on update cascade,
foreign key (TeamID) references Team(TeamID) on delete cascade on update cascade
); 

create table TeamFile(
TFileID int auto_increment not null primary key,
TFileName varchar(200) not null,
TFileModifyName varchar(200),
TFileType varchar(100) not null,
TFilePath varchar(200) not null,
TeamID int not null,
foreign key (TeamID) references Team(TeamID) on delete cascade on update cascade
);

create table LectureRoom(
LectureRoomNo int not null primary key,
RoomLocation varchar(20) not null,
RoomFloor int not null,
MaxNumOfPeople int not null,
RoomType varchar(20) not null
);

create table UserReservation(
ReservationDate date not null,
ReservationStartTime time not null, 
ReservationEndTime time not null,
ReservationNumOfPeople int not null, #인원
LectureRoomNo int not null,
foreign key (LectureRoomNo) references LectureRoom(LectureRoomNo) on delete cascade on update cascade,
UserID int, foreign key (UserID) references User(UserID) on delete cascade on update cascade
);
select UserID from UserReservation where UserID = 13;
select * from UserReservation;
select * from LectureRoom;
drop table UserReservation;

# 회원가입 전 인증메일
create table UserEmail(
UserEmailID int auto_increment not null primary key,
UserEmail varchar(100) not null, 
UserCertificationNum int, #인증번호
UserCertificationTime Datetime #인증번호 받은 시간
);

create table Student(
StudentID int auto_increment not null primary key,
StudentGrade ENUM ('1학년', '2학년', '3학년', '4학년', '입력해주세요') default '입력해주세요' not null , #학년
StudentGender varchar(20) not null default '입력해주세요', # male / female
StudentColleges ENUM ('인문대학', '사회과학대학', '경영대학', '법과대학', 'ICT융합대학', '미래융합대학', '입력해주세요') default '입력해주세요' not null, #단과대학
StudentMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부', '사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과', '입력해주세요') default '입력해주세요' not null, #전공
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
ProfessorRoom varchar(10) default '입력해주세요', #교수실
ProfessorRoomNum varchar(30) default '입력해주세요', #교수실전화번호 
ProfessorColleges ENUM ('인문대학', '사회과학대학', '경영대학', '법과대학', 'ICT융합대학', '미래융합대학', '입력해주세요') default '입력해주세요' not null, #단과대학
ProfessorMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부', '사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과', '입력해주세요') default '입력해주세요' not null, #전공
UserID int, foreign key (ProfessorID) references user(UserID) on delete cascade on update cascade
);

create table WithdrawalUser(
WUserID int auto_increment not null primary key,
WUserName varchar(20) not null,
WUserPhoneNum varchar(30) not null,
WUserEmail varchar(100) not null unique key,
WUserLoginID varchar(30) binary not null unique key,
WUserRole ENUM ('STUDENT', 'PROFESSOR', 'ADMINISTRATOR'),
WAuthority varchar(20) not null default 'ROLE_USER', # ROLE_USER, ROLE_ADMIN
WEnabled boolean not null default 0, # 활성화:1 비활성화:0
WithdrawalDate date not null
);

create table WithdrawalStudent(
WStudentID int auto_increment not null primary key,
WStudentGrade ENUM ('1학년', '2학년', '3학년', '4학년') not null, #학년
WStudentGender varchar(20) not null, # male / female
WStudentColleges ENUM ('인문대학', '사회과학대학', '경영대학', '법과대학', 'ICT융합대학', '미래융합대학') not null, #단과대학
WStudentMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부', '사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과') not null, #전공
WStudentDoubleMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부','사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과', '없음') default '없음', #복수전공
WithdrawalDate date not null,
WUserID int, foreign key (WStudentID) references WithdrawalUser(WUserID) on delete cascade on update cascade
);

create table WithdrawalProfessor(
WProfessorID int auto_increment not null primary key,
WProfessorRoom varchar(10), #교수실
WProfessorRoomNum varchar(30), #교수실전화번호 
WProfessorColleges ENUM ('인문대학', '사회과학대학', '경영대학', '법과대학', 'ICT융합대학', '미래융합대학') not null, #단과대학
WProfessorMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부', '사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과') not null, #전공
WithdrawalDate date not null,
WUserID int, foreign key (WProfessorID) references WithdrawalUser(WUserID) on delete cascade on update cascade
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
   Event Dormant_Scheduler_test ON SCHEDULE EVERY 1 day STARTS '2021-04-09'
    DO
    UPDATE User set Dormant = 1 WHERE LoginDate <= DATE_SUB(NOW(), INTERVAL 6 month);

# 탈퇴계정 6개월 후 데이터 삭제
CREATE
	EVENT WithdrawaUserDelete ON SCHEDULE EVERY 1 day STARTS '2021-05-04'
    DO
    DELETE FROM WithdrawalUser WHERE WithdrawalDate <= DATE_SUB(NOW(), INTERVAL 6 month); 
CREATE
	EVENT WithdrawaStudentDelete ON SCHEDULE EVERY 1 day STARTS '2021-05-04'
    DO
    DELETE FROM WithdrawalStudent WHERE WithdrawalDate <= DATE_SUB(NOW(), INTERVAL 6 month); 
CREATE
	EVENT WithdrawaProfessorDelete ON SCHEDULE EVERY 1 day STARTS '2021-05-04'
    DO
    DELETE FROM WithdrawalProfessor WHERE WithdrawalDate <= DATE_SUB(NOW(), INTERVAL 6 month); 
    
DROP EVENT email_validation_Scheduler;
DROP EVENT email_validation_Scheduler_test;
DROP EVENT Dormant_Scheduler;
DROP EVENT Dormant_Scheduler_test;

/*
* ON DELETE SET NULL
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