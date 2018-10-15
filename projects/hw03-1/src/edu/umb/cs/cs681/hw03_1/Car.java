package edu.umb.cs.cs681.hw03_1;

public class Car {
	
	private int price;
	private String brand;
	private int year;

	public Car(String brand,int price, int year)
	{
		this.price = price;
		this.brand = brand;
		this.year = year;
	}
	
	public void setPrice(int price)
	{
		this.price = price;
	}

	public int getPrice()
	{
		return this.price;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public String getBrand()
	{
		return this.brand;
	}
	
	public void setYear(int year)
	{
		this.year = year;
	}

	public int getYear()
	{
		return this.year;
	}

	

}
