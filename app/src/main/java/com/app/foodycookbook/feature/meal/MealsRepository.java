package com.app.foodycookbook.feature.meal;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.app.foodycookbook.baseui.BaseRepository;
import com.app.foodycookbook.networking.ApiService;
import com.app.foodycookbook.networking.BaseNetworkSubscriber;
import com.app.foodycookbook.networking.Resource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/*This class is responsible for processing(fetch and save) of facts data used in application...*/
public class MealsRepository extends BaseRepository {
    private ApiService mApiService;
    private Application mApplication;
    private MutableLiveData<Resource<Meals>> mMealsDataStatus = new MutableLiveData<>();


    @Inject
    MealsRepository(ApiService apiService, Application application) {
        mApiService = apiService;
        mApplication = application;
    }

    public void doGetMeals() {
        mMealsDataStatus.setValue(Resource.loading(null));
        addSubscription(
                mApiService.doGetMeals().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new BaseNetworkSubscriber<Meals>(mApplication) {
                            @Override
                            public void onNext(Meals meals) {
                                super.onNext(meals);
                                mMealsDataStatus.setValue(Resource.success(meals));
                            }

                            @Override
                            public void onComplete() {
                                super.onComplete();
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                mMealsDataStatus.setValue(Resource.errorData(e, null));

                            }
                        }));
    }



    public MutableLiveData<Resource<Meals>> getMealsData() {
        return mMealsDataStatus;
    }

}
