package com.app.foodycookbook.dagger.modules;

import com.app.foodycookbook.dagger.scopes.AppScope;
import com.app.foodycookbook.networking.ApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/*Module class that will provide instance of variable that are related to network Api's to inject via Dagger...*/

@Module
public class ApiModule {

    @Provides
    @AppScope
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


}
