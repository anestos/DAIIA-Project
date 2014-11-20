package agents;

import behaviours.Auction;
import behaviours.SearchForService;
import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
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
		
		SequentialBehaviour seq = new SequentialBehaviour();
		
		seq.addSubBehaviour(new SearchForService(this, "bidder", 3));
		seq.addSubBehaviour(new Auction(this, 500, 50, 5));
		
		addBehaviour(seq);
	}
}
