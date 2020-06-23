package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.proj.trelloproj.web.entity.Board;
import com.proj.trelloproj.web.entity.Member;


public class TeamBoardService {

	public List<Board> teamBoardList(int currentTeamId) {
		
		String sql = 	"select B.* from Board B join\r\n" + 
						"Member M on B.createrId = M.id\r\n" + 
						"where B.teamId = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		List<Board> list = new ArrayList<Board>();
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql)) {

				st.setInt(1, currentTeamId);
				
			try (ResultSet rs = st.executeQuery();) {

				while(rs.next()) {
					Board board = new Board(
							rs.getInt("id"),
							rs.getInt("teamId"),
							rs.getString("name"),
							toDate(rs.getTimestamp("regDate")),
							rs.getInt("createrId")
							);
							
					list.add(board);
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return list;

	}

	public List<Member> teamBoardMaker(int currentTeamId) {
		
		String sql =	"select M.* from Board B join\r\n" + 
						"Member M on B.createrId = M.id\r\n" + 
						"where B.teamId = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		List<Member> list = new ArrayList<Member>();
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql)) {

				st.setInt(1, currentTeamId);
				
			try (ResultSet rs = st.executeQuery();) {

				while(rs.next()) {
					Member member = new Member(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("pwd"),
							rs.getString("name"),
							rs.getString("nickname"),
							rs.getString("email"),
							toDate(rs.getTimestamp("regDate"))
							);
							
					list.add(member);
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
}
