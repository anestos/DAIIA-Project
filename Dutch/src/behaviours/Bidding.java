package behaviours;

import java.util.Random;

import agents.MuseumAgent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Bidding extends Behaviour{
	protected MuseumAgent agent;
	protected int highestValue;
	protected boolean auctionFinished = false;
	
	public Bidding(MuseumAgent agent) {
		this.agent = agent;
		
		Random rnd = new Random();
		highestValue = rnd.nextInt(500);
		
		agent.log("Highest bid = " + highestValue);
	}
	
	public boolean isAuctionFinished() {
		return auctionFinished;
	}

	public void setAuctionFinished(boolean auctionFinished) {
		this.auctionFinished = auctionFinished;
	}

	@Override
	public void action() {
		ACLMessage rcv = agent.blockingReceive();
		
		if(rcv.getPerformative() == ACLMessage.REFUSE) {
			agent.log("End of auction received");
			auctionFinished = true;
			return;
		}
		
		int requestedPrice = Integer.parseInt(rcv.getContent());
		
		ACLMessage msg;
		
		if(requestedPrice <= highestValue) {
			msg = new ACLMessage(ACLMessage.AGREE);
		} else {
			msg = new ACLMessage(ACLMessage.CANCEL);
		}
		
		msg.addReceiver(rcv.getSender());
		agent.send(msg);
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return auctionFinished;
	}
	
}
