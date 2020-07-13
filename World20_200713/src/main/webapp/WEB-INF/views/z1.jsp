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
	<button>z1 click</button>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('button').click(function(){
				$.ajax({
					type: 'post',
					url: 'z1',
					dataType: "text",
					data: {
						'zz' : "helloing"
					},
					success: function(result) {
						console.log(result)
						}


					
					});
					
				});

			});		
	</script>
	
	<button>z2 click</button>
	<script type="text/javascript">
		var arr = ["shin", "hae", "won"]
		$(document).ready(function(){
			$('button').click(function(){
				$.ajax({
					type:'post',
					url:'z2',
					traditional:true,
					DataType: 'text',
					Data:{
						'var': arr
					},
					success:function(result){
						console.log(result)
					},
					error:function(request,status,error){
						console.log(error)
					}
				});
			});
		});
	</script>
	
	
</body>
</html>