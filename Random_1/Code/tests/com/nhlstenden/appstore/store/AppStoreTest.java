package com.nhlstenden.appstore.store;

import com.nhlstenden.appstore.model.App;
import com.nhlstenden.appstore.model.Currency;
import org.junit.jupiter.api.Test;
import com.nhlstenden.appstore.store.AppStore;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AppStoreTest
{

	@Test
	void addApp_appHasNudity_throwsIllegalArgumentException()
	{
		AppStore appStore = new AppStore(Currency.EUR);
		App app = new App("Adult App", 5.0, false, true);

		assertThrows(IllegalArgumentException.class, () -> appStore.addApp(app));
	}
}