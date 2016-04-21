package com.ck.rscp.domain.interactor;

import com.ck.rscp.domain.executor.PostExecutionThread;
import com.ck.rscp.domain.executor.ThreadExecutor;
import com.ck.rscp.domain.repository.MovieRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ckunder on 07-04-2016.
 */
public class GetGenreInteractorImp extends Interactor implements GetGenreInteractor {

    private MovieRepository mMovieRepository;
    private String mLanguage;

    @Inject
    public GetGenreInteractorImp(MovieRepository movieRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mMovieRepository = movieRepository;
    }

    @Override
    protected Observable buildObservable() {
        return mMovieRepository.getGenres(mLanguage);
    }

    @Override
    public void execute(String language, Subscriber subscriber) {
        mLanguage = language;
        super.execute(subscriber);
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }

}
