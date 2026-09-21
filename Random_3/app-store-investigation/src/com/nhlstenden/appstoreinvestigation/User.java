package com.nhlstenden.appstoreinvestigation;

import java.time.LocalDate;
import java.time.Period;

public class User
{
    private String name;
    private String email;
    private LocalDate dateOfBirth;

    public User(String name, String email, LocalDate dateOfBirth)
    {
        this.setName(name);
        this.setEmail(email);
        this.setDateOfBirth(dateOfBirth);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        this.name = name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        if (EmailValidator.isValid(email))
        {
            this.email = email;

            return;
        }

        this.email = null;
    }

    public LocalDate getDateOfBirth()
    {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        if (dateOfBirth == null)
        {
            throw new IllegalArgumentException("Date of birth cannot be null.");
        }

        this.dateOfBirth = dateOfBirth;
    }

    public int getAge()
    {
        LocalDate today = LocalDate.now();

        return Period.between(this.dateOfBirth, today).getYears();
    }
}