# Pre-emptive_Priority_Scheduler

This is an assignment I completed while enrolled at SHSU.

prompt:
       
    You are to implement a simple uni-processor scheduling simulator.  Your simulator will step through a sequence of time units,
    performing the actions of a simple operating system scheduler.
       
    The main input to your simulator will be a file containing process-information.
    Each line in the file will be of the form:     Arrive Priority CPU_Time   
       
    Examples: 
               process that arrives at time 0, has a priority of 5 and has one CPU burst of duration 8:	0 5 8
               process that arrives at time 2, has a priority of 8 and has a CPU burst of duration 72:	2 8 72
       
    An example of a complete input file is given below.  It contains two of the processes found above in addition to a 3rd
    with first line containing a single value indicating how many processes there are:
	          3
	          0 5 8
	          2 8 72
	          5 2 7

    Some helpful notes/limitations:
    1) Arrival times will be strictly increasing in the input file.
    2) There will be at most 100 processes.
    3) Lower numbers imply higher priority, with the highest priority a 0 and the lowest priority a 9.
    
    Your scheduler should be a preemptive priority scheduler with round-robin as a secondary scheduling criteria.
    The best solution will allow the user to vary the time quantum but a default of 2 should be used for quantum.
    
    Include enough output of your program to show processes being dispatched, preempted, completed, etc.  
    At the end of the simulation, output the turnaround time for each process as well as average turnaround time.

###Don't Cheat###

This is an assignment I completed while attending SHSU. If you're viewing this code with the intent to cheat, you'll likely be caught.
