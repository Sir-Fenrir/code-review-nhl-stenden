package com.nhlstenden.vegtablegarden.veggies;

import com.nhlstenden.vegtablegarden.vegetable.Color;
import com.nhlstenden.vegtablegarden.vegetable.Status;
import com.nhlstenden.vegtablegarden.vegetable.Vegetable;

public class Kale extends Vegetable
{
    public Kale()
    {
        super(0.0057, 0.032, 16, Color.YELLOW);
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

        if (days >= 6) {
            if (totalRain > 40)
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
}
