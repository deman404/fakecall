package com.relapolires.ayaap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;


public class ToolsAds {
    Context contaxt;
    public LinearLayout adView;

    String TAG = "showads";

public ToolsAds(Context ctxhere){
    this.contaxt =ctxhere;
}

    UnityBannerListener unityBannerListener;
Activity ctx_unityy;
    public void initunity(String unityGameID, Boolean testMode, String banner_id, Activity ctx_unity) {
        try {
            ctx_unityy=ctx_unity;
            UnityInterstitalAdsListener    interstitalAdsListener = new UnityInterstitalAdsListener(banner_id,ctx_unity);
            UnityAds.addListener(interstitalAdsListener);
            UnityAds.initialize(ctx_unityy, unityGameID, testMode);
            unityBannerListener = new UnityBannerListener(ctx_unity);
            UnityBanners.setBannerListener(unityBannerListener);
        }catch (Exception e){
        }

    }
    public void unitybanner(Activity ctxa ) {
        try {

            unityBannerListener = new UnityBannerListener(ctxa);
            UnityBanners.setBannerListener(unityBannerListener);
        }catch (Exception e){
        }

    }
    public void unityprepare(Activity t, String bannerid){
        if (UnityAds.isInitialized()) {
            UnityBanners.setBannerListener(unityBannerListener);
            UnityBanners.loadBanner(t, bannerid);
        }
    }
    public void interunity(Activity ctf, String inter) {
        UnityAds.show(ctf, inter);


    }

    public class UnityBannerListener implements IUnityBannerListener {
        Activity activity;
public UnityBannerListener(Activity activity){
    this.activity=activity;
}
        @Override
        public void onUnityBannerLoaded(String s, View view) {

            ((ViewGroup)activity. findViewById(R.id.adView)).removeView(view);
            ((ViewGroup) activity.findViewById(R.id.adView)).addView(view);
        }

        @Override
        public void onUnityBannerUnloaded(String s) {
            Log.d(TAG, "onUnityBannerUnloaded: ");        }

        @Override
        public void onUnityBannerShow(String s) {
            Log.d(TAG, "onUnityBannerShow: ");

        }

        @Override
        public void onUnityBannerClick(String s) {
            Log.d(TAG, "onUnityBannerClick: ");
        }

        @Override
        public void onUnityBannerHide(String s) {
            Log.d(TAG, "onUnityBannerHide: ");
        }

        @Override
        public void onUnityBannerError(String s) {
            Log.d(TAG, "onUnityBannerError: "+ s);
        }
    }
    public int select_activity_int;

    public int select_activity( ){
        return  select_activity_int;
    }
    public class UnityInterstitalAdsListener implements IUnityAdsListener {
        String ban ;
        Activity ctxs;


        public UnityInterstitalAdsListener(String bn, Activity ctx) {
            this.ban = bn;
ctxs=ctx;


        }

        @Override
        public void onUnityAdsReady(String s) {


        }

        @Override
        public void onUnityAdsStart(String s) {

        }

        @Override
        public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
 if (select_activity()==1){
    select_activity_int=0;
   ctxs. startActivity(new Intent(ctxs, Activitycallerwaiting.class));

}else if (select_activity()==2){
    select_activity_int=0;
    ctxs. startActivity(new Intent(ctxs, Activityvideowaiting.class));

}

        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
             if (select_activity()==1){
                ctxs. startActivity(new Intent(ctxs, Activitycallerwaiting.class));

            }else if (select_activity()==2){
                ctxs. startActivity(new Intent(ctxs, Activityvideowaiting.class));

            }

        }
    }
}