package com.nhlstenden.uservalidation;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailRuleTest
{
    private UserAccount createAccountWithEmail(String email)
    {
        return new UserAccount("Jenjira", "jenjira123", email, LocalDate.of(2000, 1, 1));
    }

    @Test
    void isValid_correctEmailFormat_returnsTrue()
    {
        EmailRule rule = new EmailRule();
        UserAccount account = createAccountWithEmail("jenjira@email.com");

        boolean result = rule.isValid(account);

        assertTrue(result);
    }

    @Test
    void isValid_missingAtSymbol_returnsFalse()
    {
        EmailRule rule = new EmailRule();
        UserAccount account = createAccountWithEmail("jenjiraemail.com");

        boolean result = rule.isValid(account);

        assertFalse(result);
    }

    @Test
    void isValid_missingDomainExtension_returnsFalse()
    {
        EmailRule rule = new EmailRule();
        UserAccount account = createAccountWithEmail("jenjiira@email");

        boolean result = rule.isValid(account);

        assertFalse(result);
    }

    @Test
    void isValid_containsSpace_returnsFalse()
    {
        EmailRule rule = new EmailRule();
        UserAccount account = createAccountWithEmail("jenjira @email.com");

        boolean result = rule.isValid(account);

        assertFalse(result);
    }
}