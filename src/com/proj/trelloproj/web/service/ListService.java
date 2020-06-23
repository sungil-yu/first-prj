package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.proj.trelloproj.web.entity.List;

public class ListService {

	public java.util.List<List> getLists(int boardId) throws ClassNotFoundException, SQLException {
		java.util.List<List> lists = new ArrayList<List>();
		
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "select * from List where boardId = ? ORDER BY `order`;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, boardId);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			List l = new List(
					rs.getInt("id"),
					rs.getInt("boardId"),
					rs.getString("name"),
					rs.getInt("order"),
					rs.getDate("regDate"),
					rs.getInt("createrId"),
					rs.getBoolean("archive"),
					rs.getInt("check")
					);
			
			lists.add(l);
		}
		
		rs.close();
		st.close();
		con.close();
		
		return lists;
	}

	public List insertList(List list) throws ClassNotFoundException, SQLException {
		List resultList = null;
		
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql1 = "INSERT INTO List(name, createrID, boardId,`order`) VALUES(?,?,?,?);";
		String sql2 = "SELECT * FROM List WHERE createrId = ? ORDER BY REGDATE DESC LIMIT 1;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setString(1, list.getName());
		st1.setInt(2, list.getCreaterId());
		st1.setInt(3, list.getBoardId());
		st1.setInt(4, list.getOrder());
		st1.executeUpdate();
		PreparedStatement st2 = con.prepareStatement(sql2);
		st2.setInt(1, list.getCreaterId());
		ResultSet rs = st2.executeQuery();
		if(rs.next()) {
			resultList = new List(
					rs.getInt("id"),
					rs.getInt("boardId"),
					rs.getString("name"),
					rs.getInt("order"),
					rs.getDate("regDate"),
					rs.getInt("createrId"),
					rs.getBoolean("archive"),
					rs.getInt("check")
					);
		}
		
		rs.close();
		st1.close();
		st2.close();
		con.close();
		
		return resultList;
	}

	public int getlastOrder(int boardId) throws ClassNotFoundException, SQLException {
		int lastOrder = 0;
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "select `order` from List where Boardid = ? order by `order` desc LIMIT 1;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, boardId);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			lastOrder = rs.getInt("order");
		}
		
		rs.close();
		st.close();
		con.close();
		
		return lastOrder;
	}

	public void deleteList(int listId) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "delete from List where id=? ;";
		String sql1 = "delete from Card where listId = ?;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, listId);
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setInt(1, listId);
		st.executeUpdate();
		st1.executeUpdate();
		
		st1.close();
		st.close();
		con.close();
	}

	public void updateList(String nlt, int id) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "update List set name=? where id=?;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, nlt);
		st.setInt(2, id);
		st.executeUpdate();
		
		st.close();
		con.close();
	}

	public void setCheck(int listId, int listCheck) throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "update List set `check`=? where id=?;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, listCheck);
		st.setInt(2, listId);
		st.executeUpdate();
		
		st.close();
		con.close();
	}

}
