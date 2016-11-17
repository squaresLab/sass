package omnet.components;

public class ServerFactory {
	
	int[] numServers;
	
	public ServerFactory(){
		numServers = new int[4];
	}
	
	public Server getA(){
		Server a = new Server("A"+numServers[0]++);
		a.setCost(.5);
		a.power = 150;
		a.powerPerNormal = 150.0 / 50.0;
		return a;
	}

	public Server getB(){
		Server b = new Server("B"+numServers[1]++);
		b.setCost(.7);
		b.power = 200;
		b.powerPerNormal = 200.0 / 130.0;
		return b;
	}
	
	public Server getC(){
		Server c = new Server("C"+numServers[2]++);
		c.setCost(1);
		c.power = 300;
		c.powerPerNormal = 300.0 / 150.0;
		return c;
	}
	
	public Server getD(){
		Server d = new Server("D"+numServers[3]++);
		d.setCost(.5);
		d.power = 80;
		d.powerPerNormal = 80.0 / 25.0;
		d.latency = 60;
		return d;
	}
	
	public int[] getNumServers(){
		return numServers;
	}
	
	public int getIndex(String location){
		int index = -1;
		
		switch (location) {
		case "A": index = 0; break;
		case "B": index = 1; break;
		case "C": index = 2; break;
		case "D": index = 3; break;
		}
		
		return index;
		
	}
}
