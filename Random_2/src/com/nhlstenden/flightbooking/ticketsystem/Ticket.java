package com.nhlstenden.flightbooking.ticketsystem;

import com.nhlstenden.flightbooking.airplane.Luggage;
import com.nhlstenden.flightbooking.airport.AirportLocation;
import com.nhlstenden.flightbooking.flightsystem.Flight;

import java.util.List;

public record Ticket(AirportLocation departureAirport, AirportLocation arrivalAirport, String firstname,
                     String lastname, List<Luggage> luggage, Flight flight)
{
}
