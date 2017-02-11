package com.arbuleac.chuckjokes;


import android.app.Application;

import com.arbuleac.chuckjokes.injection.Injector;

import timber.log.Timber;

public class ChuckApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
        Timber.plant(new Timber.DebugTree());
        Injector.init(this);
    }
}
