package com.nhlstenden.vegtablegarden.garden;

import com.nhlstenden.vegtablegarden.exception.TooManyVegetablesException;
import com.nhlstenden.vegtablegarden.vegetable.Status;
import com.nhlstenden.vegtablegarden.vegetable.Vegetable;

import java.util.HashSet;

public class Garden
{
    private HashSet<Vegetable> vegetables;

    public Garden()
    {
        this.vegetables = new HashSet<>();
    }

    public HashSet<Vegetable> getVegetables()
    {
        return new HashSet<>(vegetables);
    }

    public void setVegetables(HashSet<Vegetable> vegetables) throws TooManyVegetablesException
    {
        if (vegetables == null)
        {
            throw new IllegalArgumentException("hash set can not be null");
        }

        if (vegetables.size() > 27)
        {
            throw new TooManyVegetablesException("Garden is full");
        }

        this.vegetables = new HashSet<>(vegetables);
    }

    public void addVegetable(Vegetable vegetable) throws TooManyVegetablesException
    {
        if (vegetable == null)
        {
            throw new IllegalArgumentException("vegetable can not be null");
        }

        if (vegetables.size() >= 27)
        {
            throw new TooManyVegetablesException("Garden is full");
        }

        vegetables.add(vegetable);
    }

    public HashSet<Vegetable> getReadyForHarvest()
    {
        HashSet<Vegetable> readyVegetables = new HashSet<>();

        for (Vegetable vegetable : this.vegetables)
        {
            if (vegetable.getStatus() == Status.READY_TO_HARVEST)
            {
                readyVegetables.add(vegetable);
            }
        }

        return readyVegetables;
    }
}
