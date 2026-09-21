package com.nhlstenden.appstores.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest
{
    @Test
    public void validateEmail_emailWithAtAndDot_returnsEmail()
    {
        assertEquals("john@example.com", EmailValidator.validateEmail("john@example.com"));
    }

    @Test
    public void validateEmail_emailMissingAt_returnsNull()
    {
        assertNull(EmailValidator.validateEmail("john.example.com"));
    }

    @Test
    public void validateEmail_emailMissingDot_returnsNull()
    {
        assertNull(EmailValidator.validateEmail("john@examplecom"));
    }

    @Test
    public void validateEmail_blankEmail_returnsNull()
    {
        assertNull(EmailValidator.validateEmail(""));
    }
}
