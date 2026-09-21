package com.nhlstenden.appstoreinvestigation;

public class AppleAppStore extends AppStore
{
    public AppleAppStore(Currency currency)
    {
        super(currency);
    }

    @Override
    public void uploadApp(App app)
    {
        if (app != null && app.hasNudity())
        {
            return;
        }

        super.uploadApp(app);
    }
}