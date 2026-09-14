package com.nhlstenden.uservalidation.validation;

import com.nhlstenden.uservalidation.User;
import com.nhlstenden.uservalidation.UserStorage;

public class UsernameValidation implements ValidationRule
{
    private UserStorage userStorage;

    public UsernameValidation(UserStorage userStorage)
    {
        this.userStorage = userStorage;
    }

    @Override
    public boolean validate(User user)
    {
        for (User currentUser : this.userStorage.getUsers())
        {
            if (currentUser.getUsername().equals(user.getUsername()))
            {
                return false;
            }
        }

        return true;
    }
}