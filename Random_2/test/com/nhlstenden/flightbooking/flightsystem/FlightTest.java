package com.nhlstenden.flightbooking.flightsystem;

import com.nhlstenden.flightbooking.airplane.Airplane;
import com.nhlstenden.flightbooking.airplane.CommercialPlane;
import com.nhlstenden.flightbooking.airplane.CommercialPlaneSeatType;
import com.nhlstenden.flightbooking.airplane.Luggage;
import com.nhlstenden.flightbooking.airport.AirportLocation;
import com.nhlstenden.flightbooking.ticketsystem.TicketSystem;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightTest
{
    private Flight flight;
    private Airplane boeingSomething;
    private TicketSystem ticketSystem;
    private List<Luggage> passengerLuggage;

    @Test
    void isReadyToDepart_hasEnoughFuel_returnsFalse()
    {
        this.boeingSomething = new CommercialPlane(5000000, 4633068, "79-SH-XL",
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 400, CommercialPlaneSeatType.ECONOMY, 400)),
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 10, CommercialPlaneSeatType.ECONOMY, 10)),
            new ArrayList<>(List.of()));

        this.flight = new Flight(this.boeingSomething, AirportLocation.JFK, AirportLocation.AMS, LocalDateTime.of(2026, 5, 29, 10, 23), LocalDateTime.of(2026, 5, 29, 15, 9), FlightStatus.BOARDING);

        assertTrue(this.flight.isReadyToDepart());
    }

    @Test
    void isReadyToDepart_hasNotEnoughFuel_returnsTrue()
    {
        this.boeingSomething = new CommercialPlane(5000000, 4633067, "79-SH-XL",
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 400, CommercialPlaneSeatType.ECONOMY, 400)),
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 10, CommercialPlaneSeatType.ECONOMY, 10)),
            new ArrayList<>(List.of()));

        this.flight = new Flight(this.boeingSomething, AirportLocation.JFK, AirportLocation.AMS, LocalDateTime.of(2026, 5, 29, 10, 23), LocalDateTime.of(2026, 5, 29, 15, 9), FlightStatus.BOARDING);

        assertFalse(this.flight.isReadyToDepart());
    }
}
