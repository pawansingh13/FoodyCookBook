package com.app.foodycookbook.utills;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.app.foodycookbook.R;

import java.util.Timer;
import java.util.TimerTask;

public class ViewPagerDotsFuctionality {
    private static final long DELAY_MS = 500;
    private static final long PERIOD_MS = 30000;
    private Context mContext;
    private Handler handler;
    private ImageView ivDots[];
    private int mCurrentPage = 0;
    private Timer mTimer;

    public ViewPagerDotsFuctionality(final ViewPager vpCarImages, final int mDotsCount, final Context mContext, LinearLayout llDots) {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (mCurrentPage == mDotsCount) {
                    mCurrentPage = 0;
                }
                vpCarImages.setCurrentItem(mCurrentPage++, true);
            }
        };

        mTimer = new Timer(); // This will create a new Thread
        mTimer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        ivDots = new ImageView[mDotsCount];

        for (int i = 0; i < mDotsCount; i++) {
            ivDots[i] = new ImageView(mContext);
            ivDots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            llDots.addView(ivDots[i], params);

        }
        ivDots[0].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.active_dot));
        vpCarImages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage = position;
                for (int i = 0; i < mDotsCount; i++) {
                    ivDots[i].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.nonactive_dot));
                }

                ivDots[position].setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
