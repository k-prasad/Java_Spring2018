package edu.umb.cs.cs681.hw04_1;

import java.util.ArrayList;

public class Client {
	
	public static void main(String[] args) 
	{
		System.out.println("Generating Prime numbers, Please wait...");
		PrimeNumberGenerator gen = new PrimeNumberGenerator(1L, 1000000L);
		
		Thread thread = new Thread(gen);
		try {
			thread.start();
			thread.join();
			
		} 
		catch (InterruptedException e) 
		{
			System.out.println("Interrupted Exception: "+e);
		}
		
		ArrayList<Long> primeList = new ArrayList<> ();
		gen.getPrimes().forEach(x -> primeList.add(x));
		System.out.println("\nPrime numbers have been added to a List. \nTotal number of primes in the list is = " + primeList.size());
		
		System.out.println("\nGOOD BYE!");
   
		
		
	}

}
