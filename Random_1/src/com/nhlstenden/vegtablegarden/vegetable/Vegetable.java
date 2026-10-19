package com.nhlstenden.vegtablegarden.vegetable;

public abstract class Vegetable
{
    private double sizeInCm;
    private double cmPerLux;
    private double cmPerMillimeter;
    private int ripeLength;
    private Color color;
    private Status status;

    public Vegetable(double cmPerLux, double cmPerMillimeter, int ripeLength, Color color)
    {
        this.setCmPerLux(cmPerLux);
        this.setCmPerMillimeter(cmPerMillimeter);
        this.setRipeLength(ripeLength);
        this.setColor(color);

        this.sizeInCm = 0;
        this.status = Status.GROWING;
    }

    public double getCmPerLux()
    {
        return this.cmPerLux;
    }

    public void setCmPerLux(double cmPerLux)
    {
        if (cmPerLux < 0.0)
        {
            throw new IllegalArgumentException("centimeter per lux can not be negative");
        }

        this.cmPerLux = cmPerLux;
    }

    public double getCmPerMillimeter()
    {
        return this.cmPerMillimeter;
    }

    public void setCmPerMillimeter(double cmPerMillimeter)
    {
        if (cmPerMillimeter < 0.0)
        {
            throw new IllegalArgumentException("centimeter per millimeter can not be negative");
        }

        this.cmPerMillimeter = cmPerMillimeter;
    }

    public int getRipeLength()
    {
        return this.ripeLength;
    }

    public void setRipeLength(int ripeLength)
    {
        if (ripeLength < 0)
        {
            throw new IllegalArgumentException("ripe length can not be negative");
        }

        this.ripeLength = ripeLength;
    }

    public Color getColor()
    {
        return this.color;
    }

    public void setColor(Color color)
    {
        if (color == null)
        {
            throw new IllegalArgumentException("color can not be null");
        }

        this.color = color;
    }

    public Status getStatus()
    {
        return this.status;
    }

    protected void setStatus(Status status)
    {
        if (status == null)
        {
            throw new IllegalArgumentException("status can not be null");
        }

        this.status = status;
    }

    public double getSizeInCm()
    {
        return this.sizeInCm;
    }

    public void setSizeInCm(double sizeInCm)
    {
        if (sizeInCm < 0.0)
        {
            throw new IllegalArgumentException("size in centimeters can not be negative");
        }

        this.sizeInCm = sizeInCm;
    }

    public void grow(int lux, int mm, int amountOfDays)
    {
        if (status == Status.DESTROYED)
        {
            return;
        }

        double sunLight = lux * cmPerLux;
        double rainGrowth = mm * cmPerMillimeter;

        double totalGrowth = (sunLight + rainGrowth) * amountOfDays;

        sizeInCm += totalGrowth;

        if (sizeInCm >= ripeLength)
        {
            status = Status.READY_TO_HARVEST;
        }
    }
}
