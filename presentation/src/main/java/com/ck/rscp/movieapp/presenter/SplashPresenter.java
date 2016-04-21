package com.ck.rscp.movieapp.presenter;

import com.ck.rscp.domain.datamodel.DefaultFilter;
import com.ck.rscp.domain.datamodel.Filter;
import com.ck.rscp.domain.datamodel.GenreModel;
import com.ck.rscp.domain.interactor.DefaultSubscriber;
import com.ck.rscp.domain.interactor.GetFilterInteractor;
import com.ck.rscp.domain.interactor.GetGenreInteractor;
import com.ck.rscp.domain.interactor.SetFilterInteractor;
import com.ck.rscp.domain.utils.RandomizerUtil;
import com.ck.rscp.movieapp.di.PerActivity;
import com.ck.rscp.movieapp.view.views.SplashView;
import com.ck.rscp.movieapp.view.views.SplashViewEvents;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by ckunder on 19-04-2016.
 */
@PerActivity
public class SplashPresenter extends Presenter<SplashView> implements SplashViewEvents {

    private static int TOTAL_GENRES = 3;
    private SplashView mSplashView;

    private final GetFilterInteractor getFilterInteractor;
    private final SetFilterInteractor setFilterInteractor;
    private final GetGenreInteractor getGenreInteractor;

    private Filter mFilter;

    @Inject
    public SplashPresenter(@Named("getFilter") GetFilterInteractor getFilterInteractor, @Named("setFilter") SetFilterInteractor setFilterInteractor, @Named("movieGenre") GetGenreInteractor getGenreInteractor) {
        this.getFilterInteractor = getFilterInteractor;
        this.setFilterInteractor = setFilterInteractor;
        this.getGenreInteractor = getGenreInteractor;
    }

    @Override
    public void setView(SplashView view) {
        this.mSplashView = view;
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

    @Override
    public void getGenres() {
        getGenreInteractor.execute("en", new GetGenreSubscriber());
    }

    private final class GetGenreSubscriber extends DefaultSubscriber<List<GenreModel>> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            mFilter = DefaultFilter.getDefaultFilterWithGenres();
            SplashPresenter.this.setFilterInteractor.execute(mFilter, new SetFilterSubscriber());
            mSplashView.continueToMainActivity(mFilter, null);
        }

        @Override
        public void onNext(List<GenreModel> genres) {

            if (genres != null && genres.size() > TOTAL_GENRES) {
                mFilter = DefaultFilter.getDefaultFilterWithoutGenres();
                List<GenreModel> genreModels = new ArrayList<>();
                int size = genres.size();
                for (int i = 0; i < TOTAL_GENRES; i++) {
                    int position = RandomizerUtil.randomize(0, size - 1);
                    GenreModel genre = genres.get(position);
                    if (!genreModels.contains(genre)) {
                        genreModels.add(genre);
                    }
                }
                mFilter.setGenres(genreModels);
                SplashPresenter.this.setFilterInteractor.execute(mFilter, new SetFilterSubscriber());
                mSplashView.continueToMainActivity(mFilter, new ArrayList<GenreModel>(genres));
            } else {
                mFilter = DefaultFilter.getDefaultFilterWithGenres();
                SplashPresenter.this.setFilterInteractor.execute(mFilter, new SetFilterSubscriber());
                mSplashView.continueToMainActivity(mFilter, new ArrayList<GenreModel>(genres));
            }
        }
    }


    private final class SetFilterSubscriber extends DefaultSubscriber<Boolean> {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(Boolean result) {
        }
    }

}
