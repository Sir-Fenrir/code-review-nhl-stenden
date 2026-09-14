package com.nhlstenden.uservalidation.validation;

import com.nhlstenden.uservalidation.User;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidation implements ValidationRule
{
    private int minimumAge;

    public AgeValidation(int minimumAge)
    {
        if (minimumAge < 0)
        {
            throw new IllegalArgumentException("Minimum age cannot be below 0");
        }

        this.minimumAge = minimumAge;
    }

    public int getMinimumAge()
    {
        return this.minimumAge;
    }

    @Override
    public boolean validate(User user)
    {
        LocalDate dateOfBirth = user.getDateOfBirth();

        if (dateOfBirth == null)
        {
            return false;
        }

        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

        return age >= this.minimumAge;
    }
}