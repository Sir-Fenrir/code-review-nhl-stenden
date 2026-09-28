package com.nhlstenden.flightbooking.airplane;

import com.nhlstenden.flightbooking.airport.AirportDistances;
import com.nhlstenden.flightbooking.airport.AirportLocation;
import com.nhlstenden.flightbooking.flightsystem.NoSeatsAvailableOnFlightException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PrivatePlaneTest
{
    @Test
    void getEmptySeats_10Empty_returns10()
    {
        List<Luggage> luggage = new ArrayList<>(List.of());
        PrivatePlane bezosPlane = new PrivatePlane(100000, 80000, "R1CH-AF", 10, 10, luggage);

        assertEquals(10, bezosPlane.getEmptySeats());
    }

    @Test
    void getEmptySeats_0Empty_returns0()
    {
        List<Luggage> luggage = new ArrayList<>(List.of());
        PrivatePlane bezosPlane = new PrivatePlane(100000, 80000, "R1CH-AF", 0, 10, luggage);

        assertEquals(0, bezosPlane.getEmptySeats());
    }

    @Test
    void testReserveOneRandomSeat_10Seats_returns9()
    {
        List<Luggage> luggage = new ArrayList<>(List.of());
        PrivatePlane bezosPlane = new PrivatePlane(100000, 80000, "R1CH-AF", 10, 10, luggage);

        bezosPlane.reserveOneRandomSeat();

        assertEquals(9, bezosPlane.getEmptySeats());
    }

    @Test
    void testReserveOneRandomSeat_0SeatsAvailable_throwsNoSeatsAvailableOnFlightEcxepction()
    {
        List<Luggage> luggage = new ArrayList<>(List.of());
        PrivatePlane bezosPlane = new PrivatePlane(100000, 80000, "R1CH-AF", 0, 10, luggage);

        assertThrows(NoSeatsAvailableOnFlightException.class, () ->
        {
            bezosPlane.reserveOneRandomSeat();
        });
    }

    @Test
    void testCalculateFuelConsumptionInLiters_MexToLax_returns32768Point7()
    {
        List<Luggage> luggage = new ArrayList<>(List.of());
        PrivatePlane bezosPlane = new PrivatePlane(100000, 80000, "R1CH-AF", 0, 10, luggage);

        assertEquals(32768.7, bezosPlane.calculateFuelConsumptionInLiters(AirportDistances.getDistanceBetweenAirportsInKm(AirportLocation.MEX, AirportLocation.LAX)), 0.1);
    }
}
