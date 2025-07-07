<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <h1>회원가입</h1>
    <form method="post" action="register">
      <label>아이디 : <input type="text" name="id" /></label><br />
      <label>비밀번호 : <input type="password" name="pwd" /></label><br />
      <label>이름 : <input type="text" name="name" /></label><br />
      <label>나이 : <input type="number" name="age" /></label><br />
      <input type="submit" value="회원가입">
    </form>
  </body>
</html>
