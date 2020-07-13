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
<script src ="/resources/js/uploadfn.js" type="text/javascript"></script> <!-- originalname 쓰기위해 추가해주기!  -->
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
			
			<div class="form-group">  <!-- 7.13  -->
				<label>첨부파일</label>
				<ul class="uploadedList clearfix">
				
				</ul>
			</div>
			
			
		</div><!--class=row  -->
		
		<div class="row">
			<div class="form-group">
				<button class="btn btn-info" id="reply_form">댓글</button> <!-- 이걸누르면 댓글 다는 mycollapse 폼 열렸다가 닫혔다가!  -->
				<button class="btn btn-warning" id="update">수정</button>
				<button class="btn btn-danger" id="delete">삭제</button>
				<button class="btn btn-primary" id="list">목록</button>
			</div>
		</div><!-- class=row -->
		
		
		<!--댓글작업  ->collapse ->댓글 버튼 누르면 나오게하는거!! --><!-- 정적으로 만들어진 것(직접 타이핑). 다만 보이게 안보이게만 해준 것일뿐! -->
		<div class="row"> 
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
		
		

		<!-- $  ( "#replies").html(str); 이것 때문에 이건 쓸모 없어짐  -->
		<!-- 비교적 나중작업!! 7/7  댓글 들어가는것 화인한 이후!!! getlist 함수에서 아래 부트스트랩을 필요한 갯수만큼 만들어 낼 것이다!!   -->
		<div id="replies" class="row">
			<!-- 여기부터 없는거임 --><div class="panel panel-success">
				<div class="panel-heading">
					<span>rno : 3</span>, <span>작성자 : 홍길동</span>
					<span class="pull-right">2020년 07년 07일...</span>
				</div>
				<div class="panel-body">
					<p>댓글 내용입니다.</p>
					<button data-name="홍길동" class="btn btn-warning btn-xs replymodify">수정</button>  
					<button class="btn btn-danger btn-xs replydelete">삭제</button>
				</div>
			<!-- 여기까지 없는거임 --></div>
		</div>

		<!-- bootstrap - modals 복붙 7/7일 오후, id추가, data-backdrop=static ->close 눌러야만 사라짐 추가 -->
		<div class="row">
			<div data-backdrop="static" id="myModal" class="modal fade" tabindex="-1" role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-rno">rno 데이터</h4> <!-- 수정함 -->
						</div>
						<div class="modal-body">
							<p class="modal-replyer">홍길동</p>
							<input value="댓글내용입니다." class="modal-replytext form-control"/> <!-- 수정함  / 프젝할때는 로그인됐을때만~~ -->
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-warning modal-update" 
								data-dismiss="modal">댓글수정</button> <!-- 누르는 순간 ajax 통신 되도록!  -->
							
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
		</div>

	</div><!-- class=container  -->
	
	
	
	
	
	<!-- ******************자바스크립트******************-->
	<script type="text/javascript">
		
		var bno =${vo.bno};  /* 전역변수! script 어디서든 사용가능/ (지역변수: 해당 함수 안에서 사용가능! ) vo.bno는 boardcontroller에서 가져옴!  */  

		getList(bno);  /* 7/7 댓글 무조건 실행되게! */

		$(document).ready(function(){

			$.getJSON("/getAttach/"+bno, function(arr){ /* 7.13   result는 배열일 것~~  */
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
					
					$('.uploadedList').append(str);  
				}
				/* list.jsp에서 복붙  */
				
			});

			
		
			/* 삭제! */
			$('#replies').on("click",".replydelete",function(){
				var rno = $(this).attr("data-rno");				

				$.ajax({
					type: 'delete',  
					url: '/replies',  /* delete으로 넘어가니 replies만해도 된다.  */
					headers:{
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "DELETE"
					},
					dataType: 'text',
					data: JSON.stringify({  
						rno : rno
					}),
					success : function(result){
						if(result === "success"){
							getList(bno);  /* 댓글 목록 갱신 */
						}
					},
					error : function(request,status,error){
						console.log(error);
					}					
					
				});
				/* alert(rno + "번 : 삭제버튼 클릭") */
			});


			/* 수정버튼 클릭시 ajax(수정이니까 put) 통신하여 rno, replytext 넘어간다!  */   
			$('.modal-update').click(function(){
				var rno = $('.modal-rno').text();
				var replytext = $('.modal-replytext').val();
				$.ajax({
					type: 'put',  /*수정은  put,  삭제는 delete  */
					url: '/replies/'+rno,  /* put으로 넘어가니 replies만해도 된다.  */
					headers:{
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "PUT"
					},
					dataType: 'text',
					data: JSON.stringify({  /* 여기 rno 붙여도됨 ->pathvariable 안해도된다.  */
						replytext : replytext
					}),
					success : function(result){
						if(result === "success"){
							getList(bno);  /* 댓글 목록 갱신 */
						}
					},
					error : function(request,status,error){
						console.log(error);
					}					
				});								
			});
			
			
			/* 수정  버튼 누르면 모달 창 뜨게!!  / 정적으로 만들어진 조상태그 이용하여 클릭버튼 만들기 */
			$('#replies').on("click",".replymodify",function(){  
				var rno = $(this).attr("data-rno"); /* this 지금 클릭한 요소 */
				var replyer = $(this).attr("data-name");
				var replytext = $(this).prev().text();  /* p tag를 가리키게된다.  */ /* text 안에 파라 없으면 이걸로  가져오세요  */

				$('.modal-rno').text(rno); /* rno로 세팅하세요!  */
				$('.modal-replyer').text(replyer);
				$('.modal-replytext').val(replytext);  /* input tag 이기 때문에 var를 사용함!  */
				$('#myModal').modal("show");
				
				/* alert(rno +"번:수정버튼이 클릭") */
			});
			
			//댓글등록하는 버튼
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

						getList(bno); /* 댓글 달면 바로 댓글 리스트 보이게  */
					},
					error : function(request,status,error){
						console.log(error);
					}									
				});
			});
			
			
			/* 이걸누르면 댓글 다는 mycollapse 폼 열렸다가 닫혔다가! */
			$('#reply_form').click(function(){   
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

		/* 댓글보기. db 들려야  7/7 */
		function getList(bno){  //글 자세히 보기 클릭했을때, 댓글 목록도 자동으로 나오도록~~ 
		
			var str = ''  /* 위에꺼 복붙. ' " 조심 / 동적으로 들어가는 데이터 앞뒤로 '' 해주고 좌우로 ++ 해줄 것!   '+ 변하는거  +'-> ctrl x 하기 */
			
			$.getJSON("/replies/all/"+bno, function(data){ 
				console.log(data)
				for(var i = 0; i < data.length; i ++){ /* 여기에 전에 썼던 코드 기억할것!!  */  /* 동적으로 ->click 이벤트 안먹을수있다.  */
					str += '<div class="panel panel-success"><div class="panel-heading"><span>rno:'+data[i]["rno"]+'</span>, <span>작성자 :'+data[i]["replyer"]+'</span><span class="pull-right">'+data[i]["updatedate"]+'</span></div><div class="panel-body"><p>'+data[i]["replytext"]+'</p><button data-name="'+data[i]["replyer"]+'" data-rno="'+data[i]["rno"]+'" class="btn btn-warning btn-xs replymodify">수정</button><button data-rno="'+data[i]["rno"]+'" class="btn btn-danger btn-xs replydelete">삭제</button></div></div>'
				}
				$("#replies").html(str);  //div 안에다 이것과 관련된걸 덮어씌워라. 추가하고싶으면 uppend
			});
		}

		
	</script>


</body>
</html>