package com.proj.trelloproj.web.controller;

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
import com.google.gson.GsonBuilder;
import com.proj.trelloproj.web.entity.Board;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.BoardService;

@WebServlet("/boardreg")
public class BoardRegController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Member member = (Member) request.getSession().getAttribute("User");
		
		InputStream is = request.getInputStream();
		Scanner scan = new Scanner(is, "UTF-8");
		String line = scan.nextLine();
		
		Gson gson = new Gson();
				   
		Board board = gson.fromJson(line, Board.class);
		board.setCreaterId(member.getId());
		
		BoardService service = new BoardService();
		Board result = null;
		try {
			result = service.insertBoard(board);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String resultJson = gson.toJson(result);
		response.getWriter().write(resultJson);
	}
}