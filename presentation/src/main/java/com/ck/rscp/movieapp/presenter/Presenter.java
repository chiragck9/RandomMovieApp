package com.ck.rscp.movieapp.presenter;

/**
 * Created by ckunder on 07-04-2016.
 */
public abstract class Presenter<T> {


    public abstract void setView(T view);

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onDestroy();
}
