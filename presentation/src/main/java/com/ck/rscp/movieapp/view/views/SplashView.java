package com.ck.rscp.movieapp.view.views;

import com.ck.rscp.domain.datamodel.Filter;
import com.ck.rscp.domain.datamodel.GenreModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckunder on 19-04-2016.
 */
public interface SplashView {

    void continueToMainActivity(Filter filter, ArrayList<GenreModel> genres);
}
