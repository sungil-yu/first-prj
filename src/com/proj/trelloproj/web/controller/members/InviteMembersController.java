package com.proj.trelloproj.web.controller.members;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.entity.Team;
import com.proj.trelloproj.web.service.TrelloInviteService;

@WebServlet("/team/members")
public class InviteMembersController extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
	
		Integer tempTeamId = null;
		
		String tempTeamId_ = request.getParameter("teamId");
		
		if(tempTeamId_ != null && !tempTeamId_.isEmpty()) {
			tempTeamId = Integer.parseInt(tempTeamId_);
		request.getSession().setAttribute("tempTeamId", tempTeamId);
		}
		
		int currentTeamId = (int) request.getSession().getAttribute("tempTeamId");
		System.out.println(currentTeamId);
		
		TrelloInviteService service = new TrelloInviteService();
		
		List<Member> list = service.getTeamMember(currentTeamId);
		Team team = service.currentTeam(currentTeamId);
		Member leader = service.getLeader(currentTeamId);
		
		
		request.setAttribute("leader", leader);
		request.setAttribute("team", team);
		request.setAttribute("teamList", list);
		request.getRequestDispatcher("/team/members.jsp").forward(request, response);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		PrintWriter out = response.getWriter();
		out.write(json);


	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Member inviter = (Member) request.getSession().getAttribute("User");
		TrelloInviteService service = new TrelloInviteService();
		int currentTeamId = (int) request.getSession().getAttribute("tempTeamId");
		InputStream is = request.getInputStream();
		Scanner scan = new Scanner(is, "UTF-8");
		String json = scan.nextLine();
		Gson gson = new Gson();
		Member member = gson.fromJson(json, Member.class);
		Map<String, Boolean> errors = new HashMap<String, Boolean>();

		// 珥덈�諛쏆� �쑀��媛� 議댁옱�븯�뒗吏� �뿬遺�
		Member invitee = service.checkMember(member);
		if (invitee == null) {
			errors.put("NotMember", Boolean.TRUE);
		}

		
		try {
			
			int result = 0;
			result = service.invitingMember(invitee, inviter, currentTeamId);
			
			if (result == 0) {
				errors.put("isMember", Boolean.FALSE);
			}else {
				errors.put("isMember", Boolean.TRUE);
			}

			
		} catch (SQLException e) {
			response.sendError(500, "�꽌踰꾩삤瑜�");
		}

		
		String error = gson.toJson(errors);
		PrintWriter out = response.getWriter();
		out.write(error);
	}

}
