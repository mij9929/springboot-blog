<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div class="card-header">
		<br />
		<h3>${board.title}</h3>
		<br />
		<div class="list-inline-item d-flex justify-content-between">
			<h6>
				 작성자: <span><i>${board.user.username}</i></span>
			</h6>
			
			<h6>
				조회: <span><i>${board.count}</i></span>
			</h6>
		</div>
	</div>

	<hr />

	<div>
		<div class="card-body">${board.content}</div>
	</div>

	<div class="container">
		<a href="/board/${board.id}/updateForm"
			class="btn btn-outline-primary btn-sm"">수정
			</button>
		</a>
		<c:if test="${board.user.id == principal.user.id}">
			<button id="btn-delete" class="btn btn-outline-primary btn-sm"">삭제</button>
		</c:if>
		<button class="btn btn-outline-primary btn-sm"
			onclick="history.back()">돌아가기</button>
	</div>

	<hr />

	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul class="list-group">
			<c:forEach var="reply" items="${board.replys}">
				<li class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div>
					<div class="d-flex">
						<div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
						<c:if test="${reply.user.id eq principal.user.id}">
							<form>
								<input type="hidden" id="replyId" value="${reply.id}" />
								<button id="btn-reply-delete" class="badge">삭제</button>
								<button id="btn-reply-update" class="badge">수정</button>
							</form>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>

	<hr />
	<!-- comment write  -->
	<div class="card">
		<div class="card-header">댓글 작성</div>
		<form>
			<input type="hidden" id="userId" value="${principal.user.id}" /> <input
				type="hidden" id="boardId" value="${board.id}"  />
			<div>
				<div class="card-body d-flex justify-content-between">
					<textarea id="reply-content" rows="1" class="form-control"></textarea>
					 &nbsp;
					<button id="btn-reply-save" class="badge">등 록</button>
				</div>
		
			</div>
		</form>
		<!-- comment write  -->

	</div>
	<hr />

</div>


<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>