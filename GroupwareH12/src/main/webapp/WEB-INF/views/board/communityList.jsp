<!-- 게시판 리스트 출력 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="css/boardList.css" type="text/css">
<link rel="stylesheet" href="css/menubar.css" type="text/css">
<title>community list</title>
</head>
<body>
	<div class="mheader">
		<!--메뉴바 -->
		<div class="menubar">
			<div class="menubarWidth">
				<div class="menubarLogo">
					<!--로고추가하기-->
				</div>
				<div class="menubarMid">
					<nav id="navigation1">
						<ul id="topInfo">
							<!-- sign out -->
							<li><sec:authorize access="isAuthenticated()">
									<a href="#"
										onclick="document.getElementById('logout').submit();">로그아웃</a>
								</sec:authorize>
								<form id="logout" action="${path}/logout.do" method="POST">
									<input name="${_csrf.parameterName}" type="hidden"
										value="${_csrf.token}" />
								</form></li>
							<li><a href="${path}/inquiryList">문의</a></li>
							<li><a href="${path}/myPage?R=${UserRole}">마이페이지</a></li>
							<li><a href="">사이트맵</a></li>
						</ul>
					</nav>
					<!-- 메뉴 -->
					<nav id="navigation2">
						<ul id="topMenu">
							<li><a href="homeLogin">홈</a></li>
							<li><a href="${path}/email/emailLogin">메일</a></li>
							<li><a href="${path}/communityList">게시판</a>
								<ul id="subMenu">
									<li><a href="${path}/noticeList">공지사항</a></li>
									<li><a href="${path}/communityList">커뮤니티</a></li>
									<li><a href="#">후기</a></li>
								</ul></li>
							<li><a href="">강의실</a></li>
							<li><a href="">일정관리</a></li>
							<li><a href="">조회</a></li>
							<li><a href="">팀원관리</a></li>
							<li><a href="">문서관리</a></li>
						</ul>
					</nav>
				</div>
				<!-- menubar_mid -->
			</div>
			<!-- menubar_width -->
		</div>
		<!-- menubar -->
	</div>
	<!-- mheader -->
	<div class="mbody">
		<div class="mcontWidth">

			<!-- left_box -->
			<div class="rightBox">
				<section>
					<div class="section">
						<br>
						<h2>커뮤니티</h2>
						<hr>
					</div>
				</section>

				<section>
					<div class="section2">
						<div id="search">
							<select name="SelectOption" id="selectOption">
								<option value="all">전체</option>
							</select> <input type="text" placeholder="검색어 입력하세요."> <input
								type="submit" value="검색">
						</div>

						<form action="" name="CommunityList" method="POST" id="form">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<table class="communityList">
								<thead>
									<tr>
										<th class="col1">번호</th>
										<th class="col2">제목</th>
										<th class="col3">작성자</th>
										<th class="col4">작성일</th>
										<th class="col5">댓글</th>
										<th class="col6">조회수</th>
									</tr>
									<hr>
								</thead>
								<tbody>
									<c:forEach items="${communityList}" var="communityList"
										varStatus="status">
										<tr>
											<td><c:out value="${status.count}" /></td>
											<td><a href="${path}/bulletinContent?no=$"><c:out
														value="제목" /></a></td>
											<td><c:out value="ㅇㅇㅇ" /></td>
											<td><c:out value="0" /></td>
											<td><c:out value="0" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<hr>
							<div id="page" class="btn">
								<input type="button" value="←" id="leftList"> <input
									type="button" value="1" id="pageList"> <input
									type="button" value="→" id="rightList"> <a
									href="${path}/communityWrite"><input type="button"
									value="글쓰기" id="write"></a>
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
</body>
</html>