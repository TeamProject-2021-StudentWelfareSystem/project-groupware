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
<meta charset="UTF-8">
<title>top bar</title>
</head>
<body>

	<!-- Topbar -->
	<nav
		class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

		<!-- Topbar Navbar -->
		<ul class="navbar-nav ml-auto">

			<!-- Nav Item - 관리자 설정 -->
			<li class="nav-item dropdown no-arrow"><sec:authorize
					access="hasRole('ROLE_ADMIN')">
					<a class="nav-link dropdown-toggle" href="${path}/admin/manageList"
						id="alertsDropdown" role="button"> <i class="fas fa-user-cog" title="관리자"></i>
					</a>
				</sec:authorize></li>

			<!-- Nav Item - 마이페이지 -->
			<li class="nav-item dropdown no-arrow"><sec:authorize
					access="isAuthenticated()">
					<a class="nav-link dropdown-toggle"
						href="${path}/myPage?R=${UserRole}" id="messagesDropdown"
						role="button"> <i class="fas fa-info-circle" title="내 정보"></i>
					</a>
				</sec:authorize></li>

			<!-- Nav Item - 회원가입 -->
			<li class="nav-item dropdown no-arrow"><sec:authorize access="isAnonymous()">
					<a class="nav-link dropdown-toggle" href="${path}/infoConsent"
						id="alertsDropdown" role="button">
						<i class="far fa-user-circle" title="회원가입"></i>
					</a>
				</sec:authorize></li>

			<!-- sign in -->
			<li class="nav-item dropdown no-arrow"><sec:authorize
					access="isAnonymous()">
					<a class="nav-link dropdown-toggle" href="${path}/login"
						id="messagesDropdown" role="button"> <i
						class="fas fa-sign-in-alt" title="로그인"></i>
					</a>
				</sec:authorize>
				<form id="signUp" action="/login" method="POST">
					<input name="${_csrf.parameterName}" type="hidden"
						value="${_csrf.token}" />
				</form></li>
			<!-- sign out -->
			<li class="nav-item dropdown no-arrow"><sec:authorize
					access="isAuthenticated()">
					<a class="nav-link dropdown-toggle" href="#"
						onclick="document.getElementById('logout').submit();"
						id="messagesDropdown" role="button" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
						class="fas fa-sign-out-alt" title="로그아웃"></i>
					</a>
				</sec:authorize>
				<form id="logout" action="${path}/logout.do" method="POST">
					<input name="${_csrf.parameterName}" type="hidden"
						value="${_csrf.token}" />
				</form></li>
		</ul>

	</nav>
	<!-- End of Topbar -->

</body>
</html>