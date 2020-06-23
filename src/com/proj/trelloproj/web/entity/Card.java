package com.proj.trelloproj.web.entity;

import java.util.Date;

public class Card {

	private int id;
	private int listId;
	private String content;
	private int order;
	private Date regDate;
	private int createrId;
	private int archive;
	private int check;
	
	public Card() {

	}

	public Card(int id, int listId, String content, int order, Date regDate, int createrId, int archive,
			int check) {
		super();
		this.id = id;
		this.listId = listId;
		this.content = content;
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

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int isArchive() {
		return archive;
	}

	public void setArchive(int archive) {
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
		return "Card [id=" + id + ", listId=" + listId + ", content=" + content + ", order=" + order + ", regDate="
				+ regDate + ", createrId=" + createrId + ", archive=" + archive + ", check=" + check + "]";
	}

}
