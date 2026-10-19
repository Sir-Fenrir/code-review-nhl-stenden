package com.nhlstenden.vegtablegarden.vegetable;

import com.nhlstenden.vegtablegarden.veggies.Asparagus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VegetableTest
{

    @Test
    void testGrow_TenDaysWithLessThan14mm_AsparagusDestroyed()
    {
        Asparagus asparagus = new Asparagus();

        asparagus.grow(5000, 10, 10);

        assertEquals(Status.DESTROYED, asparagus.getStatus());
    }

    @Test
    void setSizeInCm_sizeInNegative_throwsException() //checking so I remember how to use assertThrows.
    {
        Asparagus asparagus = new Asparagus();

        assertThrows(IllegalArgumentException.class, () -> {
            asparagus.setSizeInCm(-1);
        });
    }

}