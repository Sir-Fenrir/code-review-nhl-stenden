package com.nhlstenden.appstores.controller;

import com.nhlstenden.appstores.appstore.App;
import com.nhlstenden.appstores.appstore.AppStore;
import com.nhlstenden.appstores.user.User;

import java.util.ArrayList;
import java.util.List;

public class AppStoresController
{
    private List<AppStore> appStores;
    private List<User> users;

    public AppStoresController()
    {
        this.setAppStores(new ArrayList<>());
        this.setUsers(new ArrayList<>());
    }

    public List<AppStore> getAppStores()
    {
        return this.appStores;
    }

    public void setAppStores(List<AppStore> appStores)
    {
        if (appStores == null)
        {
            throw new IllegalArgumentException("appStores cannot be null");
        }

        for (AppStore appStoresItem : appStores)
        {
            if (appStoresItem == null)
            {
                throw new IllegalArgumentException("appStores cannot be null");
            }
        }

        this.appStores = new ArrayList<>(appStores);
    }

    public List<User> getUsers()
    {
        return this.users;
    }

    public void setUsers(List<User> users)
    {
        if (users == null)
        {
            throw new IllegalArgumentException("users cannot be null");
        }

        for (User usersItem : users)
        {
            if (usersItem == null)
            {
                throw new IllegalArgumentException("users cannot be null");
            }
        }

        this.users = new ArrayList<>(users);
    }

    public void registerApp(App app, AppStore appStore)
    {
        if (app == null || appStore == null)
        {
            throw new IllegalArgumentException("app or appStore cannot be null");
        }

        appStore.addApp(app);
    }

    public double getTotalRevenue(AppStore appStore)
    {
        if (appStore == null)
        {
            throw new IllegalArgumentException("appStore cannot be null");
        }

        return appStore.getTotalRevenue();
    }

    public double getSingleAppRevenue(App app, AppStore appStore)
    {
        if (app == null || appStore == null)
        {
            throw new IllegalArgumentException("app or appStore cannot be null");
        }

        return appStore.getSingleAppRevenue(app);
    }

    public void addUser(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("user cannot be null");
        }

        this.users.add(user);
    }

    public void removeUser(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("user cannot be null");
        }

        this.users.remove(user);
    }

    public void addAppStore(AppStore appStore)
    {
        if (appStore == null)
        {
            throw new IllegalArgumentException("appStore cannot be null");
        }

        this.appStores.add(appStore);
    }

    public void removeAppStore(AppStore appStore)
    {
        if (appStore == null)
        {
            throw new IllegalArgumentException("appStore cannot be null");
        }

        this.appStores.remove(appStore);
    }
}
