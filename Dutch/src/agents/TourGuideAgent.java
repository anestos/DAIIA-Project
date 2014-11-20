package agents;

import jade.core.*;
import jade.core.behaviours.SenderBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.states.MsgReceiver;

public class TourGuideAgent extends MuseumAgent {

	protected void setup() {
		log("Hello world!");
	} 
}
