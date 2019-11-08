package com.dagger.data;

import com.dagger.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RepoRequester {

    private final RepoService service;

    @Inject
    RepoRequester(RepoService service){
        this.service =service;
    }

    public Single<List<Repo>> getTrendingRepos(){
        return service.getTrendingRepos()
                .map(TrendingRepoResponse::repos)
                .subscribeOn(Schedulers.io());
    }
}
//    .map(trendingRepoResponse -> trendingRepoResponse.repos())    //lamda map