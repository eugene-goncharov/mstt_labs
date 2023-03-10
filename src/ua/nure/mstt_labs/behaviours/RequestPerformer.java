package ua.nure.mstt_labs.behaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ua.nure.mstt_labs.BookBuyerAgent;
import ua.nure.mstt_labs.Buyer;

/**
 * @author Eugene Goncharov
 */
public class RequestPerformer extends Behaviour {
    private static final long serialVersionUID = 523215840059536944L;

    private AID bestSeller; // The agent who provides the best offer
    private int bestPrice;  // The best offered price
    private int repliesCnt = 0; // The counter of replies from seller agents
    private MessageTemplate mt; // The template to receive replies
    private int step = 0;
    private final String targetBookTitle;

    public RequestPerformer(BookBuyerAgent buyerAgent, String targetBookTitle) {
        super(buyerAgent);
        this.targetBookTitle = targetBookTitle;
    }

    public void action() {
        final AID[] sellerAgents = ((Buyer) myAgent).getSellerAgents();

        switch (step) {
            case 0:
                // Send the cfp to all sellers
                ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                for (final AID sellerAgent : sellerAgents) {
                    cfp.addReceiver(sellerAgent);
                }
                cfp.setContent(targetBookTitle);
                cfp.setConversationId("book-trade");
                cfp.setReplyWith("cfp" + System.currentTimeMillis()); // Unique value
                myAgent.send(cfp);

                // Prepare the template to get proposals
                mt = MessageTemplate.and(MessageTemplate.MatchConversationId("book-trade"),
                        MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
                step = 1;
                break;
            case 1:
                // Receive all proposals/refusals from seller agents
                ACLMessage reply = myAgent.receive(mt);
                if (reply != null) {
                    // Reply received
                    if (reply.getPerformative() == ACLMessage.PROPOSE) {
                        // This is an offer
                        int price = Integer.parseInt(reply.getContent());
                        if (bestSeller == null || price < bestPrice) {
                            // This is the best offer at present
                            bestPrice = price;
                            bestSeller = reply.getSender();
                        }
                    }
                    repliesCnt++;
                    if (repliesCnt >= sellerAgents.length) {
                        // We received all replies
                        step = 2;
                    }
                } else {
                    block();
                }
                break;
            case 2:
                // Send the purchase order to the seller that provided the best offer
                ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                order.addReceiver(bestSeller);
                order.setContent(targetBookTitle);
                order.setConversationId("book-trade");
                order.setReplyWith("order" + System.currentTimeMillis());
                myAgent.send(order);

                // Prepare the template to get the purchase order reply
                mt = MessageTemplate.and(MessageTemplate.MatchConversationId("book-trade"),
                        MessageTemplate.MatchInReplyTo(order.getReplyWith()));
                step = 3;
                break;
            case 3:
                // Receive the purchase order reply
                reply = myAgent.receive(mt);
                if (reply != null) {
                    // Purchase order reply received
                    if (reply.getPerformative() == ACLMessage.INFORM) {
                        // Purchase successful. We can terminate
                        System.out.println(targetBookTitle + " successfully purchased from agent "
                                + reply.getSender().getName());
                        System.out.println("Price = " + bestPrice);
                        myAgent.doDelete();
                    } else {
                        System.out.println("Attempt failed: requested book already sold.");
                    }

                    step = 4;
                } else {
                    block();
                }
                break;
        }
    }

    public boolean done() {
        if (step == 2 && bestSeller == null) {
            System.out.println("Attempt failed: " + targetBookTitle + " not available for sale");
        }
        return ((step == 2 && bestSeller == null) || step == 4);
    }
}
