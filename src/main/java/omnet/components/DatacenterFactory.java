package omnet.components;

import java.util.Arrays;

import omnet.Scenario;

public class DatacenterFactory {

	Scenario s;
	
	double mult = 1;
	
	String[] serverLabels = {"A","B","C","D"};
	
	public DatacenterFactory(Scenario s){
		this.s = s;
	}
	
	public Datacenter getDatacenter(String label) {
		int index = Arrays.asList(serverLabels).indexOf(label);
		Datacenter ans = new Datacenter(label);
		
		ans.cost = s.getCost()[index];
		ans.power = s.getPower()[index];
		ans.powerPerNormal = s.getPowerPerNormal()[index];
		ans.latency = s.getLatency()[index];
		
		return ans;
	}
	
	public Datacenter getDatacenter(int index) {
		return getDatacenter(serverLabels[index]);
	}
	
	public Datacenter getA() {
		
		Datacenter a = new Datacenter("A");
		
		a.cost = .5 * mult;
		a.power = 150;
		a.powerPerNormal = 150.0 / 50.0;
		a.latency = 120;
		
		return a;
	}
	
	public Datacenter getB(){
		Datacenter b = new Datacenter("B");
		b.cost = .7*mult;
		b.power = 200;
		b.powerPerNormal = 200.0 / 130.0;
		b.latency = 120;
		
		return b;
	}
	
	public Datacenter getC(){
		Datacenter c = new Datacenter("C");
		c.cost = 1*mult;
		c.power = 300;
		c.powerPerNormal = 300.0 / 150.0;
		c.latency = 120;
		
		return c;
	}
	
	public Datacenter getD(){
		Datacenter d = new Datacenter("D");
		d.cost = .5*mult;
		d.power = 80;
		d.powerPerNormal = 80.0 / 25.0;
		d.latency = 60;
		
		return d;
	}


}
