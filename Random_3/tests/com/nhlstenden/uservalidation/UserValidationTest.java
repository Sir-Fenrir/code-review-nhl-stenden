package com.nhlstenden.uservalidation;

import com.nhlstenden.uservalidation.validation.AgeValidation;
import com.nhlstenden.uservalidation.validation.EmailValidation;
import com.nhlstenden.uservalidation.validation.ValidationRule;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidationTest
{
    @Test
    public void storeUser_validUser_userIsStored()
    {
        UserStorage userStorage = new UserStorage();

        List<ValidationRule> validationRules = new ArrayList<>();
        validationRules.add(new EmailValidation());
        validationRules.add(new AgeValidation(18));

        UserValidation userValidation =
                new UserValidation(validationRules, userStorage);

        User user = new User(
                "Giovanni",
                "Wachtwoord123!",
                "giovanniberlijn@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        userValidation.storeUser(user);

        assertTrue(userStorage.getUsers().contains(user));
    }
}