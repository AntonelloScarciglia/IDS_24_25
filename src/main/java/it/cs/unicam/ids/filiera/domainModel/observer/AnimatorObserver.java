package it.cs.unicam.ids.filiera.domainModel.observer;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.products.Invite;

public class AnimatorObserver implements Observer {

    @Override
    public void update(Subject s) {
		if (s instanceof Invite invite) {
            AuthUser animator = invite.getEvent().getCreator();
            String subject = "Response to the invitation for the event: " + invite.getEvent().getName();

            String body;
            if (invite.isStatus()) {
                body = "User " + invite.getAddressee().getEmail() + " has accepted the invitation.";
            } else
                body = "User " + invite.getAddressee().getEmail() + " has declined the invitation.";

            sendEmail(animator.getEmail(), subject, body);
		}
    }

    private void sendEmail(String recipient, String subject, String body) {
        System.out.println("Sending email to: " + recipient + ". Subject: " + subject + ". Body: " + body);
    }
}