<!-- 관리자 메뉴-회원 목록 클릭 시 정보 출력 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/userManage.css">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/userManage.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>user info management</title>
</head>
<body>
	<div class="mjs_ws">

		<div class="mheader">
			<!--배경화면-->
			<div id="mjs_film"
				style="z-index: 99997; position: absolute; display: none; width: 100%; height: 100%; background-color: #000000; filter: Alpha(opacity = 60); opacity: 0.4; -moz-opacity: 0.6;"></div>
			<!--메뉴 -->
			<div id="auth_check_div" align="center"
				style="z-index: 99999; position: absolute;"></div>
			<div class="menubar">
				<div class="menubar_width">
					<div class="menubar_logo">
						<!--로고추가하기-->
					</div>
					<div class="menubar_mid">
						<ul class="top_info">
							<li><a href="">사이트맵</a></li>
							<li><a href="manageList">관리자메뉴</a></li>
							<li><a href="">문의</a></li>
							<li><a href="home">로그아웃</a></li>
						</ul>
						<!-- 메뉴 -->
						<ul class="top_menu">
							<li id="home_tab" class="t_menu"><a href="home"><span
									class="tmenu_pack mover">홈</span></a></li>
							<li id="mail_tab" class="t_menu"><a href=""><span
									class="tmenu_pack mover">메일</span></a></li>
							<li id="gboard_tab" class="t_menu"><a href=""><span
									class="tmenu_pack mover">게시판</span></a></li>
							<li id="rectureRoom_tab" class="t_menu"><a href=""><span
									class="tmenu_pack mover">강의실</span></a></li>
							<li id="scheduling_tab" class="t_menu"><a href=""><span
									class="tmenu_pack mover">일정관리</span></a></li>
							<li id="memo_tab" class="t_menu"><a href=""><span
									class="tmenu_pack mover">메모</span></a></li>
							<li id="inquiry_tab" class="t_menu"><a href=""><span
									class="tmenu_pack mover">조회</span></a></li>
							<li id="teammates_tab" class="t_menu"><a href=""><span
									class="tmenu_pack mover">팀원관리</span></a></li>
							<li id="documents_tab" class="t_menu"><a href=""><span
									class="tmenu_pack mover">문서관리</span></a></li>
						</ul>
					</div><!-- menubar_mid -->
				</div><!-- menubar_width -->
			</div><!-- menubar -->
		</div><!-- mheader -->
		<nav>
			<div class="mbody">
				<div class="mcont_width">
					<div class="left_box">
						<div class="left_info">
							<!--로그인 후 화면-->
							<div class=img>
								<img src="user.png" alt="userimg" width="50" height="50">
							</div>

							<br>
							<div class=userName>님, 안녕하세요!</div>
							<div class="userColleges">
								<h4 color="blue">소속 :</h4>
							</div>
							<div class="userGrade">
								<h4 color="blue">학년 :</h4>
							</div>

						</div>
					</div>
					<div class="right_box">
						<section id="section">
							<div class="section">
								<h2>&nbsp;사용자 관리</h2>
								<hr>
							</div>
						</section>
						<section id="section2">
							<!-- 교수 사용자 -->
							<div id="professorInfo">
								<h3>&nbsp;사용자 정보</h3>
								<form action="" name="ModifyProfessor" method="POST" id="form">
									<table>
										<tr>
											<td class="col1"><label for="id">&nbsp;아이디(학번)&nbsp;</label></td>
											<td class="col2"><input type="text" name="UserLoginID"
												id="userLoginID" class="inputBox" placeholder="" autofocus
												autocomplete="off" disabled readonly value=${UserLoginID}></td>
										</tr>
										<tr>
											<td class="col1"><label for="name">이름</label></td>
											<td class="col2"><input type="text" name="UserName"
												id="userName" class="inputBox" autocomplete="off" disabled
												readonly value=${UserName}></td>
											<td class="col1"><label for="phoneNumber">연락처</label></td>
											<td class="col2"><input type="text" name="UserPhoneNum"
												id="userPhoneNum" class="inputBox" autocomplete="off"
												disabled readonly value=${UserPhoneNum}></td>
										</tr>
										<tr>
											<td class="col1"><label for="professorColleges">단과대학</label></td>
											<td class="col2"><input name="ProfessorColleges"
												id="professorColleges" class="inputBox" disabled readonly
												value=${ProfessorColleges}></td>
											<td class="col1"><label for="professorMajor">전공</label></td>
											<td class="col2"><input name="ProfessorMajor"
												id="professorMajor" class="inputBox" disabled readonly
												value=${ProfessorMajor}></td>
										</tr>
										<tr>
                        					<td class="col1"><label for="professorRoom">교수실</label></td>
                        					<td class="col2"><input name="professorRoom"
                           						id="professorRoom" class="inputBox"disabled readonly></td>
			                        		<td class="col1"><label for="professorRoom">교수실 전화번호</label></td>
			                        		<td class="col2"><input name="professorRoomNum"
			                           		id="professorRoomNum" class="inputBox"disabled readonly></td>
			                     		</tr>
										<tr>
											<td class="col1"><label for="email">이메일</label></td>
											<td class="col2"><input type="text" name="UserEmail"
												id="userEmail" class="inputBox" autocomplete="off" disabled
												readonly value=${UserEmail}></td>
											<td class="col3"><input type="email" value="@mju.ac.kr"
												disabled readonly name="email" id="mju" class="inputBox"
												autocomplete="off"></td>
										</tr>
										<tr>
											<td class="col1"><label for="infoOpen">정보 공개</label></td>
											<td class="col2"><input type="text" name="UserInfoOpen"
												id="userInfoOpen" class="inputBox" autocomplete="off"
												disabled readonly value=${UserInfoOpen}></td>
										</tr>
									</table>
								</form>
							</div><!-- professorInfo -->
							<div id="btnDiv">
								<input type="button" id="deleteBtn" value="삭제"> <a
									href="ManageList"><input type="button" id="listBtn"
									value="목록"></a>
							</div>
						</section>
					</div><!-- right_box -->
				</div><!-- mcont_width -->
			</div><!-- mbody -->
		</nav>
	</div><!-- mjs_ws -->
</body>
</html>