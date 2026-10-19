package com.nhlstenden.vegtablegarden.generator;

import java.util.Random;

public class WeatherGenerator
{
    public static int getRandomMm()
    {
        Random random = new Random();

        return random.nextInt(49);
    }

    public static int getRandomLux()
    {
        Random random = new Random();

        return random.nextInt(1000, 101593);
    }
}
