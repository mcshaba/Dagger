package com.dagger.data;

import com.dagger.model.Repo;
import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

@AutoValue
public abstract class TrendingRepoResponse {

    @Json(name = "items")
    public abstract List<Repo> repos();

    public static JsonAdapter<TrendingRepoResponse> jsonAdapter(Moshi moshi){
        return new AutoValue_TrendingRepoResponse.MoshiJsonAdapter(moshi);
    }
}
