package edu.umb.cs.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;
	
	public class CancellablePrimeNumberGenerator extends PrimeNumberGenerator
	{
		private boolean done = false;
		ReentrantLock lock = null;
		int count = 0;

		public CancellablePrimeNumberGenerator(long from, long to) {
			super(from, to);
			lock = new ReentrantLock();
		}
			
		public void setDone(){
			lock.lock();
			
			try{
				done = true;
			} finally {
				lock.unlock();
			}
		}

		public void run()
		{
			
			System.out.println("\n\nPrime numbers generated Under Thread-"+ ++count );
			//count = count + 1;
			for (long n = from; n <= to; n++) 
			{
				lock.lock();
				try {
						if(done==true)
						{
						
						System.out.println("Stopped generating Prime Numbers.");
						this.primes.clear();
						break; 
						}
						
				}
				
				 finally {
						lock.unlock();
					}
					
					
					if( isPrime(n) ){ 
						 
						this.primes.add(n); 
						
						System.out.print(n+", ");
					} 
					
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						
					}
					
					
				
				
				
			}
		}
	}



