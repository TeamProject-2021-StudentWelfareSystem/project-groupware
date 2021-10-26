package com.mju.groupware.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class LoginSuccessHandler implements AuthenticationSuccessHandler { // 로그인 성공 후 부가작업

	private String DefaultUrl;
	private RequestCache requestCache = new HttpSessionRequestCache(); // 사용자의 요청을 저장하고 꺼낼 수 있는 RequestCache
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public LoginSuccessHandler() {
		DefaultUrl = "/home";
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		clearAuthenticationAttributes(request); // 로그인 실패 에러 세션 제거 메소드
		resultRedirectStrategy(request, response, authentication); // redirect url 작업을 위한 메소드
	}

	// 로그인 실패 에러 세션 제거
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession Session = request.getSession(false);
		if (Session == null) { // 세션이 null 즉, 세션에 에러가 없다면 그냥 return
			return;
		}
		Session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION); // WebAttributes.AUTHENTICATION_EXCEPTION 이름 값으로 정의된 세션을 지움
	}

	// redirect login success url
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest != null) { // 세션에 이동할 url의 정보가 담겨져 있을 때(인증 권한이 필요한 페이지에 접근했을 경우)
			String TargetUrl = savedRequest.getRedirectUrl(); // savedRequest 객체를 통해 getRedirectUrl (로그인화면을 보기 전에 갔던 url)을 가져옴
			redirectStrategy.sendRedirect(request, response, TargetUrl); // TargetUrl로 화면 이동
		} else { // 직접 로그인 화면으로 접근했을 경우
			redirectStrategy.sendRedirect(request, response, DefaultUrl); // DefaultUrl(homeLogin)로 화면 이동
		}
	}

}
