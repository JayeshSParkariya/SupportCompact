package org.supportcompact;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;


public class CoreApp extends Application {

    private static CoreApp mInstance;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static synchronized CoreApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
