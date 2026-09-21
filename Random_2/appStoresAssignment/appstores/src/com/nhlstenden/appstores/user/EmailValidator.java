package com.nhlstenden.appstores.user;

public class EmailValidator
{
    private EmailValidator()
    {

    }

    public static String validateEmail(String email)
    {
        if (email.contains("@") && email.contains("."))
        { // I simplified the validation to focus on mastering pure OOP logic
            return email;
        }
        else
        {
            return null;
        }
    }
}
