package com.nhlstenden.vegtablegarden.garden;

import com.nhlstenden.vegtablegarden.exception.TooManyVegetablesException;
import com.nhlstenden.vegtablegarden.veggies.Asparagus;
import com.nhlstenden.vegtablegarden.veggies.Pepper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GardenTest
{
    @Test
    void testAddVegetable_27Vegetables_allowed() throws TooManyVegetablesException
    {
        Garden garden = new Garden();

        for (int i = 0; i < 27; i++)
        {
            garden.addVegetable(new Asparagus());
        }

        assertThrows(TooManyVegetablesException.class, () -> {
           garden.addVegetable(new Pepper());
        });
    }
}