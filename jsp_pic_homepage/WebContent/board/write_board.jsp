<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 게시글 작성</title>
<link href="css/header_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script/login_form.js"></script>
</head>
<body>
	<div id="wrapper">
		<div>
			<nav class="header_top">

				<%
					if (session.getAttribute("sessionId") == null) {
				%>
				<nav class="member_login">
					<input type="button" value="로그인"
						onclick="open_win('BoardServlet?command=loginformAction')">
					<a href="BoardServlet?command=MemberFormAction">회원가입</a>
				</nav>
				<%
					} else {
				%>
				<nav class="member_login">
					<%=session.getAttribute("sessionId")%>
					님 <input type="button" value="로그아웃"
						onclick="window.location='/jsp_pic_homepage/board/logout.jsp'">
				</nav>

				<%
					}
				%>

			</nav>
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
		<div id="category_left">
			<nav class="navi_left">
				<ul>
					<li><a
						href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=boardMain_ct-ani=1">동물</a></li>
					<li><a
						href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=boardMain_ct-peo=1">인물</a></li>
					<li><a
						href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=boardMain_ct-nat=1">배경</a></li>
					<li><a
						href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=boardMain_ct-car=1">자동차</a></li>
					<li><a
						href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=boardMain_ct-mat=1">만화</a></li>
					<li><a
						href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=boardMain_ct-gam=1">게임</a></li>
				</ul>
			</nav>
		</div>
		<div class="content">
			<div class="h2_title">
				<h2>사진게시판 글쓰기</h2>
			</div>

			<!-- method post -> get // ?command=write_board // -->
			<!-- action="selsports.jsp?sel1=document.getElementById("sel1").value " -->
			<form name="frm" method="post" action="BoardServlet?command=write_board"
				enctype="multipart/form-data">
				<input type="hidden" name="command" value="write_board">
				<table border="1px solid black">
					<tr>
						<th>분 류</th>
						<td><select name="selectCT" class="required">
								<option value="ani">동물</option>
								<option value="peo">인물</option>
								<option value="nat">자연</option>
								<option value="car">자동차</option>
								<option value="mat">만화</option>
								<option value="gam">게임</option>
						</select></td>
					</tr>
					<tr>
						<th>제 목</th>
						<td><input class="photo_name_title" type="text"
							name="pic_name" /></td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td><h3><%=session.getAttribute("sessionId")%></h3></td>
					</tr>
					<tr>
						<th>파일첨부</th>
						<td><input type="file" name="pic_pic" /></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea cols="80" rows="20" name="pic_con"></textarea>
						</td>
					</tr>
				</table>

				<br />
				<div class="botton_button">
					<input id="btn_submit" type="submit" value="등록" />
					<input id="btn_submit" type="button" value="목록"
						onclick="location.href='BoardServlet?command=boardMain'" />
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