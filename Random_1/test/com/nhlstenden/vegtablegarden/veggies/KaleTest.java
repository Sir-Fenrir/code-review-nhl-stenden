package com.nhlstenden.vegtablegarden.veggies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KaleTest
{
    @Test
    void testGrow_SixDaysOver40mm_noGrowthOnDaySix() {

        Kale kale = new Kale();

        for (int day = 0; day < 5; day++)
        {
            kale.grow(5000, 8, 1);
        }

        double sizeBefore = kale.getSizeInCm();

        kale.grow(5000, 8, 1);

        assertEquals(sizeBefore, kale.getSizeInCm());
    }
}