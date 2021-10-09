package com.app.foodycookbook.utills;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;

public class CustomWebView extends WebView {

    public OnBottomReachedListener mOnBottomReachedListener = null;
    private int mMinDistance = 0;

    public interface OnBottomReachedListener {
        void onBottomReached(View v);
    }
    public void setOnBottomReachedListener(OnBottomReachedListener bottomReachedListener, int allowedDifference ) {
        mOnBottomReachedListener = bottomReachedListener;
        mMinDistance = allowedDifference;
    }
    @Override
    protected void onScrollChanged(int left, int top, int oldLeft, int oldTop) {
        if (mOnBottomReachedListener != null) {
            if ((computeVerticalScrollRange() - (top + getHeight())) <= mMinDistance)
                mOnBottomReachedListener.onBottomReached(this);
        }
        super.onScrollChanged(left, top, oldLeft, oldTop);
    }

    public CustomWebView(Context context) {
        super(context);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
    }


}
