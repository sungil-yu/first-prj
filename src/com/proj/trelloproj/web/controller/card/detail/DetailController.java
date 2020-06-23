package com.proj.trelloproj.web.controller.card.detail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proj.trelloproj.web.entity.Board;
import com.proj.trelloproj.web.entity.Card;
import com.proj.trelloproj.web.entity.Comment;
import com.proj.trelloproj.web.entity.Description;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.CardDetailService;


@WebServlet("/board/detail")
public class DetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
//		�꽌鍮꾩뒪�븿�닔 媛앹껜�솕
		CardDetailService cardDetailService = new CardDetailService();
		
//		--------------------------------------
//		uid �꽭�뀡�뿉�꽌 諛쏆븘�삤湲
		Member m = (Member) request.getSession().getAttribute("User");
		int uid = m.getId();
		
//		---------------------------------------
//		cardId �꽭�뀡�뿉�꽌 諛쏆븘�삤湲�
		int cardId = Integer.parseInt(request.getParameter("cid"));

		
//		---------------------------------------
//		id �넻�빐  Member 諛쏆븘�삤湲�
		
		Member member = null;
		
		try {
			member = cardDetailService.getMemberById(uid);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		--------------------------------------		
//		cardId瑜� �넻�빐 Card 諛쏆븘�삤湲�
		Card card = null;
		
		try {
			card = cardDetailService.getCard(cardId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

			
//		-----------------------------------------
		Board board = null;
		
		try {
			board = cardDetailService.getBoard(cardId);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		----------------------------------------
//		cardId瑜� �넻�빐 Description 諛쏆븘�삤湲�
		
		Description description = null;
		
		try {
			description = cardDetailService.getDescription(cardId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

//		-------------------------------------
//		cardId瑜� �넻�빐 comment 諛쏆븘�삤湲�
		
		
		List<Comment> commentList = null;
		
		try {
			commentList = cardDetailService.getCommentList(cardId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Member> commentMemberList = null;
		
		try {
			commentMemberList = cardDetailService.getCommentMemberList(cardId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//		媛앹껜�뱾�쓣 session ���옣�냼�뿉 蹂대궡湲�
		HttpSession session = request.getSession();
		session.setAttribute("member", member);
		session.setAttribute("card", card);
		request.setAttribute("board", board);
		session.setAttribute("description", description);
		session.setAttribute("commentList", commentList);
		session.setAttribute("commentMemberList", commentMemberList);
		
//		媛앹껜�뱾�쓣 request ���옣�냼�뿉 蹂대궡湲�
//		request.setAttribute("card", card);
//		request.setAttribute("description", description);
//		request.setAttribute("cardMemberList", cardMemberList);
//		request.setAttribute("commentList", commentList);
//		request.setAttribute("commentMemberList", commentMemberList);
		
		request.getRequestDispatcher("../card/detail.jsp").forward(request, response);
		
	}
}
