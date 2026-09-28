package com.nhlstenden.flightbooking.flightsystem;

public class NoSeatsAvailableOnFlightException extends RuntimeException
{
    public NoSeatsAvailableOnFlightException(String message)
    {
        super(message);
    }
}
