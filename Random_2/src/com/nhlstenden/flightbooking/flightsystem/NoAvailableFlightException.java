package com.nhlstenden.flightbooking.flightsystem;

public class NoAvailableFlightException extends RuntimeException
{
    public NoAvailableFlightException(String message)
    {
        super(message);
    }
}
