package com.ck.rscp.data.cache;

import com.ck.rscp.data.exceptions.DefaultException;

/**
 * Created by ckunder on 19-04-2016.
 */
public interface IDataCache {
    void set(String key, Object value) throws DefaultException;

    Object get(String key) throws DefaultException;
}
