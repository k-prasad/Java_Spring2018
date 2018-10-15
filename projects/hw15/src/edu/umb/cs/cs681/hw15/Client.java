package edu.umb.cs.cs681.hw15;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Client {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Thread thread;
		ArrayList <Thread> threads = new ArrayList<>();
	
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		
		Path dir = Paths.get("src/edu/umb/cs/cs681/hw15/file_root");
		
		AccessCounter accessCount =  new AccessCounter(lock);
		
		RequestHandler requestHandle = new RequestHandler(dir, accessCount);
		
		for (int x=0; x < 10; x++){
			
			thread = new Thread(requestHandle);
			thread.start();
			
			threads.add(thread);
		}
		
	
		lock.writeLock().lock();
		try {
			for (Thread t: threads){
				t.interrupt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
		
		requestHandle.setDone();
		
		
		
		try {
			for (Thread t: threads){
				t.join();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();}
		
	}

}
