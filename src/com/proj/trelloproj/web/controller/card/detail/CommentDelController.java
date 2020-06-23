package com.proj.trelloproj.web.controller.card.detail;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proj.trelloproj.web.entity.Card;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.CardDetailService;

@WebServlet ("/board/detail/comment-del")
public class CommentDelController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		CardDetailService cardDetailService = new CardDetailService();
		
		HttpSession session = request.getSession();
		
		Card card = (Card)session.getAttribute("card");
		int cardId = card.getId();
		
		Member myInfo = (Member)session.getAttribute("member");
		int writerId = myInfo.getId();
		
		String content = request.getParameter("m");
		System.out.println(content);
		 
		int result = -1;
		 
		try {
			result = cardDetailService.getResultFromDeletion(cardId, writerId, content);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("DB議곗옉 寃곌낵:"+result);
		Gson gson = new GsonBuilder()
		   .setDateFormat("yyyy-MM-dd-HH:mm:ss")
		   .create();
		String json = gson.toJson(result);
		
		//�엯�젰�븳 遺�遺꾩쓣 detail-comment �솕硫댁뿉 異쒕젰
		PrintWriter out = response.getWriter();
		out.write(String.valueOf(result));
	
	}
	
}
