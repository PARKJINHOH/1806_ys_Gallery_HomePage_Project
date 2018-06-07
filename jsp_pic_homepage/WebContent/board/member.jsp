<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원가입 페이지</title>
<link href="css/member.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script/login_form.js"></script>
<script type="text/javascript">
	function idCheck() {
		if(document.frm.id_id.value == ""){
			alert("아이디를 적어주세요");
			document.frm.id_id.focus();
			return;
		}
		var url = "BoardServlet?command=loginsameMemberForm?userid=" + document.frm.id_id.value;
		window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, height=200");
		
		return true;
	}
	
	function nickcheck() {
		var nick_reg = document.frm.id_nickname.value;
		var reg_nick = /^[a-zA-Z0-9_-]+$/g;
		var rst_nick = reg_nick.test(nick_reg);
		
		if(document.frm.id_nickname.value == ""){
			alert("닉네임을 적어주세요");
			document.frm.id_nickname.focus();
			return;
		}
		
		if (!rst_nick) {
			alert("닉네임은 영어로 입력해 주세요.");
			document.frm.id_nickname.focus();
			return;
		} else if (document.frm.id_nickname.value.length >= 11) {
			alert("닉네임은 10자리를 초과할 수 없습니다.");
			document.frm.id_nickname.focus();
			return;
		}
		
		var url = "BoardServlet?command=loginconfirmNick?nick=" + document.frm.id_nickname.value;
		window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=550, height=200");
		
		return true;
	}
	
	
	function membercheck() {
		var id_reg = document.frm.id_id.value;
		var reg = /^[a-zA-Z0-9_-]+$/g;
		var rst = reg.test(id_reg);
		
		var nick_reg = document.frm.id_nickname.value;
		var reg_nick = /^[a-zA-Z0-9_-]+$/g;
		var rst_nick = reg.test(reg_nick);

		var pass_reg = document.frm.id_pwd.value;
		var reg_pass = /^[A-Za-z0-9_-]{6,12}$/;
		var rst_pass = reg_pass.test(pass_reg);

		var email_reg = document.frm.id_email.value;

		var reg_email = /^[a-z][a-z0-9_-]{2,11}@([a-z\d\.-]+)\.([a-z\.]{2,6})$/;
		var rst_email = reg_email.test(email_reg);

		var phone_reg = document.frm.id_tel.value;
		var reg_phone = /^\d{3}-\d{4}-\d{4}$/;
		var rst_phone = reg_phone.test(phone_reg);
		
		if(document.frm.reid.value.length == 0){
			alert("중복 체크를 하지 않았습니다.");
			frm.id_id.focus();
			return false;
		}

		if (!rst) {
			alert("아이디는 영어로 입력해 주세요.");
			return false;
		} else if (document.frm.id_id.value.length == 0) {
			alert("아이디를 입력해 주세요.");
			return false;
		} else if (document.frm.id_id.value.length >= 11) {
			alert("아이디를 10자리를 초과할 수 없습니다.");
			return false;
		}
		
		if (!rst) {
			alert("닉네임은 영어로 입력해 주세요.");
			return false;
		} else if (document.frm.id_nickname.value.length >= 11) {
			alert("닉네임은 10자리를 초과할 수 없습니다.");
			return false;
		}

		if (!rst_pass) {
			alert("비밀번호는 6-12자리 입니다.");
			return false;
		} else if (document.frm.id_pwd.value.length == 0) {
			alert("비밀번호를 입력해 주세요");
			return false;
		}

		if (document.frm.id_pwd.value != document.frm.pass2.value) {
			alert("비밀번호 확인해 주세요");
			return false;
		}

		if (!rst_email) {
			alert("유효한 이메일을 입력해 주세요");
			return false;
		}

		if (!rst_phone) {
			alert("유효한 핸드폰 번호를 입력해 주세요");
			return false;
		}
		
		return true;
	}
</script>
</head>
<body>
	<div id="wrapper">
		<div>		
		<nav class="header"></nav>
		</div>
		<div id="category_top">
			<nav class="navi">
				<ul>
					<li><a
						href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=boardMain_pp=1">인기사진</a></li>
					<li><a
						href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=boardMain_pg=1">최근사진</a></li>
				</ul>
			</nav>
		</div>
		
		<div class="content">
			<form name="frm" method="post" action="BoardServlet">
				<input type="hidden" name="command" value="MemberAction">
				<h1 style="border-bottom: 1px solid #ccc">회원가입</h1>
				<div class="member_group">
					<p class="control_label">아이디</p>

					<input name="id_id" class="login_box" type="text" />
					<input id="member_check" type="button" value="중복확인" onclick="return idCheck()"/>
					<input type="hidden" name="reid" size="20">
					 <br />
					<p class="member_atten">* 아이디는 10자리 이하로 적어주세요.</p>
				</div>
				<div class="member_group">
					<p class="control_label">비밀번호</p>
					<input name="id_pwd" class="login_box" type="password" />
					<p class="member_atten">* 비밀번호는 6~12자리로 입력해 주세요.</p>
				</div>
				<div class="member_group">
					<p class="control_label">비밀번호 확인</p>
					<input name="pass2" class="login_box" type="password" /> &nbsp;*같은
					암호를 입력하세요
				</div>
				<div class="member_group">
					<p class="control_label">이메일 주소</p>
					<input name="id_email" class="login_box" type="text" />
					<p class="member_atten">* 이메일 앞자리는 3~11자리로 입력해 주세요.</p>
				</div>
				<div class="member_group">
					<p class="control_label">닉네임</p>
					<input class="login_box" type="text" name="id_nickname" />
					<input type="hidden" name="renick" size="20">
					<input id="id_nickchbutoon" type="button" value="중복검사" onclick="return nickcheck()"/>
					<p class="member_atten">* 영문 10자리 이하로 입력해주세요.</p><br>
				</div>
				<div class="member_group">
					<p class="control_label">핸드폰 번호</p>
					<input name="id_tel" class="login_box" type="text" />
				</div>
				
			<div class="bottom_btn" style="border-top: 1px solid #ccc">
				<input id="left" type="reset" value="취소" onclick="history.go(-1)" >
				<input type="submit" id="right" value="등록" onclick="return membercheck()" >
			</div>
			</form>
		</div>


		<div class="footer">
			<br>
			위 내용에 대한 저작권 및 법적 책임은 자료제공사 또는 글쓴이에 있으며 jsp_pic_homepage의 입장과 다를 수 있습니다.<br>
				Copyright (c) jsp_pic_homepage.com All rights reserved.
		</div>
	</div>
</body>
</html>