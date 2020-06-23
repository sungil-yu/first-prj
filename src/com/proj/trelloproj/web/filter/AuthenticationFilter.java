package com.proj.trelloproj.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.Filter;

@WebFilter("/index")

public class AuthenticationFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		
		boolean login = false;
		
		if(session != null) {
			if(session.getAttribute("User") != null) {
				login =true;
			}
		}
		
		if(login) {
			chain.doFilter(request, response);
		}else {
			request.getRequestDispatcher("member/login.jsp").forward(request, response);
		}
	}

}
