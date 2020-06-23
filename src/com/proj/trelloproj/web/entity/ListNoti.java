package com.proj.trelloproj.web.entity;

import java.util.Date;

public class ListNoti {

	private int id;
	private int listId;
	private Date regDate;
	private int memberId;
	private boolean watch;
	
	public ListNoti() {

	}

	public ListNoti(int id, int listId, Date regDate, int memberId, boolean watch) {
		super();
		this.id = id;
		this.listId = listId;
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

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
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
		return "ListNoti [id=" + id + ", listId=" + listId + ", regDate=" + regDate + ", memberId=" + memberId
				+ ", watch=" + watch + "]";
	}
	
}
