package com.nhlstenden.vegtablegarden.garden;

import com.nhlstenden.vegtablegarden.exception.TooManyVegetablesException;
import com.nhlstenden.vegtablegarden.veggies.Asparagus;
import com.nhlstenden.vegtablegarden.veggies.Kale;
import com.nhlstenden.vegtablegarden.veggies.Pepper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreenhouseTest
{

    @Test
    void testRunWeather_withVegetables_vegetableGrows() throws TooManyVegetablesException
    {
        Greenhouse greenhouse1 = new Greenhouse();
        Asparagus asparagus1 = new Asparagus();

        greenhouse1.addVegetable(asparagus1);

        double sizeBefore = asparagus1.getSizeInCm();

        greenhouse1.runWeather();
        double sizeAfterFirst = asparagus1.getSizeInCm();

        greenhouse1.runWeather();
        double sizeAfterSecond = asparagus1.getSizeInCm();

        System.out.println(sizeBefore);
        System.out.println(sizeAfterFirst);
        System.out.println(sizeAfterSecond);

        assertTrue(asparagus1.getSizeInCm() > sizeBefore);
        assertTrue(sizeAfterSecond > sizeAfterFirst);
    }

    @Test
    void testRunWeather_TenDays_TracksVegetableGrowth() throws TooManyVegetablesException
    {
        Greenhouse greenhouse = new Greenhouse();
        Asparagus asparagus = new Asparagus();
        Pepper pepper = new Pepper();
        Kale kale = new Kale();

        greenhouse.addVegetable(asparagus);
        greenhouse.addVegetable(pepper);
        greenhouse.addVegetable(kale);

        for (int day = 1; day <= 10; day++) {

            greenhouse.runWeather();

            System.out.println("Day " + day);
            System.out.println("Asparagus: " + asparagus.getSizeInCm());
            System.out.println("Pepper: " + pepper.getSizeInCm());
            System.out.println("Kale: " + kale.getSizeInCm());
            System.out.println();
        }

        // assertTrue(asparagus.getSizeInCm() >= 0);
        // assertTrue(pepper.getSizeInCm() >= 0);
        // assertTrue(kale.getSizeInCm() >= 0);
        // not needed, checking just if values add up.
    }
}