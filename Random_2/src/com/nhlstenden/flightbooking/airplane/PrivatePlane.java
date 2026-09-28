package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.flightsystem.NoSeatsAvailableOnFlightException;

import java.util.ArrayList;
import java.util.List;

public class PrivatePlane extends Airplane
{
    private int availableSeats;
    private int totalSeats;

    public PrivatePlane(float maxFuelLevelInLiters, float fuelLevelInLiters, String code, int availableSeats, int totalSeats, List<Luggage> luggage)
    {
        super(maxFuelLevelInLiters, code, fuelLevelInLiters, luggage);
        this.setAvailableSeats(availableSeats);
        this.totalSeats = totalSeats;
        this.setFuelCalculationConstants(new ArrayList<>(List.of(1.31, 1.87, 0.4)));
    }

    public int getAvailableSeats()
    {
        return this.availableSeats;
    }

    public void setAvailableSeats(int availableSeats)
    {
        if (availableSeats < 0)
        {
            throw new IllegalArgumentException("availableSeats cannot be less than 0");
        }

        this.availableSeats = availableSeats;
    }

    public int getTotalSeats()
    {
        return this.totalSeats;
    }

    @Override
    public int getEmptySeats()
    {
        return this.getAvailableSeats();
    }

    @Override
    public void setLuggage(List<Luggage> luggage)
    {
        if (luggage == null)
        {
            throw new IllegalArgumentException("luggage cannot be null");
        }

        for (Luggage luggage1 : luggage)
        {
            if (luggage1.luggageType().equals(LuggageType.HOLD))
            {
                throw new IllegalArgumentException("LuggageType cannot be HOLD on a PrivatePlane");
            }
        }
        this.getLuggage().clear();
        this.getLuggage().addAll(luggage);
    }

    @Override
    public void reserveOneRandomSeat()
    {
        if (this.getAvailableSeats() <= 0)
        {
            throw new NoSeatsAvailableOnFlightException("Airplane seats are already full");
        }

        this.setAvailableSeats(this.getAvailableSeats() - 1);
    }

    @Override
    public double calculateFuelConsumptionInLiters(int distanceInKm)
    {
        return this.getTotalSeats() * this.getFuelCalculationConstants().get(0) * distanceInKm +
            ((this.getTotalSeats() - this.getAvailableSeats()) * this.getFuelCalculationConstants().get(1)) +
            (this.getTotalLuggageWeight() * this.getFuelCalculationConstants().get(2));
    }
}
