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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.proj.trelloproj.web.entity.Invitation;
import com.proj.trelloproj.web.entity.Member;
import com.proj.trelloproj.web.entity.MemberDetail;
import com.proj.trelloproj.web.entity.Team;


public class TrelloInviteService {

	public List<Member> getTeamMember(int currentTeamId) {

		String sql = " select M.*,Iv.teamId from Invitation Iv join Member M on Iv.inviteeId = M.id where Iv.teamId = ? and Iv.acceptDate IS NOT NULL";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";


		List<Member> list = new ArrayList<Member>();
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, currentTeamId);
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
		
		return list;
	}

	public List<Member> autoSearch(String option, String input, Map<String, Object> map) {
		
		String sql = "SELECT M.* FROM MemberDetail MD join Member M on MD.memberId = M.id WHERE " + option + " LIKE ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		List<Member> list = new ArrayList<Member>();
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, input + "%");
			try (ResultSet rs = st.executeQuery();) {

				while (rs.next()) {
					Member member = new Member(rs.getInt("id"), rs.getString("uid"), rs.getString("pwd"),
							rs.getString("name"), rs.getString("nickname"), rs.getString("email"),
							toDate(rs.getTimestamp("regDate")));
					list.add(member);
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		map.put("members", list);
		return list;
	}

	public Date toDate(Timestamp date) {
		return new Date(date.getTime());
	}

	public Member checkMember(Member invitedMember) {

		String sql = "SELECT * FROM Member WHERE uid = ? ";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		List<Member> list = new ArrayList<Member>();
		Member member = null;

		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, invitedMember.getUid());
			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					member = new Member(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("pwd"),
							rs.getString("name"),
							rs.getString("nickname"), 
							rs.getString("email"),
							toDate(rs.getTimestamp("regDate")));

				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		return member;
	}

	public int invitingMember(Member invitee, Member inviter, int currentTeamId) throws SQLException {

		int result = 0;

		// 筌띾슣鍮� 占쎌뵠沃섓옙 �룯�뜄占쏙옙留� 占쎌돳占쎌뜚占쎌뵠占쎌뵬筌롳옙
		String sql = "select * from Invitation where teamId = ? and inviteeId = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {


			st.setInt(1, currentTeamId);
			st.setInt(2, invitee.getId());

			ResultSet rs = st.executeQuery();
			//占쎌뵠占쎌쟽野껓옙 餓λ쵐爰쏙옙釉�筌욑옙筌띾Þ�� member揶쏆빘猿쒙옙�벥 占쎌��눧��以� 占쎈솇占쎈뼊占쎈퉸 �룯�뜄占쏙옙釉�占쎈뮉野껉퍔�뵠 �넫�뿫�뼄. 占쎌뵬占쎈뼊占쎌뵠占쎌쟽野껓옙
			if (!rs.next()) {
				
				String sql1 = "insert into Invitation(inviterId,inviteeId,teamId) value(?,?,?)";
				String url1 = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";

				try (
						Connection con1 = DriverManager.getConnection(url1, "post", "123123");
						PreparedStatement st1 = con1.prepareStatement(sql1);) 
				{

					st1.setInt(1, inviter.getId());
					st1.setInt(2, invitee.getId());
					st1.setInt(3, currentTeamId);

					result = st1.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("�룯�뜄占� 占쎌궎�몴占�");
				}
				
			}

		}catch (Exception e) {
			System.out.println("JDBC 占쎈염野껉퀣�뼄占쎈솭");
		}

		return result;
	}

	
	public int delTeamMember(Member member) {
		
		String sql = "delete from Invitation where inviteeId = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		int result = 0;

		try (
				Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) 
		{

			st.setInt(1, member.getId());

			 result = st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("�빊遺얘컩 占쎌궎�몴占�");
		}
		
		return result;
	}

	public int cancelInvite(int inviteeId, int teamId, int memberId) {
		
		
		String sql = "delete from Invitation where teamId = ? and inviteeId = ? and inviterId = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		int result = 0;

		try (
				Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) 
		{

			st.setInt(1,teamId);
			st.setInt(2,inviteeId);
			st.setInt(3,memberId);
			result = st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("�빊遺얘컩 占쎌궎�몴占�");
		}
		
		return result;
	}

	public Map<String, Object> invitationMember(Member invitee) {
		
		String sql = "select M.* from " + 
				"Invitation Iv join Member M " + 
				"on Iv.inviterId = M.id " + 
				"join Team T on Iv.teamId = T.id " + 
				"where Iv.inviteeId = ? and " + 
				"Iv.acceptDate is null;";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		Member member = null;
		 Map<String, Object> map = new HashMap<String, Object>();
		List<Member> list = new ArrayList<Member>();
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {
			
			st.setInt(1, invitee.getId());
			
			try (ResultSet rs = st.executeQuery();) {

				while (rs.next()) {
				 member = new Member(
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
			
		map.put("member", list);
		
		return map;
	}

	public Map<String, Object> invitationTeam(Member invitee, Map<String, Object> map) {
		String sql = "select T.* from " + 
				"Invitation Iv join Member M " + 
				"on Iv.inviterId = M.id " + 
				"join Team T on Iv.teamId = T.id " + 
				"where Iv.inviteeId = ? and " + 
				"Iv.acceptDate is null;";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		
		Team team = null;
		List<Team> list = new ArrayList<Team>();
		
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {
			
			st.setInt(1, invitee.getId());
			
			try (ResultSet rs = st.executeQuery();) {

				while (rs.next()) {
				 team = new Team(
							rs.getInt("id"),
							rs.getString("name"),
							toDate(rs.getTimestamp("regDate")),
							rs.getInt("createrId"));
				list.add(team);
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
			
		map.put("team", list);
		
		return map;
	}

	public Map<String, Object> invitationIv(Member invitee, Map<String, Object> map) {
		String sql = "select Iv.* from " + 
				"Invitation Iv join Member M " + 
				"on Iv.inviterId = M.id " + 
				"join Team T on Iv.teamId = T.id " + 
				"where Iv.inviteeId = ? and " + 
				"Iv.acceptDate is null;";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		
		Invitation iv = null;
		
		List<Invitation> list = new ArrayList<Invitation>();
		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {
			
			st.setInt(1, invitee.getId());
			
			try (ResultSet rs = st.executeQuery();) {

				while (rs.next()) {
					iv = new Invitation(
							rs.getInt("id"),
							rs.getInt("inviterId"),
							rs.getInt("inviteeId"),
							rs.getInt("teamId"),
							toDate(rs.getTimestamp("invDate")),
							null);
				list.add(iv);
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
			
		map.put("invitation", list);
		
		return map;
	}

	public int acceptTeam(int teamId, int inviteeId) {
		
		String sql = "update Invitation set acceptDate = CURRENT_TIMESTAMP where teamId = ? and inviteeId = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		int result = 0;

		try (
				Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) 
		{

			st.setInt(1,teamId);
			st.setInt(2,inviteeId);
			result = st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("sqlException"); 
		}
		
		return result;
	}

	public int refuseTeam(int teamId, int inviteeId) {
		
		String sql = "delete from Invitation where teamId = ? and inviteeId = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		int result = 0;

		try (
				Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) 
		{

			st.setInt(1,teamId);
			st.setInt(2,inviteeId);
			result = st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("sqlException"); 
		}
		
		return result;
	}

	public Team currentTeam(int currentTeamId) {
		
		String sql = "select * from Team where id = ? ";
			
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		
		Team team = null;
		
		try (
				Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {
			
			st.setInt(1, currentTeamId);
			
			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
				 team = new Team(
							rs.getInt("id"),
							rs.getString("name"),
							toDate(rs.getTimestamp("regDate")),
							rs.getInt("createrId"));
				}
			}
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
			
		
		return team;
	}

	public Member getLeader(int currentTeamId) {
		
		
		
		String sql = "select M.* FROM Member M join Team T on M.id = T.createrId where T.id = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		
		Member member = null;

		try (Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);) {
			
			st.setInt(1, currentTeamId);
			try (ResultSet rs = st.executeQuery();) {

				if (rs.next()) {
					member = new Member(
							rs.getInt("id"),
							rs.getString("uid"),
							rs.getString("pwd"),
							rs.getString("name"),
							rs.getString("nickname"), 
							rs.getString("email"),
							toDate(rs.getTimestamp("regDate")));

				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		return member;
	}

	public void autoDetailSearch(String option, String input, Map<String, Object> map, List<Member> memberList) {
		List<MemberDetail> list = new ArrayList<MemberDetail>();
		
		
		for(Member member : memberList) {
			
		String sql = "SELECT MD.* FROM MemberDetail MD join Member M on MD.memberId = M.id WHERE M.id = ?";
		String url = "jdbc:mysql://dev.notepubs.com:9898/post?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
		MemberDetail md = null;
		try (
				Connection con = DriverManager.getConnection(url, "post", "123123");
				PreparedStatement st = con.prepareStatement(sql);){
		
			st.setInt(1, member.getId());
			
		try(ResultSet rs = st.executeQuery();){
			
		
	

			while (rs.next()) {
				md = new MemberDetail(
						rs.getInt("id"),
						rs.getInt("memberId"),
						rs.getInt("age"),
						rs.getString("gender"),
						rs.getString("birthday"),
						rs.getString("profilePicture")
						);
				list.add(md);

			}
			
			
		} catch (Exception e) {
			System.out.println("sqlException"); 
		}
			
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
		map.put("Md", list);
	}
}


