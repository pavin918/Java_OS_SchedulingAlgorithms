/** FCFSSchedulingAlgorithm.java
 * 
 * A first-come first-served scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class FCFSSchedulingAlgorithm extends BaseSchedulingAlgorithm {
	
	//private LinkedBlockingQueue<Process> jobs;
	private PriorityQueue<Process> jobs;

    FCFSSchedulingAlgorithm(){
        // Fill in this method
        /*------------------------------------------------------------*/
    	this.activeJob = null;
    	this.jobs = new PriorityQueue<Process>(10, new Comparator<Process>()
    	{
    		public int compare(Process p, Process q)
    		{
    			long a = p.getArrivalTime();
    			long b = q.getArrivalTime();
    			if (a != b)
    			{
    				if (a < b)
    				{
    					return -1;
    				}
    				return 1;
    			}
    			if (p.getPID() <= q.getPID())
    			{
    				return -1;
    			}
    			return 1;
    		}
    	});

        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
        // Remove the next lines to start your implementation
        
        // Fill in this method
        /*------------------------------------------------------------*/
    	if (p.getBurstTime() > 0)
    	{	
    		jobs.add(p);
    	}

        /*------------------------------------------------------------*/
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
        // Remove the next lines to start your implementation

        // Fill in this method
        /*------------------------------------------------------------*/
        return jobs.remove(p);


        /*------------------------------------------------------------*/
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
        for (char i = 0; i < jobs.size(); i++)
        {
        	Process p = jobs.poll();
        	otherAlg.addJob(p);
        }
    }

    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime){
        // Remove the next lines to start your implementation
        
        // Fill in this method
        /*------------------------------------------------------------*/
    	activeJob = jobs.peek();
    	return activeJob;


        /*------------------------------------------------------------*/
    }

    public String getName(){
        return "First-Come First-Served";
    }
    
}