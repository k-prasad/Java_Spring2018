package edu.umb.cs.cs681.hw21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrimeNumberGenExecutorTest {

	public static void main(String[] args) {
		InterruptiblePrimeNumberGenerator gen1 = new InterruptiblePrimeNumberGenerator(1L, 500000L);
		InterruptiblePrimeNumberGenerator gen2 = new InterruptiblePrimeNumberGenerator(500000L, 1000000L);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(gen1);
		executor.execute(gen2);
		executor.shutdown();		
		
		try {
			executor.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread-1 generated: " + gen1.getPrimes().size() + " prime numbers");
		System.out.println("Thread-2 generated: " + gen2.getPrimes().size() + " prime numbers");
	}
}

		
		