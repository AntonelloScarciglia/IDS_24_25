package it.cs.unicam.ids.filiera.serviceLayer;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.products.Event;
import it.cs.unicam.ids.filiera.domainModel.products.Invite;
import it.cs.unicam.ids.filiera.domainModel.products.SupplyChainPoint;
import it.cs.unicam.ids.filiera.repositories.EventRepository;
import it.cs.unicam.ids.filiera.repositories.InviteRepository;
import it.cs.unicam.ids.filiera.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class EventService {
	@Autowired
    private EventRepository eventRepo;
	@Autowired
	private InviteRepository inviteRepo;

    public Event getEventById(Long eventId) {
        Optional<Event> optionalEvent = eventRepo.findById(eventId);
        return optionalEvent.orElse(null);
    }


    /**
     * Invites a user to an event by creating an invite object.
     *
     * @param event the event to which the user is being invited
     * @param user    the user being invited to the event
     * @return an Invite object representing the invitation
     */
    public Invite inviteUser(Event event, AuthUser user) {
		Invite invite = new Invite(event, user);
		event.getInviteList().add(invite);
		eventRepo.save(event);
		return invite;
    }

    /**
     * Creates a new event and saves it to the repository.
     *
	 * @param name 		  the name of the event
     * @param date        the date of the event
     * @param inviteList  the list of invites associated with the event
     * @param category    the category of the event
     * @param location    the location of the event
     * @param description the description of the event
     * @return the newly created event
     */
    public Event createEvent(String name,
                             Date date,
                             List<Invite> inviteList,
                             String category,
                             SupplyChainPoint location,
                             String description,
                             AuthUser creator) {
        Event event = new Event(name, date, inviteList, category, location, description, creator);
        eventRepo.save(event);
        return event;
    }

    /**
     * Method to cancel an event from the repository
     *
     * @param eventId Long
     */
    public void cancelEvent(Long eventId) {
        Optional<Event> eventOpt = eventRepo.findById(eventId);
        if (eventOpt.isPresent()) {
            eventRepo.delete(eventOpt.get());
        } else {
            System.out.println("Event not found");
        }
    }

    /**
     * Retrieves all the events from the repository.
     *
     * @return a list of all events stored in the repository
     */
    public List<Event> getAllEvents() {
        return StreamSupport.stream(eventRepo.findAll().spliterator(), false)
				.collect(Collectors.toList());
    }

    /**
     * Method to get information about an event
     *
     * @param event Event
     * @return String
     */
    public String ToStringEventInfo(Event event) {
        return eventRepo.getEventBy(event).toString();
    }

    /**
     * Method to set a new location for the event
     *
     * @param event
     * @param location SupplyChainPoints
     */
    public void addLocation(Event event, SupplyChainPoint location) {
        Event found = eventRepo.getEventBy(event);
        if(found != null) {
            found.setLocation(location);
            eventRepo.save(found);
        } else {
            System.out.println("Event not found");
        }
    }

    /**
     * Method to save an event into repository
     *
     * @param event Event
     */
    public void save(Event event) {
        eventRepo.save(event);
    }

    public void getInvite() {
        eventRepo.findAll().forEach(event -> {
            event.getInviteList().forEach(invite -> {
                if (invite.isStatus()) {
                    System.out.println(invite.getAddressee());
                }
            });
        });
    }

//	/**
//	 * @param invite
//	 */
//	public void createInvite(Invite invite) {
//		Invite inv = new Invite();
//	}
//
//	/**
//	 * @param invite
//	 */
//	public void saveInvite(int invite) {
//		// TODO - implement EventService.saveInvite
//		throw new UnsupportedOperationException();
//	}

}