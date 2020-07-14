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
	<h1>로그인화면</h1>
	<div class="container">
		<div class="row">
			<form action="/member/loginpost" method="post">
				<div class="form-group">
					<label for="id">아이디입력</label>
					<input id="id" name="id" class="form-control">
				</div>
				<div class="form-group">
					<label for="pw">비밀번호지만 실제론 이름</label>
					<input id="pw" name="pw" class="form-control">
				</div>
				<div class="form-group">
					<button type="submit" class="btn form-control">로그인</button>
				</div>
			</form>
		</div>
	</div>
	
	
</body>
</html>