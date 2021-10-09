package com.app.foodycookbook.feature.meal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.foodycookbook.networking.Resource;

import javax.inject.Inject;

public class MealsViewModel extends AndroidViewModel {
    private MealsRepository mMealsRepository;
    private Application mAppContext;
    private MediatorLiveData<Resource<Meals>> mMealsMediatorLiveData = new MediatorLiveData<>();

    @Inject
    MealsViewModel(@NonNull Application application, MealsRepository repository) {
        super(application);
        mMealsRepository = repository;
        mAppContext = application;
        observeData();
    }

    private void observeData() {
        mMealsMediatorLiveData.addSource(mMealsRepository.getMealsData(), expenseSummaryModelResource  -> {
            switch (expenseSummaryModelResource.mStatus) {
                case LOADING:
                    mMealsMediatorLiveData.setValue(Resource.loading(null));
                    break;
                case SUCCESS:
                    mMealsMediatorLiveData.setValue(Resource.success(expenseSummaryModelResource.mData));
                    break;
                case ERROR:
                    mMealsMediatorLiveData.setValue(Resource.errorData(expenseSummaryModelResource.mError, null));
                    break;
                case VALIDATION:
                    mMealsMediatorLiveData.setValue(Resource.validation(expenseSummaryModelResource.mData));
                    break;
            }
        });





    }



    public MutableLiveData<Resource<Meals>> getMeals() {
        return mMealsMediatorLiveData;
    }

    public void doGetMeals() {
        mMealsRepository.doGetMeals();
    }


}
