package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proj.trelloproj.web.entity.Invitation;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.entity.Team;



public class TrelloInvitingService {
	
	
	



	public Map<String, Object> getMembersInvited(int currentTeamId, int userId) {
		
		List<Member> list = new ArrayList<Member>();
		
		Map<String, Object> map = new HashMap<String,Object>();
		String sql = "select M.* " + 
				"from Member M join Invitation Iv " + 
				"on M.id = Iv.inviteeId " + 
				"join Team T " + 
				"on Iv.teamId = T.id " + 
				"where Iv.inviterId = ? " + 
				"and Iv.teamId = ? " + 
				"and Iv.acceptDate is null ";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {

		st.setInt(1, userId);
		st.setInt(2, currentTeamId);
		
			try (ResultSet rs = st.executeQuery();) {
				while (rs.next()) {
					Member member = new Member(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("pwd"),
							rs.getString("name"),
							rs.getString("nickname"),
							rs.getString("email"),
							toDate(rs.getTimestamp("regDate")));
					
					list.add(member);
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		map.put("member",list);
		return map;
	}

	public Date toDate(Timestamp date) {
		return new Date(date.getTime());
	}

	public Map<String, Object> getTeamInf(int currentTeamId, int userId,  Map<String, Object> map) {
		
		List<Team> list = new ArrayList<Team>();
		String sql = "select T.* " + 
				"from Member M join Invitation Iv " + 
				"on M.id = Iv.inviteeId " + 
				"join Team T " + 
				"on Iv.teamId = T.id " + 
				"where Iv.inviterId = ? " + 
				"and Iv.teamId = ? " + 
				"and Iv.acceptDate is null ";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {

		st.setInt(1, userId);
		st.setInt(2, currentTeamId);
		
			try (ResultSet rs = st.executeQuery();) {
				while (rs.next()) {
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
		map.put("team", list);
		return map;
	}

	public Map<String, Object> getInvitation(int currentTeamId, int userId, Map<String, Object> map) {
		List<Invitation> list = new ArrayList<Invitation>();
		String sql = "select Iv.* " + 
				"from Member M join Invitation Iv " + 
				"on M.id = Iv.inviteeId " + 
				"join Team T " + 
				"on Iv.teamId = T.id " + 
				"where Iv.inviterId = ? " + 
				"and Iv.teamId = ? " + 
				"and Iv.acceptDate is null ";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {

		st.setInt(1, userId);
		st.setInt(2, currentTeamId);

			try (ResultSet rs = st.executeQuery();) {
				while (rs.next()) {
					Invitation invitation = new Invitation(
							rs.getInt("id"),
							rs.getInt("inviterId"),
							rs.getInt("inviteeId"),
							rs.getInt("teamId"),
							toDate(rs.getTimestamp("invDate")),
							null
						);
					
					list.add(invitation);
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		map.put("invitation", list);
		return map;
	}
}
