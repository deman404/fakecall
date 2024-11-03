package com.relapolires.ayaap;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RawRes;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Tools_Ads extends Application {

    AdView mAdViewAdMob ;
    RelativeLayout relativeLayout ;

    com.facebook.ads.InterstitialAd mInterstitialFacebook ;
    com.facebook.ads.AdView mAdViewFacebook ;



    public String nativeadsadmob = "";
    public String nativeadsfacebook = "";
    public String Unity_id_ads = "";
    public String unity_inter_ads = "";
    public String unity_rewardads = "";
    public String unity_bannerads = "";
    public boolean unity_testads;

    InterstitialAd admobinterstital;
    public String selectnetwork ="admob";
     public static MediaPlayer player;

    public String admobads = "Admob";
    public String fbads = "facebook";
    public String unityads = "unity";
    public String idadmob = "";
    public String rewardsvideo ="";


    private static final String ONESIGNAL_APP_ID = "ce66e42d-acb3-4a45-9664-bae9ccb85660";


    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        new TaskingAd(finalsapi.url).execute();

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

    }



    public static void setBip(final Context context, @RawRes final int mRaw){

        player = MediaPlayer.create(context, mRaw);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setVolume(1f, 1f);
        try{
            player.prepare();

        }catch (Exception e){
            e.printStackTrace();
        }

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                player = null;
            }
        });

        player.start();
    }

    public void setsoundavatar(final Context context, @RawRes final int mRaw){

        player = MediaPlayer.create(context, mRaw);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setVolume(1f, 1f);
        try{
            player.prepare();

        }catch (Exception e){
            e.printStackTrace();
        }

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                player = null;
                setsoundavatar(context , mRaw);
            }
        });


        player.start();
    }
    public static void stopSound(){
        try {
            if(player != null) {
                player.stop();
                player.reset();
                player.release();
            }
        } catch (Exception e) {
         }
    }

    private class TaskingAd extends AsyncTask<String, String, String> {

        HttpURLConnection connection;
        URL url = null;
        File file ;
        String link ;

        public TaskingAd(String link) {
            this.link = link;
        }

        @Override
        protected String doInBackground(String... strings) {

            file = new File(getApplicationContext().getFilesDir().getPath() +"/motya.json");
            if (checkinternet()) {

                try {

                    url = new URL(link);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(15000);
                    connection.setConnectTimeout(10000);
                    connection.setRequestMethod("GET");

                } catch (IOException e1) {
                    e1.printStackTrace();
                    return e1.toString();
                }

                try {

                    int responseCode = connection.getResponseCode();

                    if (responseCode == HttpURLConnection.HTTP_OK) {

                        InputStream inputStream = connection.getInputStream();
                        return buffToString(new InputStreamReader(inputStream), true);

                    } else {

                        if (file.exists()) {
                            Log.d("motya" , "File Json exists : "+file);
                            return buffToString(new FileReader(file), false);
                        }

                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return e2.toString();
                } finally {
                    connection.disconnect();
                }

            } else {

                try {
                    return buffToString(new FileReader(file), false);

                } catch (IOException e) {
                    e.printStackTrace();
                    return e.toString();
                }
            }


            return "motya";
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject urlObject = new JSONObject(result);
                JSONObject jsObj = urlObject.getJSONObject(APIstati.jsonobject);
                JSONArray infoArray = jsObj.getJSONArray(APIstati.jsonarray);

                for (int j=0 ; j< infoArray.length() ; j++){

                    JSONObject info = infoArray.getJSONObject(j);

                    selectnetwork = info.getString(APIstati.jsonnetworkads);

                    idadmob = info.getString(APIstati.admobid);

                    nativeadsadmob =     info.getString(APIstati.nativeadmob); //"ca-app-pub-3940256099942544/2247696110";
                    rewardsvideo = info.getString(APIstati.jsonrewardvideo);

                    String BannerAd =     info.getString(APIstati.admobbanner); //"ca-app-pub-3940256099942544/6300978111";
                    String InterstitialAd =    info.getString(APIstati.jsoninterstital); // "ca-app-pub-3940256099942544/1033173712";

                    String BannerFb = info.getString(APIstati.jsonbannerfb);
                    String InterstitialFb = info.getString(APIstati.jsoninterfb);
                    nativeadsfacebook = info.getString(APIstati.jsonnativefb);


                      unity_bannerads = info.getString( "Unity_Banner");
                      Unity_id_ads = info.getString( "Unity_GameId");
                      unity_inter_ads = info.getString( "Unity_Interstitial");
                      unity_rewardads = info.getString( "Unity_Reward");
                      unity_testads = info.getBoolean( "Unity_Test");



                    if (selectnetwork.equals(admobads)){
                        try {
                            RequestAdMobAd(BannerAd,InterstitialAd);
                            requestfacebook(BannerFb,InterstitialFb);
                        }catch (Exception e){
                            Log.d("motya" , "Faild to Build Request Admob : "+e);
                        }

                    }else if (selectnetwork.equals(fbads)){
                        try {
                            requestfacebook(BannerFb,InterstitialFb);
                        }catch (Exception e){
                        }

                    }


                }


            } catch (JSONException e) {


            }
        }


    }
    AdRequest adRequest;
    public void RequestAdMobAd(String banner , String Interstitial){
        MobileAds.initialize(this);

        adRequest  = new AdRequest.Builder().build();

        InterstitialAd.load(this,Interstitial, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                admobinterstital = interstitialAd;

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error

                admobinterstital = null;
                adRequest  = new AdRequest.Builder().build();

            }
        });


        mAdViewAdMob = new AdView(this);
        mAdViewAdMob.setAdSize(AdSize.BANNER);
        mAdViewAdMob.setAdUnitId(banner);
        AdRequest adRequestBanner =  new AdRequest
                .Builder()

                .build();
        mAdViewAdMob.loadAd(adRequestBanner);
        mAdViewAdMob.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

                if (relativeLayout != null){
                    setBannerAd(relativeLayout);
                }

            }

        });



    }

    public void requestfacebook(String banner , String Interstitial){
         AudienceNetworkAds.initialize(this);

         mAdViewFacebook = new com.facebook.ads.AdView(this, banner, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        mAdViewFacebook.loadAd((com.facebook.ads.AdView.AdViewLoadConfig) mAdViewFacebook.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.d("motya" , "Banner Facebook on Loaded");

                if (relativeLayout != null){
                    setBannerAd(relativeLayout);
                }
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        }));


         mInterstitialFacebook = new com.facebook.ads.InterstitialAd(this, Interstitial);

        mInterstitialFacebook.loadAd((com.facebook.ads.InterstitialAd.InterstitialLoadAdConfig) mInterstitialFacebook.buildLoadAdConfig().withAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        }));
    }


     public void showInterstitial(Activity activity){
        createinterstital(selectnetwork,activity);
    }


    public void setBannerAd(RelativeLayout bannerView) {
        createbanner(selectnetwork, bannerView);
    }

    private void createinterstital(String checking,Activity activity){

        if (checking.equals(admobads)) {
             if (admobinterstital != null) {
                admobinterstital.show(activity);
            }
        } else if (checking.equals(fbads)) {
             if (mInterstitialFacebook != null && mInterstitialFacebook.isAdLoaded()) {
                mInterstitialFacebook.show();
            }

        }

    }
    private void createbanner(String check, RelativeLayout relativeLayout){

        if (check.equals(admobads)){

            if (mAdViewAdMob == null) {
                return;
            }
            if (mAdViewAdMob.getParent() != null){
                ((ViewGroup) mAdViewAdMob.getParent()).removeView(mAdViewAdMob);
            }
            relativeLayout.removeAllViews();
            relativeLayout.addView(mAdViewAdMob);
            relativeLayout.invalidate();

        }else if (check.equals(fbads)){

            if (mAdViewFacebook == null) {
                return;
            }
            if (mAdViewFacebook.getParent() != null){
                ((ViewGroup) mAdViewFacebook.getParent()).removeView(mAdViewFacebook);
            }
            relativeLayout.removeAllViews();
            relativeLayout.addView(mAdViewFacebook);
            relativeLayout.invalidate();


        }

    }

    protected String buffToString(Reader ourReader, boolean save) {
        try {
            BufferedReader reader = new BufferedReader(ourReader);
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            if (save) {
                if (!result.toString().equals(null)) {
                    writejson(result.toString(), Tools_Ads.this);
                }
            }

            return (result.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
    private void writejson(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("motya.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean checkinternet() {
        final ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) {

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {

                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

                return true;
            }

            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {

                return true;
            }
        }
        return false;
    }



}
