<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Please sign in</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
  </head>
  <body>
     <div class="container">
      <form class="form-signin" method="POST" action="${path}/login.do">
        <h2 class="form-signin-heading">로그인</h2>
        <p>
          <label for="username" class="sr-only">userLoginID</label>
          <input type="text" id="userLoginID" name="userLoginID" class="form-control" placeholder="ID" required="" autofocus="">
        </p>
        <p>
          <label for="password" class="sr-only">userLoginPwd</label>
          <input type="password" id="userLoginPwd" name="userLoginPwd" class="form-control" placeholder="Password" required="">
        </p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />  
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
	</div>
  </body>
  <whale-quicksearch translate="no"></whale-quicksearch>
</html>