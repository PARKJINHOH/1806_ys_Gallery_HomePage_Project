package com.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

import com.controller.action.Action;
import com.controller.action.BoardMainAction;
import com.controller.action.BoardMainActionPop;
import com.controller.action.BoardMainActioncty;
import com.controller.action.DeleteFormAction;
import com.controller.action.LoginAction;
import com.controller.action.MemberAction;
import com.controller.action.MemberFormAction;
import com.controller.action.Update_write_boardAction;
import com.controller.action.Update_write_boardFormAction;
import com.controller.action.WriteBoardFormAction;
import com.controller.action.Write_BoardAcion;
import com.controller.action.loginFormAction;
import com.controller.action.loginsameMemberForm;
import com.controller.action.loginsameNickForm;
import com.controller.action.photoboardAction;
import com.controller.action.photoboardFormAction;
import com.dao.BoardDAO;
import com.dto.BoardVO;

public class ActionFactory {

	static String str;
	static String strphoto;
	static String strphotonum;
	static String strconfirmID;
	static String strconfirmIDint;
	static String strconfirmNick;
	static String strconfirmNickint;
	String pgString = "";
	int pagecount = 1;
	static int page = 0;
	static int pageresult = 0;

	private static ActionFactory instance = new ActionFactory();

	private ActionFactory() {
		super();
	}

	public static ActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory : " + command);
		str = strCut(command, "-", 3, -1, false, false);
		strphoto = strCut(command, "p", 14, 0, false, false);
		strphotonum = strCut(command, "=", 1, -1, false, false);
		
		//아이디 중복확인
		strconfirmID = strCut(command, "l", 19, 0, false, false);
		strconfirmIDint = strCut(command, "=", 12, -1, false, false);
		
		//닉네임 중복확인
		strconfirmNick = strCut(command, "l", 16, 0, false, false);
		strconfirmNickint = strCut(command, "=", 12, -1, false, false);

		BoardDAO bDao = BoardDAO.getInstance();
		List<BoardVO> boardMainMost = bDao.selectAllBoardMost();

		for (int i = 1; i < boardMainMost.size() + 1; i++) {
			if ((i % 4) == 0) {
				pagecount++;
			}
			if ((boardMainMost.size() % 4) == 0) {
				pagecount = boardMainMost.size() / 4;
			}
		}
		for (page = 0; page <= pagecount; page++) {
			pgString = "boardMain_pg=" + page;
			if (command.equals(pgString)) {
				pageresult = page;
				action = new BoardMainAction();
			}
		}
		for (page = 0; page <= pagecount; page++) {
			pgString = "boardMain_pp=" + page;
			if (command.equals(pgString)) {
				pageresult = page;
				action = new BoardMainActionPop();
			}
		}

		if (command.equals("boardMain")) {
			action = new BoardMainAction();
		} else if (command.equals("write_board")) {
			action = new Write_BoardAcion();
		} else if (command.equals("write_board_form")) {
			action = new WriteBoardFormAction();
		} else if (command.equals("MemberFormAction")) {
			action = new MemberFormAction();
		} else if (command.equals("MemberAction")) {
			action = new MemberAction();
		} else if (command.equals("loginAction")) {
			action = new LoginAction();
		} else if (command.equals("loginformAction")) {
			action = new loginFormAction();
		} else if (str.equals("ani") || str.equals("peo") || str.equals("nat") || str.equals("car")
				|| str.equals("mat") || str.equals("gam")) {
			action = new BoardMainActioncty();
		} else if (strphoto.equals("photoboardForm")) {
			action = new photoboardFormAction();
		} else if (command.equals("photoboardAction")) {
			action = new photoboardAction();
		} else if(command.equals("update_write_board")){
			action = new Update_write_boardAction();
		} else if(command.equals("update_write_boardForm")){
			action = new Update_write_boardFormAction();
		} else if(command.equals("DeleteForm")){
			action = new DeleteFormAction();
		} else if(strconfirmID.equals("loginsameMemberForm")){
			action = new loginsameMemberForm();
		} else if(strconfirmNick.equals("loginconfirmNick")){
			action = new loginsameNickForm();
		} 

		return action;
	}

	public int pg() {
		return pageresult;
	}

	public String str() {
		return str;
	}

	public int strphotonum() {
		return Integer.parseInt(strphotonum);
	}
	
	public String strconfirmIDint(){
		return strconfirmIDint;
	}
	
	public String strconfirmNickint(){
		return strconfirmNickint;
	}

	public String strCut(String szText, String szKey, int nLength, int nPrev, boolean isNotag, boolean isAdddot) {

		String r_val = szText;
		int oF = 0, oL = 0, rF = 0, rL = 0;
		int nLengthPrev = 0;

		try {
			byte[] bytes = r_val.getBytes("UTF-8");

			nLengthPrev = (r_val.indexOf(szKey) == -1) ? 0 : r_val.indexOf(szKey);
			nLengthPrev = r_val.substring(0, nLengthPrev).getBytes("MS949").length;
			nLengthPrev = (nLengthPrev - nPrev >= 0) ? nLengthPrev - nPrev : 0;

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
