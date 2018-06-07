package com.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.dao.MemberDAO;
import com.dto.BoardVO;
import com.dto.MemberVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//썸네일
import java.net.URLEncoder;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.JAI;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import java.io.File;

@SuppressWarnings("serial")
public class Write_BoardAcion extends HttpServlet implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		// 다운로드 경로 (바꾸면 바뀜)
		String savepath = "pic_original";

		// 최대 업로드 파일 크기
		int uploadfilesizelimit = 5 * 1024 * 1024; // 5MB
		String enctype = "UTF-8";

		ServletContext context = request.getSession().getServletContext();
		// ServletContext context = getServletContext();
		String uploadfilepath = "C:\\Users\\JIN\\eclipse-jee-neon-3-win32-x86_64\\eclipse\\web_workspace\\jsp_pic_homepage\\WebContent\\pic_original";
		String Sumuploadfilepath = "C:\\Users\\JIN\\eclipse-jee-neon-3-win32-x86_64\\eclipse\\web_workspace\\jsp_pic_homepage\\WebContent\\pic_copy";
		String Consumuploadfilepath = "C:\\Users\\JIN\\eclipse-jee-neon-3-win32-x86_64\\eclipse\\web_workspace\\jsp_pic_homepage\\WebContent\\pic_sum";

		System.out.println("서버상의 실제 디렉토리");
		System.out.println(uploadfilepath);

		MultipartRequest multi = null;
		try {

			multi = new MultipartRequest(request, uploadfilepath, uploadfilesizelimit, enctype,
					new DefaultFileRenamePolicy());

		} catch (Exception e) {
			System.out.println("multi 예외발생 : " + e);
		}

		BoardVO bVo = new BoardVO();

		String uploadname = uploadfilepath + "\\" + multi.getFilesystemName("pic_pic");
		String sumuploadname = Sumuploadfilepath + "\\" + multi.getFilesystemName("pic_pic");
		String dbsumuploadname = "pic_copy\\" + multi.getFilesystemName("pic_pic");
		String consumuploadname = Consumuploadfilepath + "\\" + multi.getFilesystemName("pic_pic");
		String dbConsumuploadname =  "pic_sum\\" + multi.getFilesystemName("pic_pic");

		bVo.setPic_cty(multi.getParameter("selectCT"));
		bVo.setPic_name(multi.getParameter("pic_name"));
		bVo.setPic_pic(uploadname);
		bVo.setPic_sum(dbsumuploadname);
		bVo.setPic_consum(dbConsumuploadname);

		// 썸네일 만들기
		try {
			RenderedOp op = JAI.create("fileload", uploadname);
			// JAI.create(파일로드 옵션값, 원본파일이름), 원본이미지 로드
			BufferedImage obuffer = op.getAsBufferedImage(); // 이미지를 버퍼로 만들어라
			int twidth = 340;
			int theight = 220;

			BufferedImage tbuffer = new BufferedImage(twidth, theight, BufferedImage.TYPE_INT_RGB);
			// 썸네일 용(가로, 세로, 색상모델지정)
			Graphics2D g = (Graphics2D) tbuffer.getGraphics(); // 펜?
			g.drawImage(obuffer, 0, 0, twidth, theight, null);
			/*
			 * 이 생성된 펜으로 그린다. obuffer에 있는 이미지를 0,0에서 twidth, theight 만큼 그린다.
			 */

			// 이미지 형식에 맞게 맞는 아이로 생성해서 보여줌
			// tbuffer에 있는 이미지를 jpg 확장자로, tname파일이름으로 그려라(썸네일 이미지 생성)
			ImageIO.write(tbuffer, "jpg", new File(sumuploadname));
			ImageIO.write(tbuffer, "png", new File(sumuploadname));
			ImageIO.write(tbuffer, "gif", new File(sumuploadname));

		} catch (Exception e) {
			System.out.println("썸네일 오류 : " + e);
		}
		
		try {
			RenderedOp op = JAI.create("fileload", uploadname);
			// JAI.create(파일로드 옵션값, 원본파일이름), 원본이미지 로드
			BufferedImage obuffer = op.getAsBufferedImage(); // 이미지를 버퍼로 만들어라
			int swidth = 720;
			int sheight = 405;

			BufferedImage tbuffer = new BufferedImage(swidth, sheight, BufferedImage.TYPE_INT_RGB);
			// 썸네일 용(가로, 세로, 색상모델지정)
			Graphics2D g = (Graphics2D) tbuffer.getGraphics(); // 펜?
			g.drawImage(obuffer, 0, 0, swidth, sheight, null);
			/*
			 * 이 생성된 펜으로 그린다. obuffer에 있는 이미지를 0,0에서 twidth, theight 만큼 그린다.
			 */

			// 이미지 형식에 맞게 맞는 아이로 생성해서 보여줌
			// tbuffer에 있는 이미지를 jpg 확장자로, tname파일이름으로 그려라(썸네일 이미지 생성)
			ImageIO.write(tbuffer, "jpg", new File(consumuploadname));
			ImageIO.write(tbuffer, "png", new File(consumuploadname));
			ImageIO.write(tbuffer, "gif", new File(consumuploadname));

		} catch (Exception e) {
			System.out.println("썸네일 오류 : " + e);
		}

		HttpSession session = request.getSession(true);
		String nickname = (String) session.getAttribute("sessionId");
		bVo.setPic_con(multi.getParameter("pic_con"));
		bVo.setPic_nickname(nickname);

		BoardDAO bDao = BoardDAO.getInstance();
		bDao.insertBoard(bVo);

		new BoardMainAction().execute(request, response);

	}
}
