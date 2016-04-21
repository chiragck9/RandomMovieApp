package com.ck.rscp.movieapp.view.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ck.rscp.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;

/**
 * Created by ckunder on 11-04-2016.
 */
public class RandomMoviePagerAdapter extends PagerAdapter {

    private static final String IMAGE_URL_BASE = "https://image.tmdb.org/t/p/original/";

    private Context mContext;
    private List<MovieDb> mMovies;

    public RandomMoviePagerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.random_movie_pager_item, collection, false);

        ImageView moviePosterImg = (ImageView) layout.findViewById(R.id.movie_poster_img);
        Picasso.with(mContext).load(IMAGE_URL_BASE + mMovies.get(position).getPosterPath()).into(moviePosterImg);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return (mMovies != null) ? mMovies.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setMovies(List<MovieDb> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }
}
