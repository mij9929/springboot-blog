let index = {
	init: function() {
		$("#btn-save").on("click", () => { // function(){}, ()=> this를 바인딩하기위해서!
			this.save()
		});

		$("#btn-delete").on("click", () => { // function(){}, ()=> this를 바인딩하기위해서!
			this.delteById()
		});

		$("#btn-update").on("click", () => { // function(){}, ()=> this를 바인딩하기위해서!
			this.update()
		});

		$("#btn-reply-save").on("click", () => { // function(){}, ()=> this를 바인딩하기위해서!
			this.replySave()
		});
		
			$("#btn-reply-delete").on("click", () => { // function(){}, ()=> this를 바인딩하기위해서!
			this.replyDelete()
		});
	}
	,

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),

		}
		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때  

		}).done(function(resp) {
			alert("등록 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청

	},

	delteById: function() {
		let id = $("#id").val();
		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때  

		}).done(function(resp) {
			alert("삭제완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	},

	update: function() {
		let id = $("#id").val();

		let data = {
			title: $("#title").val(),
			content: $("#content").val(),

		}

		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때  

		}).done(function(resp) {
			alert("수정 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청

	},

	replySave: function() {
		let data = {
			userId:$("#userId").val(),
			boardId:$("#boardId").val(),
			content: $("#reply-content").val()
		}
		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때  

		}).done(function(resp) {
			alert("댓글 작성 완료");
			location.href = `/board/${data.boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	},
	
		replyDelete: function() {
		let replyId = $("#replyId").val();
		let boardId = $("#boardId").val();
		
		alert(replyId);
		alert(boardId);
		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때  

		}).done(function(resp) {
			alert("삭제완료");
			location.href = `/board/${boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	},


}

index.init();