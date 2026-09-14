package com.nhlstenden.uservalidation;

public class UsernameRule implements ValidationRule
{
    private UserStorage storage;

    public UsernameRule(UserStorage storage)
    {
        this.storage = storage;
    }

    public boolean isValid(UserAccount account)
    {
        return !this.storage.isEmailTaken(account.getEmail());
    }
}