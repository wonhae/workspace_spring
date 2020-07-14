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
	iframe{
		width: 0px;
		height: 0px;
		border: 0px;
	}
</style>

</head>
<body>


	
	<form target="show" id="form1" enctype="multipart/form-data" action="iuploadform" method="post">
		<input type="file" name="file">
		<input type="submit" name="파일업로드">
	</form>
	<iframe name="show"></iframe>  <!-- formtag 결과를 ifame에 반영! view.jsp결과가 여기로 들어간다 -->
	
	
	<script type="text/javascript">  /* 자식창에서 호출 */
		function callFromChild(msg){
			alert(msg);
			document.getElementById("form1").reset();
		}
	</script>
	
</body>
</html>