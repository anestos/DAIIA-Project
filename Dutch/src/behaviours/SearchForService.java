package behaviours;

import agents.MuseumAgent;
import jade.core.behaviours.Behaviour;

public class SearchForService extends Behaviour {
	protected MuseumAgent agent;
	protected String type;
	protected int expectedResultSize;
	
	public SearchForService(MuseumAgent agent, String type, int expectedResultSize) {
		this.agent = agent;
		this.type = type;
		this.expectedResultSize = expectedResultSize;
	}

	@Override
	public void action() {
		agent.log("Search for " + type);
		agent.setSearchResults(agent.searchService(type));
	}

	@Override
	public boolean done() {
		return agent.getSearchResults().length == expectedResultSize;
	}
}