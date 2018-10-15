package edu.umb.cs.cs681.hw10_1;

public class Guest implements Runnable {

	private SecurityGate gate;

	public Guest() {
		gate = SecurityGate.getInstance();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		gate.enter();

		gate.exit();

		gate.getCount();

	}
	
	
}
