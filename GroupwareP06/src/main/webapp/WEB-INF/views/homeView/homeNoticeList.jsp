<!-- 공지사항 리스트 출력 화면 -->

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

</head>
<body>
	<section>
		<div class="section">
			<span class="title">공지사항</span><span class="move"><a href="${path}/noticeList">더보기</a></span>
			<hr>
		</div>
	</section>

	<section>
		<div class="section2">
			<form action="" name="NoticeList" method="POST" id="form">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<table class="noticeList">
					<tbody>
					
						<c:forEach items="${noticeList}" var="noticeList"
							varStatus="status" end="2">
							<tr>
								<td class="col1"><a
									href="${path}/noticeContent?no=${noticeList.getBoardID()}">
										<c:out value="${noticeList.getBoardSubject()}" />
								</a></td>
								<td class="col2"><c:out value="${noticeList.getBoardDate()}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</section>
</body>
</html>