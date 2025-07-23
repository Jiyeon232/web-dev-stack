<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 업로드</title>
</head>
<body>
	<h2>File Upload Test</h2>
	<form action="/upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file"><br>
		<input type="submit" value="파일업로드"/>
	</form>
	
	<h2>Multi File Upload Test</h2>
	<form action="/multiUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="files" multiple accept="image/*"><br>
		<input type="submit" value="파일업로드"/>
	</form>
</body>
</html>