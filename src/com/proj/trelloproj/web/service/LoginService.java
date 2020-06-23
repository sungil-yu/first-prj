package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.proj.trelloproj.web.entity.Member;


public class LoginService {

	public Member successLogin(String inputId, String inputPwd) {
		
		String sql = "SELECT * FROM Member where uid = ? and pwd = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		Member member = null;
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {
			
			st.setString(1,inputId);
			st.setString(2,inputPwd);
			try (ResultSet rs = st.executeQuery();) {
				
				if(rs.next()) {
					 member = new Member(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("pwd"),
							rs.getString("name"),
							rs.getString("nickname"),
							rs.getString("email"),
							toDate(rs.getTimestamp("regDate"))
							);
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		
		return member;
	}
	public Date toDate(Timestamp date) {
		return new Date(date.getTime());
	}
}
