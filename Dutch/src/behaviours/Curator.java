package behaviours;

import agents.MuseumAgent;
import jade.core.behaviours.Behaviour;

public class Curator extends Behaviour {
	private MuseumAgent agent;
	private Auction ab;
	protected String type;
	protected int expectedResultSize;
	protected int highestPrice, lowestPrice, currentPrice, step;
	protected boolean auctionFinished = false;
	protected boolean auctionStarted = false;
	
	
	public Curator(MuseumAgent agent, String type, int ers, int highestPrice, int lowestPrice, int step) {
		this.agent = agent;
		this.type = type;
		this.expectedResultSize = ers;
		this.highestPrice = highestPrice;
		this.lowestPrice = lowestPrice;
		this.currentPrice = highestPrice;
		this.step = step;
	}
	
	@Override
	public void action() {
		if(agent.getSearchResults() == null) {
			agent.searchService(type);
		} else {
			if(!auctionStarted && agent.getSearchResults().length == expectedResultSize) {
				ab = new Auction(agent, highestPrice, lowestPrice, step);
				agent.addBehaviour(ab);
				auctionStarted = true;
			}
		}
	}

	@Override
	public boolean done() {
		return ab != null && ab.terminationCondition();
	}

}
