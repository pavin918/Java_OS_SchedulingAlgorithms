/** RoundRobinSchedulingAlgorithm.java
 * 
 * A scheduling algorithm that randomly picks the next job to go.
 *
 * @author: Kyle Benson
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class RoundRobinSchedulingAlgorithm extends BaseSchedulingAlgorithm {

	private Vector<Process> jobs;
	private int jobamount;
	private Process maxprocess;
	private Process removedp;
	private int timer;
    /** the time slice each process gets */
    private int quantum;

    RoundRobinSchedulingAlgorithm() {
        // Fill in this method
        /*------------------------------------------------------------*/
    	this.jobs = new Vector<Process>();
    	this.quantum = 10;
    	this.jobamount = 0;
    	this.removedp = null;
    	this.timer = 0;
    	this.maxprocess = null;

        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue. */
    public void addJob(Process p) {
        // Remove the next lines to start your implementation
        
        // Fill in this method
        /*------------------------------------------------------------*/
    	if(this.removedp != null && p.getArrivalTime() - 1 == this.removedp.getFinishTime())
    	{
    		this.jobs.add(0, p);
    	}
    	else if (this.jobs.isEmpty() || this.maxprocess == this.jobs.lastElement())
    	{
    		this.jobs.addElement(p);
//    		this.maxprocess = p;
    	}

//    	if (p.getPID() > this.maxprocess.getPID())
    	else
    	{
    		this.jobs.add(jobs.indexOf(this.maxprocess) + 1, p);
//    		this.maxprocess = p;
    	}
    	this.maxprocess = p;
    	this.jobamount++;
//    	for (int i = 0; i < jobs.size(); i++)
//    	{
//    		if (i < jobs.size() - 1 && p.getPID() > jobs.get(i).getPID() && p.getPID() < jobs.get(i + 1).getPID())
//    		{
//    			this.jobs.add(i + 1, p);
//    			break;
//    		}
//    		if (i == jobs.size() - 1)
//    		{
//    			jobs.add(p);
//    			break;
//    		}
//    	}
//    	this.jobamount++;
//    	else
//    	{
//    		for (int i = 0; i < this.jobs.size(); i++)
//    		{
//    			if (p.getPID() < this.jobs.get(i).getPID())
//    			{
//    				this.jobs.add(i, p);
//    			}
//    		}
//    	}

        /*------------------------------------------------------------*/
    }

    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p) {
        // Remove the next lines to start your implementation
        
        // Fill in this method
        /*------------------------------------------------------------*/
    	if (!jobs.isEmpty() && maxprocess.equals(p))
    	{
//    		System.out.println(maxprocess.getPID());
    		long mpid = 0; 
    		int pindex = jobs.indexOf(p);
    		for (int i = 0; i < jobs.size(); i++)
    		{
    			if (i != pindex && jobs.get(i).getPID() > mpid)
    			{
    				mpid = jobs.get(i).getPID();
    				pindex = i;
    			}
    		}
//    		System.out.println(pindex);
    		maxprocess = jobs.get(pindex);
    		removedp = p;
    	}
    	return jobs.remove(p);


        /*------------------------------------------------------------*/
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
    	for (char i = 0; i < jobs.size(); i++)
        {
        	Process p = jobs.remove(0);
        	otherAlg.addJob(p);
        }
    }

    /**
     * Get the value of quantum.
     * 
     * @return Value of quantum.
     */
    public int getQuantum() {
        return quantum;
    }

    /**
     * Set the value of quantum.
     * 
     * @param v
     *            Value to assign to quantum.
     */
    public void setQuantum(int v) {
        this.quantum = v;
    }

    /**
     * Returns the next process that should be run by the CPU, null if none
     * available.
     */
    public Process getNextJob(long currentTime) {
        // Remove the next lines to start your implementation
        
        // Fill in this method
        /*------------------------------------------------------------*/
    	if (jobs.isEmpty())
    	{
    		return null;
    	}
//    	for (int i = 0; i < jobs.size(); i++)
//    	{
//    		System.out.println(this.jobs.get(i).getPID());
//    	}
    	//timer++;
    	if (jobs.size() < jobamount)
    	{
    		timer = 0;
    		jobamount--;
    	}
    	if (this.timer == this.quantum)
    	{
    		this.jobs.addElement(this.jobs.remove(0));
    		timer = 0;
    	}
    	timer++;
        this.activeJob = jobs.firstElement();
        return this.activeJob;

        /*------------------------------------------------------------*/
    }

    public String getName() {
        return "Round Robin";
    }
    
}