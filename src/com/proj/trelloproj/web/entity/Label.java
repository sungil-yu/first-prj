package com.proj.trelloproj.web.entity;

import java.util.Date;

public class Label {

	private int id;
	private int cardId;
	private String color;
	private String content;
	private Date regDate;
	private int createrId;
	
	public Label() {

	}

	public Label(int id, int cardId, String color, String content, Date regDate, int createrId) {
		super();
		this.id = id;
		this.cardId = cardId;
		this.color = color;
		this.content = content;
		this.regDate = regDate;
		this.createrId = createrId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "Label [id=" + id + ", cardId=" + cardId + ", color=" + color + ", content=" + content + ", regDate="
				+ regDate + ", createrId=" + createrId + "]";
	}
	
}
