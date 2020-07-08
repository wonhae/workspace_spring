<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row text-center">
			<h1>글쓰기</h1>
		</div>
		<div class="row">
			
			<form action="/board/insert" method="post">
				<div class="form-group">
					<label for="title">제목</label>
					<input name="title" id="title" class="form-control">
				</div>
				
				<div class="form-group">
					<label for="writer">작성자</label>
					<input name="writer" id="writer" class="form-control">				
				</div>
				
				<div class="form-group">
					<label for="content">내용</label>
					<textarea class="form-control" id="content" rows="5" name="content" ></textarea>
				</div>
				
			</form>
			
			<div clsss="form-group">
				<button class="btn btn-primary" id="insertbtn" >등록</button>
				<button class="btn btn-info" id="listbtn">목록</button>
			</div>
		</div>

	</div>
	
	
	<script type="text/javascript">
		$(document).ready(function(){

			$('#insertbtn').click(function(){
				$('form').submit();
			});
			
		});
	</script>
	
	
</body>
</html>