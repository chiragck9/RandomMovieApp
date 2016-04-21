package com.ck.rscp.movieapp;

import android.app.Application;

import com.ck.rscp.movieapp.di.components.ApplicationComponent;
import com.ck.rscp.movieapp.di.components.DaggerApplicationComponent;
import com.ck.rscp.movieapp.di.modules.ApplicationModule;

import io.paperdb.Paper;

/**
 * Created by ckunder on 11-04-2016.
 */
public class RandomMovieApplication extends Application {


    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        Paper.init(this);
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
