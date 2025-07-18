<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<h1>로그인 페이지</h1>
	
	<a href="/register">회원가입 페이지로 이동</a>
	<button id="btn" type="button">회원가입 페이지로 이동</button>
	
	<script>
		$("#btn").click(function() {
			location.href="/register";
		});
	</script>
</body>
</html>