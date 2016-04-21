package com.ck.rscp.movieapp.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ck.rscp.movieapp.di.components.MovieComponent;

/**
 * Created by ckunder on 11-04-2016.
 */
public class MovieDetailFragment extends BaseFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }


    private void initialize() {
        this.getComponent(MovieComponent.class).inject(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
