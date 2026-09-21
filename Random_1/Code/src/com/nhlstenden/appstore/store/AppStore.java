package com.nhlstenden.appstore.store;

import com.nhlstenden.appstore.model.App;

import com.nhlstenden.appstore.model.Currency;

public class AppStore extends Store
{

	public AppStore(Currency currency)
	{
		super(currency);
	}

	@Override
	public void addApp(App app)
	{
		if (app.hasNudity())
		{
			throw new IllegalArgumentException("Apple model.App Store does not allow apps with nudity");
		}
		super.addApp(app);
	}
}