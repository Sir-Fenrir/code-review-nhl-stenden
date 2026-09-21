package com.nhlstenden.appstores.user;

import com.nhlstenden.appstores.exception.DownloadNotAllowedException;
import com.nhlstenden.appstores.appstore.App;
import com.nhlstenden.appstores.appstore.AppStore;

import java.time.LocalDate;
import java.time.Period;

public class User
{
    private String name;
    private String email;
    private LocalDate dateOfBirth;

    public User(String name, String email, LocalDate dateOfBirth)
    {
        this.setName(name);
        this.setEmail(EmailValidator.validateEmail(email));
        this.setDateOfBirth(dateOfBirth);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("name cannot be null or blank");
        }

        this.name = name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        if (email != null && email.isBlank())
        {
            throw new IllegalArgumentException("email cannot be blank");
        }

        this.email = email;
    }

    public LocalDate getDateOfBirth()
    {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        if (dateOfBirth == null || dateOfBirth.equals(LocalDate.now()) || dateOfBirth.isAfter(LocalDate.now()))
        {
            throw new IllegalArgumentException("dateOfBirth cannot be null, now or in the future");
        }

        this.dateOfBirth = dateOfBirth;
    }

    public int getAge()
    {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void purchaseApp(App app, AppStore appStore) throws DownloadNotAllowedException
    {
        if (app == null || appStore == null)
        {
            throw new IllegalArgumentException("app or appStore cannot be null");
        }

        if (app.containsViolence() && !app.isAgeEligibleForViolence(this.getAge())) {
            throw new DownloadNotAllowedException("too young to purchase this app - contains violence");
        }

        if (app.containsNudity() && !app.isAgeEligibleForNudity(this.getAge())) {
            throw new DownloadNotAllowedException("too young to purchase this app - contains nudity");
        }

        if (!appStore.isAppAvailable(app)) {
            throw new IllegalArgumentException("app is not available in chosen app store");
        }

        appStore.addPurchase(this, app);
    }
}
