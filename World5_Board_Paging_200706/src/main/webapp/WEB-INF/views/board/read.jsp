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
				<button class="btn btn-info" id="reply_form">댓글</button> <!-- 이걸누르면 댓글 다는 mycollapse 폼 열렸다가 닫혔다가!  -->
				<button class="btn btn-warning" id="update">수정</button>
				<button class="btn btn-danger" id="delete">삭제</button>
				<button class="btn btn-primary" id="list">목록</button>
			</div>
		</div><!-- class=row -->

		<div class="row"> <!--댓글작업  ->collapse ->댓글 버튼 누르면 나오게하는거!! --><!-- 정적으로 만들어진 것(직접 타이핑). 다만 보이게 안보이게만 해준 것일뿐! -->
			<div id="myCollapse" class="collapse">  <!-- 이것만 한상태에서는 아래 작성내용이 사라진다/ script 작업 해줘야 한다! -->
				<div class="form-group">
					<label for="replyer">댓글 작성자</label>
					<input class="form-control"  id="replyer"><!--form tag로 감싸져있는게 아니라서 name 이 빠졌다!-->
				</div>
				
				<div class="form-group">
					<label for="replytext">댓글내용</label>
					<input class="form-control"  id="replytext"><!--form tag로 감싸져있는게 아니라서 name 이 빠졌다!-->
				</div>
				
				<div class="form-group">
					<button id="replyInsertBtn" class="btn btn-primary">댓글 등록</button>
				</div>
			</div><!--class=collapse  -->
		</div><!-- class=row -->
		
		
	</div><!-- class=container  -->
	
	<script type="text/javascript">
		var bno =${vo.bno};  /* 전역변수! scrip 어디서든 사용가능/ (지역변수: 해당 함수 안에서 사용가능! ) */
	
		$(document).ready(function(){

			$('#replyInsertBtn').click(function(){ /* 클릭하면 ajax 통신하도록!! ->이 데이터를 가지고 ajax 통신을 한다!! ->총 3개를 보낸다. bno,replyer,replytext */
				
				var replyer = $('#replyer').val();
				var replytext = $('#replytext').val();

				/* 7/7일 댓글 ajax  총3개 보냄 *//* board가 없다. 그냥 replies 만 있다. restcontroller로 구현할 것이기 때문에 */
				$.ajax({
					type: 'post',
					url: '/replies',  
					headers: {
						"Content-Type": "application/json",
						"X-HTTP-Method-Override": "POST"
					},
					dataType: 'text',
					data: JSON.stringify({
						bno: bno,  
						replyer: replyer,
						replytext: replytext
					}),
					success : function(result){
						$('#replyer').val(""); /* 댓글 입력하면 창에 써놓은 글자 없어지게! */
						$('#replytext').val("");
					},
					error : function(request,status,error){
						console.log(error);
					}
					
					
				});
			});

			$('#reply_form').click(function(){   /* 이걸누르면 댓글 다는 mycollapse 폼 열렸다가 닫혔다가! */
				$('#myCollapse').collapse("toggle");
			});

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