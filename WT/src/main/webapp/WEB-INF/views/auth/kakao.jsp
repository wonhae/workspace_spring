<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<title>Login Demo - Kakao JavaScript SDK</title>
</head>
<body>




	<a id="kakao-login-btn"></a>
	<a href="http://developers.kakao.com/logout"></a>
	<script type='text/javascript'>
		//<![CDATA[
		// 사용할 앱의 JavaScript 키를 설정해 주세요.
		Kakao.init('5d280068e2cb91c5f5c75e7cb6a6aea2'); //여기서 아까 발급받은 키 중 javascript키를 사용해준다.
		// 카카오 로그인 버튼을 생성합니다.
		Kakao.Auth.createLoginButton({
			container : '#kakao-login-btn',
			success : function(authObj) {
				alert(JSON.stringify(authObj));  //이건되는데...
				location.href="./login/kakao_logout"
			},
			fail : function(err) {
				alert(JSON.stringify(err));
			}
		});

		function kakao_logout(){
			Kakao.Auth.logout(function(){
				setTimeout(function(){
					location.href="http://localhost/login/kakao_logout"
				},1000);
			});
		}
		//]]>
	</script>




	<!-- <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<a id="kakao-login-btn"></a>
	<a href="http://developers.kakao.com/logout"></a>
	<script type='text/javascript'>

		Kakao.init('5d280068e2cb91c5f5c75e7cb6a6aea2'); //아까 카카오개발자홈페이지에서 발급받은 자바스크립트 키를 입력함

		//카카오 로그인 버튼을 생성합니다. 
		Kakao.Auth
				.createLoginButton({
					container : '#kakao-login-btn',
					success : function(authObj) {
						Kakao.API
								.request({

									url : '/v1/user/me',

									success : function(res) {

										console.log(res.id);//<---- 콘솔 로그에 id 정보 출력(id는 res안에 있기 때문에  res.id 로 불러온다)

										console.log(res.kaccount_email);//<---- 콘솔 로그에 email 정보 출력 (어딨는지 알겠죠?)

										console.log(res.properties['nickname']);//<---- 콘솔 로그에 닉네임 출력(properties에 있는 nickname 접근 

										// res.properties.nickname으로도 접근 가능 )
										console.log(authObj.access_token);//<---- 콘솔 로그에 토큰값 출력

										var kakaonickname = res.properties.nickname; //카카오톡 닉네임을 변수에 저장 (닉네임 값을 다른페이지로 넘겨 출력하기 위해서)

										window.location
												.replace("http://"
														+ window.location.hostname
														+ ((location.port == "" || location.port == undefined) ? ""
																: ":"
																		+ location.port)
														+ "/team_project/home?kakaonickname="
														+ kakaonickname);
										//로그인 결과 페이지로 닉네임 값을 넘겨서 출력시킨다.,
									}
								})
					},
					fail : function(error) {
						alert(JSON.stringify(error));
					}
				});
	</script> -->

</body>
</html>