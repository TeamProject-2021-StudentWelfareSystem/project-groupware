<!-- 관리자 메뉴 메인화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<!-- ajax 통신을 위한 meta tag -->
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">
<meta charset="utf-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="../css/manageList.css" type="text/css">
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/manageList.js"></script>

<title>manage page</title>
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
							<li><a href="${path}/manageList">관리자메뉴</a></li>
							<li><a href="">문의</a></li>
							<!-- sign out -->
							<li><sec:authorize access="isAuthenticated()">
									<a href="#"
										onclick="document.getElementById('logout').submit();">로그아웃</a>
								</sec:authorize>
								<form id="logout" action="${path}/logout.do" method="POST">
									<input name="${_csrf.parameterName}" type="hidden"
										value="${_csrf.token}" />
								</form></li>
						</ul>
						<!-- 메뉴 -->
						<ul class="topMenu">
							<li id="homeTab" class="tMenu"><a href="${path}/admin/homeAdmin"><span
									class="tmenuPackMover">홈</span></a></li>
							<li id="mailTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">메일</span></a></li>
							<li id="gboardTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">게시판</span></a></li>
							<li id="rectureRoomTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">강의실</span></a></li>
							<li id="schedulingTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">일정관리</span></a></li>
							<li id="memoTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">메모</span></a></li>
							<li id="inquiryTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">조회</span></a></li>
							<li id="teammatesTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">팀원관리</span></a></li>
							<li id="documentsTab" class="tMenu"><a href=""><span
									class="tmenuPackMover">문서관리</span></a></li>
						</ul>
					</div>
					<!-- menubar_mid -->
				</div>
				<!-- menubar_width -->
			</div>
			<!-- menubar -->
		</div>
		<!-- mheader -->
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
							<div class=userName>
								<input type=text name="UserName" id="userName" value=${UserName} readonly> 관리자님,
								안녕하세요!
							</div>
						</div>
						<!-- left_info -->
					</div>
					<!-- left_box -->
					<div class="rightBox">
						<section>
							<div class="section">
								<br>
								<h2>&nbsp;사용자 관리</h2>
								<hr>
							</div>
						</section>
						<section>
							<div class="section2">

								<h3>&nbsp; 사용자 리스트</h3>
								<a href="manageSleep"><input type="button" value="휴면계정관리"
									id="sleepID"></a> <a href="manageSecession"><input
									type="button" value="탈퇴계정관리" id="deleteID"></a>
								<div id="search">
									<select name="SelectOption" id="selectOption">
										<option value="all">전체</option>
									</select> <input type="text" placeholder="검색어 입력하세요."> <input
										type="submit" value="검색">
								</div>
								<form action="${path}/admin/manageList.do" name="ManageList"
									method="POST" id="form">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<table class="userList">
										<thead>
											<tr>
												<th><input type="checkbox" class="checkAll"
													id="checkAll" name="CheckAll"></th>
												<th>번호</th>
												<th>학번</th>
												<th>성명</th>
												<th>전화번호</th>
												<th>이메일</th>
												<th>직책</th>
												<th>권한</th>
												<th>접속기록</th>
											</tr>
											<hr>
										</thead>

										<tbody>
											<c:forEach items="${list}" var="list" varStatus="status">
												<tr>
													<td><input type="checkbox" name="RowCheck"
														class="check" value="${list.getUserLoginID()}" /></td>
													<td><c:out value="${status.count}" /></td>
													<td><a
														href="${path}/admin/detail?no=${list.getUserID()}&R=${list.getUserRole()}"><c:out
																value="${list.getUserLoginID()}" /></a></td>
													<td><a
														href="${path}/admin/detail?no=${list.getUserID()}&R=${list.getUserRole()}"><c:out
																value="${list.getUserName()}" /></a></td>
													<td><c:out value="${list.getUserPhoneNum()}" /></td>
													<td><c:out value="${list.getUserEmail()}" /></td>
													<td><c:out value="${list.getUserRole()}" /></td>
													<td><c:out value="${list.getAuthority()}" /></td>
													<td><c:out value="${list.getLoginDate()}" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<hr>
									<div id="page" class="btn">
										<div id="auth">

											<label for="grade">권한</label> <select id="selectRole"
												name="SelectRole">
												<option value=" " selected>-선택-</option>
												<option value="관리자">관리자</option>
												<option value="학생">학생</option>
												<option value="교수">교수</option>

											</select> <input type="button" value="변경" id="change"
												class="changeButton">
											<script>
												// ajax 통신을 위한 csrf 설정
												var token = $(
														"meta[name='_csrf']")
														.attr("content");
												var header = $(
														"meta[name='_csrf_header']")
														.attr("content");
												if (token && header) {
													$(document)
															.ajaxSend(
																	function(
																			e,
																			xhr,
																			options) {
																		xhr
																				.setRequestHeader(
																						header,
																						token);
																	});
												}

												$(".changeButton")
														.click(
																function() {
																	var CheckArr = new Array();
																	var SelectBox = $('#selectRole');
																	var OptionValue = SelectBox[0].value;

																	var list = $("input[name='RowCheck']");
																	for (var i = 0; i < list.length; i++) {
																		if (list[i].checked) {
																			CheckArr
																					.push(list[i].value);
																		}
																	}

																	if (CheckArr.length == 0) {
																		alert("선택된 사용자가 없습니다.");
																	} else {
																		var confirm_val = confirm("해당 사용자의 권한을 변경하시겠습니까?");

																		if (confirm_val) {
																			$
																					.ajax({
																						url : "manageList.do",
																						type : "POST",
																						traditional : true,
																						data : {
																							CheckArr : CheckArr,
																							OptionValue : OptionValue
																						},
																						success : function(
																								jdata) {
																							if (jdata = 1) {
																								alert("변경 성공");
																								$(
																										this)
																										.closest(
																												'form')
																										.submit();

																								location
																										.reload();
																							} else {
																								alert("변경 실패");
																							}
																						},
																						error : function() {
																							alert("서버통신 오류");
																						}
																					});
																		}
																	}
																});
											</script>
										</div>
										<input type="button" value="←" id="leftList"> <input
											type="button" value="1" id="pageList"> <input
											type="button" value="→" id="rightList"> <input
											type="button" value="목록" id="list"> <input
											type="button" value="탈퇴" class="deleteButton" id="delete">
										<script>
											// ajax 통신을 위한 csrf 설정
											var token = $("meta[name='_csrf']")
													.attr("content");
											var header = $(
													"meta[name='_csrf_header']")
													.attr("content");
											if (token && header) {
												$(document)
														.ajaxSend(
																function(e,
																		xhr,
																		options) {
																	xhr
																			.setRequestHeader(
																					header,
																					token);
																});
											}

											$(".deleteButton")
													.click(
															function() {
																var CheckArr = new Array();
																var list = $("input[name='RowCheck']");
																for (var i = 0; i < list.length; i++) {
																	if (list[i].checked) {
																		CheckArr
																				.push(list[i].value);
																	}
																}

																if (CheckArr.length == 0) {
																	alert("선택된 사용자가 없습니다.");
																} else {

																	var confirm_val = confirm("정말 탈퇴 처리하시겠습니까?");
																	if (confirm_val) {
																		$
																				.ajax({
																					url : "withdrawal.do",
																					type : "POST",
																					traditional : true,
																					data : {
																						CheckArr : CheckArr
																					},
																					success : function(
																							jdata) {
																						if (jdata = 1) {
																							alert("탈퇴 성공");
																							location
																									.reload();
																						} else {
																							alert("탈퇴 실패");
																						}
																					},
																					error : function() {
																						alert("서버통신 오류");
																					}
																				});
																	}
																}
															});
										</script>
									</div>
								</form>
							</div>
							<!-- section2 -->
						</section>
					</div>
					<!-- right_box -->
				</div>
				<!-- mcont_width -->
			</div>
			<!-- mbody -->
		</nav>
	</div>
	<!-- mjs_ws -->
</body>
</html>