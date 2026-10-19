package com.nhlstenden.vegtablegarden.veggies;

import com.nhlstenden.vegtablegarden.vegetable.Color;
import com.nhlstenden.vegtablegarden.vegetable.Status;
import com.nhlstenden.vegtablegarden.vegetable.Vegetable;

public class Asparagus extends Vegetable implements Marketable
{
    public Asparagus()
    {
        super(0.000027, 0.0049, 20, Color.BLUE);
    }

    private int days = 0;
    private int totalRain = 0;

    @Override
    public void grow(int lux, int mm, int amountOfDays)
    {

        if (getStatus() == Status.DESTROYED)
        {
            return;
        }

        days += amountOfDays;
        totalRain += mm;

        if (days >= 10)
        {
            if (totalRain < 14)
            {
                setStatus(Status.DESTROYED);
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
        return getStatus() != Status.DESTROYED && getSizeInCm() <= 29;
    }

    @Override
    public double getPrice()
    {
        return getSizeInCm() * 0.12;
    }
}
