package com.dagger.base;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application application;  //@module is a provider of dependencies

    //constructor with instance of the application
    ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideApplicationContext(){
        return application;

        // Any class can inject the application
        //Useful for getting shared preference, system services
    }

}
