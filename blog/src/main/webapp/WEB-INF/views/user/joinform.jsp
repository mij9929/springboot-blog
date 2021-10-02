<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h2>Stacked form</h2>
	<form>
		<div class="form-group">
			<label for="username">username</label> <input type="text"
				class="form-control" id="username" placeholder="Enter username"
				name="username">
		</div>
		<div class="form-group">
			<label for=email">email</label> <input type="text"
				class="form-control" id="email" placeholder="Enter email"
				name="email">
		</div>

		<div class="form-group">
			<label for="password">password</label> <input type="password"
				class="form-control" id="password" placeholder="Enter password"
				name="password">
		</div>

		<div class="form-group form-check">
			<label class="form-check-label"> <input
				class="form-check-input" type="checkbox" name="remember">
				Remember me
			</label>
		</div>

		<button id="btn-save" class="btn btn-primary">회원가입 완료</button>
		

	</form>



	<script src="/js/user.js"></script>
	<%@ include file="../layout/footer.jsp"%>