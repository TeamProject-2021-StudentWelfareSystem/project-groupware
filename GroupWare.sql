show databases;
create database GroupWare;
use GroupWare;
show tables;

create table user(
userID int auto_increment not null primary key,
userName varchar(20) not null,
userPhoneNum varchar(30) not null,
userEmail varchar(100) not null,
userLoginID varchar(30) binary not null,
userLoginPwd varchar(300) binary not null,
userRole ENUM ('STUDENT', 'PROFESSOR', 'ADMINISTRATOR'),
authority varchar(20) not null default 'ROLE_USER', # ROLE_USER, ROLE_ADMIN
enabled boolean not null default 1 # 활성화:1 비활성화:0
);

create table student(
studentID int auto_increment not null primary key,
studentNum int not null unique key, #학번
studentGrade varchar(10) not null, #학년
studentGender varchar(20) not null, # male / female
studentColleges ENUM ('인문대학', '사회과학대학', '경영대학', '법과대학', 'ICT융합대학', '미래융합대학') not null, #단과대학
studentMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부', '사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과') not null, #전공
studentDoubleMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부','사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과'), #복수전공
userID int, foreign key (userID) references user(userID) on delete cascade on update cascade
);

create table professor(
professorID int auto_increment not null primary key,
professorColleges ENUM ('인문대학', '사회과학대학', '경영대학', '법과대학', 'ict융합대학', '미래융합대학') not null, #단과대학
professorMajor ENUM ('국어국문학과', '영어영문학과', '중어중문학과', '일어일문학과', '사학과', '문헌정보학과', '아랍지역학과', '미술사학과', '철학과', '문예창작학과', 
'행정학과', '경제학과', '정치외교학과', '디지털미디어학과', '아동학과', '청소년지도학과',
'경영정보학과', '국제통상학과',
'법학과',
'융합소프트웨어학부', '디지털콘텐츠디자인학과',
'창의융합인재학부''사회복지학과', '부동산학과', '법무행정학과', '심리치료학과', '미래융합경영학과', '멀티디자인학과', '계약학과') not null, #전공
professorRoom varchar(10), #교수실
professorRoomNum varchar(30), #교수실전화번호 
userID int, foreign key (userID) references user(userID) on delete cascade on update cascade
);



# * ON DELETE SET NULL
* ON UPDATE SET NULL
옵션 SET NULL -> 부모테이블에서 primary 값이 수정 또는 삭제될 경우
하위테이블의 reference값은 존재할 수 없습니다. 옵션이 없을 경우는 에러가 발생하고 옵션 SET NULL 로 정의되면 하위테이블의 reference값이  NULL 값으로 변경되면서 참조무결성을 유지합니다.

* ON UPDATE CASCADE
옵션 CASCADE -> 부모테이블에서 primary 값이 수정될 경우
옵션 CASCADE 로 정의되면 하위테이블의 reference값은 변경된 상위테이블의 수정된 값을 가지면서 참조무결성을 유지합니다.

* ON DELETE CASCADE
옵션 CASCADE -> 부모테이블에서 primary 값이 삭제될 경우
옵션 CASCADE 로 정의되면 하위테이블의 reference값은 삭제되면서 참조무결성을 유지합니다.
