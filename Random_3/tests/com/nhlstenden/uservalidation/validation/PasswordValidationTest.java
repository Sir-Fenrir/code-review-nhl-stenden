package com.nhlstenden.uservalidation.validation;

import com.nhlstenden.uservalidation.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidationTest
{
    @Test
    public void validate_validPassword_returnsTrue()
    {
        User user = new User(
                "Giovanni",
                "Wachtwoord123!",
                "giovanniberlijn@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        PasswordValidation validation = new PasswordValidation(
                false,
                true,
                true,
                true,
                true
        );

        assertTrue(validation.validate(user));
    }

    @Test
    public void validate_passwordWithoutUppercase_returnsFalse()
    {
        User user = new User(
                "Giovanni",
                "wachtwoord123!",
                "giovanniberlijn@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        PasswordValidation validation = new PasswordValidation(
                false,
                true,
                true,
                true,
                true
        );

        assertFalse(validation.validate(user));
    }
}