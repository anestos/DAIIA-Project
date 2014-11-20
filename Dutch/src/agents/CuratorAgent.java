package agents;

import behaviours.SearchForService;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.states.MsgReceiver;

public class CuratorAgent extends MuseumAgent {

	protected void setup() {
		log("Hello world!");
		
		registerService("auctioneer", "auctioneer");
		
		addBehaviour(new SearchForService("bidder", 3));
	}
}
