package com.nhlstenden.appstore.store;

import com.nhlstenden.appstore.exception.DownloadNotAllowedException;
import com.nhlstenden.appstore.model.App;
import com.nhlstenden.appstore.model.Currency;
import com.nhlstenden.appstore.model.User;
import org.junit.jupiter.api.Test;
import com.nhlstenden.appstore.store.PlayStore;
import com.nhlstenden.appstore.store.Store;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StoreTest
{

	@Test
	void purchase_userMeetsAgeRequirement_addsPurchaseSuccessfully() throws DownloadNotAllowedException
	{
		Store store = new PlayStore(Currency.EUR);
		App app = new App("Shooter", 10.0, true, false);
		User user = new User("John", "john@student.nhlstenden.com", LocalDate.now().minusYears(20));

		store.addApp(app);
		store.purchase(user, app);

		assertEquals(7.0, store.calculateRevenue(app), 0.001);
	}

	@Test
	void purchase_userUnderMinimumAge_throwsDownloadNotAllowedException()
	{
		Store store = new PlayStore(Currency.EUR);
		App app = new App("Shooter", 10.0, true, false);
		User user = new User("John", "john@student.nhlstenden.com", LocalDate.now().minusYears(10));

		store.addApp(app);

		assertThrows(DownloadNotAllowedException.class, () -> store.purchase(user, app));
	}

	@Test
	void calculateTotalRevenue_multipleAppsPurchased_returnsSumOfRevenue() throws DownloadNotAllowedException
	{
		Store store = new PlayStore(Currency.EUR);
		App appOne = new App("App One", 10.0, false, false);
		App appTwo = new App("App Two", 20.0, false, false);
		User user = new User("John", "john@student.nhlstenden.com", LocalDate.now().minusYears(20));

		store.addApp(appOne);
		store.addApp(appTwo);
		store.purchase(user, appOne);
		store.purchase(user, appTwo);

		assertEquals(21.0, store.calculateTotalRevenue(), 0.001);
	}
}