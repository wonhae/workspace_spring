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
.th-center {
	text-align: center;
}
</style>

</head>

<body>
	<div class="container">
		<div class="row text-center">
			<h1>목록</h1>
		</div>

		<div class="row">
			<a href="/board/insert">글쓰기</a>
		</div>

		<div class="row">
			<table class="table">
				<!-- 세로선 넣고싶으면 table-bordered -->
				<thead>
					<tr>
						<th class="th-center">글번호</th>
						<th class="th-center" style="width: 50%">제목</th>
						<th class="th-center">작성자</th>
						<th class="th-center">작성일</th>
						<th class="th-center">조회수</th>
					</tr>
				</thead>

				<!-- for문돌려 한줄씩 넘겨받아야 -->
				<tbody>
					<c:forEach items="${list}" var="dto">
						<tr>
							<td>${dto.bno}</td>
							<td><a href="/board/read/${dto.bno}">${dto.title}</a></td>
							<td>${dto.writer}</td>
							<td>${dto.regDate }</td>
							<td>${dto.viewcnt }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div><!-- class = row 인 div 즉 목록을 감싸고 있는! -->

		<div class="row text-center"> <!-- 페이지 나오게 한는거!!  -->
			<nav aria-label="Page navigation">
				<ul class="pagination">

					<!-- 맨앞, 맨뒤 프젝할때는 페이지 없어지게!  -->
					<li><a
						href="/board/list?curPage=${to.curPage>1? to.curPage-1: 1}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>

					<!-- 1,2,3,4를 찍어내는건 page 라는 변수이다. / 현재페이지에 대한 변수와 페이징을 찍어내는 변수는 다르다!  -->
					<c:forEach begin="${to.beginPageNum}" end="${to.stopPagaeNum}"
						var="page">
						<!--현제페이지만  active 값을 갖게끔!! 나중에 구현할때 c if 사용해서 현재페이지 표시되게끔!-->
						<li class="${to.curPage==page? 'active' : ''}"><a
							href="/board/list?curPage=${page}">${page}</a></li>
					</c:forEach>

					<li><a
						href="/board/list?curPage=${to.curPage < to.totalPage ? to.curPage+1 : to.curPage }"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>

				</ul>
			</nav>
		</div>
		<!-- class=row 페이징처리한 것 -->

		<!-- 검색 구현 부트스트랩 - component-inputgroups// 나중에 프로젝트 할 때는 Buttons with dropdowns 로 할 것!!! -->
		<div class="row">
		<!-- target="_blank" 하면 새로운 창이 생긴다. 검색 목록을 별도의 탭에서 나오도록 한다-->
			<form target="_blank" action="/board/searchlist" method="get">
				
				<div class="input-group">
					<span class="input-group-addon"> <!--input-group-btn 그 외에는 addon 넣는다!!-->
						<select name="searchType">
							<!-- 버튼누르면 나오게!  -->
							<option disabled>검색기준</option>
							<option value="writer">작성자</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
						</select>
					</span> 
					
					<input class="form-control" name="keyword">
					<span class="input-group-btn">
						<button class="btn btn-info">검색</button>
					</span>
				</div>
			</form>

		</div>

	</div>
	<!-- class = container인 div -->


</body>
</html>