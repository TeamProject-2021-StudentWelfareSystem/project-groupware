package com.mju.groupware.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler { // 로그인 실패 후 부가작업

	private String loginIdName;
	private String loginPwdName;
	private String errorMsgName; // 로그인 페이지에서 jstl을 이용
	private String defaultFailureUrl;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String username = request.getParameter(loginIdName);
		String password = request.getParameter(loginPwdName);
		String errormsg = null;

		if (exception instanceof AuthenticationServiceException) { // null 값을 리턴한 경우 // 시스템에서 인증 요구가 거부됐을 때
			errormsg = "존재하지 않는 사용자입니다.";

		} else if (exception instanceof BadCredentialsException) { // UserDetails 객체를 리턴했으나, 아이디 또는 비밀번호가 틀린 경우
//			errormsg = MessageUtils.getMessage("error.BadCredentials");
			errormsg = "아이디 또는 비밀번호가 일치하지 않습니다. 다시 확인해주세요.";

		} else if (exception instanceof DisabledException) { // UserDetails 객체의 isEnabled() 메소드의 리턴값이 false
//			errormsg = MessageUtils.getMessage("error.Disabled");
			errormsg = "계정이 비활성화되었습니다. 관리자에게 문의하세요.";
		}
		
		request.setAttribute(loginIdName, username);
		request.setAttribute(loginPwdName, password);
		request.setAttribute(errorMsgName, errormsg);

		// 로그인 페이지로 다시 포워딩
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);

	}

	public String getLoginIdName() {
		return loginIdName;
	}

	public void setLoginIdName(String loginIdName) {
		this.loginIdName = loginIdName;
	}

	public String getLoginPwdName() {
		return loginPwdName;
	}

	public void setLoginPwdName(String loginPwdName) {
		this.loginPwdName = loginPwdName;
	}

	public String getErrorMsgName() {
		return errorMsgName;
	}

	public void setErrorMsgName(String errorMsgName) {
		this.errorMsgName = errorMsgName;
	}

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}

}