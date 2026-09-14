package com.nhlstenden.uservalidation;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordRuleTest
{
    private UserAccount createAccountWithPassword(String password)
    {
        return new UserAccount("Test", password, "test@email.com", LocalDate.of(2000, 1, 1));
    }

    @Test
    void isValid_spacesNotAllowedAndPasswordContainsSpace_returnsFalse()
    {
        PasswordRule rule = new PasswordRule(false, false, false, false, false);
        UserAccount account = createAccountWithPassword("pass word");

        boolean result = rule.isValid(account);

        assertFalse(result);
    }

    @Test
    void isValid_spacesAllowedAndPasswordContainsSpace_returnsTrue()
    {
        PasswordRule rule = new PasswordRule(true, false, false, false, false);
        UserAccount account = createAccountWithPassword("pass word");

        boolean result = rule.isValid(account);

        assertTrue(result);
    }

    @Test
    void isValid_numberRequiredAndPasswordHasNoNumber_returnsFalse()
    {
        PasswordRule rule = new PasswordRule(true, false, true, false, false);
        UserAccount account = createAccountWithPassword("password");

        boolean result = rule.isValid(account);

        assertFalse(result);
    }

    @Test
    void isValid_numberRequiredAndPasswordHasNumber_returnsTrue()
    {
        PasswordRule rule = new PasswordRule(true, false, true, false, false);
        UserAccount account = createAccountWithPassword("password1");

        boolean result = rule.isValid(account);

        assertTrue(result);
    }

    @Test
    void isValid_uppercaseRequiredAndPasswordHasNoUppercase_returnsFalse()
    {
        PasswordRule rule = new PasswordRule(true, false, false, false, true);
        UserAccount account = createAccountWithPassword("password");

        boolean result = rule.isValid(account);

        assertFalse(result);
    }

    @Test
    void isValid_specialCharacterRequiredAndPasswordHasSpecialCharacter_returnsTrue()
    {
        PasswordRule rule = new PasswordRule(true, true, false, false, false);
        UserAccount account = createAccountWithPassword("pass!word");

        boolean result = rule.isValid(account);

        assertTrue(result);
    }

    @Test
    void isValid_allRulesDisabled_returnsTrue()
    {
        PasswordRule rule = new PasswordRule(true, false, false, false, false);
        UserAccount account = createAccountWithPassword("anything");

        boolean result = rule.isValid(account);

        assertTrue(result);
    }
}