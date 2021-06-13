<!-- 관리자 메뉴-탈퇴계정관리 클릭 시 탉퇴 계정 리스트 출력 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

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
<link rel="stylesheet" href="../css/menubar.css" type="text/css">
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/manageList.js"></script>
<title>manage secession list</title>
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
								<h2 id="title">&nbsp;사용자 관리</h2>
								
							</div>
							<hr>
						</section>
						<section>

							<div class="section2">
								<h3>&nbsp; 탈퇴 계정 관리</h3>
								<div id="search">
									<select name="SelectOption" id="selectOption">
										<option value="all">전체</option>
									</select> <input type="text" placeholder="검색어 입력하세요."> <input
										type="submit" value="검색">
								</div>
								<form>
									<table class="secessionUserList">
										<thead>
											<tr>
												<th><input type="checkbox" class="checkAll" id="checkAll" name="CheckAll"></th>
												<th>번호</th>
												<th>학번</th>
												<th>성명</th>
												<th>전화번호</th>
												<th>이메일</th>
												<th>직책</th>
												<th>권한</th>
												<th>탈퇴날짜</th>
											</tr>
											<hr>
										</thead>
										<c:forEach items="${SelectWithdrawalUserList}" var="SelectWithdrawalUserList" varStatus="status">
											<tr>
												<td><input type="checkbox" name="RowCheck" class="check" value="${SelectWithdrawalUserList.getUserLoginID()}"/></td>
												<td><c:out value="${status.count}" /></td>
												<td><c:out value="${SelectWithdrawalUserList.getUserLoginID()}" /></td>
												<td><c:out value="${SelectWithdrawalUserList.getUserName()}" /></td>
												<td><c:out value="${SelectWithdrawalUserList.getUserPhoneNum()}" /></td>
												<td><c:out value="${SelectWithdrawalUserList.getUserEmail()}" /></td>
												<td><c:out value="${SelectWithdrawalUserList.getUserRole()}" /></td>
												<td><c:out value="${SelectWithdrawalUserList.getAuthority()}" /></td>
												<td><c:out value="${SelectWithdrawalUserList.getLoginDate()}" /></td>
											</tr>
										</c:forEach>
									</table>
								</form>
								<hr>
								<form>
									<div id="page">
										<input type="button" value="←" id="leftList"> <input
											type="button" value="1" id="pageList"> <input
											type="button" value="→" id="rightList"> 
											<a href="${path}/admin/manageList"> <input
											type="button" value="목록" id="list"></a>
										<input type="submit" value="복구" id="recovery" class="recoveryButton">
											
										<script>
										// ajax 통신을 위한 csrf 설정
										var token = $("meta[name='_csrf']").attr("content");
										var header = $("meta[name='_csrf_header']").attr("content");
										if(token && header) {
											$(document).ajaxSend(function(e, xhr, options) {
											    xhr.setRequestHeader(header, token);
											});
										}
										
										 $(".recoveryButton").click(function(){											    																						    
											 var CheckArr = new Array();
											  var list = $("input[name='RowCheck']");
												 for(var i=0; i<list.length; i++) {
													 if(list[i].checked) {
														 CheckArr.push(list[i].value);
													 }
												 }
												 
												 if(CheckArr.length == 0) {
													 alert("선택된 사용자가 없습니다.");
												 } else {
													 
													 var confirm_val = confirm("정말 복구 처리하시겠습니까?");
													 if(confirm_val) {
													   $.ajax({
													    url : "withdrawalRecovery.do",
													    type : "POST",
													    traditional : true,
													    data : {
													    	CheckArr : CheckArr
													    },
													    success : function(jdata){
													    	if (jdata = 1) {
															    alert("복구 성공");
															    location.reload();
													    	} else {
													    		alert("복구 실패");
													    	}
													    },
													    error: function(){alert("서버통신 오류");}
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