package com.ck.rscp.data.repository;

import info.movito.themoviedbapi.TmdbApi;

/**
 * Created by ckunder on 20-04-2016.
 */
public class TMDBService {

    private final static String TMDB_API_KEY = "d69b36c83133e83136ba44fd5076ffc8";

    private static TmdbApi mTmdbApi;

    public static TmdbApi getTMDBApi() {
        if (mTmdbApi == null) {
            mTmdbApi = new TmdbApi(TMDB_API_KEY);
        }
        return mTmdbApi;
    }
}
