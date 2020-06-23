package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proj.trelloproj.web.entity.Invitation;
import com.proj.trelloproj.web.entity.InvitationView;
import com.proj.trelloproj.web.entity.Team;

public class InvitationService {

	public List<InvitationView> getNoticeList(int id) throws ClassNotFoundException, SQLException {
		List<InvitationView> invis = new ArrayList<>();
		
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "select t.name 'teamName',i.* from Invitation i join Team t on i.teamId = t.id where inviteeId = ? and acceptDate is null;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			InvitationView temp = new InvitationView(
					rs.getInt("id"),
					rs.getInt("inviterId"),
					rs.getInt("inviteeId"),
					rs.getInt("teamId"),
					rs.getDate("invDate"),
					rs.getDate("acceptDate"),
					rs.getString("teamName")
					);
			invis.add(temp);
		}
		
		rs.close();
		st.close();
		con.close();
		
		return invis;
	}

	public void accept(int id) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "update Invitation set acceptDate = CURRENT_TIMESTAMP where id = ?";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		st.executeUpdate();
		
		st.close();
		con.close();
		
	}

	public int reject(int id) throws ClassNotFoundException, SQLException {
		int res = 0;
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "delete from Invitation where id = ?";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		res = st.executeUpdate();
		
		st.close();
		con.close();
		
		return res;
	}
}
