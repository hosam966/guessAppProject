package com.example.guessappproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String BUNDLE_CURRENT_INDEX = "BUNDLE_CURRENT_INDEX";
    private static final String TAG = MainActivity.class.getSimpleName();
    private Random mRandom;
    private TextView mTitleTextView;
    private ImageView mGuessImageView;
    private String[] mGuessArray;
    private int[] mGuessPicture = {
            R.drawable.climb,
            R.drawable.code,
            R.drawable.cook,
            R.drawable.go_cinema,
            R.drawable.play_soccer,
            R.drawable.ride_bike,
            R.drawable.sing
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRandom = new Random();
        mGuessArray = getResources().getStringArray(R.array.m_Guess_Array);
        mGuessImageView = findViewById(R.id.imageViews);
        mTitleTextView = findViewById(R.id.images_title_view);
    }

    public void onGuessClick(View view) {
        mCurrentIndex = mRandom.nextInt(6);
        showImage();
    }

    public void onNextClick(View view) {
        if (mCurrentIndex < 6) {
            ++mCurrentIndex;
        } else {
           mCurrentIndex = 0;
        }
        showImage();
    }

    public void onPreviousClick(View view) {
        if (mCurrentIndex > 0) {
            --mCurrentIndex;
            showImage();
        } else {
            mCurrentIndex = 6;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_CURRENT_INDEX, mCurrentIndex);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_CURRENT_INDEX);
            Drawable guessDrawableViews = ContextCompat.getDrawable(this, mGuessPicture[mCurrentIndex]);
            mGuessImageView.setImageDrawable(guessDrawableViews);
    }

    private void showImage() {
        mGuessImageView.setImageDrawable(ContextCompat.getDrawable(this, mGuessPicture[mCurrentIndex]));
        mTitleTextView.setText(mGuessArray[mCurrentIndex]);
    }

}
