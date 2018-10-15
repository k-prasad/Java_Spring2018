package edu.umb.cs.cs681.hw21;

import java.util.List;
import java.util.ArrayList;

public class PrimeNumberGenerator implements Runnable{
	protected long from, to;
	protected List<Long> primes;

	public PrimeNumberGenerator(long from, long to){
		if(from >= 1 && to >= from){
			this.primes = new ArrayList<Long>();
			this.from = from;
			this.to = to;
		}else{
			throw new RuntimeException("Wrong input");
		}
	}
	public List<Long> getPrimes(){ return primes; };
	
	protected boolean isEven(long n){
		if(n%2 == 0){ return true; }
		else{ return false; }
	}
	
	protected boolean isPrime(long n){
		if(n == 1){ return false; }
		if( n > 2 && isEven(n) ){ return false; }
		long i;
        for (i = (long) Math.sqrt(n); n%i != 0 && i >= 1; i--){}
        if (i == 1){ return true; }
        else{ return false; }
	}
	
	public void run(){
		for (long n = from; n <= to; n++) {
			if( isPrime(n) ){ primes.add(n); }
        }
	}
}
