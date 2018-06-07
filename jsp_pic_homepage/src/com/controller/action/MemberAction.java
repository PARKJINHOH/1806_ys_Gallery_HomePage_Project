package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDAO;
import com.dto.MemberVO;

public class MemberAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO mVo = new MemberVO();

		mVo.setId_id(request.getParameter("id_id"));
		mVo.setId_pwd(request.getParameter("id_pwd"));
		mVo.setId_nickname(request.getParameter("id_nickname"));
		mVo.setId_email(request.getParameter("id_email"));
		mVo.setId_tel(request.getParameter("id_tel"));

		MemberDAO mDao = MemberDAO.getInstance();

		int inick = mDao.insertMember(mVo);
		System.out.println("inick : " + inick);

		request.setAttribute("id_nickch", inick);

		new BoardMainAction().execute(request, response);
	}
}
