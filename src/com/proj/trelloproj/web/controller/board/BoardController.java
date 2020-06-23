package com.proj.trelloproj.web.controller.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.trelloproj.web.entity.Board;
import com.proj.trelloproj.web.entity.Card;
import com.proj.trelloproj.web.entity.List;
import com.proj.trelloproj.web.service.BoardService;
import com.proj.trelloproj.web.service.CardService;
import com.proj.trelloproj.web.service.ListService;

@WebServlet("/board/board")
public class BoardController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int boardId = 2;
		int boardId = Integer.parseInt(request.getParameter("bid"));
		
		BoardService boardService = new BoardService();
		ListService listService = new ListService();
		CardService cardService = new CardService();
		Board board = null;
		java.util.List<List> lists = new ArrayList<List>();
		java.util.List<Card> cards = new ArrayList<Card>();
		
		try {
			board = boardService.getBoard(boardId);
			lists = listService.getLists(boardId);
			cards = cardService.getCards(boardId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("board", board);
		request.setAttribute("lists", lists);
		request.setAttribute("cards", cards);
		
		request.getRequestDispatcher("board.jsp").forward(request,response);
	}
}
