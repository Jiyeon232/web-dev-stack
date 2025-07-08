<!-- HTML 주석 : 소스 코드에 보여짐 -->
<%-- JSP 주석 : 소스 코드에 안보임 

JSP Element
1. 지시어 %@ % : 컨테이너에게 알려줄 내용 지정
2. 스클릿틀릿 % % : 자바코드는 이 안에 지정
3. 출력문 %= % : 출력하는 내용 지정

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<%-- check 값도 바인딩 해서 여기서 조건을 걸 수 있다는 점! --%>
	<%
	boolean check = (boolean) request.getAttribute("check");
	String name = request.getParameter("name");
	%>
	<%-- String name = (String) request.getAttribute("name"); --%>
	<%-- check가 true인 경우 --%>
	<% if (check) { %>
	<h1><%=name%>님, 회원가입이 완료되었습니다!</h1>
	<% } else { %>
	<h1>회원가입에 실패했습니다</h1>
	<% } %>
</body>
</html>
