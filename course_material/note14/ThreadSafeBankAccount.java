
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount {
	private double accBal = 0;
	private ReentrantLock lock;
	private ReentrantLock withdrawLock;
	private ReentrantLock depositLock;
	private Condition accOverflowCondition, accNegativeCondition;
	private ThreadSafeBankAccount acc;

	public ThreadSafeBankAccount() {
		lock = new ReentrantLock();
		withdrawLock = new ReentrantLock();
		depositLock = new ReentrantLock();
		accOverflowCondition = lock.newCondition();
		accNegativeCondition = lock.newCondition();
		acc = this;
	}

	public void deposit(double amount) {
		depositLock.lock();
		try {
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getName() + " (d): current accBal: " + accBal);
			while (accBal >= 300) {
				System.out.println(Thread.currentThread().getName() + " (d): await(): accBal Overflow");
				accNegativeCondition.await();
			}
			accBal += amount;
			System.out.println(Thread.currentThread().getName() + " (d): new accBal: " + accBal);
			accOverflowCondition.signalAll();
		} catch (InterruptedException exception) {
			System.out.println(Thread.currentThread().getName() + " Thread interrupted");
		} finally {
			depositLock.unlock();
			System.out.println("Lock released");
		}
	}

	public void withdraw(double amount) {
		withdrawLock.lock();
		try {
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getName() + " (w): current accBal: " + accBal);
			while (accBal <= 0) {
				System.out.println(Thread.currentThread().getName() + " (w): await(): Insufficient funds");
				accOverflowCondition.await();
			}
			accBal -= amount;
			System.out.println(Thread.currentThread().getName() + " (w): new accBal: " + accBal);
			accNegativeCondition.signalAll();
		} catch (InterruptedException exception) {
			System.out.println(Thread.currentThread().getName() + " Thread interrupted");
		} finally {
			withdrawLock.unlock();
			System.out.println("Lock released");
		}
	}

	public static void main(String[] args) {
		ThreadSafeBankAccount bankacc = new ThreadSafeBankAccount();
		try {
			Thread t1 = new Thread(bankacc.new DepositRunnable());
			t1.start();
			Thread t2 = new Thread(bankacc.new DepositRunnable());
			t2.start();
			
			Thread t3 = new Thread(bankacc.new WithdrawRunnable());
			t3.start();
			Thread t4 = new Thread(bankacc.new WithdrawRunnable());
			t4.start();
			

			t1.interrupt();
			t2.interrupt();
			t3.interrupt();
			t4.interrupt();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private class DepositRunnable implements Runnable {
		private boolean done = false;

		public void run() {
			try {
				lock.lock();
				for (int i = 0; i < 10 && !done; i++) {
					acc.deposit(100);
					Thread.sleep(2);
				}
			} catch (InterruptedException exception) {
				done = true;
				System.out.println(Thread.currentThread().getName() + " Thread interrupted");
			} finally {
				lock.unlock();
			}
		}
	}

	private class WithdrawRunnable implements Runnable {
		private boolean done = false;

		public void run() {
			try {
				lock.lock();
				for (int i = 0; i < 10 && !done; i++) {
					acc.withdraw(100);
					Thread.sleep(2);
				}
			} catch (InterruptedException exception) {
				done = true;
				System.out.println(Thread.currentThread().getName() + " Thread interrupted");
			} finally {
				lock.unlock();
			}
		}
	}
}
