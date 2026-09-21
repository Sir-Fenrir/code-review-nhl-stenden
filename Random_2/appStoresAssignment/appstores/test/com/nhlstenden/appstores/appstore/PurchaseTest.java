package com.nhlstenden.appstores.appstore;

import com.nhlstenden.appstores.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest
{
    private App app;
    private User user;

    @BeforeEach
    public void setUp()
    {
        this.app = new App("Chess Master", 4.99, false, false);
        this.user = new User("John", "john@example.com", LocalDate.now().minusYears(25));
    }

    @Test
    public void constructor_nullUser_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new Purchase(null, this.app));
    }

    @Test
    public void constructor_nullApp_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new Purchase(this.user, null));
    }

    @Test
    public void constructor_validArguments_setsUserAndApp()
    {
        Purchase purchase = new Purchase(this.user, this.app);

        assertEquals(this.user, purchase.getUser());
        assertEquals(this.app, purchase.getApp());
    }

    @Test
    public void setUser_nullUser_throwsIllegalArgumentException()
    {
        Purchase purchase = new Purchase(this.user, this.app);

        assertThrows(IllegalArgumentException.class, () -> purchase.setUser(null));
    }

    @Test
    public void setApp_nullApp_throwsIllegalArgumentException()
    {
        Purchase purchase = new Purchase(this.user, this.app);

        assertThrows(IllegalArgumentException.class, () -> purchase.setApp(null));
    }
}
