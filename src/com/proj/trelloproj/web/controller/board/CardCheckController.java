package com.proj.trelloproj.web.controller.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.trelloproj.web.service.CardService;
import com.proj.trelloproj.web.service.ListService;

@WebServlet("/board/cardcheck")
public class CardCheckController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cardId = Integer.parseInt(request.getParameter("cid"));
		boolean isCheck = Boolean.parseBoolean(request.getParameter("chk"));
		int listId = 0;
		int listCheck = 0;
		
		CardService cardService = new CardService();
		ListService listService = new ListService();
		
		try {
			cardService.check(cardId,isCheck);
			listId = cardService.getListId(cardId);
			listCheck = cardService.getListCheck(listId);
			listService.setCheck(listId,listCheck);
//			서비스 하나로 묶어 트랜젝션 처리하는게 맞는거같음...
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.getWriter().write(String.valueOf(listCheck));
		
	}
}
