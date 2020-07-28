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
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>  <!-- 온갓 함수들 다 가지고 있음.  -->
<script 
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> <!-- collapse 이런거 해주게함 -->
<script 
	src="/resources/js/uploadfn.js" type="text/javascript"></script>	  <!-- 7.13 uploadfn.js 에 있는함수를 마음대로 이용할 수 있다! 다른곳에서 작업해놓은것을 jsp 파일에 적용할 수 있다!   -->

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
				<label>업로드할 파일을 드랍시키세요.</label> <!-- 7.13 썸네일 밑에 글자랑 아이콘이 오도록~~   -->
				<div class="fileDrop"></div>
				<ul class="uploadedList clearfix">  <!--clearfix 위로 침범하지 않도록/  col-xs-2 가장 작은애일때 왼쪽, 그거보다 커지면 오른쪽!! 생략해도 가능~ / 단말기가 커지면 커질수록 숫자가 작아진다   -->
					<!-- <li class="col-xs-2"> 
						<a href="#"><img src="/resources/show.png"></a>
						<p class="orifilename"><a><span class="glyphicon glyphicon-trash"></span></a>오리지널네임.txt</p>
					</li> -->
				</ul>  <!--div대신 ul   ->밑에 li로 하세요  -->
			</div>
			
			
			
			
			<div clsss="form-group">
				<button class="btn btn-primary" id="insertbtn" >등록</button>
				<button class="btn btn-info" id="listbtn">목록</button>
			</div>
		</div>

	</div>
	
	
	<script type="text/javascript">
		$(document).ready(function(){


			$('#insertbtn').click(function(event){  /* 7.13 등록버튼 누르면 정보 넘어가도록  */
				event.preventDefault();
				var str = '';
				$('.deletefile').each(function(index){ /* 반복문 돌린다 */
					str += "<input name='files["+index+"]' value='"+$(this).attr("href")+"' type='hidden'>";  /*this ->each(function(index)  */
					
				});

				$('form').append(str);
				$('form').submit();
			});

			$('#listbtn').click(function(){
				location.assign("/board/list");
			});
			

			/* 7.13 삭제되도록 */
			 $('.uploadedList').on("click",".deletefile",function(event){
				event.preventDefault();
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
			});



			
			
			/* 7.13 uploadajax에서 복붙 */
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

		

		

			
			
			
		});
	</script>
	
	
</body>
</html>