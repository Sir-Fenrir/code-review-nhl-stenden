package com.nhlstenden.appstores.user;

import com.nhlstenden.appstores.appstore.App;
import com.nhlstenden.appstores.appstore.AppStore;
import com.nhlstenden.appstores.appstore.AppleAppStore;
import com.nhlstenden.appstores.appstore.Currency;
import com.nhlstenden.appstores.appstore.GooglePlayStore;
import com.nhlstenden.appstores.exception.DownloadNotAllowedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
    private User user;
    private AppStore appStore;

    @BeforeEach
    public void setUp()
    {
        this.user = new User("John", "john@example.com", LocalDate.now().minusYears(25));
        this.appStore = new AppleAppStore(Currency.EURO, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void constructor_nullName_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new User(null, "john@example.com", LocalDate.now().minusYears(25)));
    }

    @Test
    public void constructor_blankName_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new User("   ", "john@example.com", LocalDate.now().minusYears(25)));
    }

    @Test
    public void validateEmail_blankEmail_setNull()
    {
        User john = new User("John", "", LocalDate.now().minusYears(21));

        assertNull(john.getEmail());
    }

    @Test
    public void validateEmail_invalidEmail_setNull()
    {
        User john = new User("John", "invalid-email", LocalDate.now().minusYears(21));

        assertNull(john.getEmail());
    }

    @Test
    public void validateEmail_validEmail_setsEmail()
    {
        assertEquals("john@example.com", this.user.getEmail());
    }

    @Test
    public void setEmail_blankEmail_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.user.setEmail(""));
    }

    @Test
    public void setEmail_nullEmail_setsNull()
    {
        this.user.setEmail(null);

        assertNull(this.user.getEmail());
    }

    @Test
    public void setDateOfBirth_nullDate_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.user.setDateOfBirth(null));
    }

    @Test
    public void setDateOfBirth_dateIsToday_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.user.setDateOfBirth(LocalDate.now()));
    }

    @Test
    public void setDateOfBirth_dateInFuture_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.user.setDateOfBirth(LocalDate.now().plusDays(1)));
    }

    @Test
    public void setDateOfBirth_validDate_setsDateOfBirth()
    {
        LocalDate dateOfBirth = LocalDate.now().minusYears(30);

        this.user.setDateOfBirth(dateOfBirth);

        assertEquals(dateOfBirth, this.user.getDateOfBirth());
    }

    @Test
    public void getAge_dateOfBirthTwentyFiveYearsAgo_returnsTwentyFive()
    {
        assertEquals(25, this.user.getAge());
    }

    @Test
    public void purchaseApp_nullApp_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.user.purchaseApp(null, this.appStore));
    }

    @Test
    public void purchaseApp_nullAppStore_throwsIllegalArgumentException()
    {
        App app = new App("Chess Master", 4.99, false, false);

        assertThrows(IllegalArgumentException.class, () -> this.user.purchaseApp(app, null));
    }

    @Test
    public void purchaseApp_violentAppUnderage_throwsDownloadNotAllowedException()
    {
        User minor = new User("Minor", "minor@example.com", LocalDate.now().minusYears(10));
        App violentApp = new App("Warzone", 9.99, true, false);
        this.appStore.addApp(violentApp);

        assertThrows(DownloadNotAllowedException.class, () -> minor.purchaseApp(violentApp, this.appStore));
    }

    @Test
    public void purchaseApp_nudityAppUnderage_throwsDownloadNotAllowedException()
    {
        User minor = new User("Minor", "minor@example.com", LocalDate.now().minusYears(17));
        App nudityApp = new App("Adult App", 9.99, false, true);
        AppStore googlePlayStore = new GooglePlayStore(Currency.EURO, new ArrayList<>(), new ArrayList<>());
        googlePlayStore.addApp(nudityApp);

        assertThrows(DownloadNotAllowedException.class, () -> minor.purchaseApp(nudityApp, googlePlayStore));
    }

    @Test
    public void purchaseApp_appNotAvailableInStore_throwsIllegalArgumentException()
    {
        App app = new App("Chess Master", 4.99, false, false);

        assertThrows(IllegalArgumentException.class, () -> this.user.purchaseApp(app, this.appStore));
    }

    @Test
    public void purchaseApp_validPurchase_addsPurchaseToAppStore() throws DownloadNotAllowedException
    {
        App app = new App("Chess Master", 4.99, false, false);
        this.appStore.addApp(app);

        this.user.purchaseApp(app, this.appStore);

        assertEquals(1, this.appStore.getPurchases().size());
    }
}
