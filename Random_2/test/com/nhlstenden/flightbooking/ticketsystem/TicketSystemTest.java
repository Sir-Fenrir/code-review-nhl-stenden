package com.nhlstenden.flightbooking.ticketsystem;

import com.nhlstenden.flightbooking.airplane.Airplane;
import com.nhlstenden.flightbooking.airplane.CommercialPlane;
import com.nhlstenden.flightbooking.airplane.CommercialPlaneSeatType;
import com.nhlstenden.flightbooking.airplane.Luggage;
import com.nhlstenden.flightbooking.airplane.LuggageType;
import com.nhlstenden.flightbooking.airport.AirportLocation;
import com.nhlstenden.flightbooking.flightsystem.Flight;
import com.nhlstenden.flightbooking.flightsystem.FlightStatus;
import com.nhlstenden.flightbooking.flightsystem.NoAvailableFlightException;
import com.nhlstenden.flightbooking.flightsystem.NoSeatsAvailableOnFlightException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class TicketSystemTest
{
    private Flight flight;
    private Airplane boeingSomething;
    private TicketSystem ticketSystem;
    private List<Luggage> passengerLuggage;

    @Test
    void bookTicket_flightAndSeatsAreAvailable_throwsNoErrors()
    {
        this.boeingSomething = new CommercialPlane(500, 450, "79-SH-XL",
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 50, CommercialPlaneSeatType.ECONOMY, 500)),
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 42, CommercialPlaneSeatType.ECONOMY, 230)),
            new ArrayList<>(List.of(new Luggage(30, LuggageType.CARRY_ON),
                new Luggage(50, LuggageType.HOLD), new Luggage(13, LuggageType.CARRY_ON))));

        this.flight = new Flight(this.boeingSomething, AirportLocation.JFK, AirportLocation.AMS, LocalDateTime.of(2026, 5, 29, 10, 23), LocalDateTime.of(2026, 5, 29, 15, 9), FlightStatus.BOARDING);
        this.ticketSystem = new TicketSystem();
        this.ticketSystem.addFlight(this.flight);
        this.passengerLuggage = new ArrayList<>(List.of(new Luggage(30, LuggageType.CARRY_ON),
            new Luggage(2, LuggageType.HOLD), new Luggage(13, LuggageType.HOLD)));

        assertDoesNotThrow(() ->
        {
            this.ticketSystem.bookTicket(AirportLocation.JFK, AirportLocation.AMS, "John", "Doe", this.passengerLuggage);
        });
    }

    @Test
    void bookTicket_flightIsNotAvailable_throwsNoFlightAvailableException()
    {
        this.boeingSomething = new CommercialPlane(500, 450, "79-SH-XL",
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 50, CommercialPlaneSeatType.ECONOMY, 500)),
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 42, CommercialPlaneSeatType.ECONOMY, 230)),
            new ArrayList<>(List.of(new Luggage(30, LuggageType.CARRY_ON),
                new Luggage(50, LuggageType.HOLD), new Luggage(13, LuggageType.CARRY_ON))));

        this.flight = new Flight(this.boeingSomething, AirportLocation.JFK, AirportLocation.AMS, LocalDateTime.of(2026, 5, 29, 10, 23), LocalDateTime.of(2026, 5, 29, 15, 9), FlightStatus.BOARDING);
        this.ticketSystem = new TicketSystem();
        this.ticketSystem.addFlight(this.flight);
        this.passengerLuggage = new ArrayList<>(List.of(new Luggage(30, LuggageType.CARRY_ON),
            new Luggage(2, LuggageType.HOLD), new Luggage(13, LuggageType.HOLD)));

        assertThrowsExactly(NoAvailableFlightException.class, () ->
        {
            this.ticketSystem.bookTicket(AirportLocation.JFK, AirportLocation.JFK, "John", "Doe", this.passengerLuggage);
        });
    }

    @Test
    void bookTicket_flightIsAvailableSeatsNotAvailable_throwsNoSeatsAvailableOnFlightException()
    {
        this.boeingSomething = new CommercialPlane(500, 450, "79-SH-XL",
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 0, CommercialPlaneSeatType.ECONOMY, 0)),
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 0, CommercialPlaneSeatType.ECONOMY, 0)),
            new ArrayList<>(List.of(new Luggage(30, LuggageType.CARRY_ON),
                new Luggage(50, LuggageType.HOLD), new Luggage(13, LuggageType.CARRY_ON))));

        this.flight = new Flight(this.boeingSomething, AirportLocation.JFK, AirportLocation.LAX, LocalDateTime.of(2026, 5, 29, 10, 23), LocalDateTime.of(2026, 5, 29, 15, 9), FlightStatus.BOARDING);
        this.ticketSystem = new TicketSystem();
        this.ticketSystem.addFlight(this.flight);
        this.passengerLuggage = new ArrayList<>(List.of(new Luggage(30, LuggageType.CARRY_ON),
            new Luggage(2, LuggageType.HOLD), new Luggage(13, LuggageType.HOLD)));

        assertThrowsExactly(NoSeatsAvailableOnFlightException.class, () ->
        {
            this.ticketSystem.bookTicket(AirportLocation.JFK, AirportLocation.LAX, "John", "Doe", this.passengerLuggage);
        });
    }

    @Test
    void bookTicket_tooMuchCarryOnLuggage_throwsTooMuchCarryOnLuggageException()
    {
        this.boeingSomething = new CommercialPlane(500, 450, "79-SH-XL",
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 0, CommercialPlaneSeatType.ECONOMY, 0)),
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 0, CommercialPlaneSeatType.ECONOMY, 0)),
            new ArrayList<>(List.of(new Luggage(30, LuggageType.CARRY_ON),
                new Luggage(50, LuggageType.HOLD), new Luggage(13, LuggageType.CARRY_ON))));

        this.flight = new Flight(this.boeingSomething, AirportLocation.JFK, AirportLocation.LAX, LocalDateTime.of(2026, 5, 29, 10, 23), LocalDateTime.of(2026, 5, 29, 15, 9), FlightStatus.BOARDING);
        this.ticketSystem = new TicketSystem();
        this.ticketSystem.addFlight(this.flight);
        this.passengerLuggage = new ArrayList<>(List.of(new Luggage(30, LuggageType.CARRY_ON),
            new Luggage(2, LuggageType.CARRY_ON), new Luggage(13, LuggageType.HOLD)));

        assertThrowsExactly(TooMuchCarryOnLuggageException.class, () ->
        {
            this.ticketSystem.bookTicket(AirportLocation.JFK, AirportLocation.LAX, "John", "Doe", this.passengerLuggage);
        });
    }
}
