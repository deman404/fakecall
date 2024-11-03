package com.relapolires.ayaap;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.unity3d.services.banners.UnityBanners;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


public class ActivityContacting extends AppCompatActivity implements View.OnClickListener {


    public NativeAd nativeAdFacebook;
    public FrameLayout frameLayout ;
    public ImageView back ;
    public Tools_Ads toolsAds;
    public ImageView ct1,ct2,ct3,ct4,ct5,ct6;

    @Override
    protected void onCreate(Bundle splash){
        super.onCreate(splash);
        setContentView(R.layout.activitycontacting);
        initads();
        contactinit();
        frameLayout = findViewById(R.id.frame_ad);
        back = findViewById(R.id.back);
        ntvad();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toolsAds.selectnetwork.equals("unity")) {
                    UnityBanners.destroy();
                }
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });

    }
   ToolsAds toolsAds_;

    public void initads(){
toolsAds_ =new ToolsAds (this);
        toolsAds = (Tools_Ads)getApplicationContext();
        RelativeLayout view = findViewById(R.id.adView);
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.initunity(toolsAds.Unity_id_ads, toolsAds.unity_testads, toolsAds.unity_bannerads,this);
         }else {
            toolsAds.setBannerAd(view);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (toolsAds.selectnetwork.equals("unity")) {
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


    public void ntvad(){


        if (toolsAds.selectnetwork.equals(toolsAds.admobads)) {
            try {
                ntvadmob(frameLayout, toolsAds.nativeadsadmob);
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

    public void contactinit(){

        ct1 = findViewById(R.id.call_contact1);
        ct2 = findViewById(R.id.call_contact2);
        ct3 = findViewById(R.id.call_contact3);
        ct4 = findViewById(R.id.call_contact4);
        ct5 = findViewById(R.id.call_contact5);
        ct6 = findViewById(R.id.call_contact6);

        ct1.setOnClickListener(this);
        ct2.setOnClickListener(this);
        ct3.setOnClickListener(this);
        ct4.setOnClickListener(this);
        ct5.setOnClickListener(this);
        ct6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.call_contact1:
                startingcalling(0);
                break;

            case R.id.call_contact2:
                startingcalling(1);
                break;

            case R.id.call_contact3:
                startingcalling(2);
                break;

            case R.id.call_contact4:
                startingcalling(3);
                break;

            case R.id.call_contact5:
                startingcalling(4);
                break;

            case R.id.call_contact6:
                startingcalling(5);
                break;
        }

    }

    public void startingcalling(int position){
        if (toolsAds.selectnetwork.equals("unity")){
            UnityBanners.destroy();
        }
        Intent intent = new Intent(getApplicationContext(), Activitycallerwaiting.class);
        intent.putExtra( Activitycallerwaiting.extraPosition, position);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
       /// ShowInterstitial();


    }

    public void ntvadmob(final FrameLayout frameLayout, String nativeAds) {

        AdLoader.Builder builder = new AdLoader.Builder(this, nativeAds);
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull com.google.android.gms.ads.nativead.NativeAd nativeAd) {

                nativeAd = nativeAd;
                com.google.android.gms.ads.nativead.NativeAdView adView = ( com.google.android.gms.ads.nativead.NativeAdView) getLayoutInflater().inflate(R.layout.ad_unified, null);
                populateUnifiedNativeAdView(nativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
            }
        });


        AdLoader adLoader = builder.withAdListener(new AdListener() {

        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());


    }
    public void loadNativeAdFB(final FrameLayout frameLayout , String nativeID) {

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

    public void inflateAdFacebook(FrameLayout frameLayout, NativeAd nativeAd) {
        nativeAd.unregisterView();
        NativeAdLayout nativeAdLayout = new NativeAdLayout(getApplicationContext());
        frameLayout.addView(nativeAdLayout);
        frameLayout.setVisibility(View.VISIBLE);
        View adView = NativeAdView.render(getApplicationContext(), nativeAd);
        nativeAdLayout.addView(adView, new ViewGroup.LayoutParams(MATCH_PARENT, 500));

    }

    public void populateUnifiedNativeAdView(com.google.android.gms.ads.nativead.NativeAd nativeAd, com.google.android.gms.ads.nativead.NativeAdView adView) {

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
       // VideoController vc = nativeAd.getVideoController();


    }




}
