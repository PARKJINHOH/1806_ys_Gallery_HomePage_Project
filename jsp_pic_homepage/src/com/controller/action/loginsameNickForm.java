package com.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.ActionFactory;
import com.dao.MemberDAO;

public class loginsameNickForm implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/loginconfirmNick.jsp";
		
		ActionFactory actionFactory =  ActionFactory.getInstance();
		String nick = actionFactory.strconfirmNickint();
		
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		int result = mDao.confirmNICK(nick);
		
		request.setAttribute("nick", nick);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}
