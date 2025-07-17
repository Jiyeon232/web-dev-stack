<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="side">
	<h1><a href="/">ERP</a></h1>
	<nav>
		<div class="menu-group">
			<div class="menu-title">프로젝트 관리</div>
			<div class="submenu">
				<a href="#">프로젝트 목록</a>
				<a href="#">업무 목록</a>
			</div>
		</div>
		
		<div class="menu-group">
			<div class="menu-title">일정 관리</div>
			<div class="submenu">
				<a href="#">일정 등록/조회</a>
				<a href="#">일정 참여자 관리</a>
			</div>
		</div>
		
		<div class="menu-group">
			<div class="menu-title">고객 관리</div>
			<div class="submenu">
				<a href="#">고객 목록</a>
				<a href="#">클레임 처리</a>
			</div>
		</div>
		
		<div class="menu-group">
			<div class="menu-title">인사 관리</div>
			<div class="submenu">
				<a href="#">부서 관리</a>
				<a href="#">출퇴근 기록</a>
			</div>
		</div>
				
		<div class="menu-group">
			<div class="menu-title">재무 관리</div>
			<div class="submenu">
				<a href="/finance/salary">급여 관리</a>
				<a href="/finance/budget">예산 계획</a>
				<a href="/finance/transaction">수입/지출 관리</a>
				<a href="/finance/stats">매출 분석</a>
				<a href="/finance/purchase">매입 내역 관리</a>
			</div>
		</div>
				
		<div class="menu-group">
			<div class="menu-title">품질 관리</div>
			<div class="submenu">
				<a href="#">품질 검사</a>
				<a href="#">불량 처리 이력</a>
			</div>
		</div>
	</nav>
</div>