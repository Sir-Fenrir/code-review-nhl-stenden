package com.nhlstenden.appstores.appstore;

import java.util.List;

public class GooglePlayStore extends AppStore
{
    public GooglePlayStore(Currency currency, List<App> apps, List<Purchase> purchases)
    {
        super(currency, apps, purchases);
    }

    @Override
    public void addApp(App app)
    {
        if (app == null)
        {
            throw new IllegalArgumentException("app cannot be null");
        }

        if (this.getApps().contains(app))
        {
            throw new IllegalArgumentException("app already exists in apps");
        }

        this.getApps().add(app);
    }

    @Override
    public double getTotalRevenue()
    {
        double totalRevenue = 0.0;

        for (Purchase purchase : this.getPurchases())
        {
            totalRevenue += purchase.getApp().getPrice();
        }

        return totalRevenue;
    }

    @Override
    public double getSingleAppRevenue(App app)
    {
        if (app == null)
        {
            throw new IllegalArgumentException("app cannot be null");
        }

        if (!isAppAvailable(app))
        {
            throw new IllegalArgumentException("this app is not available");
        }

        double singleAppRevenue = 0.0;

        for (Purchase purchase : this.getPurchases())
        {
            if (purchase.getApp() == app)
            {
                singleAppRevenue += app.getPrice();
            }
        }

        return singleAppRevenue;
    }
}
