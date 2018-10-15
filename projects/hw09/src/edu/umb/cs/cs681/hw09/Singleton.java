package edu.umb.cs.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class Singleton {

	private static Singleton instance = null;
	private static ReentrantLock lock = new ReentrantLock();
	private static int counter = 0;	
	
	private Singleton()
	{
		if (this.getClass() == Singleton.class) 
		{
			lock.lock();
			try {
				counter++;
				} finally 
						{
						lock.unlock();
						}
		}
		
	};
	
	// Factory method to create or return a singleton instance
		public static Singleton getInstance(){
			
			lock.lock();
			
			try {
				
				if(instance==null){
					instance = new Singleton();
					
					System.out.println("A new instance of singleton created by a thread\n ");
				}
				
				System.out.println(Thread.currentThread().getName() + " is executing.");
				System.out.println("\nThe running instance is: "+instance+ "\n");
				
			} finally {
				lock.unlock();
						}
			
			
			return instance;
		}
		
		

		public static String getCounter() {
			// TODO Auto-generated method stub
			return "\nTotal number of instances: "+ counter;
			
		}
	
}
