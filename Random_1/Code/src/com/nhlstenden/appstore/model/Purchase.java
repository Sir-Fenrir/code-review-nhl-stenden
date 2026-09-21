package com.nhlstenden.appstore.model;

public class Purchase
{

	private final User user;
	private final App app;

	public Purchase(User user, App app)
	{
		this.user = user;
		this.app = app;
	}

	public User getUser()
	{
		return user;
	}

	public App getApp()
	{
		return app;
	}
}