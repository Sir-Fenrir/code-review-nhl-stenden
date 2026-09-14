package com.nhlstenden.uservalidation;

import java.util.ArrayList;
import java.util.List;

public class UserValidationModule
{
    private List<ValidationRule> rules;
    private UserStorage storage;

    public UserValidationModule(UserStorage storage)
    {
        this.storage = storage;
        this.rules = new ArrayList<>();
    }

    public void addRule(ValidationRule rule)
    {
        this.rules.add(rule);
    }

    public boolean registerUser(UserAccount account)
    {
        for (ValidationRule rule : this.rules)
        {
            if (!rule.isValid(account))
            {
                return false;
            }
        }

        this.storage.store(account);

        return true;
    }

    public List<ValidationRule> getRules()
    {
        return this.rules;
    }
}