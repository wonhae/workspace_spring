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

	<button>at1 test</button>
	<p></p>
	
	<script type="text/javascript">  <!-- jquery작업할 것  -->
		$(document).ready(function(){			
			$("button").click(function(){  				/* 이벤트 핸들러 예.총 방아쇠(=이벤트 소스) 당김  */
					/* alert("test"); 					/* 테스트용  */ 
					$.ajax({ 							/* ajax와 통신하겠다!  속성: 속성값, 속성: 속성값, */
						type: 'post',  
						url: '/at1',     				/* 아직 /at1 의 post 방식은 안만들어 놓았다.  */
						dataType: 'text',  				/* 넘겨받는 방식 */
						data: {  						/* 통신하고 넘겨받는 데이터 */
							'msg' : 'hello'	  			/* json으로 객체를 만들때, 속성은 무조건 ' "로 감싼다. 값도 감싸지만, 숫자만 안감싸준다.  */
						},
						success: function(result) {
							console.log(result);

							$("p").text(result);  		 /* p tag 태그값으로 reuslt 가가지고 있는것을 집어넣어라  */
							/* var msg = $("p").text();	/* p tag 가 가지고있는 문자열 가지고옴  */ 
							
						},
						error : function(request,status,error){
							/* console.log(status); */
							/* console.log(error); */
							console.log("code : "+request.status + "\n" +"msg : "+ request.responseText + "\n" + "error : " + error);								
						},
						complete : function(){ /* 성공,실패 관계없이 무조건 실행 */
							alert('ok')
						}
							
						});  
				});  
		});

		</script>
</body>
</html>