package com.app.foodycookbook.dagger.component;


import com.app.foodycookbook.dagger.modules.ApiModule;
import com.app.foodycookbook.dagger.modules.ApplicationModule;
import com.app.foodycookbook.dagger.modules.NetworkModule;
import com.app.foodycookbook.dagger.modules.ViewModelModule;
import com.app.foodycookbook.dagger.scopes.AppScope;
import javax.inject.Singleton;
import dagger.Component;

/*Component class that define all the modules used by Dagger ...*/
@Singleton
@AppScope
@Component(modules = {
        ApplicationModule.class,
        ViewModelModule.class,
        ApiModule.class,
        NetworkModule.class
})
public interface ApplicationComponent extends Injector {
}
