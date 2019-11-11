package com.dagger.trending;

import com.dagger.R;
import com.dagger.data.TrendingRepoResponse;
import com.dagger.testutils.TestUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

public class TrendingReposViewModelTest {

    private TrendingReposViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        viewModel = new TrendingReposViewModel();

    }

    @Test
    public void loading() {
        TestObserver<Boolean> loadingObserver = viewModel.loading().test();
        try {
            viewModel.loadingUpdated().accept(true);
            viewModel.loadingUpdated().accept(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadingObserver.assertValues(true, false);
    }

    @Test
    public void repos() {

        TrendingRepoResponse response = TestUtils.loadJson("mock/get_trending_repo.json", TrendingRepoResponse.class);
        try {
            viewModel.repoUpdated().accept(response.repos());
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewModel.repos().test().assertValue(response.repos());
    }

    @Test
    public void error() {
        TestObserver<Integer> errorObserver = viewModel.error().test();
        try {
            viewModel.onError().accept(new IOException());
            viewModel.repoUpdated().accept(Collections.emptyList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        errorObserver.assertValues(R.string.api_error_repos, -1);
    }
}