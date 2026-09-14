package com.nhlstenden.uservalidation;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinimumAgeRuleTest
{
    private UserAccount createAccountWithDateOfBirth(LocalDate dateOfBirth)
    {
        return new UserAccount("Test", "pass123", "test@email.com", dateOfBirth);
    }

    @Test
    void isValid_ageExactlyEqualsMinimumAge_returnsTrue()
    {
        MinimumAgeRule rule = new MinimumAgeRule(18);
        LocalDate dateOfBirth = LocalDate.now().minusYears(18);
        UserAccount account = createAccountWithDateOfBirth(dateOfBirth);

        boolean result = rule.isValid(account);

        assertTrue(result);
    }

    @Test
    void isValid_ageAboveMinimumAge_returnsTrue()
    {
        MinimumAgeRule rule = new MinimumAgeRule(18);
        LocalDate dateOfBirth = LocalDate.now().minusYears(25);
        UserAccount account = createAccountWithDateOfBirth(dateOfBirth);

        boolean result = rule.isValid(account);

        assertTrue(result);
    }

    @Test
    void isValid_ageOneDayBelowMinimumAge_returnsFalse()
    {
        MinimumAgeRule rule = new MinimumAgeRule(18);
        LocalDate dateOfBirth = LocalDate.now().minusYears(18).plusDays(1);
        UserAccount account = createAccountWithDateOfBirth(dateOfBirth);

        boolean result = rule.isValid(account);

        assertFalse(result);
    }
}