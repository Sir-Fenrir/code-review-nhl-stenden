package com.nhlstenden.uservalidation.validation;

import com.nhlstenden.uservalidation.User;

public class EmailValidation implements ValidationRule
{
    @Override
    public boolean validate(User user)
    {
        String email = user.getEmail();

        return email != null && !email.isBlank() && email.contains("@");
    }
}