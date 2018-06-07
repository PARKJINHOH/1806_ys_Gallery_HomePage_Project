package com.controller.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class photoboardAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String uploadfilepath = "C:\\Users\\JIN\\eclipse-jee-neon-3-win32-x86_64\\eclipse\\web_workspace\\jsp_pic_homepage\\WebContent\\pic_original\\";
		String filename = request.getParameter("filename");
		String orifilename = strCut(filename, "pic_original\\", 50, -13, false, false);

		FileInputStream fromFile = new FileInputStream(filename);
		OutputStream toClient = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(orifilename, "UTF-8") + ";");

		while (true) {
			int readData = fromFile.read();
			if (readData == -1) {
				break;
			}
			toClient.write(readData);
			toClient.flush();
		}
		fromFile.close();
		toClient.close();
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

			r_val = new String(bytes, rF, rL, "UTF-8");

			if (isAdddot && rF + rL + 3 <= bytes.length) {
				r_val += "…";
			} // …을 붙일지말지 옵션
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return r_val;
	}

}
