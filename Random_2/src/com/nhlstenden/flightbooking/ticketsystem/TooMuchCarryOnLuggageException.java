package com.nhlstenden.flightbooking.ticketsystem;

public class TooMuchCarryOnLuggageException extends RuntimeException
{
    public TooMuchCarryOnLuggageException(String message)
    {
        super(message);
    }
}
