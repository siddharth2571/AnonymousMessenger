package com.ac.anonymousmessenger.advertise;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class AdmobService extends Service {


//    InterstitialAd mInterstitialAd;

    private StartAppAd startAppAd = new StartAppAd(this);
    private Timer mTimer = null;
    public static final long NOTIFY_INTERVAL = 90 * 60 * 1000; // 10 seconds
    private Handler mHandler = new Handler();

    public AdmobService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public void onCreate() {
        StartAppSDK.init(this, "111685543", "211833574", true);

//        Toast.makeText(getApplicationContext(), "service STart", Toast.LENGTH_SHORT).show();
//        mInterstitialAd = new InterstitialAd(this);
//
//        mInterstitialAd.setAdUnitId("ca-app-pub-9484488595345309/7367561675"); //your key

        // cancel if already existed

        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), NOTIFY_INTERVAL, NOTIFY_INTERVAL);
    }

    int counter = 0;

    class TimeDisplayTimerTask extends TimerTask {
        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // display toast
                    counter++;
                    Log.i("Counter", "=>" + new Date().getTime());

                    setPerformTaskSchedule();
                    boolean appinstall = appInstalledOrNot("com.ac.cricketnews");
//                    if (!appinstall) sendNotification();

//                    Toast.makeText(getApplicationContext(), getDateTime(),
//                            Toast.LENGTH_SHORT).show();

                }

            });
        }

        public void setPerformTaskSchedule() {

            startAppAd = new StartAppAd(AdmobService.this);
            startAppAd.loadAd(StartAppAd.AdMode.OFFERWALL, new AdEventListener() {
                @Override
                public void onReceiveAd(Ad ad) {
                    System.out.println("Ad received");
                    try {
                        startAppAd.showAd();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailedToReceiveAd(Ad ad) {

                }
            });


        }

        private boolean appInstalledOrNot(String uri) {
            PackageManager pm = getPackageManager();
            boolean app_installed;
            try {
                pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
                app_installed = true;
            } catch (PackageManager.NameNotFoundException e) {
                app_installed = false;
            }
            return app_installed;
        }


        /*private void setPerformTaskSchedule() {

            if (Utils.isConnectingToInternet(getApplicationContext())) {
                mInterstitialAd = new InterstitialAd(getApplicationContext());
                mInterstitialAd.setAdUnitId("ca-app-pub-9484488595345309/7367561675"); //your key
                AdRequest adRequest = new AdRequest.Builder().addTestDevice("CC5F2C72DF2B356BBF0DA198").build();

                mInterstitialAd.loadAd(adRequest);

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        mInterstitialAd.show();
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();

                    }
                });

            }
        }*/
    }

}
