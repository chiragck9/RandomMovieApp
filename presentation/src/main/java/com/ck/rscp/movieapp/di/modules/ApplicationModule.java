package com.ck.rscp.movieapp.di.modules;

import android.content.Context;

import com.ck.rscp.data.cache.DataCache;
import com.ck.rscp.data.entity.mapper.TMDBMapper;
import com.ck.rscp.data.executor.JobExecutor;
import com.ck.rscp.data.repository.MovieDataRepository;
import com.ck.rscp.domain.executor.PostExecutionThread;
import com.ck.rscp.domain.executor.ThreadExecutor;
import com.ck.rscp.domain.repository.MovieRepository;
import com.ck.rscp.movieapp.RandomMovieApplication;
import com.ck.rscp.movieapp.UIThread;
import com.ck.rscp.movieapp.di.PerActivity;
import com.ck.rscp.movieapp.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.movito.themoviedbapi.TmdbApi;

/**
 * Created by ckunder on 11-04-2016.
 */
@Module
public class ApplicationModule {

    private final RandomMovieApplication application;

    public ApplicationModule(RandomMovieApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    public Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    public ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    public PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    public MovieRepository provideMovieRepository(Context context, TMDBMapper TMDBMapper, DataCache dataCache) {
        return new MovieDataRepository(context, TMDBMapper, dataCache);
    }
}
