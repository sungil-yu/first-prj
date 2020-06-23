package com.proj.trelloproj.web.entity;

import java.util.Date;

public class Invitation {
	
	private int id;
	private int inviterId;
	private int inviteeId;
	private int teamId;
	private Date invDate;
	private Date acceptDate;
	
	public Invitation() {
	
	}

	public Invitation(int id, int inviterId, int inviteeId, int teamId, Date invDate, Date acceptDate) {
		super();
		this.id = id;
		this.inviterId = inviterId;
		this.inviteeId = inviteeId;
		this.teamId = teamId;
		this.invDate = invDate;
		this.acceptDate = acceptDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInviterId() {
		return inviterId;
	}

	public void setInviterId(int inviterId) {
		this.inviterId = inviterId;
	}

	public int getInviteeId() {
		return inviteeId;
	}

	public void setInviteeId(int inviteeId) {
		this.inviteeId = inviteeId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	@Override
	public String toString() {
		return "Invitation [id=" + id + ", inviterId=" + inviterId + ", inviteeId=" + inviteeId + ", teamId=" + teamId
				+ ", invDate=" + invDate + ", acceptDate=" + acceptDate + "]";
	}

	
}
