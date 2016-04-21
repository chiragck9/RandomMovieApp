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
public class GetWatchListInteractorImp extends Interactor implements GetWatchListInteractor {

    private MovieRepository mMovieRepository;

    @Inject
    public GetWatchListInteractorImp(MovieRepository movieRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mMovieRepository = movieRepository;
    }

    @Override
    protected Observable buildObservable() {
        return mMovieRepository.getWatchList();
    }

    @Override
    public void execute(Subscriber subscriber) {
        super.execute(subscriber);
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }

}
