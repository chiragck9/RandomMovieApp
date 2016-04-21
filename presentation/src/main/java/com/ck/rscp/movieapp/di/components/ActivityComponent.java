package com.ck.rscp.movieapp.di.components;

import android.app.Activity;

import com.ck.rscp.movieapp.di.PerActivity;
import com.ck.rscp.movieapp.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by ckunder on 11-04-2016.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}