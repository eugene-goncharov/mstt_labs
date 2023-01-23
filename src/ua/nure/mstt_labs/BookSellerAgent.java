package ua.nure.mstt_labs;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import ua.nure.mstt_labs.behaviours.OfferRequestsServer;
import ua.nure.mstt_labs.behaviours.PurchaseOrdersServer;
import ua.nure.mstt_labs.ui.BookSellerGui;

import java.util.Hashtable;

/**
 * @author Eugene Goncharov
 */
@SuppressWarnings({"unchecked", "MismatchedQueryAndUpdateOfCollection", "rawtypes"})
public class BookSellerAgent extends Agent implements Seller {
    // The catalogue of books for sale (maps the title of a book to its price)
    private Hashtable<String, Integer> catalogue;
    // The GUI by means of which the user can add books in the catalogue
    private BookSellerGui myGui;

    // Put agent initializations here
    @SuppressWarnings("rawtypes")
    protected void setup() {
        // Create the catalogue
        catalogue = new Hashtable<String, Integer>();
        // Create and show the GUI
        myGui = new BookSellerGui(this);
        myGui.showGui();

        // Register the book-selling service in the yellow pages
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("book-selling");
        sd.setName("JADE-book-trading");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
        // Add the behaviour serving requests for offer from buyer agents
        addBehaviour(new OfferRequestsServer(this));
        // Add the behaviour serving purchase orders from buyer agents
        addBehaviour(new PurchaseOrdersServer(this));
    }

    public Hashtable<String, Integer> getCatalogue() {
        return catalogue;
    }

    // Put agent clean-up operations here
    protected void takeDown() {
        // Deregister from the yellow pages
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
        // Close the GUI
        myGui.dispose();
        // Printout a dismissal message
        System.out.println("Seller-agent " + getAID().getName() + " terminating.");
    }

    /**
     * This is invoked by the GUI when the user adds a new book for sale
     */
    public void updateCatalogue(final String title, final int price) {
        addBehaviour(new OneShotBehaviour() {
            @SuppressWarnings("removal")
            public void action() {
                catalogue.put(title, price);
            }
        });
    }
}
