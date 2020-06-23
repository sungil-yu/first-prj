package com.proj.trelloproj.web.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.proj.trelloproj.web.entity.Board;
import com.proj.trelloproj.web.entity.Invitation;
import com.proj.trelloproj.web.entity.InvitationView;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.entity.MemberDetail;
import com.proj.trelloproj.web.entity.Team;
import com.proj.trelloproj.web.service.BoardService;
import com.proj.trelloproj.web.service.InvitationService;
import com.proj.trelloproj.web.service.MemberDetailService;
import com.proj.trelloproj.web.service.TeamService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/index")
public class IndexController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MemberDetailService service = new MemberDetailService();
		Member member = (Member) request.getSession().getAttribute("User");
		int id = member.getId();
		
		MemberDetail memberDetail = null;
		memberDetail = service.getDetail(id);

		TeamService teamService = new TeamService();
		List<Team> teams = null;

		BoardService boardService = new BoardService();
		List<Board> boards = null;
		
		InvitationService invitationService = new InvitationService();
		List<InvitationView> noti = null;

		try {
			teams = teamService.getTeamList(id);
			boards = boardService.getBoardList(id);
			noti = invitationService.getNoticeList(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("noti",noti );
		request.setAttribute("detail", memberDetail);
		request.setAttribute("member", member);
		request.setAttribute("team", teams);
		request.setAttribute("board", boards);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		MemberDetailService service = new MemberDetailService();
		Member member = (Member) request.getSession().getAttribute("User");
		MemberDetail memberDetail = null;
		String fileName = null;
		int id = member.getId();
		int result = 0;

		Part part = request.getPart("file");
		String realPath = request.getServletContext().getRealPath("/profile-img");

		File fileSaveDir = new File(realPath);
		if (!fileSaveDir.exists())
			fileSaveDir.mkdir();

		if (part.getSize() > 0) {
			fileName = part.getSubmittedFileName();
			String filePath = realPath + File.separator + fileName;
			part.write(filePath);
			result = service.setDetail(id, fileName);
			memberDetail = service.getDetail(id);
		}


		TeamService teamService = new TeamService();
		List<Team> teams = null;

		BoardService boardService = new BoardService();
		List<Board> boards = null;

		InvitationService invitationService = new InvitationService();
		List<InvitationView> noti = null;
		
		try {
			teams = teamService.getTeamList(id);
			boards = boardService.getBoardList(id);
			noti = invitationService.getNoticeList(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("noti",noti );
		request.setAttribute("detail", memberDetail);
		request.setAttribute("member", member);
		request.setAttribute("team", teams);
		request.setAttribute("board", boards);

		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
