package com.controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.dao.MemberDAO;
import com.dto.MemberVO;

public class LoginAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		String url = null;
		
		String id = request.getParameter("id_id");
		String pass = request.getParameter("id_pass");
		
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO mVo = null;
		try {
			mVo = mDao.memberCheck(id, pass);
			
			HttpSession session = request.getSession(true);// true : 세션이 없을경우 생성, false : 세션이 없을경우 생성안함
			session.setMaxInactiveInterval(1800); //1분간 유지 (default : 30분)
			session.setAttribute("sessionId", mVo.getId_nickname());
			System.out.println("mvo.idid" + mVo.getId_id());
			System.out.println("mvo.id_pwd" + mVo.getId_pwd());
			System.out.println("mVo.getId_nickname() : " + mVo.getId_nickname());
			
			url = "/board/checkmember.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			url = "/board/login.jsp";
			request.setAttribute("message", "아이디 혹은 비밀번호를 확인해주세요.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}
