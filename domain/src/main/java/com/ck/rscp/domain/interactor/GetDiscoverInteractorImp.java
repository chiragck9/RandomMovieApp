package com.ck.rscp.domain.interactor;

import com.ck.rscp.domain.datamodel.Filter;
import com.ck.rscp.domain.executor.PostExecutionThread;
import com.ck.rscp.domain.executor.ThreadExecutor;
import com.ck.rscp.domain.repository.MovieRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ckunder on 07-04-2016.
 */
public class GetDiscoverInteractorImp extends Interactor implements GetDiscoverInteractor {

    private MovieRepository mMovieRepository;
    private int mPage;
    private Filter mFilter;
    private boolean mUseCacheIfAvailable;

    @Inject
    public GetDiscoverInteractorImp(MovieRepository movieRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mMovieRepository = movieRepository;
    }

    @Override
    protected Observable buildObservable() {
        return mMovieRepository.getDiscover(mUseCacheIfAvailable,mPage, mFilter);
    }

    @Override
    public void execute(boolean useCache,int page, Filter filter, Subscriber subscriber) {
        mUseCacheIfAvailable = useCache;
        mPage = page;
        mFilter = filter;
        super.execute(subscriber);
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }
}
