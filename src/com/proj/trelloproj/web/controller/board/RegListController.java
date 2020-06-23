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
import com.proj.trelloproj.web.entity.List;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.BoardService;
import com.proj.trelloproj.web.service.ListService;

@WebServlet("/board/reglist")
public class RegListController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		
		Member member = (Member) request.getSession().getAttribute("User");
		int id = member.getId();
		
		InputStream is = request.getInputStream(); //request's inputStream
		Scanner scan = new Scanner(is,"UTF-8"); //inputStream으로 문자열 읽음 -> 인코딩 방식 지정
		String line = scan.nextLine();
		Gson gson = new Gson();
		List list = gson.fromJson(line, List.class);
		list.setCreaterId(id);
		
		ListService listService = new ListService();
		List result = null;
		
		try {
			int lastOrder = listService.getlastOrder(list.getBoardId());
			list.setOrder(lastOrder+1);
			result = listService.insertList(list);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String resultJson = gson.toJson(result);
		response.getWriter().write(resultJson);
	}
}
