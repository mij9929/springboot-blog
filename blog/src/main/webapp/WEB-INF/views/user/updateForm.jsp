<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div class="row">
		<div class=col-sm-6>
			<div class="card" style="width: 400px">
				<img class="card-img-top"
					src="/image/${principal.user.profileImage}" /> <br />
				<form id="upload-file-form">
					<input type="file" id="profile-image" name="profile-image">
					<button type="button" id="btn-profile" class="btn btn-primary">사진
						등록</button>
				</form>
			</div>
		</div>

		<div class="col-sm-6">
			<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
			<br />
			<form>
				<input type="hidden" id="id" value="${principal.user.id}">
				<div class="form-group">
					<label for=email">username</label> <input type="text"
						class="form-control" id="username"
						placeholder="${principal.user.username}"
						value="${ principal.user.username}" readonly>
				</div>


				<div class="form-group">
					<label for=email">email</label> <input type="text"
						class="form-control" id="email" name="email"
						value="${principal.user.email}" readonly="readonly">
				</div>

				<c:if test="${empty principal.user.oauth}">
					<div class="form-group">
						<label for="password">password</label> <input type="password"
							class="form-control" id="password" placeholder="Enter password"
							name="password">
					</div>
				</c:if>
		</div>
		</form>
	</div>

	<br /> <br />
	<div class="d-flex justify-content-end">
		<button id="btn-update" class="btn btn-primary ">회원가입 완료</button>
	</div>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


