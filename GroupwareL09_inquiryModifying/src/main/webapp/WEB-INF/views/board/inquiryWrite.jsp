<!-- 문의 작성 화면 -->


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
<meta charset="utf-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="css/boardContent.css" type="text/css">
<link rel="stylesheet" href="css/menubar.css" type="text/css">

<title>inquiry write</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/homeView/menubar.jsp"></jsp:include>
		<nav>
			<div class="mbody">
				<div class="mcontWidth">

					<jsp:include page="/WEB-INF/views/homeView/userInfoBox.jsp"></jsp:include> 
					<div class="rightBox">
						<section>
							<div class="section">
								<br>
								<h2>글 작성하기</h2>
								<hr>
							</div>
						</section>
						<section>
							<form action="InquiryWrite?${_csrf.parameterName}=${_csrf.token}"  name="InquiryWrite"
								method="POST" id="form">
								<div class="section2">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<table id="contentTable">
										<tr>
											<td><label for="title">제목 &nbsp; &nbsp; </label><input
												type="text" name="InquiryTitle" id="inquiryTitle"
												class="inputBox" value=${InquiryTitle}></td>
										</tr>
										<tr>
											<td colspan="2" id="writerPadding"><label for="writer">작성자 &nbsp; </label><input
												type="text" name="InquiryWriter" id="inquiryWriter"
												class="inputBox" placeholder = "작성자는 자동으로 입력됩니다." value="${InquiryWriter}" disabled readonly></td>
										</tr>
										<tr>
											<td id="selectPadding">
												<label for="type">&nbsp;&nbsp; 민원분류 &nbsp; </label>
												<select name="InquiryType" id="inquiryType">
												<option value="" selected>-선택-</option>
												<option value="lectureRoom">강의실 예약/이용</option>
												<option value="teamMembers">팀원관리</option>
												<option value="community">커뮤니티</option>
												<option value="review">후기</option>
												<option value="suggestion">건의</option>
												</select>
											</td>
										</tr>
										<tr>
											<td id="emailPadding"><label for="email">이메일 &nbsp; </label><input
												type="text" name="InquiryEmail" id="inquiryEmail"
												class="inputBox" placeholder = "이메일은 자동으로 입력됩니다." value="${InquiryEmail}" disabled readonly></td>
										</tr>
										<tr>
											<td id="numPadding"><label for="phoneNum">연락처 &nbsp; </label><input
												type="text" name="InquiryPhoneNum" id="inquiryPhoneNum"
												class="inputBox" placeholder = "연락처는 자동으로 입력됩니다."value="${InquiryPhoneNum}" disabled readonly></td>
											<td>
											</td>
										</tr>
										<tr>
											
										</tr>
										<tr>
											<td id="contentPadding"><textarea name="InquiryContent" id="inquiryContent"
												class="inputBox" placeholder="내용을 입력하세요"></textarea></td>
										</tr>
									</table>
									<hr>
								</div>
								<!-- section2 -->
								<div id="btn">
									<input type="submit" value="저장" id="listButton"> <a
										href="${path}/inquiryList"><input type="button"
										value="이전" id="listButton"></a>
								</div>
							</form>
						</section>
					</div>
					<!-- right_box -->

				</div>

			</div>
			<!-- mcont_width -->
	</div>
	<!-- mbody -->
	</nav>
</body>
</html>