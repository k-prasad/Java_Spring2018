package edu.umb.cs.cs681.hw06;

import java.util.ArrayList;

public class MCTest {
	public static void main (String[] args)
	{
		 long start = System.currentTimeMillis();
	    ArrayList<Thread> threads = new ArrayList<Thread>();
	    System.out.println("Welcome! \nThis might take a while.");
	    
	    ArrayList<String> numThreads = new ArrayList<String>();
	    numThreads.add("1");  
	    numThreads.add("2");
	    numThreads.add("4"); 
	    numThreads.add("6");  
	    
	    for(int k = 0; k < numThreads.size(); k++){
	    
		    final long nTimes  = Long.parseLong("10000000000");
		    final int nThread = Integer.parseInt(numThreads.get(k));
		
		    System.out.println("\nRunning "+ numThreads.get(k) + " threads. Please wait...");
		    
		    for (int i = 0; i < nThread; i++) {
		      Thread t = new Thread(
		    		  
		    		 () -> { 
		    			 	
		    			 	int n = 25;
		    		  		for (long j = 0; j < nTimes; j++) {n *= 25;};
		    		  		System.out.println("Under "+Thread.currentThread().getName() + 
		    		  				", Number of calculations made is : " + n);
		    		  })
		    		  ;
		      threads.add(t);
		      t.start();
		    }
		    
		    threads.forEach(
		    		(Thread thread)->{
		    			try {
		    				thread.join();
		    			} catch (Exception e) {
		    				e.printStackTrace();
		    			}
		    			});
	    }
	    
	    long time = System.currentTimeMillis() - start;
	    
	    System.out.println("\nTotal time taken to execute all threads is:  " +time/1000+ " seconds" );
	    
	    
  }

}
