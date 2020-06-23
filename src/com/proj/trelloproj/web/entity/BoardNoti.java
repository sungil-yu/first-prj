package com.proj.trelloproj.web.entity;

import java.util.Date;

public class BoardNoti {

	private int id;
	private int boardId;
	private Date regDate;
	private int memberId;
	private boolean watch;
	
	public BoardNoti() {

	}

	public BoardNoti(int id, int boardId, Date regDate, int memberId, boolean watch) {
		super();
		this.id = id;
		this.boardId = boardId;
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

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
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
		return "BoardNoti [id=" + id + ", boardId=" + boardId + ", regDate=" + regDate + ", memberId=" + memberId
				+ ", watch=" + watch + "]";
	}

}
