package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.proj.trelloproj.web.entity.MemberDetail;

public class MemberDetailService {

	public void insertDetail(int userId, String gender, String birthday) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "insert into MemberDetail (memberId,gender,birthday) values (?,?,?);";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, userId);
		st.setString(2, gender);
		st.setString(3, birthday);
		st.executeUpdate();
		
		st.close();
		con.close();
	}

	public int setDetail(int userId, String fileName) {
		String sql = "update MemberDetail set memberId = ?, profilePicture = ? where memberId = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	
		int result = 0;
		
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {

			
			st.setInt(1, userId);
			st.setString(2, fileName);
			st.setInt(3, userId);
			
			
			result = st.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		return result;
	}



	public MemberDetail getDetail(int userId) {
		
		String sql = "select * from MemberDetail where memberId = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		
		MemberDetail memberDetail = null;

		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {
			
			st.setInt(1, userId);
			
			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					memberDetail = new MemberDetail(
							rs.getInt("id"),
							rs.getInt("memberId"),
							rs.getInt("age"), 
							rs.getString("gender"),
							rs.getString("birthday"),
							rs.getString("profilePicture"));
					
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		return memberDetail;
	}
}
