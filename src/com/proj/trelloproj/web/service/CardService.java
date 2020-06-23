package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proj.trelloproj.web.entity.Card;

public class CardService {

	public List<Card> getCards(int boardId) throws ClassNotFoundException, SQLException {
		List<Card> cards = new ArrayList<Card>();
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "select c.* from Card c LEFT JOIN List l on c.listId = l.id where l.boardId = ? order by l.order,c.order;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, boardId);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			Card c = new Card(
					rs.getInt("id"),
					rs.getInt("listId"),
					rs.getString("content"),
					rs.getInt("order"),
					rs.getDate("regDate"),
					rs.getInt("createrId"),
					rs.getInt("archive"),
					rs.getInt("check")
					);
			cards.add(c);
		}		
		
		rs.close();
		st.close();
		con.close();

		return cards;
	}

	public int getLastOrder(int listId) throws ClassNotFoundException, SQLException {
		int lastOrder = 0;
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "select `order` from Card where listId = ? order by `order` desc LIMIT 1;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, listId);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			lastOrder = rs.getInt("order");
		}
		
		return lastOrder;
	}

	public Card insertCard(Card card) throws ClassNotFoundException, SQLException {
		Card resultCard = null;
		
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql1 = "INSERT into Card(listId,content,createrId,`order`) values(?,?,?,?);";
		String sql2 = "SELECT * FROM Card WHERE listId = ? and createrId = ? ORDER BY REGDATE DESC LIMIT 1;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setInt(1, card.getListId());
		st1.setString(2, card.getContent());
		st1.setInt(3, card.getCreaterId());
		st1.setInt(4, card.getOrder());
		st1.executeUpdate();
		PreparedStatement st2 = con.prepareStatement(sql2);
		st2.setInt(1, card.getListId());
		st2.setInt(2, card.getCreaterId());
		ResultSet rs = st2.executeQuery();
		if(rs.next()) {
			resultCard = new Card(
					rs.getInt("id"),
					rs.getInt("listId"),
					rs.getString("content"),
					rs.getInt("order"),
					rs.getDate("regDate"),
					rs.getInt("createrId"),
					rs.getInt("archive"),
					rs.getInt("check")
					);
		}
		
		rs.close();
		st2.close();
		st1.close();
		con.close();
		
		return resultCard;
	}

	public void deleteCard(int id) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "delete from Card where id=? ;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		st.executeUpdate();
		
		st.close();
		con.close();
	}

	public void check(int cardId, boolean isCheck) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "";
		
		if(isCheck)
			sql = "UPDATE Card set `check`=1 where id = ? ;";
		else
			sql = "UPDATE Card set `check`=0 where id = ? ;";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cardId);
		st.executeUpdate();
		
		st.close();
		con.close();
	}

	public int getListId(int cardId) throws ClassNotFoundException, SQLException {
		int listId = 0;
		
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "select listId from Card where id = ?;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cardId);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			listId = rs.getInt("listId");
		}
		
		rs.close();
		st.close();
		con.close();
		
		return listId;
	}

	public int getListCheck(int listId) throws ClassNotFoundException, SQLException {
		int cardCmt = 0, chkCardsCmt =0;
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		String sql = "select count(*) count from Card where listId = ?;";
		String sql1 = "select count(*) count from Card where listId = ? and `check`=1;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, listId);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			cardCmt = rs.getInt("count");
		}
		
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setInt(1, listId);
		ResultSet rs1 = st1.executeQuery();
		if(rs1.next()) {
			chkCardsCmt = rs1.getInt("count");
		}
		
		int listCheck = (int) Math.round((double)chkCardsCmt/cardCmt * 100); 
		
		return listCheck;
	}
}
