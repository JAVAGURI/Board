<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script src="/portfolio/resources/js/event2.js"></script> -->
<link rel="stylesheet" href="${ pageContext.servletContext.contextPath }/resources/css/common.css">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function() {

	/* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */
	
	if(document.getElementById("regist")) {
		const $regist = document.getElementById("regist");
		$regist.onclick = function() {
			location.href = "/portfolio/member/regist";
		}
	}
	
	if(document.getElementById("logout")) {
		const $logout = document.getElementById("logout");
		$logout.onclick = function() {
			location.href = "/portfolio/member/logout";
		}
	}
	
	if(document.getElementById("update")) {
		const $update = document.getElementById("update");
		$update.onclick = function() {
			location.href = "/portfolio/member/update";
		}
	}
	
	if(document.getElementById("writeNotice")) {
		const $writeNotice = document.getElementById("writeNotice");
		$writeNotice.onclick = function() {
			location.href = "/portfolio/notice/insert";
		}
	}
	
	if(document.getElementById("cancleNotice")) {
		const $cancleNotice = document.getElementById("cancleNotice");
		$cancleNotice.onclick = function() {
			location.href = "/portfolio/notice/list";
		}
	}
	
	if(document.getElementById("writeBoard")) {
		const $writeBoard = document.getElementById("writeBoard");
		$writeBoard.onclick = function() {
			location.href = "/portfolio/board/insert";
		}
	}
	
	if(document.getElementById("writeThumbnail")) {
		const $writeThumbnail = document.getElementById("writeThumbnail");
		$writeThumbnail.onclick = function() {
			location.href = "/portfolio/thumbnail/insert";
		}
	}
}</script>
</head>
<body>
	<!-- 헤더 영역 -->
	<div class="header">
	
		<!-- 헤드라인 -->
		<h1 align="center">Welcome JSP world</h1>
		
		<!-- 로그인 영역 -->
		<div class="login-area">
		
			<!-- 로그인이 필요한 경우 -->
			<c:if test="${ empty sessionScope.loginMember }">
			
				<!-- 로그인 폼 -->
				<form id="loginForm" action="${ pageContext.servletContext.contextPath }/member/login" method="post">
					<table>
						<tr>
							<td>
								<label class="text">ID : </label>
							</td>
							<td>
								<input type="text" name="id">
							</td>
						</tr>
							<tr>
							<td>
								<label class="text">PWD : </label>
							</td>
							<td>
								<input type="password" name="pwd">
							</td>
						</tr>
					</table>
					<div class="btns" align="right">
						<input type="button" class="btn btn-yg" value="회원가입" id="regist">
						<input type="submit" class="btn btn-or" value="로그인" id="login">
					</div>
				</form>
				
			</c:if>
			
			<c:if test="${ !empty sessionScope.loginMember }">
				<!-- 로그인 되어 있는 경우 -->
				<h3><c:out value="${ sessionScope.loginMember.nickname }"/>님의 방문을 환영합니다.</h3>
				<div class="btns">
					<input type="button" class="btn btn-yg" value="정보수정" id="update">
					<input type="button" class="btn btn-or" value="로그아웃" id="logout">
				</div>
			</c:if>
			
		</div>	<!-- login-area end -->
	</div>	<!-- header end -->
	
	<!-- 흐름 속성 제거 -->
	<br clear="both">
	
	<!-- 메뉴 영역 -->
	<div class="menu-area">
		<div class="nav-area">
			<ul>
				<li><a href="${ pageContext.servletContext.contextPath }">HOME</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/notice/list">공지사항</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/board/list">게시판</a></li>
				<li><a href="${ pageContext.servletContext.contextPath }/thumbnail/list">사진게시판</a></li>
			</ul>
		</div>	<!-- nav-area end -->
	</div>	<!-- menu-area end -->
</body>
</html>
