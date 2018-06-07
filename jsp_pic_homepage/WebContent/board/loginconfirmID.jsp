
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 중복 확인</title>
<style>
.content {
	width: 500px;
}
</style>
<script type="text/javascript" src="script/login_form.js"></script>
<script type="text/javascript">
	function idok() {
		opener.frm.id_id.value=document.frm.userid.value;
		opener.frm.reid.value=document.frm.userid.value;
		window.close();
	}
	
	function idok2() {
		opener.frm.id_id.value="";
		window.close();
	}
	
</script>
</head>
<body>
	<div class="content">
		<h2 class="tit">confirmID</h2>
		<p class="p-txt-1">아이디 중복확인</p>
		<div class="login_wrap">
			<form name="frm" method="get" action="BoardServlet" class="form_login">
				<input type="hidden" name="command" value="loginsameMemberForm">
				<input type="hidden" name="userid" value="${userid}">
				<!-- <input type="submit" value="중복체크"> -->
				<br>
				<c:if test="${result == 1}">
					${userid}는 이미 사용 중인 아이디 입니다. <br>
					<input type="button" value="돌아가기" class="cancel" onclick="idok2()">
				</c:if>
				<c:if test="${result == -1}">
					${userid}는 사용 가능한 아이디 입니다.
					<input type="button" value="아이디 사용하기" class="cancel" onclick="idok()">
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>