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
	<button>rt1</button>  <!-- 이게 동적으로 생성됐다면 -->
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('body').on("click","button",function(){ /* 이게 더 좋은코드*/ /* 달러 (' button').click(function() ->동적으로 생성된 태그에 대해선 click event 안먹는경우가 있다.*/
				var test1 = "hello"; 								 /* 이걸 RestTextController로 보내서 되돌려 받는것 해본다 */

				$.ajax({
					type: 'post',
					url: 'rt1',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'POST' 			/* post는 위에 type 과 관련되어있다.  */ 
					},
					dataType: 'text',
					data: JSON.stringify({
						test1 : test1  								/* 변수명(=속성명) : 속성값( hello를 가지고있는 실제 데이터) */
					}),
					success : function(result){
						console.log(result);
					},
					error : function(request, status,error){
						console.log(error);
					}
					
				});
			});
		});
	</script>


</body>
</html>