package edu.umb.cs.cs681.hw10_1;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			
			Thread t1 = new Thread(new Guest(), "First  Guest");
			t1.start();
			
			Thread t2 = new Thread(new Guest(), "Second Guest");
			t2.start();
			
			Thread t3 = new Thread(new Guest(), "Third  Guest");
			t3.start();
		
			
			try {
				
				t1.join();
				t2.join();
				t3.join();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}



	}

