package edu.umb.cs.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;

public class Client {
	static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) 
	{
	// TODO Auto-generated method stub
	System.out.println("WELCOME to Cancellable Prime number Generator! ");
	Thread thread1, thread2;
	CancellablePrimeNumberGenerator cancelPrimeGen = new CancellablePrimeNumberGenerator(1L, 100L);

	thread1=new Thread(cancelPrimeGen,"First Thread");thread2=new Thread(cancelPrimeGen,"Second Thread");

	thread1.start();
	System.out.println("\nFIRST thread began execution");
	thread2.start();
	System.out.println("SECOND thread began execution");

	
	lock.lock();try
		{
		thread1.interrupt();
		thread2.interrupt();
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
		lock.unlock();
			}

	cancelPrimeGen.setDone();

	try
	{
		thread1.join();
		thread2.join();
	}
	catch(InterruptedException ie)
	{
		ie.printStackTrace();
	}

	System.out.println("\n"+cancelPrimeGen.getPrimes().size()+" prime numbers were generated.");

}

}
