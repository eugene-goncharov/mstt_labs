package ua.nure.mstt_labs;

/*
  @author Eugene Goncharov
 */

import jade.core.Agent;

public class BookBuyerAgent extends Agent {
    
    protected void setup() {
        // Printout a welcome message
        System.out.println("Hello !Buyer - agent "+getAID().getName() +"is ready.");
    }
}
