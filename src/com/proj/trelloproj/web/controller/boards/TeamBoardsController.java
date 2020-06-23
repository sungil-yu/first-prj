package com.proj.trelloproj.web.controller.boards;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.trelloproj.web.entity.Board;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.entity.Team;
import com.proj.trelloproj.web.service.TeamBoardService;
import com.proj.trelloproj.web.service.TrelloInviteService;



@WebServlet("/team/boards")
public class TeamBoardsController extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		header �뀑�똿
		
		int currentTeamId = (int) request.getSession().getAttribute("tempTeamId");
		TrelloInviteService service = new TrelloInviteService();
		
		List<Member> list = service.getTeamMember(currentTeamId);
		Team team = service.currentTeam(currentTeamId);
		Member leader = service.getLeader(currentTeamId);
		
		request.setAttribute("leader", leader);
		request.setAttribute("team", team);
		
		//main�뀑�똿
		
		TeamBoardService boardService = new TeamBoardService();
		
		List<Board>  teamBoards = boardService.teamBoardList(currentTeamId);
		List<Member> boardMaker = boardService.teamBoardMaker(currentTeamId);
		
		request.setAttribute("teamBoards", teamBoards);
		request.setAttribute("boardMaker", boardMaker);
		request.getRequestDispatcher("/team/boards.jsp").forward(request, response);
	
	}
}
