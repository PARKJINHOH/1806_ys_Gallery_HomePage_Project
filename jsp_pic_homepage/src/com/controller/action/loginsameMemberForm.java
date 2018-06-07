package com.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.ActionFactory;
import com.dao.MemberDAO;

public class loginsameMemberForm implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/loginconfirmID.jsp";
		
		ActionFactory actionFactory =  ActionFactory.getInstance();
		String userid = actionFactory.strconfirmIDint();
		
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		int result = mDao.confirmID(userid);
		
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}
