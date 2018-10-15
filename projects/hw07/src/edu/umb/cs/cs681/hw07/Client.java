package edu.umb.cs.cs681.hw07;

import java.util.ArrayList;

public class Client {
			
	public static void main(String[] args) throws Exception {
				System.out.println("Welcome!");
		ArrayList<Thread> threads = new ArrayList<Thread>();
				
				CancellablePrimeNumberGenerator cpng = new CancellablePrimeNumberGenerator(1L, 25L);
				
			    final int nThread = 4;
			   
			    
			    for (int i = 0; i < nThread; i++) {
			        Thread t = new Thread(cpng);
			       
			        threads.add(t);
			        t.start();
			       
			      }
			    
			      
			    
			      threads.forEach(
			    		  (Thread thread)->{
			    			  try {
			    			  		thread.join();
			    			  } catch (Exception e) {
			    				  	e.printStackTrace();}
			    		  }
			      );
			      
			      System.out.println("\n\nTotal of " + cpng.getPrimes().size() + " prime numbers were found from (1 to 25), by running " + 
			    	       + nThread + " threads");
			      
			    
			   
			}

		


	}


