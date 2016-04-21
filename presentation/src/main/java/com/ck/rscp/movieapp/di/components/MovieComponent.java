
package com.ck.rscp.movieapp.di.components;

import com.ck.rscp.movieapp.di.PerActivity;
import com.ck.rscp.movieapp.di.modules.ActivityModule;
import com.ck.rscp.movieapp.di.modules.MovieModule;
import com.ck.rscp.movieapp.view.activities.SplashActivity;
import com.ck.rscp.movieapp.view.fragments.MainFragment;
import com.ck.rscp.movieapp.view.fragments.MovieDetailFragment;
import com.ck.rscp.movieapp.view.fragments.WatchListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MovieModule.class})
public interface MovieComponent extends ActivityComponent {
    void inject(SplashActivity splashActivity);
    void inject(MainFragment mainFragment);

    void inject(MovieDetailFragment movieDetailFragment);

    void inject(WatchListFragment watchListFragment);
}
