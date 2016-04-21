package com.ck.rscp.data.cache;

import com.ck.rscp.data.exceptions.DefaultException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.paperdb.Paper;

/**
 * Created by ckunder on 19-04-2016.
 */
@Singleton
public class DataCache implements IDataCache {

    @Inject
    public DataCache() {
    }

    @Override
    public void set(String key, Object value) throws DefaultException {
        try {
            Paper.book().write(key, value);
        } catch (Exception e) {
            throw new DefaultException();
        }
    }

    @Override
    public Object get(String key) throws DefaultException {
        try {
            return Paper.book().read(key);
        } catch (Exception e) {
            throw new DefaultException();
        }
    }
}
