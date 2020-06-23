package com.proj.trelloproj.web.entity;

import java.util.Date;

public class Team {

	private int id;
	private String name;
	private Date regDate;
	private int createrId;
	
	
	public Team() {
	
	}
	
	
	public Team(int id, String name, Date regDate, int createrId) {
		super();
		this.id = id;
		this.name = name;
		this.regDate = regDate;
		this.createrId = createrId;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getCreaterId() {
		return createrId;
	}

	public void setCreaterId(int createrId) {
		this.createrId = createrId;
	}


	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", regDate=" + regDate + ", createrId=" + createrId + "]";
	}

}
