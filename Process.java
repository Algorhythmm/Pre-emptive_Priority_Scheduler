
public class Process {

	private static int numProcesses = 0;

	int arrivTime, outTime, priority, burstTime, cyclesRemain, totalTime, repeats;
	String id;

	public Process (int arrivTime, int priority, int burstTime) {
		this.arrivTime = arrivTime;
		this.priority = priority;
		this.burstTime = burstTime;
		this.cyclesRemain = burstTime;
		this.repeats = 0;
		this.id = "Process " + ++numProcesses;
	}

	public void SetOutTime(int outTime) {
		this.outTime = outTime;
		System.out.println(id + " is finished.");

	}

	// method used to return all information about the process
	// use for testing or to present initial and/or final values of each process
	public String toString() {
		String s = this.id + "\n";
		s += "Arrival Time: " + this.arrivTime + "\n";
		s += "Priority: " + this.priority + "\n";
		s += "Burst Time : " + this.burstTime + "\n";
		s += "Time Remaining : " + this.cyclesRemain + "\n";
		return s;
	}
}


