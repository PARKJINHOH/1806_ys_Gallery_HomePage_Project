package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import javax.xml.xpath.*;
import org.xml.sax.InputSource;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import com.dto.BoardVO;
import com.sun.accessibility.internal.resources.accessibility;

import util.DBManager;

public class BoardDAO {

	private BoardDAO() {
	}

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	// 최근 게시물 불러오기
	public List<BoardVO> selectAllBoardMost() {
		String sql = "select * from picture_board order by pic_num desc";

		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BoardVO bVo = new BoardVO();

				bVo.setPic_num(rs.getInt("pic_num"));
				bVo.setPic_name(rs.getString("pic_name"));
				bVo.setPic_con(rs.getString("pic_con"));
				bVo.setPic_cty(rs.getString("pic_cty"));
				bVo.setPic_hits(rs.getInt("pic_hits"));
				bVo.setPic_nickname(rs.getString("pic_nickname"));
				bVo.setPic_pic(rs.getString("pic_pic"));
				bVo.setPic_sum(rs.getString("pic_sum"));

				System.out.println("rs.getString(pic_sum)" + rs.getString("pic_sum"));

				list.add(bVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAllBoardMost 에러 발생");
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	// 인기 게시물 불러오기
	public List<BoardVO> selectAllBoardPop() {
		String sql = "select * from picture_board order by pic_hits desc";

		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BoardVO bVo = new BoardVO();

				bVo.setPic_num(rs.getInt("pic_num"));
				bVo.setPic_name(rs.getString("pic_name"));
				bVo.setPic_con(rs.getString("pic_con"));
				bVo.setPic_cty(rs.getString("pic_cty"));
				bVo.setPic_hits(rs.getInt("pic_hits"));
				bVo.setPic_nickname(rs.getString("pic_nickname"));
				bVo.setPic_pic(rs.getString("pic_pic"));
				bVo.setPic_sum(rs.getString("pic_sum"));

				list.add(bVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAllBoardMost 에러 발생");
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	// 카테고리 게시물 불러오기
	public List<BoardVO> selectAllBoardcty(String str) {
		String sql = "select * from picture_board where pic_cty=?";

		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				bVo = new BoardVO();
				bVo.setPic_num(rs.getInt("pic_num"));
				bVo.setPic_name(rs.getString("pic_name"));
				bVo.setPic_con(rs.getString("pic_con"));
				bVo.setPic_cty(rs.getString("pic_cty"));
				bVo.setPic_hits(rs.getInt("pic_hits"));
				bVo.setPic_nickname(rs.getString("pic_nickname"));
				bVo.setPic_pic(rs.getString("pic_pic"));
				bVo.setPic_sum(rs.getString("pic_sum"));

				list.add(bVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAllBoardMost 에러 발생");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 게시글 입력
	public void insertBoard(BoardVO bvo) {
		String sql = "insert into picture_board (pic_name, pic_con, pic_cty, pic_pic, pic_sum, pic_nickname, pic_consum) value(?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bvo.getPic_name());
			pstmt.setString(2, bvo.getPic_con());
			pstmt.setString(3, bvo.getPic_cty());
			pstmt.setString(4, bvo.getPic_pic());
			pstmt.setString(5, bvo.getPic_sum());
			pstmt.setString(6, bvo.getPic_nickname());
			pstmt.setString(7, bvo.getPic_consum());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertBoard 에러 발생");
		} finally {
			DBManager.close(conn, pstmt);
		}

	}
	
	// 게시글 수정
		public void UpdateBoard(BoardVO bVo) {
			System.out.println("UpdateBoard 실행");
			String sql = "update picture_board set pic_con=?, pic_cty=?, pic_pic=?, pic_sum=? , pic_consum=?, pic_name=? where pic_num=?";

			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);

				
				pstmt.setString(1, bVo.getPic_con());
				pstmt.setString(2, bVo.getPic_cty());
				pstmt.setString(3, bVo.getPic_pic());
				pstmt.setString(4, bVo.getPic_sum());
				pstmt.setString(5, bVo.getPic_consum());
				pstmt.setString(6, bVo.getPic_name());
				pstmt.setInt(7, bVo.getPic_num());

				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("UpdateBoard 에러 발생");
			} finally {
				DBManager.close(conn, pstmt);
			}

		}

	public void updateReadCount(int num) {
		String sql = "update picture_board set pic_hits = pic_hits + 1 where pic_num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 게시판 글 상세 내용 보기 : 글번호로 찾아온다.
	public BoardVO selectOneBoardByNum(int num) {
		System.out.println("selectOneBoardByNum 실행");
		String sql = "select * from picture_board where pic_num = ?";

		BoardVO bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				bVo = new BoardVO();
				bVo.setPic_num(rs.getInt("pic_num"));
				bVo.setPic_name(rs.getString("pic_name"));
				bVo.setPic_con(rs.getString("pic_con"));
				bVo.setPic_cty(rs.getString("pic_cty"));
				bVo.setPic_hits(rs.getInt("pic_hits"));
				bVo.setPic_pic(rs.getString("pic_pic"));
				bVo.setPic_sum(rs.getString("pic_sum"));
				bVo.setPic_nickname(rs.getString("pic_nickname"));
				bVo.setPic_consum(rs.getString("pic_consum"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectOneBoardByNum 에러 발생");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bVo;
	}

	/*
	 * public BoardVO checkPassWord(String pass, String num) { String sql =
	 * "select * from board where pass=? and num=?"; Connection conn = null;
	 * PreparedStatement pstmt = null; ResultSet rs = null; BoardVO bVo = null;
	 * try { conn = DBManager.getConnection(); pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, pass); pstmt.setString(2,
	 * num); rs = pstmt.executeQuery(); if (rs.next()) { bVo = new BoardVO();
	 * bVo.setNum(rs.getInt("num")); bVo.setName(rs.getString("name"));
	 * bVo.setEmail(rs.getString("email")); bVo.setPass(rs.getString("pass"));
	 * bVo.setTitle(rs.getString("title"));
	 * bVo.setContent(rs.getString("content"));
	 * bVo.setReadcount(rs.getInt("readcount"));
	 * bVo.setWritedate(rs.getTimestamp("writedate")); } } catch (SQLException
	 * e) { e.printStackTrace(); } return bVo; }
	 */

	public void deleteBoard(int num) {
		System.out.println("deleteBoard 실행");
		String sql = "delete from picture_board where pic_num=?";
		System.out.println("num : " + num);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

}
