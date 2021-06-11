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

	private String LoginIdName;
	private String LoginPwdName;
	private String ErrorMsgName; // 로그인 페이지에서 jstl을 이용
	private String DefaultFailureUrl;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String Username = request.getParameter(LoginIdName);
		String Password = request.getParameter(LoginPwdName);
		String Errormsg = null;

		if (exception instanceof AuthenticationServiceException) { // null 값을 리턴한 경우 // 시스템에서 인증 요구가 거부됐을 때
			Errormsg = "존재하지 않는 사용자입니다.";

		} else if (exception instanceof BadCredentialsException) { // UserDetails 객체를 리턴했으나, 아이디 또는 비밀번호가 틀린 경우
			Errormsg = "아이디 또는 비밀번호가 일치하지 않습니다. 다시 확인해주세요.";

		} else if (exception instanceof DisabledException) { // UserDetails 객체의 isEnabled() 메소드의 리턴값이 false
			Errormsg = "계정이 비활성화되었습니다. 관리자에게 문의하세요.";
		}
		
		request.setAttribute(LoginIdName, Username);
		request.setAttribute(LoginPwdName, Password);
		request.setAttribute(ErrorMsgName, Errormsg);

		// 로그인 페이지로 다시 포워딩
		request.getRequestDispatcher(DefaultFailureUrl).forward(request, response);
	}

	public String getLoginIdName() {
		return LoginIdName;
	}

	public void setLoginIdName(String loginIdName) {
		this.LoginIdName = loginIdName;
	}

	public String getLoginPwdName() {
		return LoginPwdName;
	}

	public void setLoginPwdName(String loginPwdName) {
		this.LoginPwdName = loginPwdName;
	}

	public String getErrorMsgName() {
		return ErrorMsgName;
	}

	public void setErrorMsgName(String errorMsgName) {
		this.ErrorMsgName = errorMsgName;
	}

	public String getDefaultFailureUrl() {
		return DefaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.DefaultFailureUrl = defaultFailureUrl;
	}

}