package ua.nure.mstt_labs.behaviours;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ua.nure.mstt_labs.BookSellerAgent;
import ua.nure.mstt_labs.Seller;

/**
 * If the requested book is in the local catalogue the seller agent replies
 * with a PROPOSE message specifying the price. Otherwise a REFUSE message is
 * sent back.
 */
public class OfferRequestsServer extends CyclicBehaviour {

    public OfferRequestsServer(BookSellerAgent bookSellerAgent) {
        super(bookSellerAgent);
    }

    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            // Message received. Process it
            String title = msg.getContent();
            ACLMessage reply = msg.createReply();
            Integer price = ((Seller) myAgent).getCatalogue().get(title);

            if (price != null) {
                // The requested book is available for sale. Reply with the price
                reply.setPerformative(ACLMessage.PROPOSE);
                reply.setContent(String.valueOf(price.intValue()));
            } else {
                // The requested book is NOT available for sale.
                reply.setPerformative(ACLMessage.REFUSE);
                reply.setContent("not-available");
            }
            myAgent.send(reply);
        } else {
            block();
        }
    }
}
