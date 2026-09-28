package com.nhlstenden.flightbooking.airport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class AirportDistances
{
    //    Realized that I accidentally made a value that can store distances for multiple stops which can be cool bc treeset has no fixed size
    public static final HashMap<TreeSet<AirportLocation>, Integer> AIRPORT_DISTANCES = new HashMap<>(Map.of(
        new TreeSet<>(List.of(AirportLocation.JFK, AirportLocation.AMS)), 5848,
        new TreeSet<>(List.of(AirportLocation.JFK, AirportLocation.MEX)), 3366,
        new TreeSet<>(List.of(AirportLocation.JFK, AirportLocation.LAX)), 3975,
        new TreeSet<>(List.of(AirportLocation.AMS, AirportLocation.MEX)), 9206,
        new TreeSet<>(List.of(AirportLocation.AMS, AirportLocation.LAX)), 8956,
        new TreeSet<>(List.of(AirportLocation.MEX, AirportLocation.LAX)), 2500));

    private AirportDistances()
    {
    }

    public static HashMap<TreeSet<AirportLocation>, Integer> getAirportDistances()
    {
        return AIRPORT_DISTANCES;
    }

    public static int getDistanceBetweenAirportsInKm(AirportLocation departureAirport, AirportLocation arrivalAirport)
    {
        return AIRPORT_DISTANCES.get(new TreeSet<>(List.of(departureAirport, arrivalAirport)));
    }
}
