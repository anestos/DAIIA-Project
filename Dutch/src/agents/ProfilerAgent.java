package agents;

import behaviours.Bidding;
import behaviours.SearchForService;
import jade.core.*;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionInitiator;
import jade.util.leap.Iterator;

public class ProfilerAgent extends MuseumAgent {

	private String userName;

	protected void setup() {
		log("Hello world!");
		
		// Retrieving arguments.
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			userName = (String) args[0];
		}
		
		//addBehaviour(new SearchForService(this, "auctioneer", 1));
		registerService("bidder", "bidder");
		
		addBehaviour(new Bidding(this));
	}

	// Print the actual state of the agent.
	private void agentState (){
		log("User: "+userName);
	}
}