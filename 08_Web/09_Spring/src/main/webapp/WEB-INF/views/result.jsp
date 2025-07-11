<%@page import="com.kh.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 정보</h2>
	<ul>
		<li>아이디 : ${member.id}</li>
		<li>이름 : ${member.name}</li>
		<li>나이 : ${member.age}</li>
	</ul>
	<a href="/">메인 페이지로 이동</a>
</body>
</html>