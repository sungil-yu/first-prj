package com.proj.trelloproj.web.controller.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.proj.trelloproj.web.service.MemberService;

@WebServlet("/member/emailsearch")
public class EmailSearchController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		MemberService service = new MemberService();
		int isOverrap = 0;
		
		try {
			isOverrap = service.isOverrapByEmail(email);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.getWriter().write(Integer.toString(isOverrap));
	}
}
