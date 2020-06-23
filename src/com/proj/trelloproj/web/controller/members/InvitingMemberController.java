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
import com.proj.trelloproj.web.service.TrelloInvitingService;

@WebServlet("/team/members-inviting")
public class InvitingMemberController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			Member member = new Member();
			
			int currentTeamId = (int) request.getSession().getAttribute("tempTeamId");
			
			member =  (Member)request.getSession().getAttribute("User");
			
			System.out.println(member);
			int userId =member.getId();
			
			TrelloInvitingService service= new TrelloInvitingService();
			Map<String, Object> map = new HashMap<String,Object>();
			
			map = service.getMembersInvited(currentTeamId, userId);
			map = service.getTeamInf(currentTeamId, userId,map);
			map = service.getInvitation(currentTeamId, userId,map);
		
			Gson gson = new Gson();
			String maps = gson.toJson(map);
			PrintWriter out = response.getWriter();
			
			out.write(maps);
			
			
	}	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * Member user = (Member) request.getSession().getAttribute("User");
		 * TrelloInviteService service = new TrelloInviteService(); String teamId =
		 * request.getParameter("teamId"); System.out.println(teamId +"dsdsd");
		 * 
		 * 
		 * InputStream is = request.getInputStream(); Scanner scan = new Scanner(is,
		 * "UTF-8"); String json = scan.nextLine(); Gson gson = new Gson(); Member
		 * member = gson.fromJson(json, Member.class);
		 * 
		 * //珥덈�諛쏆� �쑀��媛� 議댁옱�븯�뒗吏� �뿬遺� Member invitedMember = service.checkMember(member);
		 * 
		 * if(invitedMember == null) { response.sendError(1004, "珥덈�諛쏅뒗 �븘�씠�뵒媛� 議댁옱�븯吏� �븡�븘�슜"); }
		 * 
		 * //議댁옱�븳�떎硫� �뜲�씠�꽣踰좎씠�뒪�뿉 �엯�젰 service.invitingMember(invitedMember,user);
		 */
		
		
	}
}
