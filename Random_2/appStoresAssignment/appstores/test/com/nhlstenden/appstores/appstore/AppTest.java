package com.nhlstenden.appstores.appstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest
{
    private App app;

    @BeforeEach
    public void setUp()
    {
        this.app = new App("Chess Master", 4.99, false, false);
    }

    @Test
    public void constructor_nullName_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new App(null, 4.99, false, false));
    }

    @Test
    public void constructor_blankName_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new App("   ", 4.99, false, false));
    }

    @Test
    public void constructor_negativePrice_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new App("Chess Master", -1.0, false, false));
    }

    @Test
    public void constructor_validArguments_setsAllFields()
    {
        App violentApp = new App("Warzone", 9.99, true, true);

        assertEquals("Warzone", violentApp.getName());
        assertEquals(9.99, violentApp.getPrice());
        assertTrue(violentApp.containsViolence());
        assertTrue(violentApp.containsNudity());
    }

    @Test
    public void setName_nullName_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.app.setName(null));
    }

    @Test
    public void setName_blankName_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.app.setName(""));
    }

    @Test
    public void setName_validName_setsName()
    {
        this.app.setName("Puzzle Quest");

        assertEquals("Puzzle Quest", this.app.getName());
    }

    @Test
    public void setPrice_negativePrice_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.app.setPrice(-0.01));
    }

    @Test
    public void setPrice_zeroPrice_setsPrice()
    {
        this.app.setPrice(0.0);

        assertEquals(0.0, this.app.getPrice());
    }

    @Test
    public void setPrice_validPrice_setsPrice()
    {
        this.app.setPrice(19.99);

        assertEquals(19.99, this.app.getPrice());
    }

    @Test
    public void setContainsViolence_true_updatesFlag()
    {
        this.app.setContainsViolence(true);

        assertTrue(this.app.containsViolence());
    }

    @Test
    public void setContainsNudity_true_updatesFlag()
    {
        this.app.setContainsNudity(true);

        assertTrue(this.app.containsNudity());
    }

    @Test
    public void isAgeEligibleForViolence_ageBelowMinimum_returnsFalse()
    {
        assertFalse(this.app.isAgeEligibleForViolence(15));
    }

    @Test
    public void isAgeEligibleForViolence_ageAtMinimum_returnsTrue()
    {
        assertTrue(this.app.isAgeEligibleForViolence(16));
    }

    @Test
    public void isAgeEligibleForViolence_ageAboveMinimum_returnsTrue()
    {
        assertTrue(this.app.isAgeEligibleForViolence(30));
    }

    @Test
    public void isAgeEligibleForNudity_ageBelowMinimum_returnsFalse()
    {
        assertFalse(this.app.isAgeEligibleForNudity(17));
    }

    @Test
    public void isAgeEligibleForNudity_ageAtMinimum_returnsTrue()
    {
        assertTrue(this.app.isAgeEligibleForNudity(18));
    }

    @Test
    public void isAgeEligibleForNudity_ageAboveMinimum_returnsTrue()
    {
        assertTrue(this.app.isAgeEligibleForNudity(40));
    }
}
