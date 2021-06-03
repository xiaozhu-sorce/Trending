package com.bignerdranch.android.trending.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MyRefreshLayout extends SwipeRefreshLayout {

    private RecyclerView mListView;
    private float mDownY, mUpY;
    private boolean isLoading;
    private int mScaledTouchSlop;

    public MyRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mUpY = getY();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        mDownY = 0;
        mUpY = 0;
    }

}
