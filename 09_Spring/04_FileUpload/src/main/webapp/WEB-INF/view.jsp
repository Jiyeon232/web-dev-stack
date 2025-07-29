<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상세보기</title>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</head>
<body>
	<!-- a링크를 통해 딱 해당 정보만 나올 수 있도록! 상세보기 -->
	<div class="container">
		<h1>상세 페이지</h1>
		<table class="table">
			<thead>
				<tr>
					<th>제목</th>
					<th>설명</th>
					<th>이미지</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${board.title}</td>
					<td>${board.content}</td>
					<td><img src="http://192.168.0.35:8081/${board.url}"/></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>