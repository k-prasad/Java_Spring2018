package edu.umb.cs.cs681.hw03_2;

import java.util.ArrayList;
	public class Client {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
				ArrayList<Car> carList = new ArrayList<>();
				Car c1 = new Car("Tesla", 60000, 2018);
				carList.add(c1);
				Car c2 = new Car("Lexus", 40000, 2016);
				carList.add(c2);
				Car c3 = new Car("Honda", 20000, 2017);
				carList.add(c3);
				
				System.out.println("Welcome to the Car showroom!\n");
				carList.forEach((Car c)->System.out.println(" | " +c.getBrand()+ "  | " +c.getYear()+ " | " +c.getPrice()));
				int a = c1.getPrice()+c2.getPrice()+c3.getPrice(); 
				
				
				Integer carCount = carList.stream() 
						       .map( (Car car)-> car.getBrand() ) 
						       .reduce(0,
						               (result,carMaker)-> ++result,
						               (result,intermediateR)->result);
				System.out.println("\n Implementing count() with reduce() \n Number of cars are:  " +carCount);
				Integer priceAvg =  a/(carList.stream().map( (Car car)-> car.getBrand() )
						.reduce(0, (result,carMaker)-> ++result,(result,intermediateR)->result));
			System.out.println("\n Average price of car with reduce() is:  " +priceAvg);
			
			System.out.println("\nGOOD BYE!");
			}
		

		}


