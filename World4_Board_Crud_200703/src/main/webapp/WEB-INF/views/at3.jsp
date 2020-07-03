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
	<button id="btn">at3 test</button>
	<p class="name"></p>
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#btn').click(function(){
				$.ajax({
					type: 'post',
					url: 'at3',
					dataType: 'text',
					data: {
						'id' : 'm007',
						'name' : 'lee',
						'age' : 33	
					},
					success:function(result){
						console.log(result);
						/* 파싱해야한다 js가 오브젝트로 인식하게끔 */
						var obj = JSON.parse(result);  		/* 객체 만들어  받는다 */
						console.log(obj.id);				/* 객체 접근, dto와 상관없음 */
						console.log(obj.name);     

						$('.name').text(obj.name);  
					},
					error : function(request,status,error){
						console.log(error);
					}
				});
			});
		});
	</script>

</body>
</html>