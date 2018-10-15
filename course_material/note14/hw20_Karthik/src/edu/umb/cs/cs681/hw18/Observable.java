package edu.umb.cs.cs681.hw18;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {
	
	public ArrayList<Observer> observerList;
	public boolean flag;
	ReentrantLock lock;
	
	public Observable() {
		observerList = new ArrayList<Observer>();
		flag = false;
		lock = new ReentrantLock();
	}
	
	public void addObserver(Observer o)
	{
		if(!observerList.contains(o))
		{
			observerList.add(Objects.requireNonNull(o));
			System.out.println("Observer added to List");
		}
		else
		{
			System.out.println("This Observer has already been added");
		}
	}
		
		public void deleteObserver(Observer o)
		{
			lock.lock();
			try {
				observerList.remove(o);
				System.out.println("Observer deleted from List");
			} finally  {
				lock.unlock();
			}
		}

		protected void setChanged()
		{
			lock.lock();
			flag = true;
			System.out.println("\nChanged Flag - set to True\n");
			lock.unlock();
		}

		protected void clearChanged()
		{
			lock.lock();
			flag = false;
			System.out.println("\nChanged Flag - cleared to False");
			lock.unlock();
		}

		public boolean hasChanged()
		{
			return flag;
		}
		
		public void notifyObservers(Object obj)
		{
			lock.lock();
			if(!hasChanged())
			{
				return;
			}
			lock.unlock();
			for(int i = 0; i<observerList.size(); i++)
			{
				observerList.get(i).update(this, obj);
			}
			clearChanged();
		}
		
		
		
	}

	
		
		
	


