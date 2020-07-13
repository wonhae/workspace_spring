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
	<script src ="/resources/js/uploadfn.js" type="text/javascript"></script> <!-- 7.13 시도..originalname 쓰기위해 추가해주기!  -->
<title>Insert title here</title>
</head>
<body>
		<!-- insert.jsp 복붙 -->
		<div class="container">
		<div class="row text-center">
			<h1>글 수정하기</h1>
		</div>
		<div class="row">
			
			<form action="/board/update" method="post"> <!-- pathvariable 넣어도 상관없다.  -->
				<input type="hidden" name="bno" value="${vo.bno}"> <!--대신이거 넣어줌  -->
				<div class="form-group">
					<label for="title">제목</label>
					<input value="${vo.title}" name="title" id="title" class="form-control">
				</div>
				
				<div class="form-group">
					<label for="writer">작성자</label>
					<input value="${vo.writer}" name="writer" id="writer" class="form-control">				
				</div>
				
				<div class="form-group">
					<label for="content">내용</label>
					<textarea class="form-control" id="content" rows="5" name="content" >${vo.content}</textarea>
				</div>
				
				<div class="form-group">  <!-- 7.13 시도 -->
				<label>첨부파일</label>
				<ul class="uploadedList clearfix">
				
				</ul>
			</div>
				
			</form>
			
			<div clsss="form-group">
				<button class="btn btn-primary" id="updatebtn" >수정</button>
				<button class="btn btn-info" id="listbtn">목록</button>
			</div>
		</div>

	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){


			
			
			$('#updatebtn').click(function(){
				$('form').submit()
			});

			$('#listbtn').click(function(){
				location.assign("/board/list")
			});


			/* 7.13 사진 update  시도 */
			$.getJSON("/updateAttach/"+bno, function(arr){ 
				console.log(arr);

				for(var i = 0; i < arr.length; i++){
					var str='<li class="col-xs-4">';
					str += '<a href="/displayfile?filename='+getImageLink(arr[i])+'">';
					if(checkImage(arr[i])){
						str += '<img src="/displayfile?filename='+arr[i]+'"/>';
					}else {
						str += '<img src="/resources/show.png"/>'
					}
					str += '</a>'

					str += '<p class="orifilename">';				
					str += getOriginalName(arr[i]); 
					str += '</p>'	
					str += '</li>';
					
					$('.uploadedList').modify(str);  
				}
				/* list.jsp에서 복붙  */
				
			});
			

			
		});
	</script>
	
	

</body>
</html>