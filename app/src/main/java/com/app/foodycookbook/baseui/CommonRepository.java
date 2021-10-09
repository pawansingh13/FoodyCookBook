package com.app.foodycookbook.baseui;

import android.app.Application;

import com.app.foodycookbook.networking.ApiService;

import javax.inject.Inject;

public class CommonRepository extends BaseRepository {
    private ApiService mApiService;
    private Application mApplication;


    @Inject
    public CommonRepository(ApiService apiService, Application application) {
        mApiService = apiService;
        mApplication = application;
    }



}
