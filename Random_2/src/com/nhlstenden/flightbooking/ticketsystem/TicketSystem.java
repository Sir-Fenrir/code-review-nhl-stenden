package com.nhlstenden.flightbooking.ticketsystem;

import com.nhlstenden.flightbooking.airplane.CommercialPlane;
import com.nhlstenden.flightbooking.airplane.Luggage;
import com.nhlstenden.flightbooking.airplane.LuggageType;
import com.nhlstenden.flightbooking.airport.AirportLocation;
import com.nhlstenden.flightbooking.flightsystem.Flight;
import com.nhlstenden.flightbooking.flightsystem.FlightStatus;
import com.nhlstenden.flightbooking.flightsystem.NoAvailableFlightException;
import com.nhlstenden.flightbooking.flightsystem.NoSeatsAvailableOnFlightException;

import java.util.ArrayList;
import java.util.List;

public class TicketSystem
{
    private List<Flight> flights;
    private List<Ticket> tickets;

    public TicketSystem()
    {
        this.flights = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public List<Flight> getFlights()
    {
        return this.flights;
    }

    public void addFlight(Flight flight)
    {
        this.getFlights().add(flight);
    }

    private Flight bookFlight(AirportLocation departureAirport, AirportLocation arrivalAirport, List<Luggage> luggage)
    {

        boolean foundFlight = false;
        boolean hasAvailableSeat = false;

        for (Flight flightIterator : this.getFlights())
        {
            if (flightIterator.getDepartureAirport().equals(departureAirport) && flightIterator.getArrivalAirport().equals(arrivalAirport) && (flightIterator.getAirplane() instanceof CommercialPlane) && !flightIterator.getFlightStatus().equals(FlightStatus.DEPARTED))
            {
                foundFlight = true;
                if (flightIterator.getAirplane().getEmptySeats() > 0)
                {
                    flightIterator.getAirplane().reserveOneRandomSeat();
                    flightIterator.getAirplane().getLuggage().addAll(luggage);
                    return flightIterator;
                }
            }
        }

        if (!foundFlight)
        {
            throw new NoAvailableFlightException("No flight exists with requested departure and arrival airport");
        }

        throw new NoSeatsAvailableOnFlightException("No seats available on this flight");
    }

    private boolean validateCarryOnLuggageAmount(List<Luggage> luggage)
    {
        int carryOnLuggageAmount = 0;
        for (Luggage luggageIterator : luggage)
        {
            if (luggageIterator.luggageType().equals(LuggageType.CARRY_ON))
            {
                carryOnLuggageAmount++;
            }
        }
        return carryOnLuggageAmount <= 1;
    }

    public void bookTicket(AirportLocation departureAirport, AirportLocation arrivalAirport, String firstname,
                           String lastname, List<Luggage> luggage)
    {

        if (!this.validateCarryOnLuggageAmount(luggage))
        {
            throw new TooMuchCarryOnLuggageException("Cannot have more than one carry on luggage on the ticket");
        }

        Flight flight = this.bookFlight(departureAirport, arrivalAirport, luggage);

        Ticket newTicket = new Ticket(departureAirport, arrivalAirport, firstname, lastname, luggage, flight);
        this.tickets.add(newTicket);
    }
}
