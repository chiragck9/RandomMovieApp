package com.ck.rscp.domain.interactor;

import com.ck.rscp.domain.executor.PostExecutionThread;
import com.ck.rscp.domain.executor.ThreadExecutor;

import rx.Subscriber;
import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by ckunder on 07-04-2016.
 */
public abstract class Interactor {


    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;


    private Subscription mSubscription = Subscriptions.empty();

    protected Interactor(ThreadExecutor threadExecutor,
                         PostExecutionThread postExecutionThread) {
        mThreadExecutor = threadExecutor;
        mPostExecutionThread = postExecutionThread;
    }

    protected abstract Observable buildObservable();

    protected void execute(Subscriber subscriber) {
        mSubscription = this.buildObservable()
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribe(subscriber);
    }

    protected void unsubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
