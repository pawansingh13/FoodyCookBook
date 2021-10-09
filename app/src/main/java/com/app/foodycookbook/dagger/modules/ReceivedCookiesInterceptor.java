package com.app.foodycookbook.dagger.modules;

import android.util.Log;

import com.app.foodycookbook.FoodyCookBookApplication;
import com.app.foodycookbook.preference.Prefs;
import com.app.foodycookbook.preference.SPKeys;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            Log.v("OkHttp", "7777777777777777777777777777777777777 " + cookies);
            Prefs.getInstance().saveStringSet(FoodyCookBookApplication.getApp() , SPKeys.COOKIE, cookies);
        }
        return originalResponse;
    }
}