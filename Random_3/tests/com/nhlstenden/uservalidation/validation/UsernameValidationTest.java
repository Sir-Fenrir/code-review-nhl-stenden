package com.nhlstenden.uservalidation.validation;

import com.nhlstenden.uservalidation.User;
import com.nhlstenden.uservalidation.UserStorage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsernameValidationTest
{
    @Test
    public void validate_uniqueUsername_returnsTrue()
    {
        UserStorage userStorage = new UserStorage();

        User user = new User(
                "Giovanni",
                "Wachtwoord123!",
                "giovanniberlijn@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        UsernameValidation validation = new UsernameValidation(userStorage);

        assertTrue(validation.validate(user));
    }

    @Test
    public void validate_existingUsername_returnsFalse()
    {
        UserStorage userStorage = new UserStorage();

        User existingUser = new User(
                "Giovanni",
                "Wachtwoord123!",
                "giovanniberlijn@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        userStorage.addUser(existingUser);

        User newUser = new User(
                "Giovanni",
                "Wachtwoord123!",
                "giovanniberlijn@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        UsernameValidation validation = new UsernameValidation(userStorage);

        assertFalse(validation.validate(newUser));
    }
}