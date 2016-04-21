package com.ck.rscp.movieapp.di.components;

import android.content.Context;

import com.ck.rscp.domain.executor.PostExecutionThread;
import com.ck.rscp.domain.executor.ThreadExecutor;
import com.ck.rscp.domain.repository.MovieRepository;
import com.ck.rscp.movieapp.di.modules.ApplicationModule;
import com.ck.rscp.movieapp.view.activities.BaseActivity;
import com.ck.rscp.movieapp.view.activities.MainActivity;
import com.ck.rscp.movieapp.view.activities.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ckunder on 11-04-2016.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    MovieRepository movieRepository();
}
