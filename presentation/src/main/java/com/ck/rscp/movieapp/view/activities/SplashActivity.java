package com.ck.rscp.movieapp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ck.rscp.domain.datamodel.Filter;
import com.ck.rscp.domain.datamodel.GenreModel;
import com.ck.rscp.movieapp.RandomMovieApplication;
import com.ck.rscp.movieapp.di.HasComponent;
import com.ck.rscp.movieapp.di.components.ApplicationComponent;
import com.ck.rscp.movieapp.di.components.DaggerMovieComponent;
import com.ck.rscp.movieapp.di.components.MovieComponent;
import com.ck.rscp.movieapp.di.modules.ActivityModule;
import com.ck.rscp.movieapp.di.modules.MovieModule;
import com.ck.rscp.movieapp.presenter.SplashPresenter;
import com.ck.rscp.movieapp.view.views.SplashView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ckunder on 17-04-2016.
 */
public class SplashActivity extends AppCompatActivity implements SplashView, HasComponent<MovieComponent> {

    private MovieComponent movieComponent;
    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
        inject();
        splashPresenter.setView(this);
        splashPresenter.getGenres();

    }

    private void inject() {
        this.movieComponent.inject(this);
    }

    @Override
    public void continueToMainActivity(Filter filter, ArrayList<GenreModel> genres) {
        Intent intent = MainActivity.getLaunchIntent(this, filter, genres);
        startActivity(intent);
        finish();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((RandomMovieApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    private void initializeInjector() {
        this.movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .movieModule(new MovieModule())
                .build();
    }

    @Override
    public MovieComponent getComponent() {
        return movieComponent;
    }
}
