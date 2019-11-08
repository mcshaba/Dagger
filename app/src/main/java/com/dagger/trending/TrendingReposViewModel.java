package com.dagger.trending;

import com.dagger.R;
import com.dagger.di.ScreenScope;
import com.dagger.model.Repo;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
class TrendingReposViewModel {

    //Relays are both Observables and Consumers
    private  final BehaviorRelay<List<Repo>> repoRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    TrendingReposViewModel(){

    }

    //Expose the Relays as Observables to the view
    Observable<Boolean> loading(){
        return loadingRelay;
    }

    Observable<List<Repo>> repos(){
        return repoRelay;
    }

    Observable<Integer> error(){
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated(){
        return loadingRelay;
    }


    Consumer<List<Repo>> repoUpdated(){
        errorRelay.accept(-1);
        return repoRelay;
    }

    Consumer<Throwable> onError(){
        return  throwable -> {

            Timber.e(throwable, "Error loading Repos");
            errorRelay.accept(R.string.api_error_repos);
        };
    }

}
