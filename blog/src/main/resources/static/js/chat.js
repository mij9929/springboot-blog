let index = {
	init: function() {
		$("#createRoom").on("click", () => { // function(){}, ()=> this를 바인딩하기위해서!
			this.save()
		});
		
		$("#enter").on("click", () => { // function(){}, ()=> this를 바인딩하기위해서!
			this.enter()
		});
	}
	,
	save: function() {
		let data = $("#roomName").val()

		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/api/chat",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때  
		}).done(function(resp) {
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	},
	
	enter:function(){
		var roomNum = $("#roomNum");
		var url = ('/chat/room/');
		
		window.open(url+roomNum.val());
	}
}

index.init();