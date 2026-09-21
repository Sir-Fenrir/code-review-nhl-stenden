package com.nhlstenden.appstores.controller;

import com.nhlstenden.appstores.appstore.App;
import com.nhlstenden.appstores.appstore.AppStore;
import com.nhlstenden.appstores.appstore.AppleAppStore;
import com.nhlstenden.appstores.appstore.Currency;
import com.nhlstenden.appstores.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppStoresControllerTest
{
    private AppStoresController controller;
    private App app;
    private User user;
    private AppStore appStore;

    @BeforeEach
    public void setUp()
    {
        this.controller = new AppStoresController();
        this.app = new App("Chess Master", 10.0, false, false);
        this.user = new User("John", "john@example.com", LocalDate.now().minusYears(25));
        this.appStore = new AppleAppStore(Currency.EURO, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void constructor_newController_hasEmptyAppStoresAndUsers()
    {
        assertTrue(this.controller.getAppStores().isEmpty());
        assertTrue(this.controller.getUsers().isEmpty());
    }

    @Test
    public void setAppStores_nullAppStores_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.controller.setAppStores(null));
    }

    @Test
    public void setAppStores_appStoresContainingNull_throwsIllegalArgumentException()
    {
        List<AppStore> appStores = new ArrayList<>();
        appStores.add(null);

        assertThrows(IllegalArgumentException.class, () -> this.controller.setAppStores(appStores));
    }

    @Test
    public void setAppStores_validAppStores_isDefensiveCopy()
    {
        List<AppStore> appStores = new ArrayList<>(List.of(this.appStore));
        this.controller.setAppStores(appStores);
        appStores.add(new AppleAppStore(Currency.DOLLAR, new ArrayList<>(), new ArrayList<>()));

        assertEquals(1, this.controller.getAppStores().size());
    }

    @Test
    public void setUsers_nullUsers_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.controller.setUsers(null));
    }

    @Test
    public void setUsers_usersContainingNull_throwsIllegalArgumentException()
    {
        List<User> users = new ArrayList<>();
        users.add(null);

        assertThrows(IllegalArgumentException.class, () -> this.controller.setUsers(users));
    }

    @Test
    public void registerApp_nullApp_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.controller.registerApp(null, this.appStore));
    }

    @Test
    public void registerApp_nullAppStore_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.controller.registerApp(this.app, null));
    }

    @Test
    public void registerApp_validArguments_addsAppToStore()
    {
        this.controller.registerApp(this.app, this.appStore);

        assertTrue(this.appStore.getApps().contains(this.app));
    }

    @Test
    public void getTotalRevenue_nullAppStore_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.controller.getTotalRevenue(null));
    }

    @Test
    public void getTotalRevenue_appStoreWithPurchase_returnsAppStoreRevenue()
    {
        this.appStore.addApp(this.app);
        this.appStore.addPurchase(this.user, this.app);

        assertEquals(this.appStore.getTotalRevenue(), this.controller.getTotalRevenue(this.appStore));
    }

    @Test
    public void getSingleAppRevenue_nullApp_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.controller.getSingleAppRevenue(null, this.appStore));
    }

    @Test
    public void getSingleAppRevenue_nullAppStore_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.controller.getSingleAppRevenue(this.app, null));
    }

    @Test
    public void getSingleAppRevenue_appWithPurchase_returnsSingleAppRevenue()
    {
        this.appStore.addApp(this.app);
        this.appStore.addPurchase(this.user, this.app);

        assertEquals(this.appStore.getSingleAppRevenue(this.app), this.controller.getSingleAppRevenue(this.app, this.appStore));
    }

    @Test
    public void addUser_nullUser_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.controller.addUser(null));
    }

    @Test
    public void addUser_validUser_addsUser()
    {
        this.controller.addUser(this.user);

        assertTrue(this.controller.getUsers().contains(this.user));
    }

    @Test
    public void removeUser_nullUser_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.controller.removeUser(null));
    }

    @Test
    public void removeUser_existingUser_removesUser()
    {
        this.controller.addUser(this.user);

        this.controller.removeUser(this.user);

        assertFalse(this.controller.getUsers().contains(this.user));
    }

    @Test
    public void addAppStore_nullAppStore_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.controller.addAppStore(null));
    }

    @Test
    public void addAppStore_validAppStore_addsAppStore()
    {
        this.controller.addAppStore(this.appStore);

        assertTrue(this.controller.getAppStores().contains(this.appStore));
    }

    @Test
    public void removeAppStore_nullAppStore_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.controller.removeAppStore(null));
    }

    @Test
    public void removeAppStore_existingAppStore_removesAppStore()
    {
        this.controller.addAppStore(this.appStore);

        this.controller.removeAppStore(this.appStore);

        assertFalse(this.controller.getAppStores().contains(this.appStore));
    }
}
