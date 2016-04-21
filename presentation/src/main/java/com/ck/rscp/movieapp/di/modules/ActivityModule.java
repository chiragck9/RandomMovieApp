package com.ck.rscp.movieapp.di.modules;

import android.app.Activity;

import com.ck.rscp.movieapp.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ckunder on 11-04-2016.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
    this.activity = activity;
    }
    @Provides @PerActivity
    public Activity activity() {
        return activity;
    }
}
