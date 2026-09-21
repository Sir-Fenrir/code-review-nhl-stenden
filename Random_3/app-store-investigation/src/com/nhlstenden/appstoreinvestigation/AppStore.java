package com.nhlstenden.appstoreinvestigation;

import java.util.ArrayList;
import java.util.List;

public abstract class AppStore
{
    private static final double STORE_REVENUE_PERCENTAGE = 0.70;

    private Currency currency;
    private List<App> apps;
    private List<Purchase> purchases;

    public AppStore(Currency currency)
    {
        this.setCurrency(currency);
        this.setApps(new ArrayList<>());
        this.setPurchases(new ArrayList<>());
    }

    public Currency getCurrency()
    {
        return this.currency;
    }

    public void setCurrency(Currency currency)
    {
        if (currency == null)
        {
            throw new IllegalArgumentException("Currency cannot be null.");
        }

        this.currency = currency;
    }

    public List<App> getApps()
    {
        return this.apps;
    }

    private void setApps(List<App> apps)
    {
        if (apps == null)
        {
            throw new IllegalArgumentException("Apps cannot be null.");
        }

        this.apps = apps;
    }

    public List<Purchase> getPurchases()
    {
        return this.purchases;
    }

    private void setPurchases(List<Purchase> purchases)
    {
        if (purchases == null)
        {
            throw new IllegalArgumentException("Purchases cannot be null.");
        }

        this.purchases = purchases;
    }

    public void uploadApp(App app)
    {
        if (app == null)
        {
            throw new IllegalArgumentException("App cannot be null.");
        }

        this.apps.add(app);
    }

    public void purchaseApp(User user, App app) throws DownloadNotAllowedException
    {
        if (user == null)
        {
            throw new IllegalArgumentException("User cannot be null.");
        }

        if (app == null)
        {
            throw new IllegalArgumentException("App cannot be null.");
        }

        if (user.getAge() < app.getMinimumAge())
        {
            throw new DownloadNotAllowedException("User is too young to download this app.");
        }

        Purchase purchase = new Purchase(user, app);
        this.purchases.add(purchase);
    }

    public double getTotalRevenue()
    {
        double totalRevenue = 0;

        for (Purchase purchase : this.purchases)
        {
            totalRevenue += purchase.getApp().getPrice() * STORE_REVENUE_PERCENTAGE;
        }

        return totalRevenue;
    }

    public double getRevenueForApp(App app)
    {
        if (app == null)
        {
            throw new IllegalArgumentException("App cannot be null.");
        }

        double totalRevenue = 0;

        for (Purchase purchase : this.purchases)
        {
            if (purchase.getApp() == app)
            {
                totalRevenue += app.getPrice() * STORE_REVENUE_PERCENTAGE;
            }
        }

        return totalRevenue;
    }
}