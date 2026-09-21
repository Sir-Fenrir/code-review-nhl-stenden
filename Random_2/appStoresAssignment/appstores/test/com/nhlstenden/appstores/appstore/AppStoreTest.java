package com.nhlstenden.appstores.appstore;

import com.nhlstenden.appstores.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppStoreTest
{
    private static class TestAppStore extends AppStore
    {
        public TestAppStore(Currency currency, List<App> apps, List<Purchase> purchases)
        {
            super(currency, apps, purchases);
        }

        @Override
        public void addApp(App app)
        {
            this.getApps().add(app);
        }

        @Override
        public double getTotalRevenue()
        {
            return 0.0;
        }

        @Override
        public double getSingleAppRevenue(App app)
        {
            return 0.0;
        }
    }

    private App app;
    private User user;
    private AppStore appStore;

    @BeforeEach
    public void setUp()
    {
        this.app = new App("Chess Master", 4.99, false, false);
        this.user = new User("John", "john@example.com", LocalDate.now().minusYears(25));
        this.appStore = new TestAppStore(Currency.EURO, new ArrayList<>(List.of(this.app)), new ArrayList<>());
    }

    @Test
    public void constructor_nullApps_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new TestAppStore(Currency.EURO, null, new ArrayList<>()));
    }

    @Test
    public void constructor_appsContainingNull_throwsIllegalArgumentException()
    {
        List<App> apps = new ArrayList<>();
        apps.add(null);

        assertThrows(IllegalArgumentException.class, () -> new TestAppStore(Currency.EURO, apps, new ArrayList<>()));
    }

    @Test
    public void constructor_nullPurchases_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new TestAppStore(Currency.EURO, new ArrayList<>(), null));
    }

    @Test
    public void constructor_purchasesContainingNull_throwsIllegalArgumentException()
    {
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(null);

        assertThrows(IllegalArgumentException.class, () -> new TestAppStore(Currency.EURO, new ArrayList<>(), purchases));
    }

    @Test
    public void setApps_validApps_isDefensiveCopy()
    {
        List<App> apps = new ArrayList<>(List.of(this.app));
        this.appStore.setApps(apps);
        apps.add(new App("Other App", 1.0, false, false));

        assertEquals(1, this.appStore.getApps().size());
    }

    @Test
    public void setPurchases_validPurchases_isDefensiveCopy()
    {
        List<Purchase> purchases = new ArrayList<>();
        this.appStore.setPurchases(purchases);
        purchases.add(new Purchase(this.user, this.app));

        assertEquals(0, this.appStore.getPurchases().size());
    }

    @Test
    public void removeApp_nullApp_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.appStore.removeApp(null));
    }

    @Test
    public void removeApp_existingApp_removesApp()
    {
        this.appStore.removeApp(this.app);

        assertFalse(this.appStore.getApps().contains(this.app));
    }

    @Test
    public void addPurchase_nullUser_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.appStore.addPurchase(null, this.app));
    }

    @Test
    public void addPurchase_nullApp_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.appStore.addPurchase(this.user, null));
    }

    @Test
    public void addPurchase_appNotAvailable_throwsIllegalArgumentException()
    {
        App unavailableApp = new App("Unavailable App", 2.0, false, false);

        assertThrows(IllegalArgumentException.class, () -> this.appStore.addPurchase(this.user, unavailableApp));
    }

    @Test
    public void addPurchase_availableApp_addsPurchase()
    {
        this.appStore.addPurchase(this.user, this.app);

        assertEquals(1, this.appStore.getPurchases().size());
    }

    @Test
    public void removePurchase_nullUser_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.appStore.removePurchase(null, this.app));
    }

    @Test
    public void removePurchase_nullApp_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.appStore.removePurchase(this.user, null));
    }

    @Test
    public void removePurchase_existingPurchase_removesPurchase()
    {
        this.appStore.addPurchase(this.user, this.app);

        this.appStore.removePurchase(this.user, this.app);

        assertEquals(0, this.appStore.getPurchases().size());
    }

    @Test
    public void isAppAvailable_appInStore_returnsTrue()
    {
        assertTrue(this.appStore.isAppAvailable(this.app));
    }

    @Test
    public void isAppAvailable_appNotInStore_returnsFalse()
    {
        App otherApp = new App("Other App", 1.0, false, false);

        assertFalse(this.appStore.isAppAvailable(otherApp));
    }
}
