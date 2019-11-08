package com.dagger.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RepoServiceModule {
    @Provides
    @Singleton
    static RepoService provideReposervice(Retrofit retrofit){
        return retrofit.create(RepoService.class);
    }
}
