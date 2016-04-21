package com.ck.rscp.movieapp.view.customviews;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ck.rscp.movieapp.R;

/**
 * Created by ckunder on 14-04-2016.
 */
public class AddFilterLayout extends LinearLayout implements View.OnClickListener {
    private HorizontalScrollView horizontalScrollView;

    public AddFilterLayout(Context context) {
        super(context);
        init(context);
    }

    public AddFilterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddFilterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        setOrientation(VERTICAL);

        ImageView addGenre = new ImageView(getContext());
        addGenre.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        addGenre.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.add_filter_drawable));
        addGenre.setOnClickListener(this);
        addView(addGenre);

        addGenre.setVisibility(VISIBLE);

        horizontalScrollView = new HorizontalScrollView(context);
        horizontalScrollView.removeAllViews();

        LinearLayout horizontalLinearLayout = new LinearLayout(context);
        horizontalLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        horizontalLinearLayout.setOrientation(HORIZONTAL);

        for (int i = 0; i < 7; i++) {
            final View tagView = View.inflate(getContext(), R.layout.tag_view_layout, null);
            TextView tagText = (TextView) tagView.findViewById(R.id.tag_text);
            TextView tagDelete = (TextView) tagView.findViewById(R.id.tag_delete);
            tagText.setText("Tag is on mate " + i);
            horizontalLinearLayout.addView(tagView);
        }

        horizontalScrollView.addView(horizontalLinearLayout);
        addView(horizontalScrollView);
        horizontalScrollView.setVisibility(GONE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        if (horizontalScrollView.getVisibility() == GONE) {
            horizontalScrollView.setVisibility(VISIBLE);
        } else {
            horizontalScrollView.setVisibility(GONE);

        }
    }
}
