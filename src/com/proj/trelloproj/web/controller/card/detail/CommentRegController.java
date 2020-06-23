package com.proj.trelloproj.web.controller.card.detail;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import com.proj.trelloproj.web.entity.Comment;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.CardDetailService;

@WebServlet ("/board/detail/comment-reg")
public class CommentRegController extends HttpServlet{
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
		
		Member member = null;
		
		try {
			member = cardDetailService.getMemberOnCmt(cardId, writerId);
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
		String json = gson.toJson(member);
		
		//�엯�젰�븳 遺�遺꾩쓣 detail-comment �솕硫댁뿉 異쒕젰
		PrintWriter out = response.getWriter();
		out.write(json);
	
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		HttpSession session = request.getSession();
		
		Card card = (Card)session.getAttribute("card");
		int cardId = card.getId();
		
	    InputStream is = request.getInputStream();
	    
	    Scanner scan = new Scanner(is, "UTF-8");
	    String json = scan.nextLine();
	    Gson gson = new GsonBuilder()
				   .setDateFormat("yyyy-MM-dd-HH:mm:ss")
				   .create();
	    
//	    json�삎�떇�쓣 String �삎�떇�쑝濡� 諛붽퓭以�
	    String cmt = gson.fromJson(json, String.class);
		
	    Member myInfo = (Member)session.getAttribute("member");
		int writerId = myInfo.getId();
		
		
		CardDetailService cardDetailService = new CardDetailService();
		
		
		Comment cmt2 = null;
		
		
		try {
			cmt2 = cardDetailService.getCmtInserted(cardId, cmt, writerId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		String json2 = gson.toJson(cmt2);
		
		//�엯�젰�븳 遺�遺꾩쓣 detail-comment �솕硫댁뿉 異쒕젰
		PrintWriter out = response.getWriter();
		out.write(json2);
		
		}
		
}
