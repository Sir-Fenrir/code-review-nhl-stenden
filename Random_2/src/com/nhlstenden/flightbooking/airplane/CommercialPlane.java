package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.flightsystem.NoSeatsAvailableOnFlightException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommercialPlane extends Airplane
{
    private HashMap<CommercialPlaneSeatType, Integer> availableSeats;
    private HashMap<CommercialPlaneSeatType, Integer> totalSeats;

    public CommercialPlane(float maxFuelLevelInLiters, float fuelLevelInLiters, String code,
                           HashMap<CommercialPlaneSeatType, Integer> totalSeats, HashMap<CommercialPlaneSeatType, Integer> availableSeats, List<Luggage> luggage)
    {
        super(maxFuelLevelInLiters, code, fuelLevelInLiters, luggage);
        this.setAvailableSeats(availableSeats);
        this.totalSeats = totalSeats;
        this.setFuelCalculationConstants(new ArrayList<>(List.of(1.75, 1.98, 2.02, 2.87, 0.3)));
    }

    public HashMap<CommercialPlaneSeatType, Integer> getTotalSeats()
    {
        return this.totalSeats;
    }

    public HashMap<CommercialPlaneSeatType, Integer> getAvailableSeats()
    {
        return this.availableSeats;
    }

    public void setAvailableSeats(HashMap<CommercialPlaneSeatType, Integer> availableSeats)
    {
        if (availableSeats == null || availableSeats.isEmpty())
        {
            throw new IllegalArgumentException("availableSeats cannot be null or empty");
        }

        this.availableSeats = availableSeats;
    }

    @Override
    public int getEmptySeats()
    {
        int totalAvailableSeats = 0;

        for (int value : this.getAvailableSeats().values())
        {
            totalAvailableSeats += value;
        }

        return totalAvailableSeats;
    }

    @Override
    public void setLuggage(List<Luggage> luggage)
    {
        if (luggage == null)
        {
            throw new IllegalArgumentException("luggage cannot be null");
        }

        this.getLuggage().clear();
        this.getLuggage().addAll(luggage);
    }

    @Override
    public void reserveOneRandomSeat()
    {
        if (this.getEmptySeats() <= 0)
        {
            throw new NoSeatsAvailableOnFlightException("Airplane Seats are already full");
        }

        for (Map.Entry<CommercialPlaneSeatType, Integer> entry : this.getAvailableSeats().entrySet())
        {
            if (entry.getValue() > 0)
            {
                entry.setValue(entry.getValue() - 1);
                return;
            }
        }
    }

    @Override
    public double calculateFuelConsumptionInLiters(int distanceInKm)
    {
        return (this.getTotalSeats().get(CommercialPlaneSeatType.ECONOMY) * this.getFuelCalculationConstants().get(0)) +
            (this.getTotalSeats().get(CommercialPlaneSeatType.BUSINESS) * this.getFuelCalculationConstants().get(1)) * distanceInKm +
            (this.getTotalSeats().get(CommercialPlaneSeatType.ECONOMY) - this.getAvailableSeats().get(CommercialPlaneSeatType.ECONOMY) * this.getFuelCalculationConstants().get(2)) +
            (this.getTotalSeats().get(CommercialPlaneSeatType.BUSINESS) - this.getAvailableSeats().get(CommercialPlaneSeatType.BUSINESS) * this.getFuelCalculationConstants().get(3)) +
            (this.getTotalLuggageWeight() * this.getFuelCalculationConstants().get(4));
    }
}
