package com.nhlstenden.vegtablegarden.veggies;

import com.nhlstenden.vegtablegarden.vegetable.Color;
import com.nhlstenden.vegtablegarden.vegetable.Status;
import com.nhlstenden.vegtablegarden.vegetable.Vegetable;

public class Pepper extends Vegetable implements Marketable
{
    public Pepper()
    {
        super(0.000044, 0.0013, 7, Color.PINK);
    }

    private int days = 0;
    private int totalRain = 0;

    private int rainyDays = 0;
    private int rainyDayRain = 0;

    @Override
    public void grow(int lux, int mm, int amountOfDays)
    {

        if (getStatus() == Status.DESTROYED)
        {
            return;
        }

        days += amountOfDays;
        totalRain += mm;

        if (mm > 0)
        {
            rainyDays += amountOfDays; // consecutive rainy days
            rainyDayRain += mm;
        }
        else
        {
            rainyDays = 0;
            rainyDayRain = 0;
        }

        if (rainyDays > 4 && rainyDayRain > 31)
        {
            setStatus(Status.DESTROYED);
            return;
        }

        if (days >= 10) //only checks on day ten and after, so could grow for 9 days and on day ten stop growing, but I just followed the assignment. (not realistic, but could not come up with a better way)
        {
            if (totalRain < 25)
            {
                days = 0;
                totalRain = 0;
                return;
            }

            days = 0;
            totalRain = 0;
        }

        super.grow(lux, mm, amountOfDays);
    }

    @Override
    public boolean isMarketable()
    {
        return getStatus() != Status.DESTROYED && getSizeInCm() >= 15 && getSizeInCm() <= 21;
    }

    @Override
    public double getPrice()
    {
        return getSizeInCm() * 0.15;
    }
}
