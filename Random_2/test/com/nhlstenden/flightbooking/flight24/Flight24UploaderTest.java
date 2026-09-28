package com.nhlstenden.flightbooking.flight24;

import com.nhlstenden.flightbooking.airplane.Airplane;
import com.nhlstenden.flightbooking.airplane.CommercialPlane;
import com.nhlstenden.flightbooking.airplane.CommercialPlaneSeatType;
import com.nhlstenden.flightbooking.airplane.Luggage;
import com.nhlstenden.flightbooking.airplane.LuggageType;
import com.nhlstenden.flightbooking.airport.AirportLocation;
import com.nhlstenden.flightbooking.flightsystem.Flight;
import com.nhlstenden.flightbooking.flightsystem.FlightStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Flight24UploaderTest
{
    private Flight flight;
    private Airplane boeingSomething;
    private Flight24Uploader flight24Uploader;

    @BeforeEach
    void setup()
    {
        this.boeingSomething = new CommercialPlane(500, 450, "79-SH-XL",
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 50, CommercialPlaneSeatType.ECONOMY, 500)),
            new HashMap<>(Map.of(CommercialPlaneSeatType.BUSINESS, 42, CommercialPlaneSeatType.ECONOMY, 230)),
            new ArrayList<>(List.of(new Luggage(30, LuggageType.CARRY_ON),
                new Luggage(50, LuggageType.HOLD), new Luggage(13, LuggageType.CARRY_ON))));
        this.flight = new Flight(this.boeingSomething, AirportLocation.JFK, AirportLocation.AMS, LocalDateTime.of(2026, 5, 29, 10, 23), LocalDateTime.of(2026, 5, 29, 15, 9), FlightStatus.BOARDING);
        this.flight24Uploader = new Flight24Uploader();
    }

    @Test
    void getFlightInfo_isValid_matchesString()
    {
        assertEquals("F: JFK -> AMS. Departure 29-05-2026 10:23.", Flight24Uploader.getFlightInfo(this.flight));
    }

    @Test
    void getAirplaneInfo_isValid_matchesString()
    {
        assertEquals("P: 79-SH-XL. 450.0 liter fuel. 272 empty seats.", Flight24Uploader.getAirplaneInfo(this.boeingSomething));
    }

    @Test
    void uploadFlightData_isValid_matchesUploadedData()
    {
        this.flight24Uploader.uploadData(Flight24Uploader.getAirplaneInfo(this.boeingSomething));
        assertEquals("P: 79-SH-XL. 450.0 liter fuel. 272 empty seats.", this.flight24Uploader.getFlight24Data().getFirst());
    }
}
