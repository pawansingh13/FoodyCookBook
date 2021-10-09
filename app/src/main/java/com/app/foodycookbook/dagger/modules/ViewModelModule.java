package com.app.foodycookbook.dagger.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.app.foodycookbook.baseui.BaseViewModelFactory;
import com.app.foodycookbook.dagger.annotations.ViewModelKey;
import com.app.foodycookbook.feature.meal.MealsViewModel;
import com.app.foodycookbook.feature.search.SearchViewModel;


import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/*Module class that will provide view model class object to inject via Dagger...*/
@Module
public abstract class ViewModelModule {
    // add more ViewModels
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(BaseViewModelFactory factory);



    @Binds
    @IntoMap
    @ViewModelKey(MealsViewModel.class)
    abstract ViewModel MealsViewModel(MealsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel SearchViewModel(SearchViewModel viewModel);


}
