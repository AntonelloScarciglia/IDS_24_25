package it.cs.unicam.ids.filiera.domainModel.products;
import it.cs.unicam.ids.filiera.domainModel.Users.*;

import java.util.Date;
import java.util.List;

public abstract class Phase {

    private Date start;
    private Date end;
    private SupplyChainPoint location;

    private User involvedUser;
    private List<Content> approvedContent;
    private List<Content> pendingContent;
    private String note;



    public Phase(Date start, Date end, SupplyChainPoint location) {
        this.start = start;
        this.end = end;
        this.location = location;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public SupplyChainPoint getLocation() {
        return location;
    }

    public User getInvolvedUser() {
        return involvedUser;
    }

    public List<Content> getApprovedContent() {
        return approvedContent;
    }

    public void addContent(Content content){
        pendingContent.add(content);
    }

    public List<Content> getPendingContent() {
        return pendingContent;
    }

    public String getNote() {
        return note;
    }

    public abstract String getPhaseInfo();

}