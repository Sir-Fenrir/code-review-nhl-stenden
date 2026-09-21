package com.nhlstenden.appstores.appstore;

import com.nhlstenden.appstores.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AppleAppStoreTest
{
    private App app;
    private User user;
    private AppleAppStore appleAppStore;

    @BeforeEach
    public void setUp()
    {
        this.app = new App("Chess Master", 10.0, false, false);
        this.user = new User("John", "john@example.com", LocalDate.now().minusYears(25));
        this.appleAppStore = new AppleAppStore(Currency.EURO, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void addApp_nullApp_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.appleAppStore.addApp(null));
    }

    @Test
    public void addApp_appAlreadyExists_throwsIllegalArgumentException()
    {
        this.appleAppStore.addApp(this.app);

        assertThrows(IllegalArgumentException.class, () -> this.appleAppStore.addApp(this.app));
    }

    @Test
    public void addApp_appContainsNudity_throwsIllegalArgumentException()
    {
        App nudityApp = new App("Nudity App", 5.0, false, true);

        assertThrows(IllegalArgumentException.class, () -> this.appleAppStore.addApp(nudityApp));
    }

    @Test
    public void addApp_validApp_addsApp()
    {
        this.appleAppStore.addApp(this.app);

        assertTrue(this.appleAppStore.getApps().contains(this.app));
    }

    @Test
    public void getTotalRevenue_noPurchases_returnsZero()
    {
        assertEquals(0.0, this.appleAppStore.getTotalRevenue());
    }

    @Test
    public void getTotalRevenue_withPurchases_returnsSeventyPercentOfSum()
    {
        this.appleAppStore.addApp(this.app);
        this.appleAppStore.addPurchase(this.user, this.app);

        assertEquals(7.0, this.appleAppStore.getTotalRevenue(), 0.0001);
    }

    @Test
    public void getSingleAppRevenue_nullApp_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.appleAppStore.getSingleAppRevenue(null));
    }

    @Test
    public void getSingleAppRevenue_appNotAvailable_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.appleAppStore.getSingleAppRevenue(this.app));
    }

    @Test
    public void getSingleAppRevenue_appWithPurchases_returnsSeventyPercentOfSum()
    {
        this.appleAppStore.addApp(this.app);
        this.appleAppStore.addPurchase(this.user, this.app);

        assertEquals(7.0, this.appleAppStore.getSingleAppRevenue(this.app), 0.0001);
    }
}
