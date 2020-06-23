package com.proj.trelloproj.web.controller.members;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

@WebServlet("/team/members-auto")
public class InviteMembersAutoController extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String input = request.getParameter("input");
		String option = request.getParameter("option");
		List<Member> memberList = new ArrayList<Member>();
		
	
		Member member =(Member) request.getSession().getAttribute("User");
		Map<String , Object> map = new HashMap<String , Object>();
		
		TrelloInviteService service =new TrelloInviteService();
		memberList = service.autoSearch(option, input , map);
		service.autoDetailSearch(option, input , map ,memberList);
		
		System.out.println(map);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		PrintWriter out = response.getWriter();
		out.write(json);
		
	}
	
	
}
