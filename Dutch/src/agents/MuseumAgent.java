package agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class MuseumAgent extends Agent {
	protected DFAgentDescription[] searchResults;
	
	public DFAgentDescription[] getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(DFAgentDescription[] searchResults) {
		this.searchResults = searchResults;
	}

	public void log(String s) {
		System.out.println("[" + getLocalName() + "] " + s);
	}
	
	protected void registerService(String type, String name) {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType(type);
		sd.setName(name);
		dfd.addServices(sd);

		try {
			DFService.register(this, dfd);
			log("Service [" + type + ", " + name + "]");
		}
		catch (FIPAException fe){
			fe.printStackTrace();
		}
	}
	
	public DFAgentDescription[] searchService(String type) {
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription(); 
		sd.setType(type);
		dfd.addServices(sd); 
		
		try {
			// Sets the local variable that contains all the results of the search.
			searchResults = DFService.search(this,dfd);
			
			if(searchResults != null && searchResults.length > 0)
				log("Service " + type + " found!");
			
			return searchResults;
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchResults;
	}
}