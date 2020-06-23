package com.proj.trelloproj.web.entity;

import java.util.Date;

public class List {

	private int id;
	private int boardId;
	private String name;
	private int order;
	private Date regDate;
	private int createrId;
	private boolean archive;
	private int check;
	
	public List() {

	}

	public List(int id, int boardId, String name, int order, Date regDate, int createrId, boolean archive, int check) {
		super();
		this.id = id;
		this.boardId = boardId;
		this.name = name;
		this.order = order;
		this.regDate = regDate;
		this.createrId = createrId;
		this.archive = archive;
		this.check = check;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
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

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "List [id=" + id + ", boardId=" + boardId + ", name=" + name + ", order=" + order + ", regDate="
				+ regDate + ", createrId=" + createrId + ", archive=" + archive + ", check=" + check + "]";
	}
	
}
