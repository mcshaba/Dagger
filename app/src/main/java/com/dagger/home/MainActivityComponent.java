package com.dagger.home;

import com.dagger.di.ActivityScope;
import com.dagger.ui.NavigationModule;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

//Components are interfaces

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{  //injected class

//        @BindsInstance
//        public abstract void name(@Named("string") String string);


        //override seedInstance to avoid memory leaks in Activities and controller
        @Override
        public void seedInstance(MainActivity instance) {

        }
    }

//    @Subcomponent(modules = {MainActivity.class} )
//    interface  Factory extends AndroidInjector.Factory<MainActivity>{}
}
