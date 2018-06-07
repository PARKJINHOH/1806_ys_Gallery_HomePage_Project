package com.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.controller.ActionFactory;
import com.dao.BoardDAO;
import com.dto.BoardVO;

public class BoardMainActioncty implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "/board/main.jsp";

		BoardDAO bDao = BoardDAO.getInstance();
		ActionFactory actionFactory = ActionFactory.getInstance();
		String str = actionFactory.str();
		int acFint1 = (actionFactory.pg() - 1) * 4;
		int acFint2 = (actionFactory.pg() - 1) * 4 + 1;
		int acFint3 = (actionFactory.pg() - 1) * 4 + 2;
		int acFint4 = (actionFactory.pg() - 1) * 4 + 3;

		List<BoardVO> boardMain = bDao.selectAllBoardcty(str);

		int pagecount = 1;
		int pageData;
		pageData = boardMain.size();
		for (int i = 1; i < boardMain.size() + 1; i++) {
			if ((i % 4) == 0) {
				pagecount++;
			}
			if ((boardMain.size() % 4) == 0) {
				pagecount = boardMain.size() / 4;
			}
		}

		request.setAttribute("boardMain", boardMain);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pageData", pageData);
		request.setAttribute("acFint1", acFint1);
		request.setAttribute("acFint2", acFint2);
		request.setAttribute("acFint3", acFint3);
		request.setAttribute("acFint4", acFint4);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}
}
