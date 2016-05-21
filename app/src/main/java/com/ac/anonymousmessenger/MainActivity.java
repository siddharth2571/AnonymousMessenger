package com.ac.anonymousmessenger;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;

import com.ac.anonymousmessenger.utils.AnimationUtil;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    AnimationUtil mAnimationUtil;
    CardView mainlayout;
    CardView secondarylayout;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setToolbar();
        mAnimationUtil = new AnimationUtil(MainActivity.this);

        findViewById(R.id.send_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    mAnimationUtil.revealAnimation(secondarylayout, mainlayout);
                    flag = !flag;
                } else {
                    mAnimationUtil.revealAnimationHide(secondarylayout, mainlayout);
                    flag = !flag;
                }

//                secondarylayout.setVisibility(View.VISIBLE);
            }
        });

    }

    private void init() {
        mainlayout = (CardView) findViewById(R.id.card_view);
        secondarylayout = (CardView) findViewById(R.id.secondarylayout);

    }

    public void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(setCustomTypefaceSpannable("Anonymous Messenger"));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public String setCustomTypefaceSpannable(String title) {
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "orkney_regular.otf");
        SpannableString styledStringSpan = new SpannableString(title);
        styledStringSpan.setSpan(myTypeface, 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return styledStringSpan.toString();
    }
}
