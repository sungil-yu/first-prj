package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proj.trelloproj.web.entity.Member;

public class MemberService {

	public int isOverrapByUid(String uid) throws ClassNotFoundException, SQLException {
		int isOverrap = 0;
		
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "SELECT * FROM Member WHERE uid = ? ;";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, uid);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			isOverrap = 1;
		}
		
		return isOverrap;
	}

	public int isOverrapByNick(String nick) throws ClassNotFoundException, SQLException {
		int isOverrap = 0;
		
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "SELECT * FROM Member WHERE nickname = ? ;";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, nick);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			isOverrap = 1;
		}
		
		return isOverrap;
	}
	
	public int isOverrapByEmail(String email) throws ClassNotFoundException, SQLException {
		int isOverrap = 0;
		
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "SELECT * FROM Member WHERE email = ? ;";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			isOverrap = 1;
		}
		
		return isOverrap;
	}
	
	public int insertMember(Member member) throws ClassNotFoundException, SQLException {
		int userId = 0;
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "insert into Member (uid,pwd,name,nickname,email) values (?,?,?,?,?);";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, member.getUid());
		st.setString(2, member.getPwd());
		st.setString(3, member.getName());
		st.setString(4, member.getNickname());
		st.setString(5, member.getEmail());
		
		st.executeUpdate();
		
		String sql1 = "select id from Member where uid = ?";
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setString(1,member.getUid());
		ResultSet rs = st1.executeQuery();
		if(rs.next()) {
			userId = rs.getInt("id");
		}
		
		rs.close();
		st1.close();
		st.close();
		con.close();
		
		return userId;
	}

	

}
