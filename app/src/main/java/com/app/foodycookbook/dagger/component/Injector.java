package com.app.foodycookbook.dagger.component;

import com.app.foodycookbook.FoodyCookBookApplication;
import com.app.foodycookbook.baseui.BaseActivity;
import com.app.foodycookbook.feature.container.HomeActivity;
import com.app.foodycookbook.feature.favorite.FavoriteFragment;
import com.app.foodycookbook.feature.meal.MealFragment;
import com.app.foodycookbook.feature.search.SearchActivity;
import com.app.foodycookbook.feature.splash.SplashActivity;
/*Interface here Dagger find out the component type in that dagger have to inject dependencies... */

interface Injector {
    void provideIn(FoodyCookBookApplication applicationContext);
    void provideIn(SplashActivity activity);
    void provideIn(BaseActivity activity);
    void provideIn(HomeActivity activity);
    void provideIn(MealFragment fragment);
    void provideIn(SearchActivity fragment);
    void provideIn(FavoriteFragment fragment);
}
