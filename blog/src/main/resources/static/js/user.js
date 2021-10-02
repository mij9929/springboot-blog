let index = {
	init: function() {
		$("#btn-save").on("click", () => { // function(){}, ()=> this를 바인딩하기위해서!
			this.save()
		});

		$("#btn-update").on("click", () => { // function(){}, ()=> this를 바인딩하기위해서!
			this.update()
		});

		$("#btn-profile").on("click", () => { // function(){}, ()=> this를 바인딩하기위해서!
			this.profileUpload()
		});

		$("#btn-mail-save").on("click", on => {
			this.mailSave()
		});
	}
	,
	save: function() {
		let data = {
			username: $("#username").val(),
			email: $("#email").val(),
			password: $("#password").val()
		}
		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때  
		}).done(function(resp) {
			console.log(resp);
			alert("회원가입 완료");

			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	},

	update: function() {

		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			email: $("#email").val(),
			password: $("#password").val()
		}

		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때  

		}).done(function(resp) {
			console.log(resp);
			alert("회원정보 수정 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	profileUpload: function() {

		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/user/profileUpload",
			data: new FormData($("#upload-file-form")[0]),
			enctpye: "multipart/form-data",
			processData: false,
			contentType: false,

		}).done(function(resp) {
			alert("등록 완료");
			location.reload();
			
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	mailSave: function() {
		let data = {
			receiver: $("#receiver").val(),
			title: $("#mailTitle").val(),
			content: $("#mailContent").val()
		}
		$.ajax({
			type: "POST",
			url: "/api/mail",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때  
		}).done(function(resp) {
			console.log(resp);
			alert("메세지 전송 완료");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	}
}

index.init();