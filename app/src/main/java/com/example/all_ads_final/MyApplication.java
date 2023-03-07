package com.example.all_ads_final;

import android.app.Application;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;

public class MyApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener()
        {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {

            }
        } );
        AppLovingOpenManager appLovinOpenManager = new AppLovingOpenManager(this);


    }
}
