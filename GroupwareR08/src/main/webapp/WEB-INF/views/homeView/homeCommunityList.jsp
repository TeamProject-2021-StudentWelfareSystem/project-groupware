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
<title>community list</title>
</head>
<body>
<section>
		<div class="card mb-4">
			<div class="card-header py-3">
				<a href="${path}/communityList"><span class="font-weight-bold">커뮤니티</span></a>
			</div>
				<form action="" name="CommunityList" method="POST" id="form2">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="mx-2">
					<table class="table">
						<tbody>
							<c:forEach items="${communityList}" var="communityList"
								varStatus="status" end="4">
							<tr>
								<td id="tableTitle" title="${communityList.getBoardSubject()}"><a href="${path}/communityContent?no=${communityList.getBoardID()}">
								<c:out value="${communityList.getBoardSubject()}" /></a></td>
								<td id="tableDate" class="small"><c:out value="${communityList.getBoardDate()}" /></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				</form>
		</div>
	</section>
</body>
</html>