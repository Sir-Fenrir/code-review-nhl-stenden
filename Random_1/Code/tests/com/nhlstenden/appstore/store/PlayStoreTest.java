package com.nhlstenden.appstore.store;

import com.nhlstenden.appstore.model.App;
import com.nhlstenden.appstore.model.Currency;
import org.junit.jupiter.api.Test;
import com.nhlstenden.appstore.store.PlayStore;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PlayStoreTest
{

	@Test
	void addApp_appHasNudity_doesNotThrow()
	{
		PlayStore playStore = new PlayStore(Currency.EUR);
		App app = new App("Adult App", 5.0, false, true);

		assertDoesNotThrow(() -> playStore.addApp(app));
	}
}