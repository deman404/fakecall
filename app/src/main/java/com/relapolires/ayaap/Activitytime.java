package com.relapolires.ayaap;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.BuildConfig;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.unity3d.services.banners.UnityBanners;

import java.util.Locale;
import java.util.Random;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


public class Activitytime extends AppCompatActivity {
    private LinearLayout box1, box2, box3, box4;
    private ImageView call1,cancel1, call2,cancel2,call3,cancel3, call4,cancel4;
    private TextView time1,time2,time3,time4 ;

    private NativeAd nativeAdFacebook;
    private FrameLayout frameLayout ;
    private ImageView back ;
    private Tools_Ads toolsAds;
    private int timermilles = 0 ;
    private CountDownTimer timerdown;




    private long TimeLeft = ((long) this.timermilles);
    boolean isRunning = false;
    private UnifiedNativeAd nativeAd;
    @Override
    protected void onCreate(Bundle splash){
        super.onCreate(splash);
        setContentView(R.layout.activitytime);
        initializeAd();

        initializeCounter();
        frameLayout = findViewById(R.id.frame_ad);
        back = findViewById(R.id.back);
        //loadNativeAdmob(frameLayout,toolsAds.NativeAdmob);
        setNativeAd();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toolsAds.selectnetwork.equals("unity")){
                    UnityBanners.destroy();
                }
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });



    }

    ToolsAds toolsAds_;
    private void initializeAd(){
        toolsAds_ =new ToolsAds (this);

        toolsAds = (Tools_Ads)getApplicationContext();
        RelativeLayout view = findViewById(R.id.adView);
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.initunity(toolsAds.Unity_id_ads, toolsAds.unity_testads, toolsAds.unity_bannerads,this);
         }else {
            toolsAds.setBannerAd(view);

        }
    }

    private void ShowInterstitial(){
        toolsAds = (Tools_Ads)getApplicationContext();
        toolsAds.showInterstitial(this);
    }

    private void setNativeAd(){

        //loadNativeAdFB(frameLayout,"173186393601951_356298065290782");
        if (toolsAds.selectnetwork.equals(toolsAds.admobads)) {
            try {
                loadNativeAdmob(frameLayout, toolsAds.nativeadsadmob);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (toolsAds.selectnetwork.equals(toolsAds.fbads)) {
            try {
                loadNativeAdFB(frameLayout, toolsAds.nativeadsfacebook);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void initializeCounter(){
        box1 = findViewById(R.id.counter1);
        call1 = findViewById(R.id.counter_call1);
        cancel1 = findViewById(R.id.counter_cancel1);
        time1 = findViewById(R.id.counterText1);

        box2 = findViewById(R.id.counter2);
        call2 = findViewById(R.id.counter_call2);
        cancel2 = findViewById(R.id.counter_cancel2);
        time2 = findViewById(R.id.counterText2);

        box3 = findViewById(R.id.counter3);
        call3 = findViewById(R.id.counter_call3);
        cancel3 = findViewById(R.id.counter_cancel3);
        time3 = findViewById(R.id.counterText3);

        box4 = findViewById(R.id.counter4);
        call4 = findViewById(R.id.counter_call4);
        cancel4 = findViewById(R.id.counter_cancel4);
        time4 = findViewById(R.id.counterText4);

        setEvent();
    }

    private void setEvent(){
        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setReverseUI(call2,cancel2,time2);
                setReverseUI(call3,cancel3,time3);
                setReverseUI(call4,cancel4,time4);
                setUI(call1,cancel1,time1, box1,R.string.btncount1);


            }
        });
        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReverseUI(call1,cancel1,time1);
            }
        });

        box2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReverseUI(call1,cancel1,time1);
                setReverseUI(call3,cancel3,time3);
                setReverseUI(call4,cancel4,time4);
                setUI(call2,cancel2,time2, box2,R.string.btncount2);
            }
        });
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReverseUI(call2,cancel2,time2);
            }
        });

        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReverseUI(call1,cancel1,time1);
                setReverseUI(call2,cancel2,time2);
                setReverseUI(call4,cancel4,time4);
                setUI(call3,cancel3,time3, box3,R.string.btncount3);
            }
        });
        cancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReverseUI(call3,cancel3,time3);
            }
        });

        box4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReverseUI(call1,cancel1,time1);
                setReverseUI(call2,cancel2,time2);
                setReverseUI(call3,cancel3,time3);
                setUI(call4,cancel4,time4, box4,R.string.btncount4);
            }
        });
        cancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReverseUI(call4,cancel4,time4);
            }
        });
    }

    private void setUI(ImageView call, ImageView cancel , TextView time, LinearLayout counter, int string){
        setVisible(call , false,false);
        setVisible(cancel , true,false);
        setVisible(time , true,true);
        UpdateTimeCounter(counter,time,string,call,cancel);
        /// ShowInterstitial();
    }

    private void setReverseUI(ImageView call, ImageView cancel , TextView time){
        setVisible(call , true,false);
        setVisible(cancel , false,false);
        setVisible(time , false,true);
        if (isRunning){
            timerdown.cancel();
        }
    }

    private void setVisible(View view, boolean isVisible, boolean isAnimate){
        if (isVisible){
            view.setVisibility(View.VISIBLE);
            if (isAnimate){
                Animation localAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_up);
                view.startAnimation(localAnimation);
            }
            return;
        }
        if (isAnimate){
            Animation localAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_down);
            view.startAnimation(localAnimation);
        }
        view.setVisibility(View.GONE);
    }

    private void setEnabled(View view, boolean isEnabel){
        if (isEnabel){
            view.setEnabled(true);
            return;
        }
        view.setEnabled(false);


    }

    private void setTimeMIN(int timeCase){
        timermilles = (1000*60)*timeCase;
        TimeLeft = (long) timermilles;

    }

    private void setTimeSEC(int timeCase){
        timermilles = 1000*timeCase;
        TimeLeft = (long) timermilles;

    }

    private void startTimer(final View btn, final TextView textView, String time, final ImageView call , final ImageView cancel) {

        setTimeCounter(time);
       // setEnabled(btn, false);

        timerdown = new CountDownTimer(TimeLeft, 1000) {
            public void onTick(long j) {

                isRunning = true;
                if (j != TimeLeft) {
                    TimeLeft = j;
                    updateCountDownText(textView);

                }
            }

            public void onFinish() {
                isRunning = false;
                setEnabled(btn, true);
                setVisible(textView , false,true);
                setVisible(call , true,false);
                setVisible(cancel , false,false);
                //Call A Random Hero :
                int[] list = new int[] {0, 1, 2, 3, 4, 5};
                int randomInt = list[new Random().nextInt(list.length)];
                Toast.makeText(getApplicationContext(), "Calling start", Toast.LENGTH_SHORT).show();
                if (finalsapi.randomcall){
                    makeCall(randomInt);
                    return;
                }
                makeCall(0);



            }
        }.start();
    }

    private void makeCall(int random){
        if (toolsAds.selectnetwork.equals("unity")){
            UnityBanners.destroy();
        }
        Intent intent = new Intent(getApplicationContext(), Activitycallerwaiting.class);
        intent.putExtra( Activitycallerwaiting.extraPosition, random);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void resetTimer(TextView textView) {
        TimeLeft = (long) timermilles;
        updateCountDownText(textView);
    }

    private void updateCountDownText(TextView textView) {
        int i = ((int) (TimeLeft / 1000)) / 60;
        int i2 = ((int) (TimeLeft / 1000)) % 60;
        textView.setText(String.format(Locale.getDefault(), "%02d:%02d", i, i2));
    }

    private void UpdateTimeCounter(LinearLayout linearLayout, TextView time, int text, ImageView call, ImageView cancel){

        if (isRunning) {
            timerdown.cancel();
        }

        if (getString(text).startsWith("10")){
            startTimer(linearLayout,time,"10s",call,cancel);
        } else if (getString(text).startsWith("20")){
            startTimer(linearLayout,time,"20s",call,cancel);
        }else if (getString(text).startsWith("30")){
            startTimer(linearLayout,time,"30s",call,cancel);
        }else if (getString(text).startsWith("40")){
            startTimer(linearLayout,time,"40s",call,cancel);
        }else if (getString(text).startsWith("50")){
            startTimer(linearLayout,time,"50s",call,cancel);
        }else if (getString(text).startsWith("60")){
            startTimer(linearLayout,time,"60s",call,cancel);
        }else if (getString(text).startsWith("70")){
            startTimer(linearLayout,time,"70s",call,cancel);
        }else if (getString(text).startsWith("80")){
            startTimer(linearLayout,time,"80s",call,cancel);
        }else if (getString(text).startsWith("90")){
            startTimer(linearLayout,time,"90s",call,cancel);
        }else if (getString(text).startsWith("1")){
            startTimer(linearLayout,time,"1m",call,cancel);
        }else if (getString(text).startsWith("2")){
            startTimer(linearLayout,time,"2m",call,cancel);
        }else if (getString(text).startsWith("3")){
            startTimer(linearLayout,time,"3m",call,cancel);
        }else if (getString(text).startsWith("4")){
            startTimer(linearLayout,time,"4m",call,cancel);
        }else if (getString(text).startsWith("5")){
            startTimer(linearLayout,time,"5m",call,cancel);
        }else if (getString(text).startsWith("6")){
            startTimer(linearLayout,time,"6m",call,cancel);
        }else if (getString(text).startsWith("7")){
            startTimer(linearLayout,time,"7m",call,cancel);
        }else if (getString(text).startsWith("8")){
            startTimer(linearLayout,time,"9m",call,cancel);
        }else if (getString(text).startsWith("10 M")){
            startTimer(linearLayout,time,"10m",call,cancel);
        }



    }

    private void setTimeCounter(String time){
        switch (time){
            case "10s" :
                setTimeSEC(10);
                break;
            case "20s" :
                setTimeSEC(20);
                break;
            case "30s" :
                setTimeSEC(30);
                break;
            case "40s" :
                setTimeSEC(40);
                break;
            case "50s" :
                setTimeSEC(50);
                break;
            case "60s" :
                setTimeSEC(60);
                break;
            case "70s" :
                setTimeSEC(70);
                break;
            case "80s" :
                setTimeSEC(80);
                break;
            case "90s" :
                setTimeSEC(90);
                break;

            case "1m" :
                setTimeMIN(1);
                break;
            case "2m" :
                setTimeMIN(2);
                break;
            case "3m" :
                setTimeMIN(3);
                break;
            case "4m" :
                setTimeMIN(4);
                break;
            case "5m" :
                setTimeMIN(5);
                break;
            case "6m" :
                setTimeMIN(6);
                break;
            case "7m" :
                setTimeMIN(7);
                break;
            case "8m" :
                setTimeMIN(8);
                break;
            case "9m" :
                setTimeMIN(9);
                break;
            case "10m" :
                setTimeMIN(10);
                break;

        }
    }

    private void loadNativeAdmob(final FrameLayout frameLayout, String nativeAds) {

        AdLoader.Builder builder = new AdLoader.Builder(this, nativeAds);
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                nativeAd = nativeAd;
                com.google.android.gms.ads.nativead.NativeAdView adView = (com.google.android.gms.ads.nativead.NativeAdView) getLayoutInflater().inflate(R.layout.ad_unified, null);
                populateUnifiedNativeAdView(nativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
                if (BuildConfig.DEBUG){
                    Log.d("motya", "native adMob loaded");
                }
            }
        });


        AdLoader adLoader = builder.withAdListener(new AdListener() {

        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());


    }

    private void populateUnifiedNativeAdView(com.google.android.gms.ads.nativead.NativeAd nativeAd, com.google.android.gms.ads.nativead.NativeAdView adView) {

        adView.setMediaView((com.google.android.gms.ads.nativead.MediaView) adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);
      //  VideoController vc = nativeAd.getVideoController();


    }

    private void loadNativeAdFB(final FrameLayout frameLayout , String nativeID) {

        nativeAdFacebook = new NativeAd(this, nativeID);

        nativeAdFacebook.loadAd((NativeAdBase.NativeLoadAdConfig) nativeAdFacebook.buildLoadAdConfig().withAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (nativeAdFacebook == null || nativeAdFacebook != ad) {
                    return;
                }
                try {
                    inflateAdFacebook(frameLayout,nativeAdFacebook);
                }catch (Exception e){

                    if (BuildConfig.DEBUG){
                        Log.d("motya","Somthing wrong about Native Facebook : "+e);
                    }
                }

                if (BuildConfig.DEBUG){
                    Log.d("motya","Native Facebook loaded");
                }
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        }));
    }

    private void inflateAdFacebook(FrameLayout frameLayout, NativeAd nativeAd) {
        nativeAd.unregisterView();
        NativeAdLayout nativeAdLayout = new NativeAdLayout(getApplicationContext());
        frameLayout.addView(nativeAdLayout);
        frameLayout.setVisibility(View.VISIBLE);
        View adView = NativeAdView.render(getApplicationContext(), nativeAd);
        nativeAdLayout.addView(adView, new ViewGroup.LayoutParams(MATCH_PARENT, 500));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (toolsAds.selectnetwork.equals("unity")){
            UnityBanners.destroy();
        }
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.unityprepare(this, toolsAds.unity_bannerads);
        }
    }
}
