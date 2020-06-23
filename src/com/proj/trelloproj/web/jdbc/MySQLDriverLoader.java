package com.proj.trelloproj.web.jdbc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class MySQLDriverLoader  extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
	
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (Exception ex) {
			System.out.println("jdbc�뿰寃� �삤瑜�");
			throw new ServletException(ex);
		}
		
		
	}

	
}
