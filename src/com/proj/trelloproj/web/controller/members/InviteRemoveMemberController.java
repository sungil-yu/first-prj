package com.proj.trelloproj.web.controller.members;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.service.TrelloInviteService;

@WebServlet("/team/members-remove")
public class InviteRemoveMemberController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InputStream is = request.getInputStream();
		Scanner scan = new Scanner(is, "UTF-8");
		String json = scan.nextLine();
		Gson gson = new Gson();
		Member member = gson.fromJson(json, Member.class);
		
		System.out.println(member);
		int result = 0;
		TrelloInviteService service = new TrelloInviteService();
		result = service.delTeamMember(member);
	
		Map<String, Boolean> errors = new HashMap<String ,Boolean>();
		
		if(result == 1) {
			errors.put("checkDel", Boolean.TRUE);
		}else { 
			errors.put("checkDel", Boolean.FALSE);
		}
		
		String error = gson.toJson(errors);
		PrintWriter out = response.getWriter();
		out.write(error);
		
		
	}
}
