package edu.umb.cs.cs681.hw19;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Thread thread;
		ArrayList <Thread> threads = new ArrayList<>();
	
		ReentrantLock lock = new ReentrantLock();
		
		Path dir = Paths.get("src/edu/umb/cs/cs681/hw19/file_root");
		
		AccessCounter ac =  new AccessCounter(lock);
		
		RequestHandler requestHandle = new RequestHandler(dir, ac);
		
		for (int x=0; x < 10; x++){
			
			thread = new Thread(requestHandle);
			thread.start();
			
			threads.add(thread);
		}
		
		
		lock.lock();
		try {
			threads.forEach(
					(t) -> { 
						try {
							t.interrupt();
						} catch (Exception e) {e.printStackTrace();}	
					});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	
		requestHandle.setDone();
		
		threads.forEach(
				(t) -> { 
					try {
						t.join();
					} catch (Exception e) {e.printStackTrace();}	
				});
	}

}
