package com.nhlstenden.uservalidation;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsernameRuleTest
{
    @Test
    void isValid_emailNotYetInStorage_returnsTrue()
    {
        UserStorage storage = new UserStorage();
        UsernameRule rule = new UsernameRule(storage);
        UserAccount account = new UserAccount("Somchai", "pass123", "somchai@email.com", LocalDate.of(2000, 1, 1));

        boolean result = rule.isValid(account);

        assertTrue(result);
    }

    @Test
    void isValid_emailAlreadyInStorage_returnsFalse()
    {
        UserStorage storage = new UserStorage();
        UserAccount existingAccount = new UserAccount("Somchai", "pass123", "somchai@email.com", LocalDate.of(2000, 1, 1));
        storage.store(existingAccount);

        UsernameRule rule = new UsernameRule(storage);
        UserAccount newAccount = new UserAccount("Somsri", "pass456", "somchai@email.com", LocalDate.of(1998, 5, 5));

        boolean result = rule.isValid(newAccount);

        assertFalse(result);
    }
}