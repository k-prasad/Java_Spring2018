package edu.umb.cs.cs681.hw18;

import java.util.ArrayList;

public class Client {

	public static void main(String[] args) {
			// TODO Auto-generated method stub
		
			System.out.println("WELCOME! \n");
			Observable observable = new Observable();
		
			ArrayList<Thread> threads = new ArrayList<Thread>();
			
			Thread t1 = new Thread(()-> {observable.addObserver((Observable o, Object obj)->{System.out.println("Thread-1 "+obj);});});
			 threads.add(t1);
			 t1.start();
			 Thread t2 = new Thread(()-> {observable.addObserver((Observable o, Object obj)->{System.out.println("Thread-2 "+obj);});});
			 threads.add(t2);
			 t2.start();
			 Thread t3 = new Thread(()-> {observable.addObserver((Observable o, Object obj)->{System.out.println("Thread-3 "+obj);});});
			 threads.add(t3);
			 t3.start();
			 
			 threads.forEach((Thread t) -> {
					try {
						t.join();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			 
			 //observable.addObserver((Observable o, Object obj)->{System.out.println(obj);});
			
			observable.setChanged();
			observable.notifyObservers("**Hello from Milky Way**");
			
			
			System.out.println("\nGOOD BYE!");
		}

	}

