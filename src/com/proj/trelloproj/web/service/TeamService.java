package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.entity.Team;


public class TeamService {

	public Team insertTeam(Team team) throws ClassNotFoundException, SQLException {
		
		int result = 0;
		Team t = null;
		
		Member member = new Member();
		
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "INSERT INTO Team(name, createrId) VALUES(?,?)";
		String sql1 = "SELECT * from Team where createrId = ? order by regdate desc limit 1";
		String sql2 = "INSERT INTO Invitation(inviterId,inviteeId,teamId,acceptdate) VALUES(?,?,?,CURRENT_TIMESTAMP)";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, team.getName());
		st.setInt(2, team.getCreaterId());
		result = st.executeUpdate();
		
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setInt(1, team.getCreaterId());
		ResultSet rs = st1.executeQuery();
		
		if(rs.next()) {
			t = new Team(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getDate("regDate"),
					rs.getInt("createrId")
					);
			PreparedStatement st2 = con.prepareStatement(sql2);
			st2.setInt(1, t.getCreaterId());
			st2.setInt(2, t.getCreaterId());
			st2.setInt(3, t.getId());
			st2.executeUpdate();
		}
		
		rs.close();
		st1.close();
		st.close();
		con.close();
		
		return t;
	}

	public List<Team> getTeamList(int id) throws ClassNotFoundException, SQLException {
		List<Team> teams = new ArrayList<>();
		
		String sql = "SELECT t.* FROM Invitation i join Team t on i.teamId = t.id where i.inviteeId = ? and acceptDate is not null order by regDate DESC;";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			
			Team team = new Team(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getDate("regDate"),
						rs.getInt("createrId")
					);
			teams.add(team);
		}
		
		rs.close();
		st.close();
		con.close();
		
		return teams;
		
	}


}
