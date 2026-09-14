package com.nhlstenden.uservalidation.validation;

import com.nhlstenden.uservalidation.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidationTest
{
    @Test
    public void validate_validEmail_returnsTrue()
    {
        User user = new User(
                "Giovanni",
                "Wachtwoord123!",
                "giovanniberlijn@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        EmailValidation validation = new EmailValidation();

        assertTrue(validation.validate(user));
    }

    @Test
    public void validate_emailWithoutAtSign_returnsFalse()
    {
        User user = new User(
                "Giovanni",
                "Wachtwoord123!",
                "giovanniberlijn-gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        EmailValidation validation = new EmailValidation();

        assertFalse(validation.validate(user));
    }
}