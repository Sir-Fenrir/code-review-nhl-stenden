package com.nhlstenden.flightbooking.airplane;

public record Luggage(int weightInKilograms, LuggageType luggageType)
{
    public Luggage
    {
        if (weightInKilograms < 0)
        {
            throw new IllegalArgumentException("weightInKilograms cannot be less than 0");
        }

        if (luggageType == null)
        {
            throw new IllegalArgumentException("luggageType cannot be null");
        }
    }
}
