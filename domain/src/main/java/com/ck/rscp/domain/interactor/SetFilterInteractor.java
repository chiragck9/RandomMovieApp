package com.ck.rscp.domain.interactor;

import com.ck.rscp.domain.datamodel.Filter;

import rx.Subscriber;

/**
 * Created by ckunder on 19-04-2016.
 */
public interface SetFilterInteractor {


    public void execute(Filter filter, Subscriber subscriber);
}
