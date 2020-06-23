package com.proj.trelloproj.web.entity;

import java.util.Date;

public class Description {

	private int id;
	private String content;
	private int cardId;
	private Date regDate;
	private int writerId;
	
	public Description() {
	
	}

	public Description(int id, String content, int cardId, Date regDate, int writerId) {
		super();
		this.id = id;
		this.content = content;
		this.cardId = cardId;
		this.regDate = regDate;
		this.writerId = writerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	@Override
	public String toString() {
		return "Description [id=" + id + ", content=" + content + ", cardId=" + cardId + ", regDate=" + regDate
				+ ", writerId=" + writerId + "]";
	}
	

}
