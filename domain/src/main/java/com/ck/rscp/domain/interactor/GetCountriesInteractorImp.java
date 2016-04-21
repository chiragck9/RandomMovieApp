package com.ck.rscp.domain.interactor;

import com.ck.rscp.domain.executor.PostExecutionThread;
import com.ck.rscp.domain.executor.ThreadExecutor;
import com.ck.rscp.domain.repository.MovieRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ckunder on 19-04-2016.
 */
public class GetCountriesInteractorImp extends Interactor implements GetCountriesInteractor {

    private final MovieRepository mMovieRepository;

    @Inject
    public GetCountriesInteractorImp(MovieRepository movieRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mMovieRepository = movieRepository;
    }

    @Override
    protected Observable buildObservable() {
        return mMovieRepository.getCountries();
    }

    @Override
    public void execute(Subscriber subscriber) {
        super.execute(subscriber);
    }
}
