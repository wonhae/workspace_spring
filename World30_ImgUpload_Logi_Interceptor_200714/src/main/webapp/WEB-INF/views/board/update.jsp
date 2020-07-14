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
<style type="text/css">
	.fileDrop{
		width: 80%;
		height: 200px;
		border: 1px solid red;
		margin: auto;
	}
	
	.uploadedList {  
		margin-top: 50px;
	}
	
	.uploadedList li{  /* 왼쪽.. 한칸띄고 li :  자손 / 점없앰!  */
		list-style: none; 
	}
	
	.orifilename{
		overflow: hidden;
		white-space:nowrap;
		text-overflow: ellipsis;
	}
</style>

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
				
			</div>
			</form>
			
			<div class="form-group">  <!-- 7.14 -->
				<label>업로드할 파일을 드랍시키세요</label>
				<div class="fileDrop"></div>
			</div>
			<div class="form-group"> <!-- 7.14 -->
				<label>첨부파일목록(삭제버튼누르면 곧바로 삭제됩니다.)</label>
				<ul class="uploadedList clearfix"></ul>
			</div>
			
			
			
			<div clsss="form-group">
				<button class="btn btn-primary" id="updatebtn" >수정</button>
				<button class="btn btn-info" id="listbtn">목록</button>
			</div>
		</div>

	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
			var bno =${vo.bno};  /* 숫자기때문에 감싸줄필요없다 */
			/* 7.14 사진 보여지게 read.jsp에서 복붙 */
			$.getJSON("/getAttach/"+bno, function(arr){ 


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
					str += '<a href="'+arr[i]+'" class="deletefile"><span class="glyphicon glyphicon-trash"></span></a>' /* 누르면 사라지게 이게쓰레기통 */			
					str += getOriginalName(arr[i]); 
					str += '</p>'	
					str += '</li>';
					
					$('.uploadedList').append(str);  
				}
				/* list.jsp에서 복붙  */
			});

			/* 7.14  쓰레기통 버튼 누르면 삭제되도록 insert.jsp에서 복붙 */
			 $('.uploadedList').on("click",".deletefile",function(event){
				event.preventDefault();

				var isOk = confirm("수정버튼과 상관없이 파일을 삭제합니다.")  /* 예 버튼 누를떄만 삭제되도록 */
				if(isOk){
					var that = $(this);
					$.ajax({
						type: 'post',
						url: '/deletefile',
						dataType: 'text',
						data: {
							filename : that.attr("href")
						},
						success: function(result){
							that.parent("p").parent("li").remove();
						}
					});
				}

			});

			 $('.fileDrop').on("dragenter dragover", function(event){
					event.preventDefault();
			});	

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
							/* console.log(result); /* str="<div" 와 </a></div>는 짝이다!  */
							/*var str ="<li><a href='/displayfile?filename="+getImageLink(result)+"'>";
							if(checkImage(result)) {
								str += "<img src = '/displayfile?filename="+result+"'/>" 
							}else {
								str += "<img src ='/resources/show.png'/>" 
							}
							str += getOriginalName(result);  
							str += "</a><a class='deletefile' href='"+result+"'><span class='glyphicon glyphicon-trash'></span></a></li>"; 
							*/
							
							var str='<li class="col-xs-4">';
							str += '<a href="/displayfile?filename='+getImageLink(result)+'">';
							if(checkImage(result)){
								str += '<img src="/displayfile?filename='+result+'"/>';
							}else {
								str += '<img src="/resources/show.png"/>'
							}
							str += '</a>'

							str += '<p class="orifilename">';
							str += '<a href="'+result+'" class="deletefile"><span class="glyphicon glyphicon-trash"></span></a>' /* 누르면 사라지게 이게쓰레기통 */
							str += getOriginalName(result); 
							str += '</p>'	
							str += '</li>';
							
							$('.uploadedList').append(str);   /* 덮어씌우기가 아니라 추가! 덮어씌우면 하나 업로드 하면 문구뜨고~~~ / 기존것+새로운것 이어지게끔  */
						},
						error : function(request, status, error){
							console.log(error)
						}
					});
				});
				
			
			$('#updatebtn').click(function(event){  /*  db에도 저장된다. */
				event.preventDefault();
				var str = '';
				$('.deletefile').each(function(index){ /* 반복문 돌린다 */
					str += "<input name='files["+index+"]' value='"+$(this).attr("href")+"' type='hidden'>";  /*this ->each(function(index)  */
					
				});

				$('form').append(str);
				$('form').submit();
			});

			$('#listbtn').click(function(){
				location.assign("/board/list")
			});


			
			

			
		});
	</script>
	
	

</body>
</html>