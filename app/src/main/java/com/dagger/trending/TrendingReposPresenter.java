package com.dagger.trending;

import com.dagger.data.RepoRequester;
import com.dagger.di.ScreenScope;
import com.dagger.model.Repo;

import javax.inject.Inject;

@ScreenScope
class TrendingReposPresenter  implements RepoAdapter.RepoClickedListener{


    private final TrendingReposViewModel trendingReposViewModel;
    private final RepoRequester repoRequester;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel trendingReposViewModel, RepoRequester repoRequester){

        this.trendingReposViewModel = trendingReposViewModel;
        this.repoRequester = repoRequester;
        loadRepos();
    }

    private void loadRepos() {
        repoRequester.getTrendingRepos()
                .doOnSubscribe(__ -> trendingReposViewModel.loadingUpdated().accept(true))
                .doOnEvent((data, throwable) -> trendingReposViewModel.loadingUpdated().accept(false))
                .subscribe(trendingReposViewModel.repoUpdated(), trendingReposViewModel.onError());
    }

    @Override
    public void onRepoClicked(Repo repo) {
        //Take us to a detail screen
    }
}
