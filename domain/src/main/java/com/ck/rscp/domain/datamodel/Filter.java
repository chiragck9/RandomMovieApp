package com.ck.rscp.domain.datamodel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ckunder on 07-04-2016.
 */
public class Filter implements Serializable {

    private String language;
    private String releaseDateGreaterThan;
    private String releaseDateLessThan;
    private float averageRatingGreaterThan;
    private List<GenreModel> genres;
    private boolean includeAdult;


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseDateGreaterThan() {
        return releaseDateGreaterThan;
    }

    public void setReleaseDateGreaterThan(String releaseDateGreaterThan) {
        this.releaseDateGreaterThan = releaseDateGreaterThan;
    }


    public String getReleaseDateLessThan() {
        return releaseDateLessThan;
    }

    public void setReleaseDateLessThan(String releaseDateLessThan) {
        this.releaseDateLessThan = releaseDateLessThan;
    }

    public float getAverageRatingGreaterThan() {
        return averageRatingGreaterThan;
    }


    public List<GenreModel> getGenres() {
        return genres;
    }

    public boolean includeAdult() {
        return includeAdult;

    }

    public void setAverageRatingGreaterThan(float averageRatingGreaterThan) {
        this.averageRatingGreaterThan = averageRatingGreaterThan;
    }


    public void setGenres(List<GenreModel> genres) {
        this.genres = genres;
    }

    public boolean isIncludeAdult() {
        return includeAdult;
    }

    public void setIncludeAdult(boolean includeAdult) {
        this.includeAdult = includeAdult;
    }

    @Override
    public boolean equals(Object o) {
        Filter fitler = (Filter) o;

        if (this.includeAdult != ((Filter) o).includeAdult) {
            return false;
        }

        if (!this.genres.equals(fitler.genres)) {
            return false;
        }
        if (averageRatingGreaterThan != fitler.averageRatingGreaterThan) {
            return false;
        }
        if (!this.releaseDateLessThan.equals(fitler.releaseDateLessThan)) {
            return false;
        }
        if (!this.releaseDateGreaterThan.equals(fitler.releaseDateGreaterThan)) {
            return false;
        }
        if (!this.language.equals(fitler.language)) {
            return false;
        }
        return true;
    }
}
