package edu.umb.cs.cs681.hw19;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
//import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable{
	
	private Path path;
	private AccessCounter accessCounter;
	private ArrayList<String> files;
	private volatile boolean done = false;
	
	public RequestHandler(Path path, AccessCounter accessCounter){
		this.accessCounter = accessCounter;
		this.path = path;
		this.files = new ArrayList <> ();
		//lock = new ReentrantLock();
	}
	
	public void setDone(){
	
		done = true;
		
	}

	@Override
	public void run() {
		
		String path = this.path.toAbsolutePath().toString();
		
		//System.out.println(path);
		
		File dir = new File (path);
				
		if (dir.listFiles() == null){
			
			System.out.println("No files found");

		} else if (dir.listFiles() != null) {
		
			for (File file : dir.listFiles()){
			
				try {
					files.add(file.getName());
				} catch(Exception e) {}
			}
			
				if(done==true)
				{				
					System.out.println(Thread.currentThread().getName()+ ": terminated the handler");
					this.files.clear();
					
				}
				
			
				else {
				
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
