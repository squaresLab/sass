import java.util.ArrayList;

public class SystemState {

	final int[] validL1Servers = { 1, 2, 3, 4 };
	final int[] validL2Servers = { 1, 2, 3, 4 };
	ArrayList<Integer> l1ServersInUse = new ArrayList<Integer>();
	ArrayList<Integer> l2ServersInUse = new ArrayList<Integer>();
	int currentReponseRate = 0;
	boolean usingHighTextResolution = true;
	final int maxDatabaseThreads = 5;
	int currentDatabaseAThreads = 1;
	int currentDatabaseBThreads = 1;

	public SystemState() {
		l1ServersInUse.add(new Integer(1));
		l1ServersInUse.add(new Integer(2));
		l2ServersInUse.add(new Integer(1));
		l2ServersInUse.add(new Integer(2));
	}

	public int[] getValidL1Servers() {
		return validL1Servers;
	}

	public int[] getValidL2Servers() {
		return validL2Servers;
	}

	public void addUsedServerL1(int serverNumber) {
		l1ServersInUse.add(new Integer(serverNumber));
	}

	public void addUsedServerL2(int serverNumber) {
		l2ServersInUse.add(new Integer(serverNumber));
	}

	public void removeUsedServerL1(int serverNumber) {
		l1ServersInUse.remove(new Integer(serverNumber));
	}

	public void removeUsedServerL2(int serverNumber) {
		l2ServersInUse.remove(new Integer(serverNumber));
	}

	public ArrayList<Integer> getUsedServersL1() {
		return l1ServersInUse;
	}

	public ArrayList<Integer> getUsedServersL2() {
		return l2ServersInUse;
	}

	public boolean getUsingHighTextResolution() {
		return usingHighTextResolution;
	}

	public void toogleUsingHighTextResolution() {
		if (usingHighTextResolution == false) {
			usingHighTextResolution = true;
		} else {
			usingHighTextResolution = false;
		}

	}

	public int getDatabaseAThreadCount() {
		return currentDatabaseAThreads;
	}

	public void setDatabaseAThreadCount(int count) {
		this.currentDatabaseAThreads = count;
	}

	public int getDatabaseBThreadCount() {
		return currentDatabaseBThreads;
	}

	public void setDatabaseBThreadCount(int count) {
		this.currentDatabaseBThreads = count;
	}

}
