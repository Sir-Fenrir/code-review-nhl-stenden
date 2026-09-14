package com.nhlstenden.uservalidation;

public class EmailRule implements ValidationRule
{
    private static final String EMAIL_PATTERN = "^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$";

    public boolean isValid(UserAccount account)
    {
        String email = account.getEmail();

        return email.matches(EMAIL_PATTERN);
    }
}