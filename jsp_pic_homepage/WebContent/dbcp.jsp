
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//JDBC Driver Loading...
 Class.forName("com.mysql.jdbc.Driver");

 Connection conn = null;
 Statement pstmt = null;
 ResultSet rs = null;

   
 String jdbcDriver ="jdbc:mysql://localhost:3306/pic_db" ;
 String dbUser = "root";
 String dbPass = "12341234";
    
 conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
 
 String sql="select * from member";
  
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>디비연동</h2>
	<table width='400' border='1'>
		<tr>
			<th>닉네임</th>
		</tr>
		<%
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
				pstmt = conn.createStatement();
				rs = pstmt.executeQuery(sql);
				while (rs.next()) {
					out.println("<tr>");
					out.println("<td>");
					out.println(rs.getString(4));
					out.println("</td>");
					out.println("</tr>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		%>
	</table>
</body>
</html>