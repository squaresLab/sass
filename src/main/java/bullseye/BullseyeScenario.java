package bullseye;

import java.util.Random;

public class BullseyeScenario {

	private static double[] defaultParams = new double[] {0.05, 0.10, 0.05, 0.10, 0.20, 1, 1, 1};

	double exploitPayObs;
	double exploitPOSObs;
	double exploitWebObs;
	double phishVendorObs;
	double phishEmployeeObs;
	
	double webPresenceVal;
	double payPresenceVal;
	double posPresenceVal;
	
	Random rand;
	
	public BullseyeScenario() {
		setParams(BullseyeScenario.defaultParams);
		rand = new Random();
	}
	
	public BullseyeScenario(long seed) {
		this();
		rand = new Random(seed);
	}
	
	public void mutate() {
		double[] params = toArray();
		
		int index = rand.nextInt(params.length);
		
		double delta = rand.nextGaussian() * .1;
		
		params[index] += delta;
		
		params[index] = Math.max(params[index], 0);
		
		if (index < 5) {
			params[index] = Math.min(params[index], 1);
		}
		setParams(params);
	}
	
	public void setParams(double[] params) {
		
		exploitPayObs = params[0];
		exploitPOSObs = params[1];
		exploitWebObs = params[2];
		phishVendorObs = params[3];
		phishEmployeeObs = params[4];
		
		webPresenceVal = params[5];
		payPresenceVal = params[6];
		posPresenceVal = params[7];
		
	}
	
	public String toString() {
		String ans = "";
		
		double[] array = toArray();
		
		for (int i = 0; i < array.length; i++) {
			ans += array[i];
			if (i < array.length-1) {
				ans += ",";
			}
		}
		
		return ans;
	}
	
	private double[] toArray() {
		double[] ans = new double[8];
		
		ans[0] = exploitPayObs;
		ans[1] = exploitPOSObs;
		ans[2] = exploitWebObs;
		ans[3] = phishVendorObs;
		ans[4] = phishEmployeeObs;
		
		ans[5] = webPresenceVal;
		ans[6] = payPresenceVal;
		ans[7] = posPresenceVal;	
		
		return ans;
	}

	public double getExploitPayObs() {
		return exploitPayObs;
	}

	public double getExploitPOSObs() {
		return exploitPOSObs;
	}

	public double getExploitWebObs() {
		return exploitWebObs;
	}

	public double getPhishVendorObs() {
		return phishVendorObs;
	}

	public double getPhishEmployeeObs() {
		return phishEmployeeObs;
	}

	public double getWebPresenceVal() {
		return webPresenceVal;
	}

	public double getPayPresenceVal() {
		return payPresenceVal;
	}

	public double getPosPresenceVal() {
		return posPresenceVal;
	}
	
}
