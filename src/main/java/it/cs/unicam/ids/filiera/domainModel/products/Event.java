package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date date;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Invite> inviteList;
    private String category;
    private SupplyChainPoint location;
    private String description;
    @OneToOne
    private AuthUser creator;

    public Event() {
    }

    public Event(String name, Date date, List<Invite> inviteList, String category, SupplyChainPoint location, String description, AuthUser creator) {
        this.name = name;
        this.date = date;
        this.inviteList = inviteList;
        this.category = category;
        this.location = location;
        this.description = description;
        this.creator = creator;
    }

    public String getName() {
        return name;
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


    public AuthUser getCreator() {
        return creator;
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
                '}';
    }
}