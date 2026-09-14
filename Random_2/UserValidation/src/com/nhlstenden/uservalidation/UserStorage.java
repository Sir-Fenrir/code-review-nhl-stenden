package com.nhlstenden.uservalidation;

import java.util.ArrayList;
import java.util.List;

public class UserStorage
{
    private List<UserAccount> accounts;

    public UserStorage()
    {
        this.accounts = new ArrayList<>();
    }

    public void store(UserAccount account)
    {
        this.accounts.add(account);
    }

    public boolean isEmailTaken(String email)
    {
        for (UserAccount account : this.accounts)
        {
            if (account.getEmail().equals(email))
            {
                return true;
            }
        }

        return false;
    }

    public List<UserAccount> getAccounts()
    {
        return this.accounts;
    }
}