package com.nhlstenden.uservalidation;

import java.util.ArrayList;
import java.util.List;

public class UserStorage
{
    private List<User> users;

    public UserStorage()
    {
        this.users = new ArrayList<>();
    }

    public List<User> getUsers()
    {
        return this.users;
    }

    public void addUser(User user)
    {
        this.users.add(user);
    }
}