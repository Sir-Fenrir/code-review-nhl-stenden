package com.nhlstenden.appstore.model;

import com.nhlstenden.appstore.model.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest
{

	@Test
	void minimumAge_noRestrictions_returnsZero()
	{
		App app = new App("Calculator", 0.0, false, false);
		assertEquals(0, app.getMinimumAge());
	}

	@Test
	void minimumAge_violenceOnly_returnsSixteen()
	{
		App app = new App("Shooter", 9.99, true, false);
		assertEquals(16, app.getMinimumAge());
	}

	@Test
	void minimumAge_nudityOnly_returnsEighteen()
	{
		App app = new App("Adult App", 4.99, false, true);
		assertEquals(18, app.getMinimumAge());
	}

	@Test
	void minimumAge_violenceAndNudity_returnsEighteen()
	{
		App app = new App("Extreme App", 14.99, true, true);
		assertEquals(18, app.getMinimumAge());
	}
}