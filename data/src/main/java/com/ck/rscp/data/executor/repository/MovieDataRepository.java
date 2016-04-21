package com.ck.rscp.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ck.rscp.data.cache.DataCache;
import com.ck.rscp.data.entity.mapper.TMDBMapper;
import com.ck.rscp.data.exceptions.DefaultException;
import com.ck.rscp.data.exceptions.NoMovieFoundException;
import com.ck.rscp.data.exceptions.NoNetworkConnectionException;
import com.ck.rscp.domain.datamodel.Country;
import com.ck.rscp.domain.datamodel.Filter;
import com.ck.rscp.domain.datamodel.GenreModel;
import com.ck.rscp.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.Discover;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by ckunder on 06-04-2016.
 */
@Singleton
public class MovieDataRepository implements MovieRepository {

    private static final String GENRES = "GENRES";
    private static final String FILTER = "FILTER";
    private static final String COUNTRIES = "COUNTRIES";
    private static final String MOVIES = "MOVIES";


    private Context mContext;
    private TMDBMapper mTMDBMapper;
    private DataCache mDataCache;

    @Inject
    public MovieDataRepository(Context context, TMDBMapper TMDBMapper, DataCache dataCache) {
        mContext = context;
        mTMDBMapper = TMDBMapper;
        mDataCache = dataCache;

    }

    @Override
    public Observable<List<MovieDb>> getDiscover(final boolean useCacheIfAvailable, final int page, final Filter filter) {

        return Observable.create(new Observable.OnSubscribe<List<MovieDb>>() {
            @Override
            public void call(Subscriber<? super List<MovieDb>> subscriber) {
                try {
                    if (useCacheIfAvailable) {
                        List<MovieDb> movies = (List<MovieDb>) mDataCache.get(MOVIES);
                        if (movies != null) {
                            subscriber.onNext(mTMDBMapper.transformMovies(movies));
                            subscriber.onCompleted();
                        } else {
                            getMovies(page, filter, subscriber);
                        }
                    } else {
                        getMovies(page, filter, subscriber);
                    }
                } catch (Exception e) {
                    subscriber.onError(new NoMovieFoundException(e.getCause()));
                }
            }
        });
    }


    private void getMovies(int page, Filter filter, Subscriber<? super List<MovieDb>> subscriber) throws DefaultException {

        if (isInternetConnectionAvailable()) {
            Discover discover = new Discover();
            discover.page(page)
                    .language(filter.getLanguage())
                    .includeAdult(filter.includeAdult())
                    .voteAverageGte(filter.getAverageRatingGreaterThan())
                    .releaseDateGte(filter.getReleaseDateGreaterThan())
                    .releaseDateLte(filter.getReleaseDateLessThan());

            List<GenreModel> genres = filter.getGenres();
            if (genres != null) {
                StringBuffer buffer = new StringBuffer();
                for (GenreModel genreModel : genres) {
                    buffer.append(genreModel.getId());
                    buffer.append("|");
                }
                discover.withGenres(buffer.toString());
            }

            MovieResultsPage movieResultPage = TMDBService.getTMDBApi().getDiscover().getDiscover(discover);

            if (movieResultPage != null && movieResultPage.getResults() != null) {
                mDataCache.set(MOVIES, movieResultPage.getResults());
                subscriber.onNext(mTMDBMapper.transformMovies(movieResultPage.getResults()));
                subscriber.onCompleted();
            } else {
                subscriber.onError(new NoMovieFoundException());
            }
        } else {
            subscriber.onError(new NoNetworkConnectionException());
        }
    }

    @Override
    public Observable<List<MovieDb>> getWatchList() {
        return null;
    }

    @Override
    public Observable<List<GenreModel>> getGenres(final String language) {

        return Observable.create(new Observable.OnSubscribe<List<GenreModel>>() {
            @Override
            public void call(Subscriber<? super List<GenreModel>> subscriber) {

                try {
                    List<Genre> genres = (List<Genre>) mDataCache.get(GENRES);
                    if (genres != null) {
                        subscriber.onNext(mTMDBMapper.transformGenreToGenreDomain(genres));
                        subscriber.onCompleted();
                    } else {
                        if (isInternetConnectionAvailable()) {
                            genres = TMDBService.getTMDBApi().getGenre().getGenreList(language);
                            if (genres != null) {
                                mDataCache.set(GENRES, genres);
                                subscriber.onNext(mTMDBMapper.transformGenreToGenreDomain(genres));
                                subscriber.onCompleted();
                            } else {
                                subscriber.onError(new DefaultException());
                            }
                        } else {
                            subscriber.onError(new NoNetworkConnectionException());
                        }

                    }

                } catch (Exception e) {
                    subscriber.onError(new DefaultException(e.getCause()));
                }

            }
        });
    }

    @Override
    public Observable<Filter> getFilter() {
        return Observable.create(new Observable.OnSubscribe<Filter>() {
            @Override
            public void call(Subscriber<? super Filter> subscriber) {
                try {
                    Filter filter = (Filter) mDataCache.get(FILTER);
                    subscriber.onNext(filter);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(new DefaultException());
                }
            }
        });
    }

    @Override
    public Observable<Boolean> setFilter(final Filter filter) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    mDataCache.set(FILTER, filter);
                    subscriber.onNext(true);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(new DefaultException());
                }
            }
        });
    }

    @Override
    public Observable<List<Country>> getCountries() {
        return Observable.create(new Observable.OnSubscribe<List<Country>>() {
            @Override
            public void call(Subscriber<? super List<Country>> subscriber) {
                try {
                    List<Country> countries = (List<Country>) mDataCache.get(COUNTRIES);
                    subscriber.onNext(countries);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(new DefaultException());
                }
            }
        });
    }

    private boolean isInternetConnectionAvailable() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }


}
