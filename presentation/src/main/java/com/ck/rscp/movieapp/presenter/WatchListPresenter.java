package com.ck.rscp.movieapp.presenter;

import com.ck.rscp.movieapp.di.PerActivity;
import com.ck.rscp.movieapp.view.views.WatchListView;

/**
 * Created by ckunder on 07-04-2016.
 */
@PerActivity
public class WatchListPresenter extends Presenter<WatchListView> {

    private WatchListView mWatchListView;

    @Override
    public void setView(WatchListView view) {
        mWatchListView = view;
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
