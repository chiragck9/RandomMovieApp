package com.ck.rscp.data.entity.mapper;

import com.ck.rscp.domain.datamodel.GenreModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.keywords.Keyword;

/**
 * Created by ckunder on 07-04-2016.
 */
@Singleton
public class TMDBMapper {


    @Inject
    public TMDBMapper() {
    }

    public MovieDb transformMovies(MovieDb movieDb) {
        return movieDb;
    }

    public List<MovieDb> transformMovies(Collection<MovieDb> movieDbList) {
        return (List<MovieDb>) movieDbList;
    }

    public Genre transformGenreDomainToGenre(GenreModel keywordDomain) {
        return null;
    }

    public List<Genre> transformGenreDomainToGenre(Collection<GenreModel> genreDomains) {
        List<Genre> genres = new ArrayList<>(genreDomains.size());
        Genre genre;
        for (GenreModel movieDb : genreDomains) {
            genre = transformGenreDomainToGenre(movieDb);
            if (genre != null) {
                genres.add(genre);
            }
        }
        return genres;
    }

    public GenreModel transformGenreToGenreDomain(Genre genre) {
        if (genre != null) {
            GenreModel genreDomain = new GenreModel();
            genreDomain.setId(genre.getId());
            genreDomain.setName(genre.getName());
            return genreDomain;
        }
        return null;
    }

    public List<GenreModel> transformGenreToGenreDomain(Collection<Genre> genres) {
        List<GenreModel> genreDomains = new ArrayList<>(genres.size());
        GenreModel genreDomain;
        for (Genre genre : genres) {
            genreDomain = transformGenreToGenreDomain(genre);
            if (genreDomain != null) {
                genreDomains.add(genreDomain);
            }
        }
        return genreDomains;
    }
}
