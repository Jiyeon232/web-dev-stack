<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Member member = (Member) request.getAttribute("member");
	   String id = request.getParameter("id");
	%>
	<% if (member != null) { %>
		<h1>회원 정보</h1>
		<ul>	
			<li>아이디 : <%=member.getId() %></li>
			<li>비밀번호 : <%=member.getPwd() %></li>
			<li>이름 : <%=member.getName() %></li>
			<li>나이 : <%=member.getAge() %></li>
		</ul>
	<% } else { %>
		<h1>아이디가 <%=id %>인 회원 정보가 없습니다</h1>
	<% } %>
</body>
</html>