package com.proj.trelloproj.web.controller.teams;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.TeamTeamsService;

@WebServlet("/team/team-remove")
public class TeamRemoveController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int teamId = 0;
		int result = 0;
		
		Member user = (Member) request.getSession().getAttribute("User");
		String teamId_ = request.getParameter("teamId");
		
		if(teamId_ != null && !teamId_.isEmpty()) {
			 teamId = Integer.parseInt(teamId_);
		}
		
		TeamTeamsService service = new TeamTeamsService();
		
		result = service.removeTeam(teamId);
		result +=service.removeBoard(teamId);
		
		
		Gson gson = new Gson();
		String json = gson.toJson(result);
		PrintWriter out =response.getWriter();
		out.write(json);
	}
	
}