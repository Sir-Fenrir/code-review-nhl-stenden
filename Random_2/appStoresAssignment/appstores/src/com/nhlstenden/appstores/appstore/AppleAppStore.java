package com.nhlstenden.appstores.appstore;

import java.util.List;

public class AppleAppStore extends AppStore
{
    private static final double PROFIT_PER_APP_IN_PERCENTAGE = 0.7;

    public AppleAppStore(Currency currency, List<App> apps, List<Purchase> purchases)
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

        if (getApps().contains(app))
        {
            throw new IllegalArgumentException("app already exists in apps");
        }

        if (app.containsNudity())
        {
            throw new IllegalArgumentException("app cannot contain nudity");
        }

        this.getApps().add(app);
    }

    @Override
    public double getTotalRevenue()
    {
        double totalRevenue = 0.0;

        for (Purchase purchase : getPurchases())
        {
            totalRevenue += (purchase.getApp().getPrice()) * PROFIT_PER_APP_IN_PERCENTAGE;
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

        for (Purchase purchase : getPurchases())
        {
            if (purchase.getApp() == app)
            {
                singleAppRevenue += app.getPrice() * PROFIT_PER_APP_IN_PERCENTAGE;
            }
        }

        return singleAppRevenue;
    }
}
