<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h2>Login</h2>
	<form action="/auth/loginProc" method=post>
		<div class="form-group">
			<label for="username">username</label> <input type="text"
				name="username" class="form-control" placeholder="Enter username"
				name="username">
		</div>

		<div class="form-group">
			<label for="password">password</label> <input type="password"
				name="password" class="form-control placeholder="
				password"
				name="password">
		</div>

		<div class="form-group form-check">
			<label class="form-check-label"> <input name="remember"
				class="form-check-input" type="checkbox" name="remember">
				Remember me
			</label>
		</div>

		<button id="btn-login" , class="btn btn-primary">로그인</button>
		<a
			href="https://kauth.kakao.com/oauth/authorize?client_id=60238d6273f07c2bbd97a009c80e9c7d&redirect_uri=	
http://localhost:8000/auth/kakao/callback&response_type=code"><img
			height="37.61px" src="/image/KakaoLoginBtn.png" /></a>
	</form>


</div>

<%@ include file="../layout/footer.jsp"%>


