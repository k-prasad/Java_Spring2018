package edu.umb.cs.cs681.hw10_2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class SecurityGate {
	private static SecurityGate instance;
	private static ReentrantLock lock = new ReentrantLock();
	private AtomicInteger counter = new AtomicInteger(0);
	
	
private SecurityGate (){}
	
	public static SecurityGate getInstance() {
		
		lock.lock();
		
		try {
		
			if (instance==null){
				instance = new SecurityGate();
			}
			
			return instance;
			
		} finally {
			lock.unlock();
		}
		
		
	}
	
	public void enter(){
		output("ENTERING");
		counter.updateAndGet(n -> n + 1);
		outputGate("INSIDE");
	}
	
	public void exit(){
		output("EXITING");
		counter.updateAndGet(n -> n - 1);
		outputGate("OUTSIDE");
	}
	
	public AtomicInteger getCount(){
		return this.counter;
	}
	
	
	private void output(String tag) {
        System.out.println("\n"+Thread.currentThread().getName() + ":\n\t\t " + tag + 
        		"         (Exit count: " + getCount() + ")");
    }
	private void outputGate(String tag) {
        System.out.println( "\t\t GUEST" + ": " + tag + 
        		"\t (Guests " + getCount() + ")");
    }

}
