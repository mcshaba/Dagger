package com.dagger.networking;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;

@Module
public abstract class NetworkModule {

    @Provides
    @Singleton
    static Call.Factory provideOkHttp(){
        int REQUEST_TIMEOUT = 10;
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT,TimeUnit.SECONDS);

        return httpClient.build();
        //return new OkHttpClient.Builder().build();
    }

    @Provides
    @Named("base_url")
    static String provideBaseUrl(){
        return  "https://api.github.com/";
    }
}
