package com.proj.trelloproj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proj.trelloproj.web.entity.Board;
import com.proj.trelloproj.web.entity.Card;
import com.proj.trelloproj.web.entity.Comment;
import com.proj.trelloproj.web.entity.Description;
import com.proj.trelloproj.web.entity.Member;


public class CardDetailService {

	public static String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	
	public Member getMemberById(int uid) throws ClassNotFoundException, SQLException {
//		二쇱뼱吏� 硫ㅻ쾭�쓽 id濡� 硫ㅻ쾭�쓽 �냽�꽦�쓣 遺덈윭�삤�뒗 荑쇰━
		
		Member member = null;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		
		String sql = "select * from Member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, uid);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			member = new Member(
					 rs.getInt("id"),
					 rs.getString("uid"),
					 rs.getString("pwd"),
					 rs.getString("name"),
					 rs.getString("nickname"),
					 rs.getString("email"),
					 rs.getDate("regDate"));
				}
		rs.close();
		st.close();
		con.close();
		
		return member;
		
		
	}
	
	
	public Card getCard(int cardId) throws ClassNotFoundException, SQLException {
		
		
//		二쇱뼱吏� 移대뱶id濡� 移대뱶�쓽 �냽�꽦�쓣 遺덈윭�삤�뒗 荑쇰━
		String sql = "select * from Card where id=?";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cardId);
		ResultSet rs = st.executeQuery(); 
		
		Card card = null;
//		荑쇰━寃곌낵臾몄쓣 card 媛앹껜�뿉 �떞湲�
		if(rs.next()) {
			 card = new Card(
					 rs.getInt("id"),
					 rs.getInt("listId"),
					 rs.getString("content"),
					 rs.getInt("order"),
					 rs.getDate("regDate"),
					 rs.getInt("createrId"),
					 rs.getInt("archive"),
					 rs.getInt("check"));
		}
		rs.close();
		st.close();
		con.close();
		
		return card;
		
	}
	
	public Board getBoard(int cardId) throws ClassNotFoundException, SQLException {
		Board board = null;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		
		String sql = "select b.* from Board b join List li  on b.id = li.boardId join Card cd on li.id = cd.listId where cd.id = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cardId);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			board = new Board(
					rs.getInt("id"),
					rs.getInt("teamId"),
					rs.getString("name"),
					rs.getDate("regDate"),
					rs.getInt("createrId"));
		}
		
		rs.close();
		st.close();
		con.close();
		
		return board;
	}
	
	
	public Description getDescription(int cardId) throws ClassNotFoundException, SQLException {

		
//		二쇱뼱吏� cardId濡� description�쓽 �냽�꽦�쓣 遺덈윭�삤�뒗 荑쇰━
		String sql = "select * from Description where cardId=?";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cardId);
		ResultSet rs = st.executeQuery(); 
		
		Description description = null;
