<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for="title">Title</label> <input type="text"
				class="form-control" placeholder="Enter Title" id="mailTitle">
		</div>
		
		<div class="form-group">
			<label for="title">받는사람</label> <input type="text"
				class="form-control" placeholder="Enter Title" id="receiver">	
		</div>
		
		<div class="form-group">
			<label for="Content">Content</label>
			<textarea class="form-control summernote" rows="5" id="mailContent"></textarea>
		</div>
 
		<button id="btn-mail-save" , class="btn btn-primary">등록</button>
	</form>

</div>

<script>
      $('.summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 500
      });
    </script>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


