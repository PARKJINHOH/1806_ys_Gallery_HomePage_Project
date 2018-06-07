
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import = "com.dao.BoardDAO"%>
<%@ page import = "com.dto.BoardVO"%>
<%@ page import = "javax.servlet.http.HttpServletRequest"%>
<%-- <%@ page import = "com.dao.BoardDAO"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 게시글</title>
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
			<div class="top_button">
				<%	
					try{
						if (session.getAttribute("sessionId").equals(session.getAttribute("sessionpicId")) || session.getAttribute("sessionpicId2").equals(session.getAttribute("sessionadmin"))) {
				%>
				<input id="modify" type="button" value="수정" onclick="location.href='BoardServlet?command=update_write_boardForm&num=${photoboard.pic_num}'">
				<input id="delete" type="button" value="삭제" onclick="location.href='BoardServlet?command=DeleteForm&num=${photoboard.pic_num}'"/>
				<%
					} else {
				%>
				
				<%
					}
					}catch(Exception e){
						
						e.printStackTrace();
					}
				%>
				
			</div>
			<form method="post" action="BoardServlet">
			 <input type="hidden" name="command" value="photoboardAction">
				<div class="photo_title">
				<input type="hidden" name="pic_name" value="${photoboard.pic_name}"/>
						<h2>&nbsp;&nbsp;${photoboard.pic_name}</h2>
				</div>
				<div class="photo_name">
				<input type="hidden" name="pic_nickname" value="${photoboard.pic_nickname}"/>
						<h3>&nbsp;&nbsp;${photoboard.pic_nickname}</h3>
				</div>
				<div class="photo">
						<img src=${photoboard.pic_consum}>
				</div>
				<div class="photo_content">
				<input type="hidden" name="pic_con" value="${photoboard.pic_con}"/>
						<h4>&nbsp;&nbsp;${photoboard.pic_con}</h4>
				</div>
				<div class="photo_download">
					<input type="hidden" name="filename" value="${photoboard.pic_pic}"/>
					<input id="download" type="submit" value="다운로드" />
				</div>
				<div>
					<a href="BoardServlet?command=boardMain_pg=1" class="photo_list">목록</a>
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