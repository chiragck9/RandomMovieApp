package com.ck.rscp.domain.datamodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ck.rscp.domain.utils.DateParsingUtil;

/**
 * Created by ckunder on 19-04-2016.
 */
public class DefaultFilter {

    public static Filter getDefaultFilterWithGenres() {
        Filter filter = new Filter();
        filter.setLanguage("en");
        filter.setAverageRatingGreaterThan(5);
        filter.setIncludeAdult(true);
        filter.setReleaseDateGreaterThan("2000-01-01");
        filter.setReleaseDateLessThan(DateParsingUtil.getDateString(new Date()));

        List<GenreModel> genres = new ArrayList<>();
        GenreModel genreAction = new GenreModel();
        genreAction.setId(28);
        GenreModel genreThriller = new GenreModel();
        genreThriller.setId(53);
        GenreModel genreCrime = new GenreModel();
        genreCrime.setId(80);

        genres.add(genreAction);
        genres.add(genreThriller);
        genres.add(genreCrime);

        filter.setGenres(genres);
        return filter;
    }

    public static Filter getDefaultFilterWithoutGenres() {
        Filter filter = new Filter();
        filter.setLanguage("en");
        filter.setAverageRatingGreaterThan(5);
        filter.setIncludeAdult(true);
        filter.setReleaseDateGreaterThan("2000-01-01");
        filter.setReleaseDateLessThan(DateParsingUtil.getDateString(new Date()));
        return filter;
    }
}
