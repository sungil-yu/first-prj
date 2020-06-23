package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.entity.Team;

public class TeamTeamsService {

	
	public List<Team> getTeams(Member user) {
		
		String sql ="select T.* from Invitation Iv join\r\n" + 
					"Team T on Iv.teamId = T.id\r\n" + 
					"where  Iv.inviteeId = ? and Iv.acceptDate is not null";
		
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		List<Team> list = new ArrayList<Team>();
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql)) {

				st.setInt(1, user.getId());
				
			try (ResultSet rs = st.executeQuery();) {

				while(rs.next()) {
					Team team = new Team(
							rs.getInt("id"),
							rs.getString("name"),
							toDate(rs.getTimestamp("regDate")),
							rs.getInt("createrId")
							);
							
					list.add(team);
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return list;

	}

	
	public Date toDate(Timestamp date) {
		return new Date(date.getTime());
	}


	public int outTeam(int teamId, Member user) {
		int result = 0;
		
		String sql ="delete from Invitation where teamId = ? and inviteeId = ? ";
	
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql)) {

				st.setInt(1, teamId);
				st.setInt(2, user.getId());
				
				result = st.executeUpdate();
			
	
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		
		return result;
	}


	

	public int removeTeam(int teamId) {
		
	int result = 0;
		
		String sql ="delete from Team where id = ? ";
	
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql)) {

				st.setInt(1, teamId);
				
				result = st.executeUpdate();
			
	
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		
		return result;
	}


	public int removeBoard(int teamId) {
			int result = 0;
		
		String sql ="delete from Board where teamId = ? ";
	
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql)) {

				st.setInt(1, teamId);
				
				result = st.executeUpdate();
			
	
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		
		return result;
	}
}

