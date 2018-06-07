<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 홈페이지</title>

<link href="css/header_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script/login_form.js"></script>
<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>


</head>
<body>
	<script src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
	<script>
		$(document).ready(
				function() {
					var totalPage = "${pagecount}";

					/*DB list*/
					$("#paging").append("< ");
					for (var j = 1; j <= totalPage; j++)
						$("#paging").append(
								"<a href='?command=boardMain_pg=" + j + "'>"
										+ j + "</a> ");
					$("#paging").append(" >");
				});
	</script>
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
		<form action="photoboard" method="post">
			<!-- <input type="hidden" name="command" value="photoboard"> -->
			<div class="content">
				<c:forEach var="item" items="${boardMain}" begin="${acFint1}"
					end="${acFint1}" step="1">
					<a href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=photoboardForm=${item.pic_num}">
				</c:forEach>

				<article>
					<div class="pic">
						<c:forEach var="item" items="${boardMain}" begin="${acFint1}"
							end="${acFint1}" step="1">
							<c:if test="${item.pic_sum == null}">
								<img src=${item.pic_sum}>
							</c:if>
							<img src=${item.pic_sum}>
						</c:forEach>
					</div>
					<div class="title">
						<c:forEach var="item" items="${boardMain}" begin="${acFint1}"
							end="${acFint1}" step="1">
							<h2>&nbsp;&nbsp;${item.pic_name}</h2>
						</c:forEach>
					</div>
					<div class="hits">
						<c:forEach var="item" items="${boardMain}" begin="${acFint1}"
							end="${acFint1}" step="1">
							<h2>&nbsp;&nbsp;${item.pic_hits} 조회</h2>
						</c:forEach>
					</div>
				</article>
				</a>
				<c:forEach var="item" items="${boardMain}" begin="${acFint2}"
					end="${acFint2}" step="1">
					<a
						href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=photoboardForm=${item.pic_num}">
				</c:forEach>
				<article>
					<div class="pic">
						<c:forEach var="item" items="${boardMain}" begin="${acFint2}"
							end="${acFint2}" step="1">
							<img src=${item.pic_sum}>
						</c:forEach>
					</div>
					<div class="title">
						<c:forEach var="item" items="${boardMain}" begin="${acFint2}"
							end="${acFint2}" step="1">
							<h2>&nbsp;&nbsp;${item.pic_name}</h2>
						</c:forEach>
					</div>
					<div class="hits">
						<c:forEach var="item" items="${boardMain}" begin="${acFint2}"
							end="${acFint2}" step="1">
							<h2>&nbsp;&nbsp;${item.pic_hits} 조회</h2>
						</c:forEach>
					</div>
				</article>
				</a>
				<c:forEach var="item" items="${boardMain}" begin="${acFint3}"
					end="${acFint3}" step="1">
					<a
						href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=photoboardForm=${item.pic_num}">
				</c:forEach>
				<article>
					<div class="pic">
						<c:forEach var="item" items="${boardMain}" begin="${acFint3}"
							end="${acFint3}" step="1">
							<img src=${item.pic_sum}>
						</c:forEach>
					</div>
					<div class="title">
						<c:forEach var="item" items="${boardMain}" begin="${acFint3}"
							end="${acFint3}" step="1">
							<h2>&nbsp;&nbsp;${item.pic_name}</h2>
						</c:forEach>
					</div>
					<div class="hits">
						<c:forEach var="item" items="${boardMain}" begin="${acFint3}"
							end="${acFint3}" step="1">
							<h2>&nbsp;&nbsp;${item.pic_hits} 조회</h2>
						</c:forEach>
					</div>
				</article>
				</a>
				<c:forEach var="item" items="${boardMain}" begin="${acFint4}"
					end="${acFint4}" step="1">
					<a
						href="http://localhost:8080/jsp_pic_homepage/BoardServlet?command=photoboardForm=${item.pic_num}">
				</c:forEach>
				<article>
					<div class="pic">
						<c:forEach var="item" items="${boardMain}" begin="${acFint4}"
							end="${acFint4}" step="1">
							<img src=${item.pic_sum}>
						</c:forEach>
					</div>
					<div class="title">
						<c:forEach var="item" items="${boardMain}" begin="${acFint4}"
							end="${acFint4}" step="1">
							<h2>&nbsp;&nbsp;${item.pic_name}</h2>
						</c:forEach>
					</div>
					<div class="hits">
						<c:forEach var="item" items="${boardMain}" begin="${acFint4}"
							end="${acFint4}" step="1">
							<h2>&nbsp;&nbsp;${item.pic_hits} 조회</h2>
						</c:forEach>
					</div>
				</article>
				</a>

				<!-- 페이지 수 -->
				<div id="paging"></div>

				<!--  -->

				<%
					if (session.getAttribute("sessionId") == null) {
				%>

				<%
					} else {
				%>
				<a href="BoardServlet?command=write_board_form" class="write">글쓰기</a>

				<%
					}
				%>
			</div>
		</form>

		<div class="footer">
		<br>
			위 내용에 대한 저작권 및 법적 책임은 자료제공사 또는 글쓴이에 있으며 jsp_pic_homepage의 입장과 다를 수 있습니다.<br>
				Copyright (c) jsp_pic_homepage.com All rights reserved.
		</div>
	</div>
</body>
</html>