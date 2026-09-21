package com.nhlstenden.appstore.model;

public class App
{

	private final String name;
	private final double price;
	private final boolean violence;
	private final boolean nudity;

	public App(String name, double price, boolean violence, boolean nudity)
	{
		this.name = name;
		this.price = price;
		this.violence = violence;
		this.nudity = nudity;
	}

	public int getMinimumAge()
	{
		int minimumAge = 0;
		if (violence)
		{
			minimumAge = 16;
		}
		if (nudity)
		{
			minimumAge = 18;
		}
		return minimumAge;
	}

	public String getName()
	{
		return name;
	}

	public double getPrice()
	{
		return price;
	}

	public boolean hasViolence()
	{
		return violence;
	}

	public boolean hasNudity()
	{
		return nudity;
	}
}