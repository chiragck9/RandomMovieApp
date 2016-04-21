package com.ck.rscp.movieapp.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import com.ck.rscp.domain.datamodel.Filter;
import com.ck.rscp.domain.datamodel.GenreModel;
import com.ck.rscp.movieapp.R;
import com.ck.rscp.movieapp.di.HasComponent;
import com.ck.rscp.movieapp.di.components.DaggerMovieComponent;
import com.ck.rscp.movieapp.di.components.MovieComponent;
import com.ck.rscp.movieapp.di.modules.MovieModule;
import com.ck.rscp.movieapp.view.fragments.FilterBottomSheetFragment;
import com.ck.rscp.movieapp.view.fragments.MainFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements HasComponent<MovieComponent> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private MovieComponent movieComponent;
    private Filter mFilter;
    private List<GenreModel> mGenres;
    private MainFragment mMainFragment;
    private Animation rotation;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
        setSupportActionBar(toolbar);
        getIntentExtras();
        mMainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        mMainFragment.setFilter(mFilter);
    }

    @OnClick(R.id.fab)
    public void onDiscoverMovies() {

        rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotation.setRepeatCount(Animation.INFINITE);
        fab.startAnimation(rotation);

        mMainFragment.onDiscoverMovies();
    }

    public void clearAnimation() {
        fab.clearAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_filter) {
            if (mGenres != null) {
                FilterBottomSheetFragment.newInstance(mFilter, mGenres).show(getSupportFragmentManager(), "bottom sheet");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    public static Intent getLaunchIntent(Context context, Filter filter, ArrayList<GenreModel> genres) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("FILTER", filter);
        intent.putExtra("GENRES", genres);
        return intent;
    }

    private void getIntentExtras() {
        if (getIntent() != null) {
            mFilter = (Filter) getIntent().getExtras().getSerializable("FILTER");
            mGenres = (List<GenreModel>) getIntent().getExtras().getSerializable("GENRES");
        }
    }
}
