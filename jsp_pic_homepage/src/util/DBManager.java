package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");

			// jdbc/?? 이란 이름을 객체를 찾아서 datasource가 받는다.
			DataSource ds = (DataSource) envContext.lookup("jdbc/pic_db");

			// ds가 생성되었으므로 connection을 구한다.
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getConnection 에러 발생");
		}
		return conn;
	}

	// select을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("close1 에러 발생");
		}
	}

	// DML(insert, update, delete)를 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("close2 에러 발생");
		}

	}

}
