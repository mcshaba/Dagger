package com.dagger.trending;

import com.dagger.data.RepoRequester;
import com.dagger.data.TrendingRepoResponse;
import com.dagger.model.Repo;
import com.dagger.testutils.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class TrendingReposPresenterTest {

    @Mock RepoRequester repoRequester;
    @Mock TrendingReposViewModel viewModel;
    @Mock Consumer<Throwable> onErrorConsumer;
    @Mock Consumer<List<Repo>> onSuccessConsumer;
    @Mock Consumer<Boolean> onLoadingConsumer;

    private TrendingReposPresenter presenter;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(viewModel.loadingUpdated()).thenReturn(onLoadingConsumer);
        when(viewModel.onError()).thenReturn(onErrorConsumer);
        when(viewModel.repoUpdated()).thenReturn(onSuccessConsumer);
    }

    @Test
    public void  onRepoLoaded() throws Exception{
        List<Repo> repos = setUpSuccess();
        initializePresenter();

        //verify that
        verify(repoRequester).getTrendingRepos();

        verify(onSuccessConsumer).accept(repos);
        verifyZeroInteractions(onErrorConsumer);
    }

    @Test
    public void onError() throws Exception{
        Throwable error = setUpError();
        initializePresenter();
        verify(onErrorConsumer).accept(error);
        verifyZeroInteractions(onSuccessConsumer);

    }

    @Test
    public void onLoadingSuccess() throws  Exception {
        setUpSuccess();
        initializePresenter();
        InOrder inOrder = Mockito.inOrder(onLoadingConsumer);
        verify(onLoadingConsumer).accept(true);
        verify(onLoadingConsumer).accept(false);
    }


    @Test
    public void onLoadingError() throws Exception {
        //noinspection ThrowableNotThrown
        setUpError();
        initializePresenter();
        InOrder inOrder = Mockito.inOrder(onLoadingConsumer);
        verify(onLoadingConsumer).accept(true);
        verify(onLoadingConsumer).accept(false);
    }
    @Test
    public void onRepoClicked() {

        //TODO
    }

    private List<Repo> setUpSuccess(){

        TrendingRepoResponse response = TestUtils.loadJson("mock/get_trending_repo.json", TrendingRepoResponse.class);
        List<Repo>  repos = response.repos();

        when(repoRequester.getTrendingRepos()).thenReturn(Single.just(repos));
        return repos;

    }

    private Throwable setUpError(){
        Throwable error = new IOException();
        when(repoRequester.getTrendingRepos()).thenReturn(Single.error(error));
        return error;

    }

    private void initializePresenter(){
        presenter = new TrendingReposPresenter(viewModel,repoRequester);
    }
}