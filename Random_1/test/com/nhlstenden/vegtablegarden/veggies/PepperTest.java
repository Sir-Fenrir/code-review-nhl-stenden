package com.nhlstenden.vegtablegarden.veggies;

import com.nhlstenden.vegtablegarden.vegetable.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PepperTest
{

    @Test
    void testGrow_FiveRainyDaysOver31mm_destroyed()
    {
        Pepper pepper = new Pepper();

        for (int day = 0; day < 5; day++)
        {
            pepper.grow(5000, 7, 1);
        }

        assertEquals(Status.DESTROYED, pepper.getStatus());
    }

    @Test
    void testGrow_TenDaysLessThan25mm_noGrowthOnDayTen() {

        Pepper pepper = new Pepper();

        for (int day = 0; day < 9; day++)
        {
            pepper.grow(5000, 0, 1);
        }

        double sizeBefore = pepper.getSizeInCm();

        pepper.grow(5000, 0, 1);

        assertEquals(sizeBefore, pepper.getSizeInCm());
    }
}