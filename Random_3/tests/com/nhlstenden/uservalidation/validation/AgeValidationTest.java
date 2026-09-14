package com.nhlstenden.uservalidation.validation;

import com.nhlstenden.uservalidation.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgeValidationTest
{
    @Test
    public void validate_userOlderThanMinimumAge_returnsTrue()
    {
        User user = new User(
                "Giovanni",
                "Wachtwoord123!",
                "giovanniberlijn@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        AgeValidation validation = new AgeValidation(18);

        assertTrue(validation.validate(user));
    }

    @Test
    public void validate_userYoungerThanMinimumAge_returnsFalse()
    {
        User user = new User(
                "Giovanni",
                "Wachtwoord123!",
                "giovanniberlijn@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        AgeValidation validation = new AgeValidation(30);

        assertFalse(validation.validate(user));
    }

    @Test
    public void constructor_negativeMinimumAge_throwsIllegalArgumentException()
    {
        assertThrows(
                IllegalArgumentException.class,
                () -> new AgeValidation(-1)
        );
    }
}