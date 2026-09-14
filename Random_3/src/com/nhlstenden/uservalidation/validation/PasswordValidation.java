package com.nhlstenden.uservalidation.validation;

import com.nhlstenden.uservalidation.User;

public class PasswordValidation implements ValidationRule
{
    private boolean spacesAllowed;
    private boolean specialCharacterRequired;
    private boolean numberRequired;
    private boolean lowercaseRequired;
    private boolean uppercaseRequired;

    public PasswordValidation(
            boolean spacesAllowed,
            boolean specialCharacterRequired,
            boolean numberRequired,
            boolean lowercaseRequired,
            boolean uppercaseRequired)
    {
        this.spacesAllowed = spacesAllowed;
        this.specialCharacterRequired = specialCharacterRequired;
        this.numberRequired = numberRequired;
        this.lowercaseRequired = lowercaseRequired;
        this.uppercaseRequired = uppercaseRequired;
    }

    @Override
    public boolean validate(User user)
    {
        String password = user.getPassword();

        if (password == null || password.isBlank())
        {
            return false;
        }

        if (!this.validateSpaces(password))
        {
            return false;
        }

        if (!this.validateSpecialCharacters(password))
        {
            return false;
        }

        if (!this.validateNumbers(password))
        {
            return false;
        }

        if (!this.validateLowercase(password))
        {
            return false;
        }

        if (!this.validateUppercase(password))
        {
            return false;
        }

        return true;
    }

    private boolean validateSpaces(String password)
    {
        if (this.spacesAllowed)
        {
            return true;
        }

        return !password.contains(" ");
    }

    private boolean validateSpecialCharacters(String password)
    {
        if (!this.specialCharacterRequired)
        {
            return true;
        }

        for (char character : password.toCharArray())
        {
            if (!Character.isLetterOrDigit(character) && !Character.isWhitespace(character))
            {
                return true;
            }
        }

        return false;
    }

    private boolean validateNumbers(String password)
    {
        if (!this.numberRequired)
        {
            return true;
        }

        for (char character : password.toCharArray())
        {
            if (Character.isDigit(character))
            {
                return true;
            }
        }

        return false;
    }

    private boolean validateLowercase(String password)
    {
        if (!this.lowercaseRequired)
        {
            return true;
        }

        for (char character : password.toCharArray())
        {
            if (Character.isLowerCase(character))
            {
                return true;
            }
        }

        return false;
    }

    private boolean validateUppercase(String password)
    {
        if (!this.uppercaseRequired)
        {
            return true;
        }

        for (char character : password.toCharArray())
        {
            if (Character.isUpperCase(character))
            {
                return true;
            }
        }

        return false;
    }
}