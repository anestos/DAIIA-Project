package behaviours;

import java.util.ArrayList;

import agents.MuseumAgent;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;

public class Auction extends Behaviour{
	protected AID[] bidders;
	protected MuseumAgent agent;
	protected ACLMessage[] msgQueue;
	protected int highestPrice, lowestPrice, currentPrice, step;
	protected boolean auctionFinished = false;
	
	public Auction(MuseumAgent agent, int highestPrice, int lowestPrice, int step) {
		this.agent = agent;
		this.highestPrice = highestPrice;
		this.lowestPrice = lowestPrice;
		this.currentPrice = highestPrice;
		this.step = step;
		this.bidders = getBidders(agent.getSearchResults());
		this.msgQueue = new ACLMessage[bidders.length];
	}
	
	@Override
	public void action() {
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.setContent("" + currentPrice);
		
		broadcast(msg);
		
		for(int i = 0; i < bidders.length; i++) {
			ACLMessage rcv = agent.blockingReceive();
			msgQueue[i] = rcv;
			
			if(rcv.getPerformative() == ACLMessage.AGREE) {
				agent.log("" + rcv.getSender().getName().toString() 
						+ " won the auction with " + currentPrice);
				
				
				auctionFinished = true;
			}
		}
		
		if(terminationCondition()) {
			msg = new ACLMessage(ACLMessage.REFUSE);
			
			broadcast(msg);
		}
		
		currentPrice -= step;
	}

	@Override
	public boolean done() {
		return terminationCondition();
	}
	
	protected boolean terminationCondition() {
		return auctionFinished || currentPrice < lowestPrice;
	}

	protected AID[] getBidders(DFAgentDescription[] ad) {
		AID[] result = new AID[ad.length];
		
		for(int i = 0; i < ad.length ; i++) {
			result[i] = ad[i].getName();
		}
		
		return result;
	}
	
	protected void broadcast(ACLMessage msg) {
		for(int i = 0; i < bidders.length; i++) {
			msg.addReceiver(bidders[i]);
		}
		
		agent.send(msg);
	}
}
