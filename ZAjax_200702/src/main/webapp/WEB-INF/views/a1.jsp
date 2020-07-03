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

	<%
	List<MemberDTO> list = new ArrayList<MemberDTO>();
	list.add(new MemberDTO("m001", "kim", 22));
	list.add(new MemberDTO("m002", "lee", 23));
	list.add(new MemberDTO("m003", "park", 24));
	
	ObjectMapper mapper = ObjectMapper();
	String listStr = mapper.writeValueAsString(list);
	pageContext.setAttribute("listStr",listStr);
	%>


	<button id='btn'>ajaxList</button>
	<p class="aa"></p>
	<p class="bb"></p>

	<script type="text/javascript">
				
		
			$(document).ready(function(){
				$('#btn').click(function(){ 
					$.ajax({
						type: 'post',
						url: 'a4',
						dataType: 'text',
						data: {
							listStr : JSON.stringify(${listStr})
						},
						success : function(kk){
							console.log(kk);

							var obj = JSON.parse(kk)  //데이터 폼을 만든다???
							console.log(obj);
							console.log(obj.id);
							console.log(obj.name);

							$('.aa').text(obj.id);
							$('.bb').text(kk);
							
						},
						error : function(request,status,error){
							console.log(error);
						},
						complete : function(){
							alert('ok')
						}				
		
					});
				});
			});
		</script>

</body>
</html>