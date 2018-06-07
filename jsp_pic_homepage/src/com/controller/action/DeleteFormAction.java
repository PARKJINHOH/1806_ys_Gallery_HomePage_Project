package com.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;

public class DeleteFormAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/main.jsp";

		BoardDAO bDao = BoardDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		
		bDao.deleteBoard(num);
		response.sendRedirect("http://localhost:8080/jsp_pic_homepage/BoardServlet?command=boardMain_pg=1");
//		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//		dispatcher.forward(request, response);
	}
}
