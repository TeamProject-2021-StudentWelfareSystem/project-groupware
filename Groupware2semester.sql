drop database GroupWare;
show databases;
create database GroupWare;
use GroupWare;
show tables;

# drop 모음
drop table BoardFile;
drop table Board;
drop table InquiryBoard;
drop table UserReservation;
drop table TeamUser;
drop table TeamFile;
drop table TeamBoard;
drop table Team;
drop table UserSchedule;
drop table UserReview;
drop table Student;
drop table Professor;
drop table User;
drop table UserEmail;

# 회원가입 전 인증메일
create table UserEmail(
UserEmailID int auto_increment not null primary key,
UserEmail varchar(100) not null, 
UserCertificationNum int, #인증번호
UserCertificationTime Datetime #인증번호 받은 시간
);

create table User(
UserID int auto_increment not null primary key,
UserName varchar(20) not null,
UserPhoneNum varchar(30) not null,
UserEmail varchar(100) not null,
UserLoginID varchar(30) binary not null unique key,
UserLoginPwd varchar(300) binary not null,
UserRole ENUM ('STUDENT', 'ADMINISTRATOR'),
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

create table Student(
StudentID int auto_increment not null primary key,
StudentGrade varchar(20) not null , #학년
StudentGender varchar(20) not null default '입력해주세요', # male / female
StudentColleges varchar(100) not null, #단과대학
StudentMajor varchar(100) not null, #전공
StudentDoubleMajor varchar(100), #복수전공
LoginDate date,
UserID int, foreign key (StudentID) references user(UserID) on delete cascade on update cascade
);

create table UserReview(
ReviewID int auto_increment not null primary key,
Positive varchar(30) not null,
Contribute varchar(30) not null,
Respect varchar(30) not null,
Flexible varchar(30) not null,
ClassName varchar(50) not null,
ClassProfessorName varchar(50) not null,
ReviewDate date not null,
UserID int not null, #대상
WriterUserID int not null, #작성자
TeamName varchar(50) not null, 
foreign key (UserID) references User(UserID) on delete cascade on update cascade
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

create table InquiryBoard(
IBoardID int auto_increment not null primary key,
IBoardSubject varchar(100) not null,
IBoardContent varchar(10000) not null,
IBoardWriter varchar(20) not null,
IBoardDate dateTime not null,
IBoardType varchar(50) not null,
IBoardDelete boolean default 0,
IBoardAnswer varchar(1000),
State varchar(20) not null,
UserEmail varchar(100) not null,
UserPhoneNum varchar(30) not null,
UserID int not null,
foreign key (UserID) references User(UserID) on delete cascade on update cascade
);

create table Board(
BoardID int auto_increment not null primary key,
BoardSubject varchar(100) not null,
BoardContent varchar(10000) not null,
BoardWriter varchar(20) not null,
BoardDate dateTime not null,
BoardHit int default 0,
BoardType varchar(100) not null,
BoardDelete boolean default 0 not null,
UserID int not null,
foreign key (UserID) references User(UserID) on delete cascade on update cascade
);

create table BoardFile(
BFileID int auto_increment not null primary key,
BOriginalFileName varchar(200) not null,
BStoredFileName varchar(200) not null,
BFileSize int not null,
BDelete boolean default 0 not null,
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

create table UserSchedule(
_id int auto_increment not null primary key,
title varchar(50) not null,
description varchar(100) not null,
start varchar(100) not null,
end varchar(100) not null,
backgroundColor varchar(30) not null,
allDay boolean,
UserID int not null,
foreign key (UserID) references User(UserID) on delete cascade on update cascade
);
select date_format(ScheduleStartDate,'%Y-%M-%D %H:%i') as date from UserSchedule;
select date_format(ScheduleEndDate,'%Y-%M-%D %H:%i') as date from UserSchedule;

create table TeamUser(
UserID int not null,
TeamID int not null,
UserName varchar(20) not null,
UserLoginID varchar(30) not null,
foreign key (UserID) references User(UserID) on delete cascade on update cascade,
foreign key (TeamID) references Team(TeamID) on delete cascade on update cascade
); 

create table TeamBoard(
TBoardID int auto_increment not null primary key,
TBoardSubject varchar(100) not null,
TBoardContent varchar(10000) not null,
TBoardWriter varchar(20) not null,
TBoardDate dateTime not null,
TBoardDelete boolean default 0 not null,
TUserLoginID varchar(30) not null,
TeamID int not null,
foreign key (TeamID) references Team(TeamID) on delete cascade on update cascade
);

create table TeamFile(
TFileID int auto_increment not null primary key,
TOriginalFileName varchar(200) not null,
TStoredFileName varchar(200) not null,
TFileSize int not null,
TBoardID int not null,
TFileDelete boolean default 0 not null,
foreign key (TBoardID) references TeamBoard(TBoardID) on delete cascade on update cascade
);

# 추가) 로그인 세션
create table persistent_logins(
username varchar(100) not null,
series varchar(500) PRIMARY key,
token varchar(500) not null,
last_used timestamp not null);
select * from persistent_logins;




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
    DELETE FROM User WHERE Withdrawal = 1 and LoginDate <= DATE_SUB(NOW(), INTERVAL 6 month); 
CREATE
	EVENT WithdrawaStudentDelete ON SCHEDULE EVERY 1 day STARTS '2021-05-04'
    DO
    DELETE FROM Student WHERE LoginDate <= DATE_SUB(NOW(), INTERVAL 6 month); 
    
#강의실예약 하루마다 비우기
CREATE
	EVENT UserReservationDelete ON SCHEDULE EVERY 1 day STARTS '2021-05-30'
    DO
    DELETE FROM User UserReservation WHERE ReservationDate <= DATE_SUB(NOW(), INTERVAL 1 day);
 
#한 학기 지나면 팀 지우기
CREATE
	EVENT TeamDelete ON SCHEDULE EVERY 1 day STARTS '2021-05-30'
    DO
    DELETE From Team WHERE TeamCreationDate <= DATE_SUB(NOW(), INTERVAL 5 month);
    
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