package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.dto.BoardVO;
import com.dto.MemberVO;

import util.DBManager;

public class MemberDAO {

	private MemberDAO() {
	}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	// 최근 회원 불러오기
	public List<MemberVO> selectAllMemberMost() {
		String sql = "select * from member order by id_num desc";

		List<MemberVO> list = new ArrayList<MemberVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				MemberVO mVo = new MemberVO();

				mVo.setId_num(rs.getInt("id_num"));
				mVo.setId_id(rs.getString("id_id"));
				mVo.setId_pwd(rs.getString("id_pwd"));
				mVo.setId_nickname(rs.getString("id_nickname"));
				mVo.setId_email(rs.getString("id_email"));
				mVo.setId_tel(rs.getString("id_tel"));

				list.add(mVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectAllMemberMost 에러 발생");
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	// 회원 가입
	public int insertMember(MemberVO mvo) {
		String sql = "insert into member (id_id, id_pwd, id_nickname, id_email, id_tel) value(?,?,?,?,?)";

		int insertresult = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			if(insertresult==-1){
				pstmt.setString(1, mvo.getId_id());
				pstmt.setString(2, mvo.getId_pwd());
				pstmt.setString(3, mvo.getId_nickname());
				pstmt.setString(4, mvo.getId_email());
				pstmt.setString(5, mvo.getId_tel());
				pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			insertresult = 1;
			System.out.println("insertMember 에러 발생");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return insertresult;

	}

	public MemberVO memberCheck(String id, String pass) throws SQLException {

		String sql = "select * from member where id_id=? and id_pwd=?";

		MemberVO mVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pass);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				mVo = new MemberVO();
				mVo.setId_id(rs.getString("id_id"));
				mVo.setId_pwd(rs.getString("id_pwd"));
				mVo.setId_nickname(rs.getString("id_nickname"));
			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("memberCheck 에러 : ");
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return mVo;
	}
	
	// 아이디 중복 확인
	public int confirmID(String userid) {
		int result = -1;
		System.out.println("userid : " + userid);
		String sql = "select id_id from member where id_id=? ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
		} catch (Exception e) {
			System.out.println("confirmID 에러 : ");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;

	}
	
	// 닉네임 중복 확인
		public int confirmNICK(String nickname) {
			int result = -1;
			String sql = "select id_nickname from member where id_nickname=? ";

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, nickname);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					result = 1;
				} else {
					result = -1;
				}
			} catch (Exception e) {
				System.out.println("confirmNICK 에러 : ");
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return result;

		}

}
