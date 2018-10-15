package edu.umb.cs.cs681.hw01;


public class Client {

	public static void main(String[] args) {
			// TODO Auto-generated method stub
			System.out.println("WELCOME! \n");
			Observable observable = new Observable();
			observable.addObserver((Observable o, Object obj)->{System.out.println(obj);});
			observable.setChanged();
			observable.notifyObservers("***************************************\nClient Says Hello from Milky Way Galaxy\n***************************************");
			System.out.println("\nGOOD BYE!");
		}

	}

