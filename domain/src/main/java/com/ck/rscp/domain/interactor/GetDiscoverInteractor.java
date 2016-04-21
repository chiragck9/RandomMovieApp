package com.ck.rscp.domain.interactor;

import com.ck.rscp.domain.datamodel.Filter;

import rx.Subscriber;

/**
 * Created by ckunder on 11-04-2016.
 */
public interface GetDiscoverInteractor extends BaseInteractor {

    public void execute(boolean useCache, int page, Filter filter, Subscriber subscriber);

}
