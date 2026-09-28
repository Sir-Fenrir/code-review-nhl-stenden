package com.nhlstenden.flightbooking.flight24;

import com.nhlstenden.flightbooking.airplane.Airplane;
import com.nhlstenden.flightbooking.flightsystem.Flight;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Flight24Uploader
{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private List<String> flight24Data = new ArrayList<>();

    public Flight24Uploader()
    {
    }

    public List<String> getFlight24Data()
    {
        return this.flight24Data;
    }

    public static String getFlightInfo(Flight flight)
    {
        return "F: " + flight.getDepartureAirport().toString() + " -> " + flight.getArrivalAirport().toString() + ". Departure " + flight.getDepartureTime().format(formatter) + ".";
    }

    public static String getAirplaneInfo(Airplane airplane)
    {
        return "P: " + airplane.getCode() + ". " + airplane.getFuelLevelInLiters() + " liter fuel. " + airplane.getEmptySeats() + " empty seats.";
    }

    public void uploadData(String data)
    {
        this.flight24Data.add(data);
    }
}
