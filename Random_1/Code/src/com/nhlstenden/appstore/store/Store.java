package com.nhlstenden.appstore.store;

import com.nhlstenden.appstore.exception.DownloadNotAllowedException;
import com.nhlstenden.appstore.model.App;
import com.nhlstenden.appstore.model.Currency;
import com.nhlstenden.appstore.model.Purchase;
import com.nhlstenden.appstore.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class Store
{

	private final Currency currency;
	private final List<App> apps;
	private final List<Purchase> purchases;

	protected Store(Currency currency)
	{
		this.currency = currency;
		this.apps = new ArrayList<>();
		this.purchases = new ArrayList<>();
	}

	public void addApp(App app)
	{
		apps.add(app);
	}

	public void purchase(User user, App app) throws DownloadNotAllowedException
	{
		if (user.getAge() < app.getMinimumAge())
		{
			throw new DownloadNotAllowedException(
				user.getName() + " does not meet the minimum age requirement for " + app.getName());
		}
		purchases.add(new Purchase(user, app));
	}

	public double calculateTotalRevenue()
	{
		double total = 0;
		for (App app : apps)
		{
			total += calculateRevenue(app);
		}
		return total;
	}

	public double calculateRevenue(App app)
	{
		long purchaseCount = purchases.stream()
			.filter(purchase -> purchase.getApp().equals(app))
			.count();
		return purchaseCount * app.getPrice() * getStoreShare();
	}

	protected double getStoreShare()
	{
		return 0.7;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	protected List<App> getApps()
	{
		return apps;
	}

	protected List<Purchase> getPurchases()
	{
		return purchases;
	}
}