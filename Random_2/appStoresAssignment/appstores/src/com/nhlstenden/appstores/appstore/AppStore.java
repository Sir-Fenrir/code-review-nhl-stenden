package com.nhlstenden.appstores.appstore;

import com.nhlstenden.appstores.user.User;

import java.util.ArrayList;
import java.util.List;

public abstract class AppStore
{
    private Currency currency;
    private List<App> apps;
    private List<Purchase> purchases;

    public AppStore(Currency currency, List<App> apps, List<Purchase> purchases)
    {
        this.setCurrency(currency);
        this.setApps(apps);
        this.setPurchases(purchases);
    }

    public Currency getCurrency()
    {
        return this.currency;
    }

    public void setCurrency(Currency currency)
    {
        this.currency = currency;
    }

    public List<App> getApps()
    {
        return this.apps;
    }

    public void setApps(List<App> apps)
    {
        if (apps == null)
        {
            throw new IllegalArgumentException("apps cannot be null");
        }

        for (App appsItem : apps)
        {
            if (appsItem == null)
            {
                throw new IllegalArgumentException("apps cannot be null");
            }
        }

        this.apps = new ArrayList<>(apps);
    }

    public List<Purchase> getPurchases()
    {
        return this.purchases;
    }

    public void setPurchases(List<Purchase> purchases)
    {
        if (purchases == null)
        {
            throw new IllegalArgumentException("purchases cannot be null");
        }

        for (Purchase purchasesItem : purchases)
        {
            if (purchasesItem == null)
            {
                throw new IllegalArgumentException("purchases cannot be null");
            }
        }

        this.purchases = new ArrayList<>(purchases);
    }

    public void removeApp(App app)
    {
        if (app == null)
        {
            throw new IllegalArgumentException("app cannot be null");
        }

        this.getApps().remove(app);
    }

    public void addPurchase(User user, App app)
    {
        if (user == null || app == null)
        {
            throw new IllegalArgumentException("user or app cannot be null");
        }

        if (!isAppAvailable(app))
        {
            throw new IllegalArgumentException("this app is not available");
        }

        Purchase purchase = new Purchase(user, app);
        this.getPurchases().add(purchase);
    }

    public void removePurchase(User user, App app)
    {
        if (user == null || app == null)
        {
            throw new IllegalArgumentException("user or app cannot be null");
        }

        this.getPurchases().removeIf(purchase -> purchase.getUser() == user && purchase.getApp() == app);
    }

    public boolean isAppAvailable(App app)
    {
        return this.getApps().contains(app);
    }

    public abstract void addApp(App app);

    public abstract double getTotalRevenue();

    public abstract double getSingleAppRevenue(App app);
}
