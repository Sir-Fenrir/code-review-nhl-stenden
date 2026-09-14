package com.nhlstenden.uservalidation;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserValidationModuleTest
{
    @Test
    void registerUser_allRulesPass_returnsTrueAndStoresAccount()
    {
        UserStorage storage = new UserStorage();
        UserValidationModule module = new UserValidationModule(storage);
        module.addRule(new EmailRule());
        module.addRule(new MinimumAgeRule(18));

        UserAccount account = new UserAccount("Jenjira", "pass123", "jenjira@email.com", LocalDate.now().minusYears(20));

        boolean result = module.registerUser(account);

        assertTrue(result);
        assertEquals(1, storage.getAccounts().size());
    }

    @Test
    void registerUser_oneRuleFails_returnsFalseAndDoesNotStoreAccount()
    {
        UserStorage storage = new UserStorage();
        UserValidationModule module = new UserValidationModule(storage);
        module.addRule(new EmailRule());
        module.addRule(new MinimumAgeRule(18));

        UserAccount underageAccount = new UserAccount("Jenjira", "pass123", "jenjira@email.com", LocalDate.now().minusYears(10));

        boolean result = module.registerUser(underageAccount);

        assertFalse(result);
        assertEquals(0, storage.getAccounts().size());
    }

    @Test
    void registerUser_duplicateEmail_returnsFalse()
    {
        UserStorage storage = new UserStorage();
        UserAccount existingAccount = new UserAccount("Jenjira", "pass123", "somchai@email.com", LocalDate.of(2000, 1, 1));
        storage.store(existingAccount);

        UserValidationModule module = new UserValidationModule(storage);
        module.addRule(new UsernameRule(storage));

        UserAccount newAccount = new UserAccount("Jenjira", "pass456", "jenjira@email.com", LocalDate.of(1998, 5, 5));

        boolean result = module.registerUser(newAccount);

        assertFalse(result);
    }

    @Test
    void addRule_addingTwoRules_rulesListSizeIsTwo()
    {
        UserStorage storage = new UserStorage();
        UserValidationModule module = new UserValidationModule(storage);

        module.addRule(new EmailRule());
        module.addRule(new MinimumAgeRule(18));

        assertEquals(2, module.getRules().size());
    }
}