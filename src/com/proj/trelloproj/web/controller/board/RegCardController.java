package com.proj.trelloproj.web.controller.board;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.proj.trelloproj.web.entity.Card;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.CardService;

@WebServlet("/board/regcard")
public class RegCardController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		
		Member member = (Member) request.getSession().getAttribute("User");
		int id = member.getId();
		
		InputStream is = request.getInputStream(); 
		Scanner scan = new Scanner(is,"UTF-8");
		String line = scan.nextLine();
		Gson gson = new Gson();
		Card card = gson.fromJson(line, Card.class);
		card.setCreaterId(id);
		
		CardService service = new CardService();
		Card result = null;
		
		int listCheck = 0;
		
		try {
			int lastOrder = service.getLastOrder(card.getListId());
			card.setOrder(lastOrder+1);
			result = service.insertCard(card);
			
			listCheck = service.getListCheck(card.getListId());
			result.setArchive(listCheck);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String resultJson = gson.toJson(result);
		response.getWriter().write(resultJson);
	}
}
