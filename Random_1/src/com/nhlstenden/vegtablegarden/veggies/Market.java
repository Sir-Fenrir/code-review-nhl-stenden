package com.nhlstenden.vegtablegarden.veggies;

import java.util.HashSet;

public class Market
{
    private HashSet<Marketable> vegetables;

    public Market()
    {
        this.vegetables = new HashSet<>();
    }

    public void addVegetable(Marketable vegetable)
    {
        if (vegetable.isMarketable())
        {
            vegetables.add(vegetable);
        }
    }

    public double getTotalRevenue()
    {
        double total = 0.0;

        for (Marketable marketable : this.vegetables)
        {
            if (marketable.isMarketable())
            {
                total += marketable.getPrice();
            }
        }

        return total;
    }
}
