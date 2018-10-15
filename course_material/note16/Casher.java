package edu.umb.cs.threads.futures;
 
public class Casher{
	private Future future = new Future();

	public Pizza order(){
		System.out.println("An order is made.");
		Thread thread = new Thread(()->{RealPizza realPizza = new RealPizza();
										future.setRealPizza( realPizza );});
		thread.start();
//		new Thread(){
//			public void run(){
//				RealPizza realPizza = new RealPizza();
//				future.setRealPizza( realPizza );
//			}
//		}.start();
		return future;
	}

	public static void main(String[] args){
		Casher casher = new Casher();
		System.out.println("Ordering pizzas at a casher counter.");
		Pizza p1 = casher.order();
		
		System.out.println("Doing something, playing with a smartphone, etc., " +
				"until pizzas are ready to pick up...");
		try{
			Thread.sleep(2000);
		}
		catch(InterruptedException e){}
		
		System.out.println("Let's see if pizzas are ready to pick up...");
		System.out.println( p1.getPizza() );
	}
}
