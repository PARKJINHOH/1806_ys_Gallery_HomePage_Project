
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 창</title>
<style>
.content {
	width: 400px;
}
</style>
<script type="text/javascript" src="script/login_form.js"></script>
</head>
<body>
	<div class="content">
		<h2 class="tit">LOGIN</h2>
		<p class="p-txt-1">회원이 되시면 다양한 혜택을 누리실 수 있습니다</p>
		<div class="login_wrap">
			<form name="frm" method="get" action="BoardServlet" class="form_login">
			 <input type="hidden" name="command" value="loginAction">
				<fieldset>
					<legend>로그인 입력 폼</legend>
					<p>
						<input type="text" name="id_id" placeholder="아이디를 입력하세요">
					</p>
					<p>
						<input type="password" name="id_pass"  placeholder="비밀번호를 입력하세요">
					</p>
					<p>
						<input type="submit" value=" 로그인 ">
					</p>
				</fieldset>
				<br>${message}
			</form>
		</div>
	</div>
</body>
</html>