package com.nhlstenden.flightbooking.airplane;

import java.util.ArrayList;
import java.util.List;

public abstract class Airplane
{
    private final float maxFuelLevelInLiters;
    private String code;
    private float fuelLevelInLiters;
    private List<Luggage> luggage;
    private List<Double> fuelCalculationConstants;

    public Airplane(float maxFuelLevelInLiters, String code, float fuelLevelInLiters, List<Luggage> luggage)
    {
        this.maxFuelLevelInLiters = maxFuelLevelInLiters;
        this.code = code;
        this.setFuelLevelInLiters(fuelLevelInLiters);

        if (luggage == null)
        {
            this.luggage = new ArrayList<>();
        }
        else
        {
            this.luggage = luggage;
        }
    }

    public List<Double> getFuelCalculationConstants()
    {
        return this.fuelCalculationConstants;
    }

    public void setFuelCalculationConstants(List<Double> fuelCalculationConstants)
    {
        if (fuelCalculationConstants == null || fuelCalculationConstants.isEmpty())
        {
            throw new IllegalArgumentException("fuelCalculationConstants cannot be null or empty");
        }
        for (Object fuelCalculationConstantsItem : fuelCalculationConstants)
        {
            if (fuelCalculationConstantsItem == null)
            {
                throw new IllegalArgumentException("fuelCalculationConstants cannot contain null");
            }
        }

        this.fuelCalculationConstants = new ArrayList<>(fuelCalculationConstants);
    }

    public List<Luggage> getLuggage()
    {
        return this.luggage;
    }

    public abstract void setLuggage(List<Luggage> luggage);

    public float getMaxFuelLevelInLiters()
    {
        return this.maxFuelLevelInLiters;
    }

    public String getCode()
    {
        return this.code;
    }

    public float getFuelLevelInLiters()
    {
        return this.fuelLevelInLiters;
    }

    public void setFuelLevelInLiters(float fuelLevelInLiters)
    {
        if (fuelLevelInLiters < 0)
        {
            throw new IllegalArgumentException("fuelLevelInLiters cannot be less than 0");
        }

        this.fuelLevelInLiters = fuelLevelInLiters;
    }

    //    methodinos
    public float getTotalLuggageWeight()
    {
        float totalLuggageWeight = 0;

        for (Luggage luggage : this.getLuggage())
        {
            totalLuggageWeight += luggage.weightInKilograms();
        }

        return totalLuggageWeight;
    }

    public abstract int getEmptySeats();

    public abstract void reserveOneRandomSeat();

    public abstract double calculateFuelConsumptionInLiters(int distanceInKm);
}
