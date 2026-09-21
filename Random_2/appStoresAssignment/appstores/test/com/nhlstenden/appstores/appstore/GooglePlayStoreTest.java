package com.nhlstenden.appstores.appstore;

import com.nhlstenden.appstores.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GooglePlayStoreTest
{
    private App app;
    private User user;
    private GooglePlayStore googlePlayStore;

    @BeforeEach
    public void setUp()
    {
        this.app = new App("Chess Master", 10.0, false, false);
        this.user = new User("John", "john@example.com", LocalDate.now().minusYears(25));
        this.googlePlayStore = new GooglePlayStore(Currency.EURO, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void addApp_nullApp_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.googlePlayStore.addApp(null));
    }

    @Test
    public void addApp_appAlreadyExists_throwsIllegalArgumentException()
    {
        this.googlePlayStore.addApp(this.app);

        assertThrows(IllegalArgumentException.class, () -> this.googlePlayStore.addApp(this.app));
    }

    @Test
    public void addApp_appContainsNudity_addsApp()
    {
        App nudityApp = new App("Nudity App", 5.0, false, true);

        this.googlePlayStore.addApp(nudityApp);

        assertTrue(this.googlePlayStore.getApps().contains(nudityApp));
    }

    @Test
    public void addApp_validApp_addsApp()
    {
        this.googlePlayStore.addApp(this.app);

        assertTrue(this.googlePlayStore.getApps().contains(this.app));
    }

    @Test
    public void getTotalRevenue_noPurchases_returnsZero()
    {
        assertEquals(0.0, this.googlePlayStore.getTotalRevenue());
    }

    @Test
    public void getTotalRevenue_withPurchases_returnsFullSum()
    {
        this.googlePlayStore.addApp(this.app);
        this.googlePlayStore.addPurchase(this.user, this.app);

        assertEquals(10.0, this.googlePlayStore.getTotalRevenue(), 0.0001);
    }

    @Test
    public void getSingleAppRevenue_nullApp_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.googlePlayStore.getSingleAppRevenue(null));
    }

    @Test
    public void getSingleAppRevenue_appNotAvailable_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.googlePlayStore.getSingleAppRevenue(this.app));
    }

    @Test
    public void getSingleAppRevenue_appWithPurchases_returnsFullSum()
    {
        this.googlePlayStore.addApp(this.app);
        this.googlePlayStore.addPurchase(this.user, this.app);

        assertEquals(10.0, this.googlePlayStore.getSingleAppRevenue(this.app), 0.0001);
    }
}
