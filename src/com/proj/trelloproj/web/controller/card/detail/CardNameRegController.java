package com.proj.trelloproj.web.controller.card.detail;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proj.trelloproj.web.entity.Card;
import com.proj.trelloproj.web.service.CardDetailService;

@WebServlet ("/board/detail/name-reg")
public class CardNameRegController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		Card card = (Card)session.getAttribute("card");
		int cardId = card.getId();
		
		String content = request.getParameter("content");
		
		CardDetailService cardDetailService = new CardDetailService();
		
		Card card2 = null;
		
		
		try {
			card2 = cardDetailService.getCardupdated(cardId, content);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Gson gson = new GsonBuilder()
				   .setDateFormat("yyyy-MM-dd-HH:mm:ss")
				   .create();
		
		
		String json = gson.toJson(card2);
		//�엯�젰�븳 遺�遺꾩쓣 detail-name �솕硫댁뿉 異쒕젰
		PrintWriter out = response.getWriter();
		out.write(json);
		
		}
		
}
