package com.nhlstenden.uservalidation;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserStorageTest
{
    @Test
    void isEmailTaken_emailNotStored_returnsFalse()
    {
        UserStorage storage = new UserStorage();

        boolean result = storage.isEmailTaken("nobody@email.com");

        assertFalse(result);
    }

    @Test
    void isEmailTaken_emailAlreadyStored_returnsTrue()
    {
        UserStorage storage = new UserStorage();
        UserAccount account = new UserAccount("Somchai", "pass123", "somchai@email.com", LocalDate.of(2000, 1, 1));
        storage.store(account);

        boolean result = storage.isEmailTaken("somchai@email.com");

        assertTrue(result);
    }
}