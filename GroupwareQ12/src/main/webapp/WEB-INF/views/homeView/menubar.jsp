<!-- 페이지 상단 메뉴바 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>

</head>
<body id="page-top"> 
        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${path}/home">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">FMS</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Home -->
            <li class="nav-item active">
                <a class="nav-link" href="${path}/home">
                   <i class="fas fa-home"></i>
                    <span>HOME</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                MENU
            </div>
			<!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link" href="${path}/email/emailLogin">
                    <i class="fas fa-envelope"></i>
                    <span>메일</span>
                </a>
            </li>
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="false" aria-controls="collapseTwo">
                    <i class="fas fa-pen"></i>
                    <span>게시판</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">게시판</h6>
                        <a class="collapse-item" href="${path}/noticeList">공지사항</a>
                        <a class="collapse-item" href="${path}/communityList">커뮤니티</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="false" aria-controls="collapseUtilities">
                    <i class="fas fa-chalkboard"></i>
                    <span>강의실</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">강의실</h6>
                        <a class="collapse-item" href="${path}/lectureRoom/lectureRoomList">강의실 예약</a>
                        <a class="collapse-item" href="${path}/lectureRoom/reservationConfirm">예약 확인</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link" href="${path}/schedule/mySchedule">
                    <i class="fas fa-calendar"></i>
                    <span>일정</span>
                </a>
            </li>

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="${path}/search/searchUser">
                    <i class="fas fa-search"></i>
                    <span>사용자 검색</span></a>
            </li>

             <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTeam"
                    aria-expanded="false" aria-controls="collapseTeam">
                    <i class="fas fa-user-friends"></i>
                    <span>팀</span>
                </a>
                <div id="collapseTeam" class="collapse" aria-labelledby="headingTeam"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">팀</h6>
                        <a class="collapse-item" href="${path}/team/searchLecture">팀 생성</a>
                        <a class="collapse-item" href="${path}/team/teamList">팀 조회</a>
                        <a class="collapse-item" href="${path}/team/searchMyTeam">후기 작성</a>
                        <a class="collapse-item" href="${path}/team/myTeamList">자료 공유</a>
                    </div>
                </div>
            </li>
            
			<!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseQ"
                    aria-expanded="false" aria-controls="collapseQ">
                    <i class="far fa-question-circle"></i>
                    <span>문의</span>
                </a>
                <div id="collapseQ" class="collapse" aria-labelledby="headingQ"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">문의</h6>
                        <a class="collapse-item" href="#">FAQ</a>
                        <a class="collapse-item" href="${path}/inquiryList">문의하기</a>
                    </div>
                </div>
            </li>
			
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">


        </ul>
        <!-- End of Sidebar -->

</body>
</html>