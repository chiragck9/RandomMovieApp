package com.ck.rscp.movieapp.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ck.rscp.movieapp.RandomMovieApplication;
import com.ck.rscp.movieapp.di.components.ApplicationComponent;
import com.ck.rscp.movieapp.di.modules.ActivityModule;
import com.ck.rscp.movieapp.navigation.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by ckunder on 11-04-2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getLayoutId();

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.getApplicationComponent().inject(this);
        ButterKnife.bind(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((RandomMovieApplication) getApplication()).getApplicationComponent();
    }
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
