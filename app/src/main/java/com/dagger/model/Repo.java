package com.dagger.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.threeten.bp.ZonedDateTime;

import javax.annotation.Nullable;


@AutoValue
public abstract class Repo {
    public abstract Long id();

    //can be null
    @Nullable
    public abstract String name();

    public abstract String description();

    public  abstract User owner();

    @Json(name = "stargazers_count")
    public abstract long stargacersCount();

    @Json(name = "forks_count")
    public abstract long forkCount();

//    @Json(name = "contributor_url")
//    public abstract String contributorUrl();

    @Json(name = "created_at")
    public abstract ZonedDateTime createdAt();


    @Json(name = "updated_at")
    public abstract ZonedDateTime updatedAt();

    public static JsonAdapter<Repo> jsonAdapter(Moshi moshi){
        return  new AutoValue_Repo.MoshiJsonAdapter(moshi);
    }
}
