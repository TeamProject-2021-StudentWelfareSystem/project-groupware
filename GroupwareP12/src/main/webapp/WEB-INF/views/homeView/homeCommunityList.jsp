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
<link rel="stylesheet" href="css/homeBoardList.css" type="text/css">
<title>community list</title>
</head>
<body>
<section>
		<div class="section">
			<span class="title">커뮤니티</span><span class="move"><a href="${path}/communityList">더보기</a></span>
			<hr>
		</div>
	</section>
	<section>
		<div class="section2">

			<form action="" name="CommunityList" method="POST" id="form2">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<table id="communityList">
					<tbody>
						<c:forEach items="${communityList}" var="communityList"
							varStatus="status" end="2">
							<tr>
								<td class="col1"><a
									href="${path}/communityContent?no=${communityList.getBoardID()}">
										<c:out value="${communityList.getBoardSubject()}" />
								</a></td>
								<td class="col2"><c:out value="${communityList.getBoardDate()}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
		<!-- section2 -->
	</section>
</body>
</html>