package com.proj.trelloproj.web.controller.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.LoginService;


@WebServlet("/member/login")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		if(session.getAttribute("User") != null) {
			request.getRequestDispatcher("../index.jsp").forward(request, response);
		}else if(session.getAttribute("User") == null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		LoginService service = new LoginService();
		
		String inputId = request.getParameter("id");
		String inputPwd = request.getParameter("pwd");

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);

		if (inputId == null || inputId.isEmpty())
			errors.put("id", Boolean.TRUE);

		if (inputPwd == null || inputPwd.isEmpty())
			errors.put("pwd", Boolean.TRUE);

		
		if (!errors.isEmpty())
			request.getRequestDispatcher("login.jsp").forward(request, response);

		Member member = service.successLogin(inputId, inputPwd);
		if (member == null) { //로그인 실패
			errors.put("idAndPwdError", Boolean.TRUE);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("User", member);
			response.sendRedirect("../index");
		}

	}

}
