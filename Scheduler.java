import java.util.*;
import java.io.*;
import java.net.URL;

public class Scheduler {

	public static void main(String[] args)throws Exception {
		int quantum = 2;
		int qty, timeDiff;
		int diffTotal = 0;
		float avgTurnAr;
		Boolean finished = false;
		String userInput;
		Scanner sc = new Scanner(System.in);
		Process tempProc = null;
		Process activeProc = null;
		Process[] processes;

		//read data file and populate list with jobs
		URL path = Scheduler.class.getResource("Data");
		File file = new File(path.toURI());
		Scanner fileInput = new Scanner(file);
		qty = fileInput.nextInt();
		processes = new Process[qty];

		//grab the process information and create the processes
		for(int i = 0; fileInput.hasNextLine() && i < processes.length; i++) {
			int arTi = fileInput.nextInt();
			int pr = fileInput.nextInt();
			int burTi = fileInput.nextInt();
			processes[i] = new Process(arTi, pr, burTi);
		}
		fileInput.close();

		//create priority queue
		compare pComp = new compare();
		PriorityQueue<Process> pQueue = new PriorityQueue(qty, pComp);

		//get user input for quantum
		System.out.println("Would you like to change the value for the Round Robin quantum?");
		System.out.println("Enter 'y' for yes or 'n' to use the default value of 2.");
		userInput = sc.next();
		if(userInput.equalsIgnoreCase("y")) {
			System.out.print("Please enter a POSITIVE value for the Round Robin quantum: ");
			quantum = sc.nextInt();
			//if incorrect input is given for quantum, default to a quantum of 2
			if(quantum < 0) {
				quantum = 2;
			}
		}
		sc.close();

		//Print the starting parameters of each process before emulation begins
		for(int i = 0; i < processes.length; i++){
			System.out.println(processes[i].toString());

		}

		//begin simlation
		for(int cycle = 0; finished == false; cycle ++) {

			System.out.println("CPU cycle: " + cycle);

			//first grab any arrivals from processes array and add them to the PriorityQueue
			for (int i = 0; i < processes.length; i++) {
				if(cycle == processes[i].arrivTime) {
					pQueue.add(processes[i]);
					System.out.println("\t\t" + processes[i].id + " has arrived.");
				}
			}

			//if pQueue has a process in it we can KEEP RUNNING
			if(!pQueue.isEmpty()) {
				activeProc = pQueue.poll();

				//we have ROUND ROBIN
				if(pQueue.peek() != null && activeProc.priority == pQueue.peek().priority) {
					System.out.println("\t\tTie in priority, performing Round Robin.");
					System.out.println("\t\tRunning this cycle: " + activeProc.id);
					activeProc.cyclesRemain--;
					activeProc.repeats++;

					//Make sure the active process hasnt passed the quantum
					if(activeProc.repeats == quantum) {
						activeProc.repeats = 0;
					}
					//put the process back in pQueue if it's not finished
					if(activeProc.cyclesRemain > 0) {
						pQueue.add(activeProc);
					}
					//if the process is done, set the out time and don't put it back into pQueue
					else {
						System.out.println("\t\t" + activeProc.id + " is done.");
						activeProc.outTime = cycle + 1;

					}
				} //end ROUND ROBIN if

				//NO ROUND ROBIN, just run
				else {
					System.out.println("\t\tRunning this cycle: " + activeProc.id);
					activeProc.cyclesRemain--;

					//put the process back in pQueue if it's not finished
					if(activeProc.cyclesRemain > 0) {
						pQueue.add(activeProc);
					}
					//if the process is done, set the out time and don't put it back into pQueue
					else {
						System.out.println("\t\t" + activeProc.id + " is done.");
						activeProc.outTime = cycle + 1;
					}
				}//end NO ROUND ROBIN else
			} //end KEEP RUNNING if

			//check to see if loop should continue
			if(pQueue.isEmpty() == true) {
				finished = true;
			}
		}//end simulation loop

		//print turn around times
		for(int i = 0; i < processes.length; i++) {
			timeDiff = processes[i].outTime - processes[i].arrivTime;
			diffTotal += timeDiff;

			System.out.print("\n" + processes[i].id + ":\tIn: " + processes[i].arrivTime + "\tOut: " + processes[i].outTime);
			System.out.println("\tDifference: " + timeDiff);
			//System.out.println(processes[i].toString());
		}

		avgTurnAr = (float)diffTotal / (float)processes.length;
		System.out.println("Difference Total: " + diffTotal);
		System.out.println("Average Turn-Around Time: " + avgTurnAr);
	}//end main

}

class compare implements Comparator <Process>{

	public int compare(Process p1, Process p2) {
		if(p1.priority > p2.priority) {
			return 1;
		}
		else if (p1.priority < p2.priority) {
			return -1;
		}
		return 0;
	}
}
