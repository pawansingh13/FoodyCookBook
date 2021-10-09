package com.app.foodycookbook.networking;


import android.content.Context;
import android.util.Log;

import com.app.foodycookbook.FoodyCookBookApplication;
import com.app.foodycookbook.preference.Prefs;
import com.app.foodycookbook.preference.SPKeys;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*This class is used to intercept the request created by retrofit and add required parameter for network bidding... */
public class AuthenticationInterceptor implements Interceptor {


    public AuthenticationInterceptor(Context context) {
        FoodyCookBookApplication.getApp().getDaggerAppComponent().provideIn((FoodyCookBookApplication) context.getApplicationContext());
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        HashSet<String> preferences = Prefs.getInstance().getStringSet(FoodyCookBookApplication.getApp(),
                SPKeys.COOKIE, new HashSet<String>());
        builder.header("Content-Type", "application/json");
      //  builder.header("Cookie", "AspxAutoDetectCookieSupport=1");
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
        }


        Request modifiedRequest = builder.build();
        return chain.proceed(modifiedRequest);
    }
}
