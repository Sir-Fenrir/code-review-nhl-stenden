package com.nhlstenden.appstoreinvestigation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppleAppStoreTest
{
    @Test
    public void uploadApp_appWithoutNudity_appIsUploaded()
    {
        AppleAppStore appStore = new AppleAppStore(Currency.EURO);
        App app = new App(
                "Action Game",
                9.99,
                true,
                false
        );

        appStore.uploadApp(app);

        assertEquals(1, appStore.getApps().size());
    }

    @Test
    public void uploadApp_appWithNudity_appIsNotUploaded()
    {
        AppleAppStore appStore = new AppleAppStore(Currency.EURO);
        App app = new App(
                "Mature App",
                9.99,
                false,
                true
        );

        appStore.uploadApp(app);

        assertEquals(0, appStore.getApps().size());
    }
}