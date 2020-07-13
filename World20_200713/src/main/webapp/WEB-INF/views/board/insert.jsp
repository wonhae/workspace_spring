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

<style type="text/css">
	.fileDrop{
		width: 80%;
		height: 200px;
		border: 1px solid red;
		margin: auto;
	}
</style>

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
			
			
			<!-- 7.13 -->
			<div class="form-group">
				<label>업로드할 파일을 드랍시키세요.</label>
				<div class="fileDrop"></div>
				<ul class="uploadedList"></ul>  <!--div대신 ul로 하세요  -->
			</div>
			
			
			
			
			<div clsss="form-group">
				<button class="btn btn-primary" id="insertbtn" >등록</button>
				<button class="btn btn-info" id="listbtn">목록</button>
			</div>
		</div>

	</div>
	
	
	<script type="text/javascript">
		$(document).ready(function(){


			/* 7.13 uploadajax에서 복붙 */
			$('.fileDrop').on("drop",function(event){
				event.preventDefault();

				var files = event.originalEvent.dataTransfer.files;
				
				var file = files[0];  /* 하나만 가져옴   여기부터 아래2줄더 for 문 돌리면 여러개 가져오기 가능! */
				var formData = new FormData();
				formData.append("file",file);

				$.ajax({
					type : 'post',
					url : '/uploadajax',
					dataType : 'text',
					data : formData, 
					processData : false,
					contentType : false,
					success : function(result){   
						console.log(result); /* str="<div" 와 </a></div>는 짝이다!  */
						var str ="<li><a href='/displayfile?filename="+getImageLink(result)+"'>";  /* href에 파일명 뿐 아니라 소스(예 이미지의 데이터)도 들어갈 수 있다.  */

						if(checkImage(result)) {/* filename은 result에 있다.  */ /* 이미지일 경우! */
							str += "<img src = '/displayfile?filename="+result+"'/>" /* 여기 썸네일이찍힌다!. 바로 파일명 쓰면 안된다. 이미지를 바이트형태로 만들어 str에 넣는다. 데이터를 서버에서 이미지 태그에 집어넣어줄수있다. 데이터자체를  */
						}else {
							str += "<img src ='/resources/show.png'/>"  /*  7.10  이미지 태그가 아닐경우 방금만든 show.png 들어간다. 나중에 소스값이 달라진다!   */
						}

						
						str += getOriginalName(result);   /* result 가 파일명이었다.  */
						str += "</a><a class='deletefile' href='"+result+"'><span class='glyphicon glyphicon-trash'></span></a></li>"; /* uri 가 적혀있는게 아니라 파일명이 적혀있다. 쓰레기통 아이콘누르면 삭제되게. 깜빡임없이.   */
						
						$('.uploadedList').append(str);   /* 덮어씌우기가 아니라 추가! 덮어씌우면 하나 업로드 하면 문구뜨고~~~ / 기존것+새로운것 이어지게끔  */
					},
					error : function(request, status, error){
						console.log(error)
					}
				});
			});


			

			$('#insertbtn').click(function(){
				$('form').submit();
			});
			
		});
	</script>
	
	
</body>
</html>