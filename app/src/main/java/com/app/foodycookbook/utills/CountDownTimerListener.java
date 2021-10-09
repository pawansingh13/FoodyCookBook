package com.app.foodycookbook.utills;

import android.widget.TextView;

public interface CountDownTimerListener {
    void CountDownTimerListener(long millisUntilFinished, TextView mTvGeekResponceInfo);
    void countDownFinsh(TextView mTvGeekResponceInfo);
}
