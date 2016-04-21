package com.ck.rscp.movieapp.view.fragments;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ck.rscp.domain.datamodel.Filter;
import com.ck.rscp.domain.utils.SystemUtils;
import com.ck.rscp.movieapp.R;
import com.ck.rscp.movieapp.di.components.MovieComponent;
import com.ck.rscp.movieapp.presenter.MainPresenter;
import com.ck.rscp.movieapp.view.activities.MainActivity;
import com.ck.rscp.movieapp.view.adapters.RandomMoviePagerAdapter;
import com.ck.rscp.movieapp.view.customcomponents.CircularViewPagerHandler;
import com.ck.rscp.movieapp.view.customcomponents.RotateUpTransformer;
import com.ck.rscp.movieapp.view.views.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.StringIdElement;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @Bind(R.id.movies_poster_pager)
    ViewPager moviesPager;
    @Bind(R.id.movie_discover_tags)
    HorizontalScrollView moviesTagsScrollView;

    private Filter mFilter;
    private RandomMoviePagerAdapter mRandomMoviePagerAdapter;

    public MainFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViewPager();
    }

    @Override
    public void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }

    private void setUpViewPager() {
        mRandomMoviePagerAdapter = new RandomMoviePagerAdapter(getContext());
        moviesPager.setPageTransformer(true, new RotateUpTransformer());
        moviesPager.setOffscreenPageLimit(4);
        moviesPager.setClipToPadding(false);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        int screenHeight = SystemUtils.getScreenHeight(getActivity());
        params.height = (int) (screenHeight * 0.6);
        moviesPager.setLayoutParams(params);
        moviesPager.addOnPageChangeListener(new CircularViewPagerHandler(moviesPager));
        moviesPager.setAdapter(mRandomMoviePagerAdapter);
    }

    private void setUpHorizontalScrollView() {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 0, 20, 0);

        LinearLayout horizontalCountryLayout = new LinearLayout(getContext());
        horizontalCountryLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        horizontalCountryLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 0; i < mFilter.getGenres().size(); i++) {
            horizontalCountryLayout.addView(addViewToHorizontalScroll(mFilter.getGenres().get(i).getName()), layoutParams);
        }

        String rgt = mFilter.getReleaseDateGreaterThan();
        String rlt = mFilter.getReleaseDateLessThan();
        float rating = mFilter.getAverageRatingGreaterThan();

        horizontalCountryLayout.addView(addViewToHorizontalScroll(rgt.substring(0, 3)), layoutParams);
        horizontalCountryLayout.addView(addViewToHorizontalScroll(rlt.substring(0, 3)), layoutParams);
        horizontalCountryLayout.addView(addViewToHorizontalScroll(String.valueOf(rating)), layoutParams);
        moviesTagsScrollView.addView(horizontalCountryLayout);

    }

    private View addViewToHorizontalScroll(String text) {
        final View tagView = View.inflate(getContext(), R.layout.tag_view_layout, null);
        tagView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rounded_corners_drawable_light));
        TextView tagDelete = (TextView) tagView.findViewById(R.id.tag_delete);
        tagDelete.setVisibility(View.GONE);
        TextView tagText = (TextView) tagView.findViewById(R.id.tag_text);
        tagText.setText(text);
        tagText.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
        return tagView;
    }

    private void initialize() {
        this.getComponent(MovieComponent.class).inject(this);
        mainPresenter.setView(this);
        mainPresenter.getMovieDiscover(true);
    }

    public void onDiscoverMovies() {
        mainPresenter.getMovieDiscover(false);
    }

    @Override
    public void onMovieDiscoverLoaded(List<MovieDb> movies) {
        ((MainActivity) getActivity()).clearAnimation();
        mRandomMoviePagerAdapter.setMovies(movies);
    }

    @Override
    public void onMovieDiscoverNotLoaded() {
        ((MainActivity) getActivity()).clearAnimation();
        Snackbar.make(this.getView(), "Movies Failure", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void setFilter(Filter filter) {
        this.mFilter = filter;
        setUpHorizontalScrollView();
    }
}
