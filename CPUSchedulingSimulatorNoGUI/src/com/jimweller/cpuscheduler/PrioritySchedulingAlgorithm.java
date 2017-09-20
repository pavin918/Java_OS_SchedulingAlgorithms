/** PrioritySchedulingAlgorithm.java
 * 
 * A single-queue priority scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

import com.jimweller.cpuscheduler.Process;

public class PrioritySchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
    
	private PriorityQueue<Process> jobs;
	private boolean preemptive;
	
    PrioritySchedulingAlgorithm(){
        // Fill in this method
        /*------------------------------------------------------------*/
    	this.activeJob = null;
    	this.jobs = new PriorityQueue<Process>(10, new Comparator<Process>()
    	{
    		public int compare(Process p, Process q)
    		{
    			long ppw = p.getPriorityWeight();
    			long qpw = q.getPriorityWeight();
    			long pat = p.getArrivalTime();
    			long qat = q.getArrivalTime();
    			
    			if (!preemptive)
    			{
	    			if (p.isStarted() && !q.isStarted())
	    			{
	    				return -1;
	    			}
	    			if (!p.isStarted() && q.isStarted())
	    			{
	    				return 1;
	    			}
    			}
    			if (ppw < qpw) //if p and q are in the ready queue
				{
					return -1;
				}
				if (ppw > qpw)
				{
					return 1;
				}
				if (p.getPID() <= q.getPID())
				{
					return -1;
				}
				return 1;
    		}
    	});
    	this.preemptive = false;

        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
        // Remove the next lines to start your implementation
        
        // Fill in this method
        /*------------------------------------------------------------*/
    	jobs.add(p);
//    	if (preemptive && jobs.peek().equals(p))
//    	{
//    		activeJob = p;
//    	}


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
        return "Single-Queue Priority";
    }

    /**
     * @return Value of preemptive.
     */
    public boolean isPreemptive(){
        // Remove the next lines to start your implementation
        
        // Fill in this method
        /*------------------------------------------------------------*/
        return preemptive;


        /*------------------------------------------------------------*/
    }
    
    /**
     * @param v Value to assign to preemptive.
     */
    public void setPreemptive(boolean v){
        // Remove the next lines to start your implementation
        
        // Fill in this method
        /*------------------------------------------------------------*/
        preemptive = v;


        /*------------------------------------------------------------*/
    }
    
}