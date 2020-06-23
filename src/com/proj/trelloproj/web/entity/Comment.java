package com.proj.trelloproj.web.entity;

import java.util.Date;

public class Comment {

	private int id;
	private int cardId;
	private String content;
	private Date regDate;
	private int writerId;
	
	public Comment() {

	}

	public Comment(int id, int cardId, String content, Date regDate, int writerId) {
		super();
		this.id = id;
		this.cardId = cardId;
		this.content = content;
		this.regDate = regDate;
		this.writerId = writerId;
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

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", cardId=" + cardId + ", content=" + content + ", regDate=" + regDate
				+ ", writerId=" + writerId + "]";
	}
	
	
}
