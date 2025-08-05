<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<h1>회원관리</h1>
	<sec:authorize access="isAnonymous()"></sec:authorize>
	<sec:authorize access="isAuthenticated()"></sec:authorize>
	<sec:authorize access="hasRole('ADMIN')"></sec:authorize>
	
	<div id="anonymous">
		<a href="/register">회원가입</a><br>
		<a href="/login">로그인</a><br>
	</div>
	
	<div id="authenticated">
		<a href="/logout" id="logout">로그아웃</a><br>
		<a href="/mypage" id="mypage">마이페이지</a><br>
	</div>
	
	<a href="/admin" id="admin">관리자 페이지</a><br>
	
	<script>
		const token = localStorage.getItem("token");
		//alert(token); // 페이지 들어가자마자 token 로드
		
		if (token !== null) {
			$("#authenticated").show();
			$("#anonymous").hide();
			$("#admin").hide();
			
			$.ajax({
				url: '/check',
				type: 'get',
				data: { token : token },
				success: function(data) {
					console.log(data);
					console.log(data.role);
					if (data.role === "ROLE_ADMIN") {
						$("#admin").show();
					}
				}
			});
		} else {
			$("#anonymous").show();
			$("#authenticated").hide();
			$("#admin").hide();
		}
		
		$("#logout").click((e) => {
			e.preventDefault(); // href -> 다른 페이지로 이동하는 것을 막아버리기 (button 태그로 바꿔도 됨!)
			localStorage.removeItem("token"); // 토큰 제거
			location.reload(); // 페이지 재로딩
		});
		
		$("#mypage").click((e) => {
			e.preventDefault();
			
			$.ajax({
				url: '/mypage',
				type: 'get',
				beforeSend: function(xhr) {
					xhr.setRequestHeader('Authorization', 'Bearer ' + token);
				},
				success: function(data) {
					//console.log(data);
					$('body').html(data); // body에 html 태그 넣어서 보여주기!
				}
			});
		});
		
		$("#admin").click((e) => {
			e.preventDefault();
			
			$.ajax({
				url: '/admin',
				type: 'get',
				beforeSend: function(xhr) {
					xhr.setRequestHeader('Authorization', 'Bearer ' + token);
				},
				success: function(data) {
					//console.log(data);
					$('body').html(data);
				}
			});
		});
	</script>
</body>
</html>