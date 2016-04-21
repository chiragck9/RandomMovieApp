package com.ck.rscp.domain.repository;

import com.ck.rscp.domain.datamodel.Country;
import com.ck.rscp.domain.datamodel.Filter;
import com.ck.rscp.domain.datamodel.GenreModel;

import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;
import rx.Observable;

/**
 * Created by ckunder on 06-04-2016.
 */
public interface MovieRepository {

    Observable<List<MovieDb>> getDiscover(boolean useCacheIfAvailable, int page, Filter filter);

    Observable<List<MovieDb>> getWatchList();

    Observable<List<GenreModel>> getGenres(String language);

    Observable<Filter> getFilter();

    Observable<Boolean> setFilter(Filter filter);

    Observable<List<Country>> getCountries();
}
