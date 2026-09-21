package com.nhlstenden.appstores.appstore;

public class App
{
    private static final int MINIMUM_VIOLANCE_APP_AGE_VALUE = 16;
    private static final int MINIMUM_NUDITY_APP_AGE_VALUE = 18;
    private String name;
    private double price;
    private boolean containsViolence;
    private boolean containsNudity;

    public App(String name, double price, boolean containsViolence, boolean containsNudity)
    {
        this.setName(name);
        this.setPrice(price);
        this.setContainsViolence(containsViolence);
        this.setContainsNudity(containsNudity);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("name cannot be null or blank");
        }

        this.name = name;
    }

    public double getPrice()
    {
        return this.price;
    }

    public void setPrice(double price)
    {
        if (price < 0.0)
        {
            throw new IllegalArgumentException("price cannot be negative");
        }

        this.price = price;
    }

    public boolean containsViolence()
    {
        return this.containsViolence;
    }

    public void setContainsViolence(boolean containsViolence)
    {
        this.containsViolence = containsViolence;
    }

    public boolean containsNudity()
    {
        return this.containsNudity;
    }

    public void setContainsNudity(boolean containsNudity)
    {
        this.containsNudity = containsNudity;
    }

    public boolean isAgeEligibleForViolence(int age)
    {
        return age >= MINIMUM_VIOLANCE_APP_AGE_VALUE;
    }

    public boolean isAgeEligibleForNudity(int age) {
        return age >= MINIMUM_NUDITY_APP_AGE_VALUE;
    }
}
