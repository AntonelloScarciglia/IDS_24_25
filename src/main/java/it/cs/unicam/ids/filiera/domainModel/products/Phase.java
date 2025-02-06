package it.cs.unicam.ids.filiera.domainModel.products;
import it.cs.unicam.ids.filiera.domainModel.Users.*;

import java.util.Date;
import java.util.List;

public abstract class Phase {

	private Date start;
	private Date end;
	private Location loc;
	private User involvedUser;
	private List<Content> approvedContent;
	private List<Content> pendingContent;
	private String note;

	public abstract String getPhaseInfo();

	public Phase(Date start, Date end, Location loc) {
		this.start = start;
		this.end = end;
		this.loc = loc;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public Location getLoc() {
		return loc;
	}

	public User getInvolvedUser() {
		return involvedUser;
	}

	public List<Content> getApprovedContent() {
		return approvedContent;
	}

	public List<Content> getPendingContent() {
		return pendingContent;
	}

	public String getNote() {
		return note;
	}


}