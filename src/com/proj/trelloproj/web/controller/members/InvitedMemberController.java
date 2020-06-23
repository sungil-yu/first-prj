package com.proj.trelloproj.web.controller.members;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.TrelloInviteService;

@WebServlet("/team/members-invited")
public class InvitedMemberController extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int currentTeamId = 0;
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String currentTeamId_ = request.getParameter("teamId");
		currentTeamId = Integer.parseInt(currentTeamId_);
	
		Member user = (Member) request.getSession().getAttribute("User");
		TrelloInviteService service = new TrelloInviteService();
		List<Member> list = service.getTeamMember(currentTeamId);
//		member = service.checkMember(member);
		Member leader = service.getLeader(currentTeamId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("member", user);
		map.put("teamMember", list);
		map.put("leader", leader);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		PrintWriter out = response.getWriter();
		out.write(json);
		

	}
}