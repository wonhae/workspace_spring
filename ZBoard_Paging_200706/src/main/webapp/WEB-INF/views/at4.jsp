<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.domain.MemberDTO"%>
<%@page import="java.util.List"%>
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
	<button>at4 test</button>
	<p></p>
	
	<!--list 데이터 받았다는 가정하에  ->넘겨주려함 ->바로 안넘어감->문자열로 넘겨주려(배열..너무귀찮다) ->    -->
	<%  //여기에 넣은건 js에서 인식 못한다. ->pageContext
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		list.add(new MemberDTO("m001","kim",22));
		list.add(new MemberDTO("m002","lee",23));
		list.add(new MemberDTO("m003","park",24));	
		
		ObjectMapper mapper = new ObjectMapper();
		String listStr = mapper.writeValueAsString(list); //문자열이된다.
		pageContext.setAttribute("listStr", listStr);  //json만들어져있다. 
	%>
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('button').click(function(){
				$.ajax({
					type: 'post',
					url: '/at4',
					dataType: 'text',  /* 통신하고 넘겨받은것!!*/
					data: {
						listStr : JSON.stringify(${listStr})
					},
					success : function(result){
						console.log(result);
						/* 배열 풀어헤치려면 반복문 필요 */
						/* 파싱부터 하기 */
						var obj = JSON.parse(result); /* obj는 배열 */
						$('p').text(obj[0]['id']);  /*  달러 ('p')[0] ptag가 배열일경우 */

						for(var i = 0; i <obj.length; i ++){ /* js에서는 자료형이없다 */
							for(x in obj[i]){
								console.log(x);  /* x는 속성  id */
								console.log(obj[i][x]); /* x는 값   m001*/
								console.log(":::::::")
							}
						}
					}
				});
			});
		});
	</script>	
</body>
</html>