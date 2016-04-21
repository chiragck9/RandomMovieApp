package com.ck.rscp.domain.interactor;

import rx.Subscriber;

/**
 * Created by ckunder on 11-04-2016.
 */
public interface GetWatchListInteractor extends BaseInteractor{


    public void execute(Subscriber subscriber);
}
