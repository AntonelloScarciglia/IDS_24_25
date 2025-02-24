package it.cs.unicam.ids.filiera.serviceLayer;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.products.Event;
import it.cs.unicam.ids.filiera.domainModel.products.Invite;
import it.cs.unicam.ids.filiera.domainModel.products.SupplyChainPoint;
import it.cs.unicam.ids.filiera.repositories.EventRepository;
import it.cs.unicam.ids.filiera.util.Status;

import java.util.Date;
import java.util.List;

public class EventService {

	private EventRepository eventRepo;


	/**
	 * Invites a user to an event by creating an invite object.
	 *
	 * @param eventId the event to which the user is being invited
	 * @param user the user being invited to the event
	 * @return an Invite object representing the invitation
	 */
	public Invite inviteUser(Event eventId, AuthUser user) {
		 return new Invite(eventId, user);
	}

	/**
	 * Creates a new event and saves it to the repository.
	 *
	 * @param date the date of the event
	 * @param inviteList the list of invites associated with the event
	 * @param category the category of the event
	 * @param location the location of the event
	 * @param description the description of the event
	 * @param status the current status of the event
	 * @param expiryDate the expiry date of the event
	 * @return the newly created event
	 */
	public Event createEvent(Date date, List<Invite> inviteList, String category, SupplyChainPoint location, String description, Status status, Date expiryDate) {
		Event event = new Event(date, inviteList,category,location,description,status,expiryDate);
		eventRepo.save(event);
		return event;
	}

	/**
	 * Method to cancel an event from the repository
	 * @param eventId Long
	 */
	public void cancelEvent(Long eventId) {
		if(eventRepo.findById(eventId).isPresent()){
			eventRepo.delete(eventRepo.findById(eventId).get());
		}else {
			System.out.println("Event not found");
		}

	}

	/**
	 * Retrieves all the events from the repository.
	 *
	 * @return a list of all events stored in the repository
	 */
	public List<Event> getAllEvents() {
		return (List<Event>) eventRepo.findAll();
	}

	/**
	 * Method to get information about an event
	 * @param event Event
	 * @return String
	 */
	public String ToStringEventInfo(Event event) {
		return eventRepo.getEventBy(event).toString();
	}

	/**
	 * Method to set a new location for the event
	 * @param event
	 * @param location SupplyChainPoints
	 */
	public void addPosition(Event event, SupplyChainPoint location) {
		eventRepo.getEventBy(event).setLocation(location);
	}

	/**
	 * 
	 * @param event
	 * @param user
	 */
	public void upload(int event, int user) {
		// TODO - implement EventService.upload
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param event
	 */
	public void update(int event) {
		// TODO - implement EventService.update
		throw new UnsupportedOperationException();
	}

	/**
	 * Method to save an event into repository
	 * @param event Event
	 */
	public void save(Event event) {
		eventRepo.save(event);
	}

	public void getInvite() {
		eventRepo.findAll().forEach(event -> {
			event.getInviteList().forEach(invite -> {
				if(invite.isStatus()){
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