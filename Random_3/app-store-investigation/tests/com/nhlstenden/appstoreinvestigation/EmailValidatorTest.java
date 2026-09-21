package com.nhlstenden.appstoreinvestigation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest
{
    @Test
    public void isValid_validEmail_returnsTrue()
    {
        String email = "johndoe@gmail.com";

        boolean result = EmailValidator.isValid(email);

        assertTrue(result);
    }

    @Test
    public void isValid_emailWithoutAtSign_returnsFalse()
    {
        String email = "johndoegmail.com";

        boolean result = EmailValidator.isValid(email);

        assertFalse(result);
    }

    @Test
    public void isValid_emailWithoutDomain_returnsFalse()
    {
        String email = "john@";

        boolean result = EmailValidator.isValid(email);

        assertFalse(result);
    }

    @Test
    public void isValid_onlyAtSigns_returnsFalse()
    {
        String email = "@@@";

        boolean result = EmailValidator.isValid(email);

        assertFalse(result);
    }

    @Test
    public void isValid_nullEmail_returnsFalse()
    {
        String email = null;

        boolean result = EmailValidator.isValid(email);

        assertFalse(result);
    }

    @Test
    public void isValid_blankEmail_returnsFalse()
    {
        String email = "";

        boolean result = EmailValidator.isValid(email);

        assertFalse(result);
    }
}