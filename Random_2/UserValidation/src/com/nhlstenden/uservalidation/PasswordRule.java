package com.nhlstenden.uservalidation;

public class PasswordRule implements ValidationRule
{
    private boolean allowSpaces;
    private boolean specialCharactersRequired;
    private boolean numbersRequired;
    private boolean lowercaseRequired;
    private boolean uppercaseRequired;

    public PasswordRule(boolean allowSpaces, boolean specialCharactersRequired, boolean numbersRequired, boolean lowercaseRequired, boolean uppercaseRequired)
    {
        this.allowSpaces = allowSpaces;
        this.specialCharactersRequired = specialCharactersRequired;
        this.numbersRequired = numbersRequired;
        this.lowercaseRequired = lowercaseRequired;
        this.uppercaseRequired = uppercaseRequired;
    }

    public boolean isAllowSpaces()
    {
        return this.allowSpaces;
    }

    public boolean isSpecialCharactersRequired()
    {
        return this.specialCharactersRequired;
    }

    public boolean isNumbersRequired()
    {
        return this.numbersRequired;
    }

    public boolean isLowercaseRequired()
    {
        return this.lowercaseRequired;
    }

    public boolean isUppercaseRequired()
    {
        return this.uppercaseRequired;
    }

    public boolean isValid(UserAccount account)
    {
        String password = account.getPassword();

        if (!this.allowSpaces && password.contains(" "))
        {
            return false;
        }

        if (this.specialCharactersRequired && !password.matches(".*[^a-zA-Z0-9].*"))
        {
            return false;
        }

        if (this.numbersRequired && !password.matches(".*[0-9].*"))
        {
            return false;
        }

        if (this.lowercaseRequired && !password.matches(".*[a-z].*"))
        {
            return false;
        }

        if (this.uppercaseRequired && !password.matches(".*[A-Z].*"))
        {
            return false;
        }

        return true;
    }
}