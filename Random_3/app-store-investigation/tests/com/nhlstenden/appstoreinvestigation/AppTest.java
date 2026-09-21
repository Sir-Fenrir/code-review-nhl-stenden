package com.nhlstenden.appstoreinvestigation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest
{
    @Test
    public void getMinimumAge_appWithoutViolenceOrNudity_returnsZero()
    {
        App app = new App(
                "Weather App",
                2.99,
                false,
                false
        );

        int result = app.getMinimumAge();

        assertEquals(0, result);
    }

    @Test
    public void getMinimumAge_appWithViolence_returnsSixteen()
    {
        App app = new App(
                "Action Game",
                9.99,
                true,
                false
        );

        int result = app.getMinimumAge();

        assertEquals(16, result);
    }

    @Test
    public void getMinimumAge_appWithNudity_returnsEighteen()
    {
        App app = new App(
                "Mature App",
                9.99,
                false,
                true
        );

        int result = app.getMinimumAge();

        assertEquals(18, result);
    }

    @Test
    public void getMinimumAge_appWithViolenceAndNudity_returnsEighteen()
    {
        App app = new App(
                "Mature Action Game",
                14.99,
                true,
                true
        );

        int result = app.getMinimumAge();

        assertEquals(18, result);
    }
}