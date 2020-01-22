package omnet.components;

import java.util.ArrayList;
import java.util.Arrays;

import omnet.Scenario;

public class DatacenterFactory {

	Scenario s;
	
	double mult = 1;
	
	public static String[] serverLabels = {
			"eu-north-1a","eu-north-1b","eu-north-1c","ap-south-1a","ap-south-1b","ap-south-1c","eu-west-3a","eu-west-3b","eu-west-3c",
			"eu-west-2a","eu-west-2b","eu-west-2c","eu-west-1a","eu-west-1b","eu-west-1c","ap-northeast-2a","ap-northeast-2b",
			"ap-northeast-2c","ap-northeast-1a","ap-northeast-1c","ap-northeast-1d","sa-east-1a","sa-east-1b","sa-east-1c",
			"ca-central-1a","ca-central-1b","ap-southeast-1a","ap-southeast-1b","ap-southeast-1c","ap-southeast-2a","ap-southeast-2b",
			"ap-southeast-2c","eu-central-1a","eu-central-1b","eu-central-1c","us-east-1a","us-east-1b","us-east-1c","us-east-1d",
			"us-east-1e","us-east-1f","us-east-2a","us-east-2b","us-east-2c","us-west-1b","us-west-1c","us-west-2a","us-west-2b",
			"us-west-2c","us-west-2d"};
	
	public static String[] regionLabels = {"eu-north-1","ap-south-1","eu-west-3","eu-west-2","eu-west-1","ap-northeast-2","ap-northeast-1","sa-east-1","ca-central-1",
			"ap-southeast-1","ap-southeast-2","eu-central-1","us-east-1","us-east-2","us-west-1","us-west-2"};
	
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
	
	public ArrayList<Datacenter> initializeDatacenters(){
		
		ArrayList<Datacenter> ans = new ArrayList<Datacenter>();
		
		for (int i = 0; i < serverLabels.length; i++) {
			Datacenter dc = getDatacenter(serverLabels[i]);
			ans.add(dc);
			
		}
		
		return ans;
		
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
