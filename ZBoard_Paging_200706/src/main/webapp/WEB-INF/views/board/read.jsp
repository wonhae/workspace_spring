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
			<h1>글 자세히 보기</h1>
		</div>

		<div class="row">  <!-- div 가 row -> 최소 한줄!  -->
			<div class="form-group">
				<label for="title">제목</label>
				<input readonly value="${vo.title}" class="form-control">
			</div>
		</div>

		<div class="row">
			<div class="form-group">
				<label for="writer">작성자</label>
				<input readonly value="${vo.writer}" class="form-control">
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<label for="content">내용</label>
				<textarea  rows ="5" readonly value="${vo.content}" class="form-control">${vo.content }</textarea>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<button class="btn btn-info" id="reply_form">댓글</button> <!-- 이걸누르면 댓글 다는 폼 열렸다가 닫혔다가!  -->
				<button class="btn btn-warning" id="update">수정</button>
				<button class="btn btn-danger" id="delete">삭제</button>
				<button class="btn btn-primary" id="list">목록</button>
			</div>
		</div>

	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#update').click(function(){
				location.assign("/board/update/${vo.bno}")  /*지금은 pathvariable 이용/  예전엔 a태그 사용하고,  update?이렇게 */
			});

			$('#delete').click(function(){
				location.assign("/board/delete/${vo.bno}")
			});

			$('#list').click(function(){
				location.assign("/board/list")
			});
		});
	</script>


</body>
</html>