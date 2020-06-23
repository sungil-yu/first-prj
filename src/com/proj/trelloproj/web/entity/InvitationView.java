package com.proj.trelloproj.web.entity;

import java.util.Date;

public class InvitationView extends Invitation{
	
	private String teamName;
	
	public InvitationView() {
	
	}

	public InvitationView(int id, int inviterId, int inviteeId, int teamId, Date invDate, Date acceptDate,
			String teamName) {
		
		super(id,inviterId,inviteeId,teamId,invDate,acceptDate);
		this.teamName = teamName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
}
