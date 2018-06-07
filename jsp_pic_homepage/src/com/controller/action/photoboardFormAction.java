package com.controller.action;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.ActionFactory;
import com.dao.BoardDAO;
import com.dto.BoardVO;

public class photoboardFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/photoboard.jsp";

		BoardDAO bDao = BoardDAO.getInstance();
		ActionFactory actionFactory = ActionFactory.getInstance();
		int strphotonum = actionFactory.strphotonum();
		bDao.updateReadCount(strphotonum);
		BoardVO photoboard = bDao.selectOneBoardByNum(strphotonum);
		
		
		request.setAttribute("photoboard", photoboard);
		
		String pic_nick = photoboard.getPic_nickname();
		String admin = "admin";
		request.setAttribute("pic_nick",pic_nick);
		
		// 세션 문제
		HttpSession session = request.getSession();
		session.removeAttribute("sessionpicId");
		session.setMaxInactiveInterval(1800); //1분간 유지 (default : 30분)
		session.setAttribute("sessionpicId", pic_nick);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

	public String strCut(String szText, String szKey, int nLength, int nPrev, boolean isNotag, boolean isAdddot) { // 문자열
																													// 자르기

		String r_val = szText;
		int oF = 0, oL = 0, rF = 0, rL = 0;
		int nLengthPrev = 0;

		try {
			byte[] bytes = r_val.getBytes("UTF-8"); // 바이트로 보관 if(szKey != null
													// && !szKey.equals(“”)) {
			nLengthPrev = (r_val.indexOf(szKey) == -1) ? 0 : r_val.indexOf(szKey); // 일단
																					// 위치찾고
			nLengthPrev = r_val.substring(0, nLengthPrev).getBytes("MS949").length; // 위치까지길이를
																					// byte로
																					// 다시
																					// 구한다
			nLengthPrev = (nLengthPrev - nPrev >= 0) ? nLengthPrev - nPrev : 0; // 좀
																				// 앞부분부터
																				// 가져오도록한다.

			// x부터 y길이만큼 잘라낸다. 한글안깨지게.
			int j = 0;

			if (nLengthPrev > 0)
				while (j < bytes.length) {
					if ((bytes[j] & 0x80) != 0) {
						oF += 2;
						rF += 3;
						if (oF + 2 > nLengthPrev) {
							break;
						}
						j += 3;
					} else {
						if (oF + 1 > nLengthPrev) {
							break;
						}
						++oF;
						++rF;
						++j;
					}
				}

			j = rF;

			while (j < bytes.length) {
				if ((bytes[j] & 0x80) != 0) {
					if (oL + 2 > nLength) {
						break;
					}
					oL += 2;
					rL += 3;
					j += 3;
				} else {
					if (oL + 1 > nLength) {
						break;
					}
					++oL;
					++rL;
					++j;
				}
			}

			r_val = new String(bytes, rF, rL, "UTF-8"); // charset 옵션

			if (isAdddot && rF + rL + 3 <= bytes.length) {
				r_val += "…";
			} // …을 붙일지말지 옵션
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return r_val;
	}

}
