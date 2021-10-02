<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
	<h1>채팅방</h1>
	<div id="send" class="container tab-pane active">
		<br>
		<table class="table table-dark table-striped text-center">
			<thead>
				<tr>
					<th>방 번호</th>
					<th>방 제</th>
					<th>비밀번호</th>
					<th>들어가기</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="room" items="${rooms.content}">
					<tr>
						<td>${room.roomNum}</td>
						<td>${room.roomName}</td>
						<td>null</td>
						<td><div>
								<input type="hidden"  id="roomNum" value="${room.roomNum}" >
								<button id="enter" >들어가기</button>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<table class="inputTable">
			<tr>
				<th>방 제목</th>
				<th><input type="text" name="roomName" id="roomName"></th>
				<th><button id="createRoom">방 만들기</button></th>
			</tr>

		</table>
	</div>
</div>

<script src="/js/chat.js"></script>