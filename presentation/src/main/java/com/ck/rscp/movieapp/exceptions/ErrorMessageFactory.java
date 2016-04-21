package com.ck.rscp.movieapp.exceptions;

import com.ck.rscp.data.exceptions.NoMovieFoundException;
import com.ck.rscp.data.exceptions.NoNetworkConnectionException;

/**
 * Created by ckunder on 07-04-2016.
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {
    }

    public static String createError(Throwable e) {
        if (e instanceof NoMovieFoundException) {
            return "";
        } else if (e instanceof NoNetworkConnectionException) {
            return "";
        } else {
            return "";
        }
    }
}
