package com.nhlstenden.uservalidation;

import java.time.LocalDate;

public class User
{
    private String username;
    private String password;
    private String email;
    private LocalDate dateOfBirth;

    public User(String username, String password, String email, LocalDate dateOfBirth)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getEmail()
    {
        return this.email;
    }

    public LocalDate getDateOfBirth()
    {
        return this.dateOfBirth;
    }
}