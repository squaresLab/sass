package omnet;

import java.util.ArrayList;

import omnet.Omnet.Seams2018Scenario;
import omnet.components.Datacenter;
import omnet.components.DatacenterFactory;
import omnet.components.Server;
import omnet.components.ServerFactory;
import system.Fitness;
import omnet.tactics.StartServer;
import system.SystemState;
import tactics.FailableTactic;
import tactics.Tactic;
import omnet.Scenario;

public class Omnet extends SystemState {
	
	public enum Seams2018Scenario {
			normal,requests,fourserv,requestsfourserv,econ,failc,unreliable;
		public static Seams2018Scenario fromString(String name){
			Seams2018Scenario scenario;
			
			switch (name){
			//TODO
			case "failc": scenario = Seams2018Scenario.failc; break;
			case "unreliable": scenario = Seams2018Scenario.unreliable; break;
			//done
			case "econ": scenario = Seams2018Scenario.econ; break;
			case "requests": scenario = Seams2018Scenario.requests; break;
			case "fourserv": scenario = Seams2018Scenario.fourserv; break;
			case "requestsfourserv": scenario = Seams2018Scenario.requestsfourserv; break;
			default: scenario = Seams2018Scenario.normal; break;
			}
			
			return scenario;
		}
	}

	// requests / sec on the system, assumed constant for now
	public int SYSTEM_DEMAND = 3000;
	
	static double NORMAL_PROFIT_PER_SECOND = .005;

	static double DIMMED_PROFIT_PER_SECOND = .001;
	
	private double dimmedResponses,normalResponses,income,cost,latency;

	private static double MAX_LATENCY_PER_SERVER = 1000;
	
	private ArrayList<Datacenter> datacenters;
	
	DatacenterFactory datacenterFactory;

	public static final int MAX_SERVER_COUNT_PER_LOC = 5;
	
	private Scenario scenario;
	
	public Omnet(Scenario s){
		
		this.scenario = s;
		
		datacenterFactory = new DatacenterFactory(s);
		datacenters = datacenterFactory.initializeDatacenters();
		
		// we will start with 1 datacenter of A, B, and C
		// each datacenter starts with one server, this is handeled by the datacenter factory
		/*
		datacenters.add(datacenterFactory.getA());
		datacenters.add(datacenterFactory.getB());
		datacenters.add(datacenterFactory.getC());
		*/
		
		
		s.applyTo(this);
		
		/*
		servers.add(serverFactory.getD());
		servers.add(serverFactory.getE());
		servers.add(serverFactory.getF());
		servers.add(serverFactory.getG());
		servers.add(serverFactory.getH());
		servers.add(serverFactory.getI());
		servers.add(serverFactory.getJ());
		servers.add(serverFactory.getK());
		servers.add(serverFactory.getL());
		servers.add(serverFactory.getM());
		servers.add(serverFactory.getN());
		servers.add(serverFactory.getO());
		servers.add(serverFactory.getP());
		*/
		
	}
	
	@Override
	public void accept(Tactic tactic){
		
		scenario.acceptTactic(tactic);
		super.accept(tactic);
	}
	
