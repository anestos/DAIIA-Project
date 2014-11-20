package behaviours;

import agents.MuseumAgent;
import jade.core.behaviours.Behaviour;

public class SearchForService extends Behaviour {
	protected MuseumAgent agent;
	protected String type;
	protected int expectedResultSize;
	
	public SearchForService(String type, int expectedResultSize) {
		this.type = type;
		this.expectedResultSize = expectedResultSize;
		this.agent = (MuseumAgent)myAgent;
	}

	@Override
	public void action() {
		agent.log("Search for " + type);
		agent.searchService(type);
	}

	@Override
	public boolean done() {
		return agent.getSearchResults().length == expectedResultSize;
	}
}