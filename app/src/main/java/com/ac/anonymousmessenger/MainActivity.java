package com.ac.anonymousmessenger;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ac.anonymousmessenger.advertise.AdmobService;
import com.ac.anonymousmessenger.customfont.CustomEditText;
import com.ac.anonymousmessenger.customfont.CustomeTextView;
import com.ac.anonymousmessenger.utils.AnimationUtil;
import com.ac.anonymousmessenger.utils.ShareCopyUtils;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

import butterknife.Bind;
import butterknife.ButterKnife;
import tyrantgit.explosionfield.ExplosionField;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    AnimationUtil mAnimationUtil;
    CardView mainlayout;
    CardView secondarylayout;
    boolean flag = true;
    ExplosionField explosionField;

    int setRepeted = 500;

    @Bind(R.id.txt_500)
    TextView txt500;
    @Bind(R.id.txt_1000)
    CustomeTextView txt1000;
    @Bind(R.id.txt_2000)
    CustomeTextView txt2000;
    @Bind(R.id.txt_3000)
    CustomeTextView txt3000;
    @Bind(R.id.txt_4000)
    CustomeTextView txt4000;
    @Bind(R.id.txt_5000)
    CustomeTextView txt5000;

    @Bind(R.id.msgEdt)
    CustomEditText msgEdt;
    @Bind(R.id.outputEdt)
    CustomeTextView outputEdt;

    @Bind(R.id.shareView)
    CardView shareView;

    @Bind(R.id.share_txt)
    CustomeTextView shareTxt;

    @Bind(R.id.copy_txt)
    CustomeTextView copyTxt;


    @Bind(R.id.rate_txt)
    CustomeTextView rateTxt;

    ProgressDialog pg;


    private StartAppAd startAppAd = new StartAppAd(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        setAdvertise();
        init();
        setToolbar();
        setClicker();


        explosionField = ExplosionField.attach2Window(this);
        shareView.setVisibility(View.VISIBLE);

        mAnimationUtil = new AnimationUtil(MainActivity.this);
        findViewById(R.id.send_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    shareView.setVisibility(View.GONE);
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

    private void setAdvertise() {
        StartAppSDK.init(this, "111685543", "204097369", true);
        StartAppAd.showSlider(this);
        setStartService();
//        setStartAppAd();
    }

    public void setStartAppAd() {

        final StartAppAd startAppAd = new StartAppAd(this);
        startAppAd.loadAd(new AdEventListener() {
            @Override
            public void onReceiveAd(Ad ad) {
                System.out.println("Ad received");

                startAppAd.showAd();
            }

            @Override
            public void onFailedToReceiveAd(Ad ad) {

            }
        });

    }

    public void setStartService() {
        Intent i = new Intent().setClass(getApplication(), AdmobService.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        // Launch the new activity and add the additional flags to the intent
        getApplication().startService(i);

        startService(i);
    }

    private void setClicker() {

        copyTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder(outputEdt.getText().toString());
                ShareCopyUtils.Copyclipbord(getApplicationContext(), sb.append("From Anonymous Messenger"));
            }
        });

        shareTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShareCopyUtils.sharebutton(getApplicationContext(), new StringBuilder(outputEdt.getText().toString()));
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,
                        "Anonymous Messenger-");
                sharingIntent
                        .putExtra(Intent.EXTRA_TEXT, outputEdt.getText().toString() + "From Anonymous Messenger");
                startActivity(Intent.createChooser(sharingIntent, "Share To.."));


            }
        });

        rateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApplicaitonLink();
            }
        });


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                resetExplosionAnimation(secondarylayout);
//                explosionField.clear();
//                setExplosionAnimation(v);

                if (!TextUtils.isEmpty(msgEdt.getText().toString())) {


                    switch (v.getId()) {
                        case R.id.txt_500:
                            setRepeted = 500;
                            break;
                        case R.id.txt_1000:
//                            setExplosionAnimation(v);
                            setRepeted = 1000;
                            break;
                        case R.id.txt_2000:
                            setRepeted = 2000;
                            break;
                        case R.id.txt_3000:
                            setRepeted = 3000;
                            break;
                        case R.id.txt_4000:
                            setRepeted = 4000;
                            break;
                        case R.id.txt_5000:
                            setRepeted = 5000;
                            break;
                    }
//                    resetExplosionAnimation(v);
                    mAnimationUtil.revealAnimationHide(secondarylayout, mainlayout);
                    new AsyncLineCreator().execute();
                } else {
                    msgEdt.setError("Enter Msg First");
                }
            }
        };

        txt500.setOnClickListener(clickListener);
        txt1000.setOnClickListener(clickListener);
        txt2000.setOnClickListener(clickListener);
        txt3000.setOnClickListener(clickListener);
        txt4000.setOnClickListener(clickListener);
        txt4000.setOnClickListener(clickListener);
        txt5000.setOnClickListener(clickListener);

    }

    public void openApplicaitonLink() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        Log.i("URI", "=>" + uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    public void setProgressbar() {
        pg = new ProgressDialog(this);
        pg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pg.show();
    }

    class AsyncLineCreator extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setProgressbar();
        }

        @Override
        protected String doInBackground(String... params) {
            return startRepeting();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            outputEdt.setText(s);
            shareView.setVisibility(View.VISIBLE);
            if (null != pg) pg.dismiss();
        }

        public String startRepeting() {
            String writingText = msgEdt.getText().toString();
            StringBuilder stringLoop = new StringBuilder(writingText);
            for (int i = 1; i < setRepeted; i++) {
                stringLoop.append("\n" + writingText);
            }
            return stringLoop.toString();
        }
    }

    private void resetExplosionAnimation(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                resetExplosionAnimation(parent.getChildAt(i));
            }
        } else {
            root.setScaleX(1);
            root.setScaleY(1);
            root.setAlpha(1);
            root.setVisibility(View.VISIBLE);
        }
    }

    private void setExplosionAnimation(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                setExplosionAnimation(parent.getChildAt(i));
            }
        } else {
            root.setClickable(true);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    explosionField.explode(v);
                    v.setOnClickListener(null);
                }
            });
        }
    }


    private void init() {
        mainlayout = (CardView) findViewById(R.id.card_view);
        secondarylayout = (CardView) findViewById(R.id.secondarylayout);
        outputEdt.setMovementMethod(new ScrollingMovementMethod());
    }

    public void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.include);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
            setStartAppAd();
        startAppAd.onBackPressed();
    }
}
