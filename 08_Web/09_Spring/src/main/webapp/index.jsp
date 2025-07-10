<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<h1>회원 관리</h1>
	<ul>
		<c:choose>
			<%-- 로그인 되어 있지 않은 경우 --%>
			<c:when test="${empty member}">
				<li><a href="/views/register.jsp">회원가입</a></li>
				<li><a href="/views/login.jsp">로그인</a></li>
			</c:when>
			<%-- 로그인 된 경우 --%>
			<c:otherwise>
				<li><a href="/views/search.jsp">회원 검색</a></li>
				<li><a href="/allMember.do">전체 회원 보기</a></li>
				<li><a href="/logout.do">로그아웃</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</body>
</html>
