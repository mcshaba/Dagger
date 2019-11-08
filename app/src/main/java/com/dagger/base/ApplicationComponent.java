package com.dagger.base;

import com.dagger.data.RepoServiceModule;
import com.dagger.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        RepoServiceModule.class} )
public interface ApplicationComponent {


    void inject(MyApplication myApplication);
}
