package com.ck.rscp.movieapp.presenter;

import com.ck.rscp.domain.datamodel.Filter;
import com.ck.rscp.domain.datamodel.GenreModel;
import com.ck.rscp.domain.interactor.DefaultSubscriber;
import com.ck.rscp.domain.interactor.GetDiscoverInteractor;
import com.ck.rscp.domain.interactor.GetDiscoverInteractorImp;
import com.ck.rscp.domain.interactor.GetFilterInteractor;
import com.ck.rscp.domain.interactor.GetGenreInteractor;
import com.ck.rscp.domain.interactor.GetGenreInteractorImp;
import com.ck.rscp.domain.interactor.SetFilterInteractor;
import com.ck.rscp.movieapp.di.PerActivity;
import com.ck.rscp.movieapp.exceptions.ErrorMessageFactory;
import com.ck.rscp.movieapp.view.views.MainView;
import com.ck.rscp.movieapp.view.views.MainViewEvents;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import info.movito.themoviedbapi.model.MovieDb;

/**
 * Created by ckunder on 07-04-2016.
 */
@PerActivity
public class MainPresenter extends Presenter<MainView> implements MainViewEvents {

    private MainView mMainView;

    //Discover interactor
    private int mPage = 1;
    private Filter mFilter;
    private Filter mLastFilter;
    private boolean mUseCacheIfAvailable;
    private String mLanguage;

    private final GetDiscoverInteractor getDiscoverInteractor;
    private final GetGenreInteractor getGenreInteractor;
    private final GetFilterInteractor getFilterInteractor;
    private final SetFilterInteractor setFilterInteractor;

    @Inject
    public MainPresenter(GetDiscoverInteractorImp getDiscoverInteractor, GetGenreInteractorImp getGenreInteractor, @Named("getFilter") GetFilterInteractor getFilterInteractor, @Named("setFilter") SetFilterInteractor setFilterInteractor) {
        this.getDiscoverInteractor = getDiscoverInteractor;
        this.getGenreInteractor = getGenreInteractor;
        this.getFilterInteractor = getFilterInteractor;
        this.setFilterInteractor = setFilterInteractor;
    }

    private void getFilter() {
        this.getFilterInteractor.execute(new GetFilterSubscriber());
    }

    private final class GetFilterSubscriber extends DefaultSubscriber<Filter> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            mMainView.onMovieDiscoverNotLoaded();
        }

        @Override
        public void onNext(Filter filter) {
//            mLastFilter = filter;
            MainPresenter.this.getDiscoverInteractor.execute(mUseCacheIfAvailable, mPage, filter, new GetMovieDiscoverSubscriber());
        }
    }

    @Override
    public void getMovieDiscover(boolean useCacheIfAvailable) {
        mUseCacheIfAvailable = useCacheIfAvailable;
        getFilter();
    }

    private final class GetMovieDiscoverSubscriber extends DefaultSubscriber<List<MovieDb>> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            mMainView.onMovieDiscoverNotLoaded();
        }

        @Override
        public void onNext(List<MovieDb> movies) {
            mMainView.onMovieDiscoverLoaded(movies);
        }
    }


    @Override
    public void getGenres() {
        this.getGenreInteractor.execute(mLanguage, new GetGenreSubscriber());
    }

    private final class GetGenreSubscriber extends DefaultSubscriber<List<GenreModel>> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            ErrorMessageFactory.createError(e.getCause());
        }

        @Override
        public void onNext(List<GenreModel> genreDomains) {

        }
    }

    @Override
    public void setView(MainView view) {
        mMainView = view;
    }


    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        this.getDiscoverInteractor.unsubscribe();
        this.getGenreInteractor.unsubscribe();
    }
}
