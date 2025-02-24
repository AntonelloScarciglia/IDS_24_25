package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.util.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import javax.security.auth.callback.UnsupportedCallbackException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private List<Invite> inviteList;
    private String category;
    private SupplyChainPoint location;
    private String description;
    private Status status;
    private Date expiryDate;

    public Event(Date date, List<Invite> inviteList, String category, SupplyChainPoint location, String description, Status status, Date expiryDate) {
        this.date = date;
        this.inviteList = inviteList;
        this.category = category;
        this.location = location;
        this.description = description;
        this.status = status;
        this.expiryDate = expiryDate;
    }

    public List<AuthUser> getInvitedUsers(){
        return inviteList.stream().map(Invite::getAddressee).collect(Collectors.toList());
    }

    public List<AuthUser> getConfirmedParticipants(){
        return inviteList.stream().map(
                invite -> {
                    if(invite.isStatus()){
                        return invite.getAddressee();
                    }else{
                        return null;
                    }
                }
        ).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public List<Invite> getInviteList() {
        return inviteList;
    }

    public String getCategory() {
        return category;
    }

    public SupplyChainPoint getLocation() {
        return location;
    }

    public void setLocation(SupplyChainPoint location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", inviteList=" + inviteList +
                ", category='" + category + '\'' +
                ", location=" + location +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", expiryDate=" + expiryDate +
                '}';
    }
}