		public double calculateProfit(){
			
			//reset fields
			dimmedResponses = 0; normalResponses = 0; income = 0; cost = 0; latency = 0;
			
			if(isStateValid()){
				
				double totalProfit=0;

				int sumTrafficLevel = 0;
				
				// take sum of traffic level over all centers
				for(int i = 0; i < datacenters.size(); i++){
					sumTrafficLevel += datacenters.get(i).getTraffic();
				}
				
				// the requests that go to each datacenter per traffic level
				double requestsPerTrafficLevel = ((double)SYSTEM_DEMAND)/sumTrafficLevel;
				
				if (!Double.isFinite(requestsPerTrafficLevel)){
					requestsPerTrafficLevel = 0;
				}
				
				if (requestsPerTrafficLevel == 0){
					latency += SYSTEM_DEMAND;
				}
				
				Datacenter current;
				for(int i =0; i < datacenters.size(); i++){	
					current = datacenters.get(i);
					
						double currentDimmerPercentage = ((double)current.getDimmer())
								/current.MAX_DIMMER_LVL;
						
						double currentRequests = requestsPerTrafficLevel * current.getTraffic();
						
						double p = current.getPower();
						double fc = current.getPowerPerNormal();
						double dc = current.getPowerPerDimmed();
						double ra = currentDimmerPercentage;
						
						// use ratio to determine the amount of power to devote to each type of response
						double maxNormal = (p * (1-ra)) / fc;
						double maxDimmed = (p * (ra)) / dc;
						
						double fullReplys,dimmedReplys;
						
						// give normal responses when under low load
						if (currentRequests <= maxNormal){
							
							fullReplys = currentRequests;
							dimmedReplys = 0;
							
						}else{
							
							fullReplys = maxNormal;
							
							// check if we have enough power to fullfill the rest of the requests as dimmed
							if (currentRequests - fullReplys >= maxDimmed){
								dimmedReplys = maxDimmed;
							}else{
								// otherwise fulfill as many as we can
								dimmedReplys = currentRequests - fullReplys;
							}
							
						}
		
						totalProfit += NORMAL_PROFIT_PER_SECOND * fullReplys + 
								DIMMED_PROFIT_PER_SECOND * dimmedReplys;
								
						totalProfit -= current.getCost();
						
						dimmedResponses += dimmedReplys;
						normalResponses += fullReplys;
						cost += current.getCost();
						income += NORMAL_PROFIT_PER_SECOND * fullReplys + 
								DIMMED_PROFIT_PER_SECOND * dimmedReplys;
						/*
						double calcLatency = 1 / (handleable - requests);
						
						if (!Double.isFinite(calcLatency)){
							calcLatency = MAX_LATENCY_PER_SERVER ;
						}
						
						latency += calcLatency;
						*/
						
						latency += currentRequests - fullReplys - dimmedReplys;
							
				}
				return totalProfit;
			}
			else {
				// if system is in invalid state return 0
				return 0;
			}
		}
	
	public Datacenter getServer(String name){
		Datacenter server;
		for (int count = 0; count < datacenters.size(); count++){
			server = datacenters.get(count);
			
			if (server.getName().equals(name)){
				return server;
			}
			
		}
		
		return null;
	}
	
	public ArrayList<Datacenter> getDatacenters() {
		return datacenters;
	}

	public void setServers(ArrayList<Datacenter> datacenters) {
		this.datacenters = datacenters;
	}

	@Override
	public boolean isStateValid() {
		// to determine if the system is valid, check to see that each server is valid
		for (int count = 0; count < datacenters.size(); count++){
			if (!datacenters.get(count).isValid())
				return false;
		}
		return true;
	}
	
	public Object clone(){
		Omnet copy = new Omnet(scenario);
		
		for (int count = 0; count < history.size(); count++){
			copy.history.add((Tactic) history.get(count).clone());
		}
		
		for (int count = 0; count < datacenters.size(); count++){
			copy.datacenters.add((Datacenter) datacenters.get(count).clone());
		}
		
		return copy;
	}
	
	public double getDimmedResponses() {
		return dimmedResponses;
	}

	public double getNormalResponses() {
		return normalResponses;
	}

	public double getIncome() {
		return income;
	}

	public double getCost() {
		return cost;
	}

	public double getProfit() {
		return income-cost;
	}

	public double getLatency() {
		return latency;
	}

	
	public DatacenterFactory getServerFactory(){
		return datacenterFactory;
	}

	@Override
	public Fitness calculateInstFitness() {
		Fitness fit = new Fitness();
		
		calculateProfit();
		
		fit.put("Cost",getCost());
		fit.put("DimmedResponses",getDimmedResponses());
		fit.put("NormalResponses",getNormalResponses());
		fit.put("Income",getIncome());
		fit.put("Profit",getProfit());
		fit.put("Latency",getLatency());

		return fit;
	}
	
	public Scenario getScenario(){
		return scenario;
	}
	
}
