<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h2>내 메일</h2>
	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#send">보낸 메일</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#receive">받은 메일</a></li>
	</ul>
	<div class="tab-content">
		<div id="send" class="container tab-pane active">
			<br>
			<table class="table table-dark table-striped text-center">
				<thead>
					<tr>
						<th>받는 사람</th>
						<th>제목</th>
						<th>내용</th>
						<th>시간</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="mail" items="${sendMails.content}">
						<tr>
							<td>${mail.receiver}</td>
							<td>${mail.title}</td>
							<td>${mail.content}</td>
							<td>${mail.timestamp}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div id="receive" class="container tab-pane fade">
			<br>
			<table class="table table-dark table-striped text-center">
				<thead>
					<tr>
						<th>보낸 사람</th>
						<th>제목</th>
						<th>내용</th>
						<th>시간</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="mail" items="${receiveMails.content}">
						<tr>
							<td>${mail.sender}</td>
							<td>${mail.title}</td>
							<td>${mail.content}</td>
							<td>${mail.timestamp}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<div class="container d-flex justify-content-end">
		<a href="/user/mailSave" class="btn btn-outline-primary btn-sm"">메일
			쓰기
			</button>
		</a>
	</div>

	<script src="/js/user.js"></script>
	<%@ include file="../layout/footer.jsp"%>