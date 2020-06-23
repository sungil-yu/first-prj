package com.proj.trelloproj.web.controller.teams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.entity.Team;
import com.proj.trelloproj.web.service.TeamTeamsService;
import com.proj.trelloproj.web.service.TrelloInviteService;


@WebServlet("/team/teams")
public class TeamTeamsController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int currentTeamId = (int) request.getSession().getAttribute("tempTeamId");
		TrelloInviteService service = new TrelloInviteService();
		
		List<Member> list = service.getTeamMember(currentTeamId);
		Team team = service.currentTeam(currentTeamId);
		Member leader = service.getLeader(currentTeamId);
		request.setAttribute("leader", leader);
		request.setAttribute("team", team);
		
		TeamTeamsService teamService = new TeamTeamsService();
		List<Team> teamList = new ArrayList<Team>();
		
		Member user = (Member) request.getSession().getAttribute("User");
		teamList = teamService.getTeams(user);
		
		request.setAttribute("teamList", teamList);
		request.getRequestDispatcher("/team/teams.jsp").forward(request, response);
	}
	
}
