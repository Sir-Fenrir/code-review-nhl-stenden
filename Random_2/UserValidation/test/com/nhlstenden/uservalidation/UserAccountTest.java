package com.nhlstenden.uservalidation;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAccountTest
{
    @Test
    void constructor_withValidValues_setsAllFieldsCorrectly()
    {
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);

        UserAccount account = new UserAccount("Jenjira", "jenjira123", "jenjira@email.com", dateOfBirth);

        assertEquals("Jenjira", account.getName());
        assertEquals("jenjira123", account.getPassword());
        assertEquals("jenjira@email.com", account.getEmail());
        assertEquals(dateOfBirth, account.getDateOfBirth());
    }

    @Test
    void setName_withNewValue_updatesName()
    {
        UserAccount account = new UserAccount("Somchai", "pass123", "somchai@email.com", LocalDate.of(2000, 1, 1));

        account.setName("Somsri");

        assertEquals("Somsri", account.getName());
    }

    @Test
    void setDateOfBirth_withNewValue_updatesDateOfBirth()
    {
        UserAccount account = new UserAccount("Somchai", "pass123", "somchai@email.com", LocalDate.of(2000, 1, 1));
        LocalDate newDateOfBirth = LocalDate.of(1995, 5, 20);

        account.setDateOfBirth(newDateOfBirth);

        assertEquals(newDateOfBirth, account.getDateOfBirth());
    }
}