package edu.umb.cs.cs681.hw03_1;

import java.util.*;


public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			ArrayList<Car> carList = new ArrayList<>();
			Car c1 = new Car("Tesla", 97000, 2018);
			carList.add(c1);
			Car c2 = new Car("Lexus", 56000, 2016);
			carList.add(c2);
			Car c3 = new Car("Honda", 36000, 2017);
			carList.add(c3);
			
			System.out.println("Welcome to the Car showroom!\n");
		
			carList.forEach((Car c)->System.out.println(" | " +c.getBrand()+ "  | " +c.getYear()+ " | " +c.getPrice()));
			
			Integer minPrice = carList.stream()
					.map((Car car)-> car.getPrice())
					.min(Comparator.comparing(price2-> price2 ))
					.get();

			System.out.println("\n Using min() in the Stream API the Car price is: \n " +minPrice);

			
			Integer maxPrice = carList.stream()
					.map((Car car)-> car.getPrice())
					.max(Comparator.comparing(price2-> price2 ))
					.get();

			System.out.println("\n Using max() in the Stream API the Car price is: \n " +maxPrice);
			
			Integer lowestPrice = carList.stream()
					.map((Car car)-> car.getPrice())
					.reduce(0, (result, carPrice)->
					{
						if(result==0) return carPrice;
						else if(carPrice < result) return carPrice;
						else return result;
					} );
		
			System.out.println("\n Using reduce() in the Stream API the Car price is: \n " +lowestPrice);
			
			System.out.println("\nGOOD BYE!");
			
			
		}
	

	}