//		荑쇰━寃곌낵臾몄쓣 card 媛앹껜�뿉 �떞湲�
		if(rs.next()) {
			description = new Description(
					 rs.getInt("id"),
					 rs.getString("content"),
					 rs.getInt("cardId"),
					 rs.getDate("regDate"),
					 rs.getInt("writerId"));
		}
		rs.close();
		st.close();
		con.close();
		
		
		return description;
	}

	
	
	public List<Comment> getCommentList(int cardId) throws ClassNotFoundException, SQLException {
		
//		二쇱뼱吏� cardId濡� Comment�쓽 �냽�꽦�쓣 遺덈윭�삤�뒗 荑쇰━
		String sql = "select cmt.* from Comment cmt join Card cd on cmt.cardId = cd.id where cd.id=? order by regDate desc";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cardId);
		ResultSet rs = st.executeQuery(); 
		
		List<Comment> commentList = new ArrayList<>();
		
		while(rs.next()) {
			 Comment comment = new Comment(
					 rs.getInt("id"),
					 rs.getInt("cardId"),
					 rs.getString("content"),
					 rs.getDate("regDate"),
					 rs.getInt("writerId"));
			 commentList.add(comment);
		}
		rs.close();
		st.close();
		con.close();
		
		return commentList;
	}

	public List<Member> getCommentMemberList(int cardId) throws ClassNotFoundException, SQLException {
		
//		二쇱뼱吏� cardId�쓽 Comment�쓽 Member �냽�꽦�쓣 遺덈윭�삤�뒗 荑쇰━
		String sql = "select m.* from Member m join Comment cmt on m.id = cmt.writerId join Card cd on cd.id = cmt.cardId"
				   + " where cd.id=? order by cmt.regDate desc";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cardId);
		ResultSet rs = st.executeQuery(); 
		
		List<Member> commentMemberList = new ArrayList<>();
		
		while(rs.next()) {
			Member member = new Member(
					 rs.getInt("id"),
					 rs.getString("uid"),
					 rs.getString("pwd"),
					 rs.getString("name"),
					 rs.getString("nickname"),
					 rs.getString("email"),
					 rs.getDate("regDate"));
			 commentMemberList.add(member);
		}
		rs.close();
		st.close();
		con.close();
		
		return commentMemberList;
	}

	public Card getCardupdated(int cardId, String name) throws ClassNotFoundException, SQLException {
		
//		二쇱뼱吏� cardId�� name�쑝濡�  Card�쓽 name�쓣 �뾽�뜲�씠�듃�븯�뒗 荑쇰━
		
		int result = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		
		String sql1 = "update Card set content=? where id=?";
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setString(1, name);
		st1.setInt(2, cardId);
		result = st1.executeUpdate();
	
		st1.close();
		
		Card card = null;
		
		if(result==1) {
			String sql2 = "select * from Card where id = ?";
			PreparedStatement st2 = con.prepareStatement(sql2);
			st2.setInt(1, cardId);
			ResultSet rs2 = st2.executeQuery();
			
			
			if(rs2.next()) {
				 card = new Card(
						 rs2.getInt("id"),
						 rs2.getInt("listId"),
						 rs2.getString("content"),
						 rs2.getInt("order"),
						 rs2.getDate("regDate"),
						 rs2.getInt("createrId"),
						 rs2.getInt("archive"),
						 rs2.getInt("check"));
				}
		rs2.close();
		st2.close();
		con.close();
		}
			
		return card;
			
	}

	public Description getDesUpdated(int cardId, String des) throws ClassNotFoundException, SQLException {

//		二쇱뼱吏� cardId�� des濡� Description�쓽 content瑜� �뾽�뜲�씠�듃�븯�뒗 荑쇰━
		
		int result = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		
		String sql1 = "update Description set content=? where cardId = ?";
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setString(1, des);
		st1.setInt(2, cardId);
		result = st1.executeUpdate();
	
		st1.close();
		
		Description des2 = null;
		
		if(result==1) {
			String sql2 = "select * from Description where cardId = ?";
			PreparedStatement st2 = con.prepareStatement(sql2);
			st2.setInt(1, cardId);
			ResultSet rs2 = st2.executeQuery();
			
			
			if(rs2.next()) {
				 des2 = new Description(
						 rs2.getInt("id"),
						 rs2.getString("content"),
						 rs2.getInt("cardId"),
						 rs2.getDate("regDate"),
						 rs2.getInt("writerId"));
						 
				}
		rs2.close();
		st2.close();
		con.close();
		}
			
		return des2;
		
	}

	public Comment getCmtInserted(int cardId, String cmt, int writerId) throws ClassNotFoundException, SQLException {
		
		
		int result = 0;
		
//		二쇱뼱吏� 臾몄옄�뿴濡� �뙎湲��쓽 �궡�슜�쓣 �엯�젰�븯怨� 
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		
		String sql1 = "insert into Comment(cardId,content,writerId) values(?,?,?)";
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setInt(1, cardId);
		st1.setString(2, cmt);
		st1.setInt(3, writerId);
		result = st1.executeUpdate();
	
		st1.close();
		
		
		Comment cmt2 = null;
		
//		�씤�꽌�듃媛� �꽦怨듯뻽�쑝硫� �궡媛� 異붽��븳 �뙎湲��쓣 遺덈윭�삤�뒗 荑쇰━
		if(result==1) {
			String sql2 = "select * from Comment where cardId=? and writerId=? order by regDate desc limit 1";
			PreparedStatement st2 = con.prepareStatement(sql2);
			st2.setInt(1, cardId);
			st2.setInt(2, writerId);
			ResultSet rs2 = st2.executeQuery(); 
			
			if(rs2.next()) {
				cmt2 = new Comment(
						rs2.getInt("id"),
						rs2.getInt("cardId"),
						rs2.getString("content"),
						rs2.getDate("regDate"),
						rs2.getInt("writerId"));
			
			}
			
			rs2.close();
			st2.close();
			con.close();
		}
		
		return cmt2;
		
		
	}
	
	public Comment getCmtUpdated(int cardId, String cmt, String cmt2, int writerId) throws ClassNotFoundException, SQLException {
		
		int result = 0;
		
//		二쇱뼱吏� 臾몄옄�뿴濡� �뙎湲��쓽 �궡�슜�쓣 �뾽�뜲�씠�듃�븯怨�
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		
		String sql1 = "update Comment set content=?,regDate=CURRENT_TIMESTAMP where cardId = ? and writerId = ? and content=?";
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setString(1, cmt2);
		st1.setInt(2, cardId);
		st1.setInt(3, writerId);
		st1.setString(4, cmt);
		result = st1.executeUpdate();
	
		st1.close();
		
		
		Comment comment = null;
		
//		�씤�꽌�듃媛� �꽦怨듯뻽�쑝硫� �궡媛� �뾽�뜲�씠�듃�븳 �뙎湲��쓣 遺덈윭�삤�뒗 荑쇰━
		if(result==1) {
			String sql2 = "select * from Comment where cardId=? and writerId=? order by regDate desc limit 1";
			PreparedStatement st2 = con.prepareStatement(sql2);
			st2.setInt(1, cardId);
			st2.setInt(2, writerId);
			ResultSet rs2 = st2.executeQuery(); 
			
			if(rs2.next()) {
				comment = new Comment(
						rs2.getInt("id"),
						rs2.getInt("cardId"),
						rs2.getString("content"),
						rs2.getDate("regDate"),
						rs2.getInt("writerId"));
			
			}
			
			rs2.close();
			st2.close();
			con.close();
		}
		
		return comment;
	}

	public Member getMemberOnCmt(int cardId, int writerId) throws ClassNotFoundException, SQLException {
		Member member = null;
		
//		�뙎湲��쓣 留뚮뱾怨� �궃 �썑�쓽 硫ㅻ쾭�쓽 �젙蹂대�� �뼸�뼱�삤�뒗 荑쇰━
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		
		String sql = "select m.* from Member m join Comment cmt on m.id = cmt.writerId "
				+ "where cmt.cardId = ? and m.id = ? order by cmt.regDate desc limit 1";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cardId);
		st.setInt(2, writerId);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			member = new Member(
					 rs.getInt("id"),
					 rs.getString("uid"),
					 rs.getString("pwd"),
					 rs.getString("name"),
					 rs.getString("nickname"),
					 rs.getString("email"),
					 rs.getDate("regDate"));
				}
		rs.close();
		st.close();
		con.close();
		
		return member;
		}

	public Description getDesInserted(int cardId, String des, int writerId) throws ClassNotFoundException, SQLException {

//		二쇱뼱吏� cardId�� des濡� Description�쓣 insert�븯�뒗 荑쇰━
		
		int result = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		
		String sql1 = "insert into Description(content,cardId,writerId) values(?,?,?)";
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setString(1, des);
		st1.setInt(2, cardId);
		st1.setInt(3, writerId);
		result = st1.executeUpdate();
		st1.close();
		
		Description des2 = null;
		
		if(result==1) {
			String sql2 = "select * from Description where cardId = ?";
			PreparedStatement st2 = con.prepareStatement(sql2);
			st2.setInt(1, cardId);
			ResultSet rs2 = st2.executeQuery();
			
			
			if(rs2.next()) {
				 des2 = new Description(
						 rs2.getInt("id"),
						 rs2.getString("content"),
						 rs2.getInt("cardId"),
						 rs2.getDate("regDate"),
						 rs2.getInt("writerId"));
						 
				}
		rs2.close();
		st2.close();
		con.close();
		}
			
		return des2;
		
	}

	public int getResultFromDeletion(int cardId, int writerId, String content) throws ClassNotFoundException, SQLException {
		int result = -1;
		
		System.out.println(content);
		System.out.println(cardId)	;
		System.out.println(writerId);
//		二쇱뼱吏� cardId, writerId, content濡� 寃��깋�븳 comment瑜� 吏��슦�뒗 荑쇰━
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "post", "123123");
		
		String sql1 = "delete from Comment where cardId= ? and writerId = ? and content = ? order by regDate desc limit 1";
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setInt(1, cardId);
		st1.setInt(2, writerId);
		st1.setString(3, content);
		result = st1.executeUpdate();
		
		st1.close();
		con.close();
		
		return result;
	}

	


}
	