package com.nhlstenden.vegtablegarden.garden;

import com.nhlstenden.vegtablegarden.generator.WeatherGenerator;
import com.nhlstenden.vegtablegarden.vegetable.Vegetable;

public class Greenhouse extends Garden
{

    public Greenhouse()
    {
        super();
    }

    public void runWeather()
    {
        int lux = WeatherGenerator.getRandomLux();
        int mm = WeatherGenerator.getRandomMm();

        for (Vegetable vegetable : this.getVegetables())
        {
            vegetable.grow(lux, mm, 1);
        }
    }
}