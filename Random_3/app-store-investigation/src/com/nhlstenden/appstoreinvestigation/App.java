package com.nhlstenden.appstoreinvestigation;

public class App
{
    private String name;
    private double price;
    private boolean hasViolence;
    private boolean hasNudity;

    public App(String name, double price, boolean hasViolence, boolean hasNudity)
    {
        this.setName(name);
        this.setPrice(price);
        this.setHasViolence(hasViolence);
        this.setHasNudity(hasNudity);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        this.name = name;
    }

    public double getPrice()
    {
        return this.price;
    }

    public void setPrice(double price)
    {
        if (price < 0)
        {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        this.price = price;
    }

    public boolean hasViolence()
    {
        return this.hasViolence;
    }

    public void setHasViolence(boolean hasViolence)
    {
        this.hasViolence = hasViolence;
    }

    public boolean hasNudity()
    {
        return this.hasNudity;
    }

    public void setHasNudity(boolean hasNudity)
    {
        this.hasNudity = hasNudity;
    }

    public int getMinimumAge()
    {
        if (this.hasNudity)
        {
            return 18;
        }

        if (this.hasViolence)
        {
            return 16;
        }

        return 0;
    }
}