package it.cs.unicam.ids.filiera.controllers;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.products.Event;
import it.cs.unicam.ids.filiera.domainModel.products.Invite;
import it.cs.unicam.ids.filiera.domainModel.products.SupplyChainPoint;
import it.cs.unicam.ids.filiera.serviceLayer.EventService;
import it.cs.unicam.ids.filiera.serviceLayer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;
	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<Event> createEvent(@RequestParam String name,
							 @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date date,
							 @RequestParam String category,
							 @RequestParam SupplyChainPoint location,
							 @RequestParam String description,
							  Long creatorId) {

		// UserID should be retrieved automatically from @AuthenticationPrincipal or HTTP Session
		AuthUser creator = userService.getUserById(creatorId);
		Event event = eventService.createEvent(name, date, List.of(), category, location, description, creator);
        return ResponseEntity.ok(event);
	}

	/**
	 * Endpoint to invite a User to an Event.
	 * @param eventId the ID of the event to which the user is invited.
	 * @param userId the ID of the user to invite.
	 * @return ResponseEntity containing the created Invite with HTTP status 200 (OK).
	 */
	@PostMapping("/{eventId}/invite")
	public ResponseEntity<Invite> inviteUsersToEvent(@PathVariable Long eventId, @RequestParam Long userId) {
		Event event = eventService.getEventById(eventId);
		AuthUser userToInvite = userService.getUserById(userId);
		eventService.inviteUser(event, userToInvite);
		return ResponseEntity.ok(eventService.inviteUser(event, userToInvite));
	}

	/**
	 * Retrieves a list of all events.
	 *
	 * @return ResponseEntity containing a list of all events with HTTP status 200 (OK).
	 */
	@GetMapping
	public ResponseEntity<List<Event>> getEvents() {
		List<Event> events = eventService.getAllEvents();
		return ResponseEntity.ok(events);
	}

	/**
	 * Retrieves detailed information about a specific event.
	 *
	 * @param eventId The ID of the event.
	 * @return ResponseEntity containing event information with HTTP status 200 (OK)
	 *         or HTTP status 404 (NOT FOUND) if the event does not exist.
	 */
	@GetMapping("/{eventId}/info")
	public ResponseEntity<String> getEventInfo(@PathVariable Long eventId) {
		Event event = eventService.getEventById(eventId);
		if (event == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
		}
		String eventInfo = eventService.ToStringEventInfo(event);
		return ResponseEntity.ok(eventInfo);
	}

	/**
	 * 
	 * @param event
	 * @param user
	 */
	public void update(int event, int user) {
		// TODO - implement EventController.update
		throw new UnsupportedOperationException();
	}

	public void acceptInvitation() {
		// TODO - implement EventController.acceptInvitation
		throw new UnsupportedOperationException();
	}

	/**
	 * Cancels an event.
	 *
	 * @param eventId The ID of the event to cancel.
	 * @return ResponseEntity containing a confirmation message with HTTP status 200 (OK).
	 */
	@DeleteMapping("/{eventId}") // Maps DELETE requests on /events/{eventId}
	public ResponseEntity<String> cancelEvent(@PathVariable Long eventId) {
		eventService.cancelEvent(eventId);
		return ResponseEntity.ok("Event cancelled");
	}
}