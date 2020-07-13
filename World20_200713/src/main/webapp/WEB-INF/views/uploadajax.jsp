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
	.fileDrop{
		width: 80%;
		height: 200px;
		border: 1px dotted red;
		
	}
</style>

</head>
<body>

	<div class="fileDrop"></div>
	<div class="uploadedList"></div>  <!-- 업로디드네임을 여기에 추가 -->
	
	<script type="text/javascript">
		$(document).ready(function(){

			$('.uploadedList').on("click",".deletefile",function(event){  /* 7.10 */
				event.preventDefault();  /* a태그 클릭하면 넘어가는 것 방지 */
				var that = $(this); /* 어떤애인지! that을감싸고있는게 div */
				$.ajax({
					type: 'post',
					url: '/deletefile',
					dataType: 'text',
					data: {
						filename : $(this).attr("href") /* 파일명가져온다 */
					},
					success : function(result){
						alert(result);
						that.parent("div").remove();   /* that 우리가 방금만든a 태그 // 폴더에서 먼저 삭제되고나서 alert창뜨면 화면에서 삭제됨*/
					}
				});
			});
			
			
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
					type : 'get',
					url : '/list',
					dataType : 'text',
					data : formData, 
					processData : false,
					contentType : false,
					success : function(result){   
						console.log(result); /* str="<div" 와 </a></div>는 짝이다!  */
						var str ="<div><a href='/displayfile?filename="+getImageLink(result)+"'>";  /* href에 파일명 뿐 아니라 소스(예 이미지의 데이터)도 들어갈 수 있다.  */

						if(checkImage(result)) {/* filename은 result에 있다.  */ /* 이미지일 경우! */
							str += "<img src = '/displayfile?filename="+result+"'/>" /* 여기 썸네일이찍힌다!. 바로 파일명 쓰면 안된다. 이미지를 바이트형태로 만들어 str에 넣는다. 데이터를 서버에서 이미지 태그에 집어넣어줄수있다. 데이터자체를  */
						}else {
							str += "<img src ='/resources/show.png'/>"  /*  7.10  이미지 태그가 아닐경우 방금만든 show.png 들어간다. 나중에 소스값이 달라진다!   */
						}

						
						str += getOriginalName(result);   /* result 가 파일명이었다.  */
						str += "</a><a class='deletefile' href='"+result+"'><span class='glyphicon glyphicon-trash'></span></a></div>"; /* uri 가 적혀있는게 아니라 파일명이 적혀있다. 쓰레기통 아이콘누르면 삭제되게. 깜빡임없이.   */
						
						$('.uploadedList').append(str);   /* 덮어씌우기가 아니라 추가! 덮어씌우면 하나 업로드 하면 문구뜨고~~~ / 기존것+새로운것 이어지게끔  */
					},
					error : function(request, status, error){
						console.log(error)
					}
				});
			});
		});

		

		function checkImage(filename) {  /* 이미지파일여부 */  /* filename은 result에 있다.  */
			var idx = filename.lastIndexOf(".");
			
			var format = filename.substring(idx+1).toUpperCase();  /* 그냥 png 를 원한다. */
			
			if(format == 'PNG' || format == 'JPG' || format == 'JPEG' || format == 'GIF') {
				return true;
			} else {
				return false;
			}
		}

		function getOriginalName(filename) {  /* 뒤에서부터 언더바 찾으면 안됨. 여기서도 이미지 파일인지 아닌지 확인하는 작업이 필요하다.  */
			if(checkImage(filename)){ /* 이미지파일의 경우 언더바가 2번째 있을때 . 좀복잡쓰  */
				var idx = filename.indexOf("_");  /* 이게 13이 나온다. 첫번째 _찾아라 */
				idx = filename.indexOf("_", idx+1);  /* 14번째부터 찾아라!! 첫번째 _ 찾아서 더하기 1하고 그이후에 _ 찾아라!!  */
				return filename.substring(idx+1); 
				
				
			} else { /* 이미지파일이 아닐 경우 언더바가 1번째 있을때 */
				var idx = filename.indexOf("_");
				return filename.substring(idx+1);  /* 오리지널네임.확장자 */
			}
		}

		function getImageLink(result) { /* 7.10 이미지파일인지아닌지 구별 . 이미지 파일일 경우만 작업. 아닐경우 그냥 리턴*/
			if(checkImage(result)){
				return result.substring(0,12) + result.substring(14);
				/* var idx = result.indexOf("s_");
				var filename = result.substring(0,idx) */
			} else {
				return result;
			}
		}
		
	</script>

</body>
</html>