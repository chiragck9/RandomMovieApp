package com.ck.rscp.movieapp.view.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ck.rscp.domain.datamodel.Filter;
import com.ck.rscp.domain.datamodel.GenreModel;
import com.ck.rscp.movieapp.R;
import com.ck.rscp.movieapp.view.customviews.TagLayout;
import com.edmodo.rangebar.RangeBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ckunder on 13-04-2016.
 */
public class FilterBottomSheetFragment extends BottomSheetDialogFragment {


    @Bind(R.id.bottom_sheet)
    LinearLayout bottomSheet;
    @Bind(R.id.filter_range_seekbar)
    RangeBar rangeSeekBar;
    @Bind(R.id.filter_genre_tag_layout)
    TagLayout genreTagLayout;
    //    @Bind(R.id.filter_countries_tag_layout)
    TagLayout countriesTagLayout;

    private HorizontalScrollView genreHorizontalScrollView;
    private HorizontalScrollView countriesHorizontalLayout;
    private Filter mFilter;
    private List<GenreModel> mGenres;

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.bottom_sheets_main, null);
        dialog.setContentView(contentView);
        ButterKnife.bind(contentView);

        rangeSeekBar = (RangeBar) contentView.findViewById(R.id.filter_range_seekbar);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
            ((BottomSheetBehavior) behavior).setHideable(true);
        }

        rangeSeekBar.setThumbColorNormal(ContextCompat.getColor(getContext(), R.color.colorPrimaryLight));
        rangeSeekBar.setThumbColorPressed(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        rangeSeekBar.setConnectingLineColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

        rangeSeekBar.setTickHeight(0);
        rangeSeekBar.setConnectingLineWeight(4);
        rangeSeekBar.setBarWeight(8);
        rangeSeekBar.setTickCount(116);
        rangeSeekBar.setThumbRadius(10);

        genreTagLayout = (TagLayout) contentView.findViewById(R.id.filter_genre_tag_layout);
        for (int i = 0; i < 10; i++) {
            final View tagView = View.inflate(getContext(), R.layout.tag_view_layout, null);
            TextView tagText = (TextView) tagView.findViewById(R.id.tag_text);
            TextView tagDelete = (TextView) tagView.findViewById(R.id.tag_delete);
            tagDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    genreTagLayout.removeView(tagView);
                }
            });
            tagText.setText("Tag is on " + i);
            genreTagLayout.addView(tagView);
        }

        genreHorizontalScrollView = (HorizontalScrollView) contentView.findViewById(R.id.genre_horizontal_layout);
        LinearLayout horizontalLinearLayout = new LinearLayout(getContext());
        horizontalLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        horizontalLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 0; i < 20; i++) {
            final View tagView = View.inflate(getContext(), R.layout.tag_view_layout, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(20, 0, 20, 0);

            TextView tagText = (TextView) tagView.findViewById(R.id.tag_text);
            TextView tagDelete = (TextView) tagView.findViewById(R.id.tag_delete);
            tagText.setText("Tag is on mate " + i);
            horizontalLinearLayout.addView(tagView, layoutParams);
        }
        genreHorizontalScrollView.addView(horizontalLinearLayout);
        genreHorizontalScrollView.setVisibility(View.GONE);

        ImageView addGenre = new ImageView(getContext());
        addGenre.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.add_filter_drawable));
        addGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (genreHorizontalScrollView.getVisibility() == View.GONE) {
                    genreHorizontalScrollView.setVisibility(View.VISIBLE);
                } else {
                    genreHorizontalScrollView.setVisibility(View.GONE);

                }
            }
        });
        genreTagLayout.addView(addGenre);


        countriesTagLayout = (TagLayout) contentView.findViewById(R.id.filter_countries_tag_layout);
        for (int i = 0; i < 7; i++) {
            final View tagView = View.inflate(getContext(), R.layout.tag_view_layout, null);
            TextView tagText = (TextView) tagView.findViewById(R.id.tag_text);
            TextView tagDelete = (TextView) tagView.findViewById(R.id.tag_delete);
            tagDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countriesTagLayout.removeView(tagView);
                }
            });
            tagText.setText("Cozbtr Tag is on " + i);
            countriesTagLayout.addView(tagView);
        }

        countriesHorizontalLayout = (HorizontalScrollView) contentView.findViewById(R.id.countries_horizontal_layout);
        LinearLayout horizontalCountryLayout = new LinearLayout(getContext());
        horizontalCountryLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        horizontalCountryLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 0; i < 10; i++) {
            final View tagView = View.inflate(getContext(), R.layout.tag_view_layout, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(20, 0, 20, 0);

            TextView tagText = (TextView) tagView.findViewById(R.id.tag_text);
            TextView tagDelete = (TextView) tagView.findViewById(R.id.tag_delete);
            tagText.setText("Tag is on mate " + i);
            horizontalCountryLayout.addView(tagView, layoutParams);
        }
        countriesHorizontalLayout.addView(horizontalCountryLayout);
        countriesHorizontalLayout.setVisibility(View.GONE);

        ImageView addCountry = new ImageView(getContext());
        addCountry.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.add_filter_drawable));
        addCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countriesHorizontalLayout.getVisibility() == View.GONE) {
                    countriesHorizontalLayout.setVisibility(View.VISIBLE);
                } else {
                    countriesHorizontalLayout.setVisibility(View.GONE);

                }
            }
        });
        countriesTagLayout.addView(addCountry);


    }


    public static FilterBottomSheetFragment newInstance(Filter filter, List<GenreModel> genres) {
        FilterBottomSheetFragment fragment = new FilterBottomSheetFragment();
        fragment.setFilter(filter);
        fragment.setGenres(genres);
        return fragment;
    }

    public void setFilter(Filter filter) {
        this.mFilter = filter;
    }

    public void setGenres(List<GenreModel> genres) {
        this.mGenres = genres;
    }
}
