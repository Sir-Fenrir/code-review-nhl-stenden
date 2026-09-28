package com.nhlstenden.flightbooking.flightsystem;

import com.nhlstenden.flightbooking.airplane.Airplane;
import com.nhlstenden.flightbooking.airport.AirportDistances;
import com.nhlstenden.flightbooking.airport.AirportLocation;

import java.time.LocalDateTime;

public class Flight
{
    private Airplane airplane;
    private AirportLocation departureAirport;
    private AirportLocation arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private FlightStatus flightStatus;

    public Flight(Airplane airplane, AirportLocation departureAirport, AirportLocation arrivalAirport, LocalDateTime departureTime, LocalDateTime arrivalTime, FlightStatus flightStatus)
    {
        this.airplane = airplane;
        this.departureAirport = departureAirport;
        this.setArrivalAirport(arrivalAirport);
        this.setDepartureTime(departureTime);
        this.setArrivalTime(arrivalTime);
        this.setFlightStatus(flightStatus);
    }

    public FlightStatus getFlightStatus()
    {
        return this.flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus)
    {
        if (flightStatus == null)
        {
            throw new IllegalArgumentException("flightStatus cannot be null");
        }

        this.flightStatus = flightStatus;
    }

    public Airplane getAirplane()
    {
        return this.airplane;
    }

    public AirportLocation getDepartureAirport()
    {
        return this.departureAirport;
    }

    public AirportLocation getArrivalAirport()
    {
        return this.arrivalAirport;
    }

    public void setArrivalAirport(AirportLocation arrivalAirport)
    {
        if (arrivalAirport == null)
        {
            throw new IllegalArgumentException("arrivalAirport cannot be null");
        }

        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureTime()
    {
        return this.departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime)
    {
        if (departureTime == null)
        {
            throw new IllegalArgumentException("departureTime cannot be null");
        }

        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime()
    {
        return this.arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime)
    {
        if (arrivalTime == null)
        {
            throw new IllegalArgumentException("arrivalTime cannot be null");
        }

        this.arrivalTime = arrivalTime;
    }

    //    methoderinos
    public boolean isReadyToDepart()
    {
        int distanceBetweenAirportsInKm = AirportDistances.getDistanceBetweenAirportsInKm(this.getDepartureAirport(), this.getArrivalAirport());
        double fuelNeededForTrip = this.getAirplane().calculateFuelConsumptionInLiters(distanceBetweenAirportsInKm);

        if (fuelNeededForTrip > this.getAirplane().getFuelLevelInLiters())
        {
            return false;
        }

        this.setFlightStatus(FlightStatus.DEPARTED);
        return true;
    }
}
