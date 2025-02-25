package it.cs.unicam.ids.filiera.domainModel.observer;

import it.cs.unicam.ids.filiera.domainModel.products.CatalogItem;
import it.cs.unicam.ids.filiera.domainModel.products.Invite;
import it.cs.unicam.ids.filiera.util.Status;

public class UserObserver implements Observer {
    @Override
    public void update(Subject s) {
        switch (s) {
            case Invite invite -> {
                String emailSubject = "Invitation to the event: " + invite.getEvent().getName();
                String emailBody = "Hello, you have received an invitation to the event. Please check the details.";
                sendEmail(invite.getAddressee().getEmail(), emailSubject, emailBody);
            }
            case CatalogItem item -> {
                if (item.getStatus() == Status.APPROVED || item.getStatus() == Status.REJECTED) {
                    String action = item.getStatus() == Status.APPROVED ? "accepted" : "declined";
                    String emailSubject = "Your product has been " + action;
                    String emailBody = "Dear user, your product '" + item.getName() + "' has been " + action + " by the curator.";
                    sendEmail(item.getOwner().getEmail(), emailSubject, emailBody);
                }
            }
            case null, default -> {
            }
        }
    }

    // SMTP services here
    private void sendEmail(String recipient, String subject, String body) {
        System.out.println("Sending email to: " + recipient + ". Subject: " + subject + ". Body: " + body);
    }
}
