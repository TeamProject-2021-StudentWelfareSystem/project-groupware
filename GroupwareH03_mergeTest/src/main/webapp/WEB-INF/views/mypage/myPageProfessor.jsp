<!-- 마이페이지 화면 (교수) -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/myPage.css">
<script src="js/jquery-3.5.1.min.js"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<title>my page</title>
</head>
<body>
	<div class="mjsWs">
		<div class="mheader">
			<!--배경화면-->
			<div id="mjsFilm"
				style="z-index: 99997; position: absolute; display: none; width: 100%; height: 100%; background-color: #000000; filter: Alpha(opacity = 60); opacity: 0.4; -moz-opacity: 0.6;"></div>
			<!--메뉴 -->
			<div id="authCheckDiv" align="center"
				style="z-index: 99999; position: absolute;"></div>
			<div class="menubar">
				<div class="menubarWidth">
					<div class="menubarLogo">
						<!--로고추가하기-->
					</div>
					<div class="menubarMid">
						<ul class="topInfo">
							<li><a href="">사이트맵</a></li>
							<li><a href="myPageProfessor">마이페이지</a></li>
							<li><a href="">문의</a></li>
							<!-- sign out -->
		                     <li><sec:authorize access="isAuthenticated()">
		                           <a href="#" onclick="document.getElementById('logout').submit();">로그아웃</a>
		                        </sec:authorize>
		                        <form id="logout" action="${path}/logout.do" method="POST">
		                           <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
		                        </form>
		                     </li>
						</ul>
						<!-- 메뉴 -->
                      <ul class="topMenu">
                        <li id="homeTab" class="tMenu">
                          <a href="homeLogin"><span class="tmenuPackMover">홈</span></a>
                        </li>
                             <li id="mailTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">메일</span></a>
                        </li>
                        <li id="gboardTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">게시판</span></a>
                        </li>
                        <li id="rectureRoomTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">강의실</span></a>
                        </li>
                        <li id="schedulingTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">일정관리</span></a>
                        </li>
                        <li id="memoTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">메모</span></a>
                        </li>
                        <li id="inquiryTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">조회</span></a>
                        </li>
                        <li id="teammatesTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">팀원관리</span></a>
                        </li>
                        <li id="documentsTab" class="tMenu">
                          <a href=""><span class="tmenuPackMover">문서관리</span></a>
                        </li>
                      </ul>
					</div><!-- menubar_mid -->
				</div><!-- menubar_width -->
			</div><!-- menubar -->
		</div><!-- mheader -->
		<nav>
			<div class="mbody">
				<div class="mcontWidth">
					<div class="leftBox">
						<div class="leftInfo">
							<!--로그인 후 화면-->
							<div class=img>
								<img src="user.png" alt="userimg" width="50" height="50">
							</div>

							<br>
							<!-- 화면 실행시 text란에 readonly가 뜨는 이유는 불러오는 값이 없어서임. -->
							<div class=userName><input type=text name="UserName" id="userName" value=${UserName} disabled readonly>님<br>안녕하세요!</div>
           					<div class="userColleges"><h4 color="blue">소속 : <input type=text name="PC" id="pc" value=${PC} disabled readonly></h4></div>
               				<div class="userMajor"><h4 color="blue">학과 : <input type=text name="UserMajor" id="userMajor" value=${UserMajor} disabled readonly></h4></div>
               				<div class="userRoom"><h4 color="blue">교수실 : <input type=text name="ProfessorRoom" id="professorRoom" value=${ProfessorRoom} disabled readonly></h4></div>
               				
						</div> <!-- left_info -->
					</div> <!-- left_box -->
					<div class="rightBox">
						<section id="memberInfo">
							<h2>회원 정보</h2>
							<br>
							<!--마이페이지 (교수)-->
							<div id="myPageProfessor" style="display: block;">

								<form action="${path}/myPageProfessor.do" name="ProfessorPage" method="POST" id="form">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<table>
										<tr>
											<td class="col1"><label for="id">아이디(학번)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></td>
											<td class="col2"><input type="text" name="UserLoginID"
												id="userLoginID" class="inputBox" placeholder="" autofocus
												autocomplete="off" disabled readonly value=${UserLoginID}></td>

										</tr>
										<tr>
											<td class="col1"><label for="name">이름</label></td>
											<td class="col2"><input type="text" name="UserName"
												id="userName" class="inputBox" autocomplete="off" disabled
												readonly value=${UserName}></td>
										</tr>
										
										<tr>
											<td class="col1"><label for="phoneNumber">연락처</label></td>
											<td class="col2"><input type="text" name="UserPhoneNum"
												id="userPhoneNum" class="inputBox" autocomplete="off"
												disabled readonly value=${UserPhoneNum}></td>
										</tr>
										<tr>
											<td class="col1"><label for="ProfessorColleges">단과대학</label></td>
											<td class="col2"><input type="text"
												name="ProfessorColleges" id="professorColleges" class="inputBox"
												disabled readonly value=${ProfessorColleges}></td>
										</tr>
										<tr>
											<td class="col1"><label for="ProfessorMajor">전공</label></td>
											<td class="col2"><input type="text" name="ProfessorMajor"
												id="professorMajor" class="inputBox" disabled readonly
												value=${ProfessorMajor}></td>
										</tr>
										<tr>
											<td class="col1"><label for="Room">교수실</label></td>
											<td class="col2"><input type="text" name="ProfessorRoom"
												id="professorRoom" class="inputBox" autocomplete="off" disabled
												readonly value=${ProfessorRoom}></td>
										</tr>
										<tr>
											<td class="col1"><label for="RoomNum">교수실 전화번호</label></td>
											<td class="col2"><input type="text" name="ProfessorRoomNum"
												id="professorRoomNum" class="inputBox" autocomplete="off" disabled
												readonly value=${ProfessorRoomNum}></td>
										</tr>
										<tr>
											<td class="col1"><label for="email">이메일</label></td>
											<td class="col2"><input type="text" name="UserEmail"
												id="userEmail" class="inputBox" autocomplete="off" disabled
												readonly value=${UserEmail}></td>
											<td class="col4"><input type="email" value="@mju.ac.kr"
												disabled readonly name="email" id="mju" class="inputBox"
												autocomplete="off"></td>
										</tr>
									</table>
								</form>
							</div>
							<br> <br>
							<div id="btnDiv">
								<a href="checkPassword"
									onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
									<input type="button" id="modifyBtn" value="수정하기">
								</a> <a href="modifyPassword"
									onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
									<input type="button" id="modifyPWBtn" value="비밀번호 변경하기">
								</a> <a href="withdrawal"
									onClick="window.open(this.href, '', 'width=800, height=800'); return false;">
									<input type="button" id="withdrawBtn" value="회원 탈퇴">
								</a>
							</div>
						</section>
					</div><!-- right_box -->
			</div><!-- mcont_width -->
		</div><!-- mbody -->
	</nav>
</div><!-- mjs_ws -->
</body>
</html>