package com.proj.trelloproj.web.entity;

import java.util.Date;

public class Member {
	
	private int id;
	private String uid;
	private String pwd;
	private String name;
	private String nickname;
	private String email;
	private Date regDate;
	
	public Member() {
	}
	
	public Member(int id, String uid, String pwd, String name, String nickname, String email, Date regDate) {
		super();
		this.id = id;
		this.uid = uid;
		this.pwd = pwd;
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.regDate = regDate;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", uid=" + uid + ", pwd=" + pwd + ", name=" + name + ", nickname=" + nickname
				+ ", email=" + email + ", regDate=" + regDate + "]";
	}
	
}
