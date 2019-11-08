package com.dagger.home;

import com.bluelinelabs.conductor.Controller;
import com.dagger.di.ActivityInjector;
import com.dagger.di.ControllerKey;
import com.dagger.trending.TrendingReposComponent;
import com.dagger.trending.TrendingReposController;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        TrendingReposComponent.class,
})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindtrendingRepoInjector(TrendingReposComponent.Builder builder);
}
