package com.proj.trelloproj.web.controller.members;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.TrelloInviteService;


@WebServlet("/team/member-invitation")
public class InvitationMemberController extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); 
		response.setCharacterEncoding("UTF-8");
		
		Map<String , Object> map = new HashMap<String, Object>();
		TrelloInviteService service = new TrelloInviteService();
		Member member = (Member) request.getSession().getAttribute("User");
		
		map =service.invitationMember(member);
		map =service.invitationTeam(member,map);
		map =service.invitationIv(member,map);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		PrintWriter out =response.getWriter();
		out.write(json);
}
}
