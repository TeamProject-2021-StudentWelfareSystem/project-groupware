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

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	// 스프링 시큐리티의 단점?이라고 볼 수 있는 경우인데
	// 로그인 안 한 상태에서 '관리자 메뉴' -> 로그인 창이 뜨겠지. 여기서 뒤로가기 후 home에서 학생 로그인 -> 로그인 성공하면 homeLogin으로 안 가고 이전에 접속시도했던 '관리자 메뉴'로 가버림. 
	// (학생 로그인 된 상태로 관리자메뉴 들어간 셈이라 forbidden 경고 뜸)
	// 근데 만약 로그인 성공 시 '반드시' homeLogin으로 가도록 설정하면 또 문제가 되는 게..관리자메뉴 접속 시도하면 나오는 로그인창에서 바로 학생로그인 하면 forbidden이 안뜨고 homeLogin이 뜸....딜레마
	// 다음에 이거 좀 고쳐보겠음!!!
	// https://okky.kr/article/416253 참고

	private String targetUrlParameter;

	private String defaultUrl;

	private boolean useReferer;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public CustomAuthenticationSuccessHandler() {
		targetUrlParameter = "";
		defaultUrl = "/homeLogin";
		useReferer = false;
	}

	public String getTargetUrlParameter() {
		return targetUrlParameter;
	}

	public void setTargetUrlParameter(String targetUrlParameter) {
		this.targetUrlParameter = targetUrlParameter;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	public boolean isUseReferer() {
		return useReferer;
	}

	public void setUseReferer(boolean useReferer) {
		this.useReferer = useReferer;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		clearAuthenticationAttributes(request);
		// user 로그인 성공하면 그의 그룹에 따라서 사용가능한 Crt센터를 받는다
		authentication.getDetails();
		// 무조건 처음화면으로
		redirectStrategy.sendRedirect(request, response, defaultUrl);
//	    int intRedirectStrategy = decideRedirectStrategy(request, response);
//	    switch (intRedirectStrategy) {
//	    case 1:
//	       useTargetUrl(request, response);
//	       break;
//	    case 2:
//	       useSessionUrl(request, response);
//	       break;
//	    case 3:
//	       useRefererUrl(request, response);
//	       break;
//	    default:
//	       useDefaultUrl(request, response);
//	    }
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
