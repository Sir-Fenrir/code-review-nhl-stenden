package com.nhlstenden.uservalidation;

import com.nhlstenden.uservalidation.validation.ValidationRule;

import java.util.List;

public class UserValidation
{
    private List<ValidationRule> validationRules;
    private UserStorage userStorage;

    public UserValidation(List<ValidationRule> validationRules, UserStorage userStorage)
    {
        this.validationRules = validationRules;
        this.userStorage = userStorage;
    }

    public boolean validateUser(User user)
    {
        for (ValidationRule validationRule : this.validationRules)
        {
            if (!validationRule.validate(user))
            {
                return false;
            }
        }

        return true;
    }

    public void storeUser(User user)
    {
        if (this.validateUser(user))
        {
            this.userStorage.addUser(user);
        }
    }
}