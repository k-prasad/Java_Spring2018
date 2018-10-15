package edu.umb.cs.cs681.hw21;

import java.util.concurrent.locks.ReentrantLock;

public class InterruptiblePrimeNumberGenerator extends PrimeNumberGenerator {

	public InterruptiblePrimeNumberGenerator(long from, long to) {
		super(from, to);
		// TODO Auto-generated constructor stub
	}
	
	private boolean done = false;
	ReentrantLock lock = new ReentrantLock();

	public void setDone()
	{
		done = true;
	}

	public void run() {
		for (long n = from; n <= to; n++) {
			lock.lock();
			try {
				if (done) {
					this.primes.clear();
					break; 
				} else {
					if (isPrime(n)) {
						this.primes.add(n);
					}
				}
			} finally {
				lock.unlock();
			}
		}
	}
}