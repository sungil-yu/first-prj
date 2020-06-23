package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proj.trelloproj.web.entity.Board;


public class BoardService {

	public Board insertBoard(Board board) throws ClassNotFoundException, SQLException {
		int result = 0;
		Board b = null;
		
		String sql = "INSERT INTO Board(teamId, name, createrId) VALUES(?,?,?)";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, board.getTeamId());
		st.setString(2, board.getName());
		st.setInt(3, board.getCreaterId());
		result = st.executeUpdate();
		
		String sql1 = "SELECT * FROM Board WHERE teamId=? ORDER BY regDate DESC;";
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setInt(1, board.getTeamId());
		ResultSet rs = st1.executeQuery();
		
		if(rs.next()) {
			
			b = new Board(
						rs.getInt("id"),
						rs.getInt("teamId"),
						rs.getString("name"),
						rs.getDate("regDate"),
						rs.getInt("createrId")
					);
			
		}
		
		st.close();
		rs.close();
		st1.close();
		con.close();
		
		return b;
	}

	public List<Board> getBoardList(int id) throws SQLException, ClassNotFoundException {
		List<Board> boards = new ArrayList<>();
		
		String sql = "select * from Board where teamId IN (select teamId from Invitation where inviteeId = ? and acceptDate is not null) order by regDate desc;";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql); 
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			
			Board board = new Board(
						rs.getInt("id"),
						rs.getInt("teamId"),
						rs.getString("name"),
						rs.getDate("regDate"),
						rs.getInt("createrId")
					);
			
			boards.add(board);
		}
		
		rs.close();
		st.close();
		con.close();
		
		return boards;
	}

	public Board getBoard(int boardId) throws ClassNotFoundException, SQLException {
		Board board = null;
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "select * from Board where id = ? ;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, boardId);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			board = new Board();
			board.setName(rs.getString("name"));
			board.setId(rs.getInt("id"));
		}
		
		rs.close();
		st.close();
		con.close();
		
		return board;
	}

}
