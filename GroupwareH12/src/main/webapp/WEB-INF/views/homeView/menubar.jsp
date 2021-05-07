<meta charset="utf-8">
<div class="mheader">
      <!--메뉴바 -->
   <div class="menubar">
       <div class="menubarWidth">
            <div class="menubarLogo"><!--로고추가하기-->
            </div>
            <div class="menubarMid">
            <nav id="navigation1">
            <ul id="topInfo">
            <!-- sign out -->
            <li><sec:authorize access="isAuthenticated()">
                     <a href="#" onclick="document.getElementById('logout').submit();">로그아웃</a>
                  </sec:authorize>
                  <form id="logout" action="${path}/logout.do" method="POST">
                     <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
                  </form>
               </li>
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
                    </ul>
                  </li>
                  <li><a href="">강의실</a></li>
                  <li><a href="">일정관리</a></li>
                  <li><a href="">조회</a></li>
                  <li><a href="">팀원관리</a></li>
                  <li><a href="">문서관리</a></li>
                </ul>
            </nav>
          </div><!-- menubar_mid -->
      </div><!-- menubar_width -->
   </div><!-- menubar -->
</div><!-- mheader -->