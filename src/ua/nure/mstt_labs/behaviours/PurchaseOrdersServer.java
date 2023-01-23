package ua.nure.mstt_labs.behaviours;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ua.nure.mstt_labs.BookSellerAgent;
import ua.nure.mstt_labs.Seller;

/**
 * @author Eugene Goncharov
 */
public class PurchaseOrdersServer extends CyclicBehaviour {


    public PurchaseOrdersServer(BookSellerAgent bookSellerAgent) {
        super(bookSellerAgent);
    }

    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            // ACCEPT_PROPOSAL Message received. Process it
            String title = msg.getContent();
            ACLMessage reply = msg.createReply();

            Integer price = ((Seller)myAgent).getCatalogue().remove(title);
            if (price != null) {
                reply.setPerformative(ACLMessage.INFORM);
                System.out.println(title+" sold to agent "+msg.getSender().getName());
            }
            else {
                // The requested book has been sold to another buyer in the meanwhile .
                reply.setPerformative(ACLMessage.FAILURE);
                reply.setContent("not-available");
            }
            myAgent.send(reply);
        }
        else {
            block();
        }
    }
}  // End of inner class OfferRequestsServer
