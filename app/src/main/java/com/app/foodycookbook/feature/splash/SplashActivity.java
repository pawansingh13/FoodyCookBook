package com.app.foodycookbook.feature.splash;

import android.os.Bundle;
import android.os.Handler;

import com.app.foodycookbook.FoodyCookBookApplication;
import com.app.foodycookbook.R;
import com.app.foodycookbook.baseui.BaseActivity;
import com.app.foodycookbook.feature.container.HomeActivity;
import com.app.foodycookbook.utills.Validation;

public class SplashActivity extends BaseActivity {
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FoodyCookBookApplication.getApp().getDaggerAppComponent().provideIn(this);
        handler = new Handler();
        handler.postDelayed(
                runnable = () -> {
                        switchActivity(HomeActivity.class);
                    Validation.overridePendingTransition(this);
                    finish();
                }, 4000);

    }


    /**
     * This method is called when activity is destroyed.
     * In this, callbacks of handler removed.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
