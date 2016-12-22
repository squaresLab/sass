package omnet.components;

public class ServerFactory {
	
	int[] numServers;
	
	public ServerFactory(){
		numServers = new int[16];
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
		b.setCost(7);
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
	
	public Server getE(){
		Server a = new Server("E"+numServers[4]++);
		a.setCost(.5);
		a.power = 150;
		a.powerPerNormal = 150.0 / 50.0;
		return a;
	}

	public Server getF(){
		Server b = new Server("F"+numServers[5]++);
		b.setCost(.7);
		b.power = 200;
		b.powerPerNormal = 200.0 / 130.0;
		return b;
	}
	
	public Server getG(){
		Server c = new Server("G"+numServers[6]++);
		c.setCost(1);
		c.power = 300;
		c.powerPerNormal = 300.0 / 150.0;
		return c;
	}
	
	public Server getH(){
		Server d = new Server("H"+numServers[7]++);
		d.setCost(.5);
		d.power = 80;
		d.powerPerNormal = 80.0 / 25.0;
		d.latency = 60;
		return d;
	}
	
	public Server getI(){
		Server a = new Server("I"+numServers[8]++);
		a.setCost(.5);
		a.power = 150;
		a.powerPerNormal = 150.0 / 50.0;
		return a;
	}

	public Server getJ(){
		Server b = new Server("J"+numServers[9]++);
		b.setCost(.7);
		b.power = 200;
		b.powerPerNormal = 200.0 / 130.0;
		return b;
	}
	
	public Server getK(){
		Server c = new Server("K"+numServers[10]++);
		c.setCost(1);
		c.power = 300;
		c.powerPerNormal = 300.0 / 150.0;
		return c;
	}
	
	public Server getL(){
		Server d = new Server("L"+numServers[11]++);
		d.setCost(.5);
		d.power = 80;
		d.powerPerNormal = 80.0 / 25.0;
		d.latency = 60;
		return d;
	}
	
	public Server getM(){
		Server a = new Server("M"+numServers[12]++);
		a.setCost(.5);
		a.power = 150;
		a.powerPerNormal = 150.0 / 50.0;
		return a;
	}

	public Server getN(){
		Server b = new Server("N"+numServers[13]++);
		b.setCost(.7);
		b.power = 200;
		b.powerPerNormal = 200.0 / 130.0;
		return b;
	}
	
	public Server getO(){
		Server c = new Server("O"+numServers[14]++);
		c.setCost(1);
		c.power = 300;
		c.powerPerNormal = 300.0 / 150.0;
		return c;
	}
	
	public Server getP(){
		Server d = new Server("P"+numServers[15]++);
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
		case "E": index = 4; break;
		case "F": index = 5; break;
		case "G": index = 6; break;
		case "H": index = 7; break;
		case "I": index = 8; break;
		case "J": index = 9; break;
		case "K": index = 10; break;
		case "L": index = 11; break;
		case "M": index = 12; break;
		case "N": index = 13; break;
		case "O": index = 14; break;
		case "P": index = 15; break;
		}
		
		return index;
		
	}
}
