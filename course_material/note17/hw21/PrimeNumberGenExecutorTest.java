package edu.umb.cs.cs681.hw21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrimeNumberGenExecutorTest {

	public static void main(String[] args) {
		MyPrimeNumberGenerator gen1 = new MyPrimeNumberGenerator(1L, 500000L);
		MyPrimeNumberGenerator gen2 = new MyPrimeNumberGenerator(500000L, 1000000L);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(gen1);
		executor.execute(gen2);
		executor.shutdown();
		try {
			executor.awaitTermination(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Threads are interrrupted, So below two threads provides each time different output.");
		System.out.println("gen1 generated " + gen1.getPrimes().size() + " prime numbers.");
		System.out.println("gen2 generated " + gen2.getPrimes().size() + " prime numbers.");
	}
}

		
		