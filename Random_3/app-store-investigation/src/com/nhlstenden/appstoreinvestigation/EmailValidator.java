package com.nhlstenden.appstoreinvestigation;

public class EmailValidator
{
    private EmailValidator()
    {
    }

    public static boolean isValid(String email)
    {
        if (email == null || email.isBlank())
        {
            return false;
        }

        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
}