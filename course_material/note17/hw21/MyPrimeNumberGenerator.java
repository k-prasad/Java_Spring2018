package edu.umb.cs.cs681.hw21;

import java.util.concurrent.locks.ReentrantLock;

public class MyPrimeNumberGenerator extends PrimeNumberGenerator {
	private boolean done = false;
	ReentrantLock lock = new ReentrantLock();

	public MyPrimeNumberGenerator(long from, long to) {
		super(from, to);
	}
	public void setDone() {
		done = true;
	}
	public void run() {
		for (long n = from; n <= to; n++) {
			lock.lock();
			try {
				if (done) {
					this.primes.clear();
					break; // Balk
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
