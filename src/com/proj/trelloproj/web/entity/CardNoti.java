package com.proj.trelloproj.web.entity;

import java.util.Date;

public class CardNoti {

	private int id;
	private int cardId;
	private Date regDate;
	private int memberId;
	private boolean watch;
	
	public CardNoti() {

	}

	public CardNoti(int id, int cardId, Date regDate, int memberId, boolean watch) {
		super();
		this.id = id;
		this.cardId = cardId;
		this.regDate = regDate;
		this.memberId = memberId;
		this.watch = watch;
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public boolean getWatch() {
		return watch;
	}

	public void setWatch(boolean watch) {
		this.watch = watch;
	}

	@Override
	public String toString() {
		return "CardNoti [id=" + id + ", cardId=" + cardId + ", regDate=" + regDate + ", memberId=" + memberId
				+ ", watch=" + watch + "]";
	}

}
