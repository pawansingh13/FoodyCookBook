package com.app.foodycookbook.dagger.modules;

import android.content.Context;

import com.app.foodycookbook.BuildConfig;
import com.app.foodycookbook.dagger.scopes.AppScope;
import com.app.foodycookbook.networking.AuthenticationInterceptor;
import com.app.foodycookbook.networking.RxErrorHandlingCallAdapterFactory2;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/*Module class that will provide instance of variable that are related to network...*/
@Module
public class NetworkModule {

    @Provides
    @AppScope
    OkHttpClient provideOkHttpClientInstance(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);
       builder.hostnameVerifier(getHostnameVerifier());
//        builder.interceptors().add(new ReceivedCookiesInterceptor());
//        builder.addInterceptor(new AuthenticationInterceptor(context));
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }

    private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }

    @Provides
    @AppScope
    Retrofit provideRetrofitInstance(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder().
                client(okHttpClient).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxErrorHandlingCallAdapterFactory2.create())
                .baseUrl(BuildConfig.BaseUrl);

        return builder.build();
    }

}