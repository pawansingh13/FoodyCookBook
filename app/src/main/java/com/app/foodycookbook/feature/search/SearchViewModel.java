package com.app.foodycookbook.feature.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.foodycookbook.feature.meal.Meals;
import com.app.foodycookbook.feature.meal.MealsRepository;
import com.app.foodycookbook.networking.Resource;

import javax.inject.Inject;

public class SearchViewModel extends AndroidViewModel {
    private SearchRepository mSearchRepository;
    private Application mAppContext;
    private MediatorLiveData<Resource<Meals>> mMealsMediatorLiveData = new MediatorLiveData<>();

    @Inject
    SearchViewModel(@NonNull Application application, SearchRepository repository) {
        super(application);
        mSearchRepository = repository;
        mAppContext = application;
        observeData();
    }

    private void observeData() {
        mMealsMediatorLiveData.addSource(mSearchRepository.getSearchMealsData(), expenseSummaryModelResource  -> {
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



    public MutableLiveData<Resource<Meals>> getSearchMeals() {
        return mMealsMediatorLiveData;
    }

    public void doSearch(String searchkey) {
        mSearchRepository.doSearch(searchkey);
    }


}
