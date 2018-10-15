package edu.umb.cs.cs681.hw18;

import java.util.ArrayList;
import java.util.Objects;

public class Observable {
	
	public ArrayList<Observer> observerList;
	public boolean flag;
	
	public Observable() {
		observerList = new ArrayList<Observer>();
		flag = false;
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
			observerList.remove(o);
			System.out.println("Observer deleted from List");
		}

		protected void setChanged()
		{
			flag = true;
			System.out.println("\nChanged Flag - set to True\n");
		}

		protected void clearChanged()
		{
			flag = false;
			System.out.println("\nChanged Flag - cleared to False");
		}

		public boolean hasChanged()
		{
			return flag;
		}
		
		public void notifyObservers(Object obj)
		{
			if(!hasChanged())
			{
				return;
			}
			for(int i = 0; i<observerList.size(); i++)
			{
				observerList.get(i).update(this, obj);
			}
			clearChanged();
		}
		
		
		
	}

	
		
		
	


