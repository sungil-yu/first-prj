package com.proj.trelloproj.web.controller.members;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.TrelloInviteService;


@WebServlet("/team/members-cancel")
public class InviteCancelController extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String inviteeId_ = request.getParameter("invitee");
		int inviteeId = Integer.parseInt(inviteeId_);
		
		String teamId_ =request.getParameter("teamId");
		int teamId = Integer.parseInt(teamId_);
		
		Member member = (Member)request.getSession().getAttribute("User");
		int memberId = member.getId();
		
		TrelloInviteService service = new TrelloInviteService();
		int result = 0;
		result = service.cancelInvite(inviteeId,teamId,memberId);
		
		Gson gson = new Gson();
		String json = gson.toJson(result);
		PrintWriter out = response.getWriter();
		out.write(json);
	
	}
}
