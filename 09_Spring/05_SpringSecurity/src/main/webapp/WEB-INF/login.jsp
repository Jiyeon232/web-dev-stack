<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<h1>로그인</h1>
	<form action="/login" method="post" id="frm">
		아이디 : <input type="text" name="id"><br>
		비밀번호 : <input type="password" name="pwd"><br>
		<button type="submit" id="login">로그인</button>
	</form>
	
	<script>
		$("#login").click((e) => {
			e.preventDefault(); // 기존 이벤트 제거
			
			$.ajax({
				type: "post",
				url: "/login",
				data: $("#frm").serialize(),
				success: function(data) {
					//alert(data); // data 받아오는지 확인!
					// localStorage에 token 키 값으로 저장
					localStorage.setItem("token", data);
					// / <- index.jsp로 이동
					location.href = "/";
				}
			});
		});
	</script>
</body>
</html>