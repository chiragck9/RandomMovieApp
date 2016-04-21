package com.ck.rscp.domain.datamodel;

import java.io.Serializable;

/**
 * Created by ckunder on 07-04-2016.
 */
public class GenreModel implements Serializable{

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        GenreModel genreModel = (GenreModel) o;
        return (this.id == genreModel.id) ? true : false;
    }
}
