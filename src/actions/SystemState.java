package actions;

import java.util.ArrayList;

public class SystemState {

	final int[] validL1Servers = { 1, 2, 3, 4 };
	final int[] validL2Servers = { 1, 2, 3, 4 };
	final int[] validL3Servers = { 1, 2, 3, 4 };
	final int[] validL4Servers = { 1, 2, 3, 4, 5 };
	final int[] validL5Servers = { 1, 2, 3, 4, 5, 6, 7 };
	final int[] validL6Servers = { 1, 2, 3, 4, 5, 6 };
	final int[] validL7Servers = { 1, 2 };
	final int[] validL8Servers = { 1, 2, 3, 4, 5, 6, 7, 8 };
	final int[] validL9Servers = { 1, 2, 3 };
	final int[] validL10Servers = { 1, 2, 3, 4, 5 };
	ArrayList<Integer> l1ServersInUse = new ArrayList<Integer>();
	ArrayList<Integer> l2ServersInUse = new ArrayList<Integer>();
	ArrayList<Integer> l3ServersInUse = new ArrayList<Integer>();
	ArrayList<Integer> l4ServersInUse = new ArrayList<Integer>();
	ArrayList<Integer> l5ServersInUse = new ArrayList<Integer>();
	ArrayList<Integer> l6ServersInUse = new ArrayList<Integer>();
	ArrayList<Integer> l7ServersInUse = new ArrayList<Integer>();
	ArrayList<Integer> l8ServersInUse = new ArrayList<Integer>();
	ArrayList<Integer> l9ServersInUse = new ArrayList<Integer>();
	ArrayList<Integer> l10ServersInUse = new ArrayList<Integer>();
	boolean usingHighTextResolution = true;
	final int maxDatabaseThreads = 5;
	int currentDatabaseAThreads = 1;
	int currentDatabaseBThreads = 1;

	public SystemState() {
		l1ServersInUse.add(new Integer(1));
		l1ServersInUse.add(new Integer(2));
		l2ServersInUse.add(new Integer(1));
		l2ServersInUse.add(new Integer(2));
		l3ServersInUse.add(new Integer(1));
	}

	public int[] getValidL1Servers() {
		return validL1Servers;
	}

	public int[] getValidL2Servers() {
		return validL2Servers;
	}

	public int[] getValidL3Servers() {
		return validL3Servers;
	}

	public int[] getValidL4Servers() {
		return validL4Servers;
	}

	public int[] getValidL5Servers() {
		return validL5Servers;
	}

	public int[] getValidL6Servers() {
		return validL6Servers;
	}

	public int[] getValidL7Servers() {
		return validL7Servers;
	}

	public int[] getValidL8Servers() {
		return validL8Servers;
	}

	public int[] getValidL9Servers() {
		return validL9Servers;
	}

	public int[] getValidL10Servers() {
		return validL10Servers;
	}

	public int getMaxServerCount() {
		return validL1Servers.length + validL2Servers.length
				+ validL3Servers.length + validL4Servers.length + validL5Servers.length
				+ validL6Servers.length + validL8Servers.length + validL8Servers.length
				+ validL9Servers.length + validL10Servers.length;
	}

	public int currentServerCount() {
		return l1ServersInUse.size() + l2ServersInUse.size()
				+ l3ServersInUse.size() + l4ServersInUse.size() + l5ServersInUse.size()
				+ l6ServersInUse.size() + l7ServersInUse.size() + l8ServersInUse.size()
				+ l9ServersInUse.size() + l10ServersInUse.size();
	}

	public void addUsedServerL1(int serverNumber) {
		l1ServersInUse.add(new Integer(serverNumber));
	}

	public void addUsedServerL2(int serverNumber) {
		l2ServersInUse.add(new Integer(serverNumber));
	}

	public void addUsedServerL3(int serverNumber) {
		l3ServersInUse.add(new Integer(serverNumber));
	}

	public void addUsedServerL4(int serverNumber) {
		l4ServersInUse.add(new Integer(serverNumber));
	}

	public void addUsedServerL5(int serverNumber) {
		l5ServersInUse.add(new Integer(serverNumber));
	}

	public void addUsedServerL6(int serverNumber) {
		l6ServersInUse.add(new Integer(serverNumber));
	}

	public void addUsedServerL7(int serverNumber) {
		l7ServersInUse.add(new Integer(serverNumber));
	}

	public void addUsedServerL8(int serverNumber) {
		l8ServersInUse.add(new Integer(serverNumber));
	}

	public void addUsedServerL9(int serverNumber) {
		l9ServersInUse.add(new Integer(serverNumber));
	}

	public void addUsedServerL10(int serverNumber) {
		l10ServersInUse.add(new Integer(serverNumber));
	}

	public void removeUsedServerL1(int serverNumber) {
		l1ServersInUse.remove(new Integer(serverNumber));
	}

	public void removeUsedServerL2(int serverNumber) {
		l2ServersInUse.remove(new Integer(serverNumber));
	}

	public void removeUsedServerL3(int serverNumber) {
		l3ServersInUse.remove(new Integer(serverNumber));
	}

	public void removeUsedServerL4(int serverNumber) {
		l4ServersInUse.remove(new Integer(serverNumber));
	}

	public void removeUsedServerL5(int serverNumber) {
		l5ServersInUse.remove(new Integer(serverNumber));
	}

	public void removeUsedServerL6(int serverNumber) {
		l6ServersInUse.remove(new Integer(serverNumber));
	}

	public void removeUsedServerL7(int serverNumber) {
		l7ServersInUse.remove(new Integer(serverNumber));
	}

	public void removeUsedServerL8(int serverNumber) {
		l8ServersInUse.remove(new Integer(serverNumber));
	}

	public void removeUsedServerL9(int serverNumber) {
		l9ServersInUse.remove(new Integer(serverNumber));
	}

	public void removeUsedServerL10(int serverNumber) {
		l10ServersInUse.remove(new Integer(serverNumber));
	}

	public ArrayList<Integer> getUsedServersL1() {
		return l1ServersInUse;
	}

	public ArrayList<Integer> getUsedServersL2() {
		return l2ServersInUse;
	}

	public ArrayList<Integer> getUsedServersL3() {
		return l3ServersInUse;
	}

	public ArrayList<Integer> getUsedServersL4() {
		return l4ServersInUse;
	}

	public ArrayList<Integer> getUsedServersL5() {
		return l5ServersInUse;
	}

	public ArrayList<Integer> getUsedServersL6() {
		return l6ServersInUse;
	}

	public ArrayList<Integer> getUsedServersL7() {
		return l7ServersInUse;
	}

	public ArrayList<Integer> getUsedServersL8() {
		return l8ServersInUse;
	}

	public ArrayList<Integer> getUsedServersL9() {
		return l9ServersInUse;
	}

	public ArrayList<Integer> getUsedServersL10() {
		return l10ServersInUse;
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
