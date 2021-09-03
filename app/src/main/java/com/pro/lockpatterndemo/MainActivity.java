package com.pro.lockpatterndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    PatternLockView patternLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Glide.with(this)
                .load("file:///android_asset/bg/bg1.jpeg")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into((ImageView) findViewById(R.id.ivBG));

        patternLockView = findViewById(R.id.patter_lock_view);
        patternLockView.addPatternLockListener(mPatternLockViewListener);
    }

    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(), "Pattern progress: " +
                    PatternLockUtils.patternToString(patternLockView, progressPattern));
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(), "Pattern complete: " +
                    PatternLockUtils.patternToString(patternLockView, pattern));
            if (PatternLockUtils.patternToString(patternLockView, pattern).equals("03458")) {
                Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();
                patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
            } else {
                Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                patternLockView.clearPattern();
            }
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };
}