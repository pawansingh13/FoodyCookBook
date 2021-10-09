package com.app.foodycookbook;

import android.app.Application;
import com.app.foodycookbook.dagger.component.ApplicationComponent;
import com.app.foodycookbook.dagger.component.DaggerApplicationComponent;
import com.app.foodycookbook.dagger.modules.ApplicationModule;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

    public class FoodyCookBookApplication extends Application {
    private static FoodyCookBookApplication mainApplication;
    private ApplicationComponent mAppComponent;
    public static FoodyCookBookApplication getApp() {
        return mainApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
        System.setProperty("http.keepAlive", "false");
        setUpDagger();
        Fabric.with(this, new Crashlytics());

    }

    /**
     * Method used to setUp dagger
     */
    private void setUpDagger() {

        mAppComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }
    /* *
     * Method used to get DaggerAppComponent instance to get required injection
     * @return AppComponent
     */
    public ApplicationComponent getDaggerAppComponent() {
        return mAppComponent;
    }

}

