package com.nhlstenden.appstoreinvestigation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppStoreTest
{
    @Test
    public void purchaseApp_userOldEnough_purchaseIsStored() throws DownloadNotAllowedException
    {
        AppStore appStore = new GooglePlayStore(Currency.EURO);
        App app = new App(
                "Action Game",
                10.00,
                true,
                false
        );
        User user = new User(
                "John",
                "johndoe@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        appStore.purchaseApp(user, app);

        assertEquals(1, appStore.getPurchases().size());
    }

    @Test
    public void purchaseApp_userTooYoung_throwsDownloadNotAllowedException()
    {
        AppStore appStore = new GooglePlayStore(Currency.EURO);
        App app = new App(
                "Mature App",
                10.00,
                false,
                true
        );
        User user = new User(
                "John",
                "johndoe@gmail.com",
                LocalDate.now().minusYears(16)
        );

        assertThrows(
                DownloadNotAllowedException.class,
                () -> appStore.purchaseApp(user, app)
        );
    }

    @Test
    public void getTotalRevenue_multiplePurchases_returnsTotalRevenue()
            throws DownloadNotAllowedException
    {
        AppStore appStore = new GooglePlayStore(Currency.EURO);

        App firstApp = new App(
                "First App",
                10.00,
                false,
                false
        );

        App secondApp = new App(
                "Second App",
                20.00,
                false,
                false
        );

        User user = new User(
                "John",
                "johndoe@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        appStore.purchaseApp(user, firstApp);
        appStore.purchaseApp(user, firstApp);
        appStore.purchaseApp(user, secondApp);

        double result = appStore.getTotalRevenue();

        assertEquals(28.00, result, 0.01);
    }

    @Test
    public void getRevenueForApp_multipleApps_returnsRevenueForSelectedApp()
            throws DownloadNotAllowedException
    {
        AppStore appStore = new GooglePlayStore(Currency.EURO);

        App firstApp = new App(
                "First App",
                10.00,
                false,
                false
        );

        App secondApp = new App(
                "Second App",
                20.00,
                false,
                false
        );

        User user = new User(
                "John",
                "johndoe@gmail.com",
                LocalDate.of(2001, 10, 8)
        );

        appStore.purchaseApp(user, firstApp);
        appStore.purchaseApp(user, firstApp);
        appStore.purchaseApp(user, secondApp);

        double result = appStore.getRevenueForApp(firstApp);

        assertEquals(14.00, result, 0.01);
    }
}