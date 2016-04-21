package com.ck.rscp.movieapp.view.views;

import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;

/**
 * Created by ckunder on 07-04-2016.
 */
public interface MainView {

    void onMovieDiscoverLoaded(List<MovieDb> movies);

    void onMovieDiscoverNotLoaded();
}
