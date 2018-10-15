package edu.umb.cs.cs681.hw12;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable{
	
	private Path path;
	private AccessCounter accessCounter;
	private ArrayList<String> files;
	private boolean done = false;
	ReentrantLock lock = null;
	
	public RequestHandler(Path path, AccessCounter accessCounter){
		this.accessCounter = accessCounter;
		this.path = path;
		this.files = new ArrayList <> ();
		lock = new ReentrantLock();
	}
	
	public void setDone(){
		lock.lock();
		
		//System.out.println("in setDone: "+done);
		//System.out.println("Done SET");
		
		try{
			done = true;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {
		
		String path = this.path.toAbsolutePath().toString();
		
		//System.out.println(path);
		
		File dir = new File (path);
				
		if (dir.listFiles() == null){
			
			System.out.println("No files found.");

		} else if (dir.listFiles() != null) {
		
			for (File file : dir.listFiles()){
			
				try {
					files.add(file.getName());
				} catch(Exception e) {
					// 
				}
			}
			
			
			lock.lock();
			try{
				
				//System.out.println(done);
			
				// Detect if a thread is done==true.
				if(done==true){				
					System.out.println(Thread.currentThread().getName()+ ": terminated the handler");
					this.files.clear();
					
				}
			}finally{
				lock.unlock();
			}
		
				
			if (done==false){
				//choosing file at random
				//System.out.println("File size: "+files.size());
				int rand = new Random().nextInt(files.size());
				
				//System.out.println("Rand is: "+rand);
				String randFile = files.get(rand);
			//	System.out.println("Rand File is: "+randFile);
					
				try {
					
					Path path2 =  Objects.requireNonNull(Paths.get(randFile));
				
					accessCounter.increment(path2);
					accessCounter.getCount(path2); 
					//accessCounter.increment(path2);
					//accessCounter.getCount(path2); 
					
				} catch (Exception e){
					System.out.println(e);
				}
				
				}
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			//System.out.println(e);
		}
		
		
	}

}
