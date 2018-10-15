package edu.umb.cs.cs681.hw10_1;

import java.util.concurrent.locks.ReentrantLock;

public class SecurityGate {
	
	private int counter = 0;
	private static SecurityGate instance = null;
	private static ReentrantLock lock = new ReentrantLock();
	// private ReentrantLock counterLock = new ReentrantLock();

	public static SecurityGate getInstance() {

		lock.lock();

		try {

			if (instance == null) {
				instance = new SecurityGate();
			}

			return instance;

		} finally {
			lock.unlock();
		}
	}

	public void exit() {
		output("EXITED ");
		lock.lock();
		outputGate("locked");
		try {
			counter--;
		} finally {
			lock.unlock();
			outputGate("unlocked");
		}
	}

	public void enter() {
		output("ENTERED");
		lock.lock();
		outputGate("locked");
		try {
			counter++;
		} finally {
			lock.unlock();
			outputGate("unlocked");
		}
	}
	
	public int getCount()
	{
		return this.counter;
	}

	private void output(String tag) {
        System.out.println("\n"+Thread.currentThread().getName() + ":\n\t\t " + tag + 
        		"         (Exit count: " + getCount() + ")");
    }
	private void outputGate(String tag) {
        System.out.println( "\t\t GATE" + ": " + tag + 
        		"\t (Guests " + getCount() + ")");
    }
}
