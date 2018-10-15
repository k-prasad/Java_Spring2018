package edu.umb.cs.cs681.hw04_2;

import java.util.ArrayList;

public class Client {
		
		public static void main(String[] args) 
		{
			System.out.println("Welcome to prime number generater!");
			
			PrimeNumberGenerator gen1 = new PrimeNumberGenerator(1L, 1000000L);
			PrimeNumberGenerator gen2 = new PrimeNumberGenerator(1000001L, 2000000L);
			Thread t1 = new Thread(gen1);
			Thread t2 = new Thread (gen2);
			
			try {
				System.out.println("\nGenerating Prime numbers from 1 to 1000000. ");
				t1.start(); 
				System.out.println("AND \nGenerating Primes from from 1000001 to 2000000. \n\nPlease Wait...");
				t2.start();
			
				t1.join(); 
				t2.join();
			
			} catch (InterruptedException e)
			{
				System.out.println("Interrupted Exception: "+e);
			}
			
			ArrayList<Long> list = new ArrayList<> ();
			gen1.getPrimes().forEach(x -> list.add(x));
			gen2.getPrimes().forEach(x -> list.add(x));
			
			System.out.println("\nThe Prime numbers have been added to a big List. \nTotal number of primes is =  " + list.size());
			System.out.println("\nThe first prime number generated is: ");
			
			list.stream()
	        .filter(x -> x.equals(2L))
	        .forEach(System.out::println);
			
			System.out.println("\nThe Last prime number generated is: ");
			list.stream()
	        .filter(x -> x.equals(1999993L))
	        .forEach(System.out::println);
			
			System.out.println("\nGOOD BYE!");
	   
			
		}


}
