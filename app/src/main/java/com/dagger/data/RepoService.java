package com.dagger.data;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RepoService {

    @GET("search/repositories?q=language:java&sort=stars&order=desc")
    Single<TrendingRepoResponse> getTrendingRepos();  //Single Returns one item or an error //Observables returns more than one item
}
