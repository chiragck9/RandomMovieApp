package com.ck.rscp.movieapp.di.modules;

import com.ck.rscp.domain.executor.PostExecutionThread;
import com.ck.rscp.domain.executor.ThreadExecutor;
import com.ck.rscp.domain.interactor.GetCountriesInteractor;
import com.ck.rscp.domain.interactor.GetCountriesInteractorImp;
import com.ck.rscp.domain.interactor.GetDiscoverInteractor;
import com.ck.rscp.domain.interactor.GetDiscoverInteractorImp;
import com.ck.rscp.domain.interactor.GetFilterInteractor;
import com.ck.rscp.domain.interactor.GetFilterInteractorImp;
import com.ck.rscp.domain.interactor.GetGenreInteractor;
import com.ck.rscp.domain.interactor.GetGenreInteractorImp;
import com.ck.rscp.domain.interactor.GetWatchListInteractor;
import com.ck.rscp.domain.interactor.GetWatchListInteractorImp;
import com.ck.rscp.domain.interactor.SetFilterInteractor;
import com.ck.rscp.domain.interactor.SetFilterInteractorImp;
import com.ck.rscp.domain.repository.MovieRepository;
import com.ck.rscp.movieapp.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ckunder on 11-04-2016.
 */
@Module
public class MovieModule {

    public MovieModule() {
    }

    @Provides
    @PerActivity
    @Named("movieWatchList")
    public GetWatchListInteractor provideGetWatchListInteractor(MovieRepository movieRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetWatchListInteractorImp(movieRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @PerActivity
    @Named("movieGenre")
    public GetGenreInteractor provideGetGenreInteractor(MovieRepository movieRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetGenreInteractorImp(movieRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @PerActivity
    @Named("movieDiscover")
    public GetDiscoverInteractor provideGetDiscoverInteractor(MovieRepository movieRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetDiscoverInteractorImp(movieRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @PerActivity
    @Named("getCountries")
    public GetCountriesInteractor provideGetCountriesInteractor(MovieRepository movieRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetCountriesInteractorImp(movieRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @PerActivity
    @Named("getFilter")
    public GetFilterInteractor provideGetFilterInteractor(MovieRepository movieRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetFilterInteractorImp(movieRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @PerActivity
    @Named("setFilter")
    public SetFilterInteractor provideSetFilterInteractor(MovieRepository movieRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new SetFilterInteractorImp(movieRepository, threadExecutor, postExecutionThread);
    }
}
