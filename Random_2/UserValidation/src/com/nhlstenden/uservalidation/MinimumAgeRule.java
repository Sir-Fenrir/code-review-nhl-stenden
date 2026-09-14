package com.nhlstenden.uservalidation;

import java.time.LocalDate;
import java.time.Period;

public class MinimumAgeRule implements ValidationRule
{
    private int minimumAge;

    public MinimumAgeRule(int minimumAge)
    {
        this.minimumAge = minimumAge;
    }

    public int getMinimumAge()
    {
        return this.minimumAge;
    }

    public boolean isValid(UserAccount account)
    {
        LocalDate dateOfBirth = account.getDateOfBirth();
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

        return age >= this.minimumAge;
    }
}