package com.ck.rscp.movieapp.presenter;

import com.ck.rscp.movieapp.di.PerActivity;
import com.ck.rscp.movieapp.view.views.DetailView;

/**
 * Created by ckunder on 07-04-2016.
 */
@PerActivity
public class MovieDetailPresenter extends Presenter<DetailView> {

    private DetailView mDetailView;

    @Override
    public void setView(DetailView view) {
        mDetailView = view;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }
}
