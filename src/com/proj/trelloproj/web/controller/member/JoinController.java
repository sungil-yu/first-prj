package com.proj.trelloproj.web.controller.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.MemberDetailService;
import com.proj.trelloproj.web.service.MemberService;

@WebServlet("/member/join")
public class JoinController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("join.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Member member = new Member();
		int userId = 0;
		
		String uid = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String nick = request.getParameter("nickname");
		String email = request.getParameter("email");
		member.setUid(uid);
		member.setPwd(pwd);
		member.setName(name);
		member.setNickname(nick);
		member.setEmail(email);
		
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		
		MemberService service = new MemberService();
		MemberDetailService detailService = new MemberDetailService();
		
		try {
			userId = service.insertMember(member);
			detailService.insertDetail(userId,gender,birthday);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		response.sendRedirect("login");
		
	}
}
