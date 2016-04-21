package com.ck.rscp.domain.interactor;

import com.ck.rscp.domain.datamodel.Filter;
import com.ck.rscp.domain.executor.PostExecutionThread;
import com.ck.rscp.domain.executor.ThreadExecutor;
import com.ck.rscp.domain.repository.MovieRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ckunder on 19-04-2016.
 */
public class SetFilterInteractorImp extends Interactor implements SetFilterInteractor {


    private MovieRepository mMovieRepository;
    private Filter mFilter;

    @Inject
    public SetFilterInteractorImp(MovieRepository movieRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mMovieRepository = movieRepository;
    }

    @Override
    protected Observable buildObservable() {
        return mMovieRepository.setFilter(mFilter);
    }

    @Override
    public void execute(Filter filter, Subscriber subscriber) {
        this.mFilter = filter;
        super.execute(subscriber);
    }
}
