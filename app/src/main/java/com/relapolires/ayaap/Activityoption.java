package com.relapolires.ayaap;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import com.unity3d.services.banners.UnityBanners;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


public class Activityoption extends AppCompatActivity implements View.OnClickListener{
    Tools_Ads toolsAds;
    ImageView back ;
    public FrameLayout FL;
    public com.google.android.gms.ads.nativead.NativeAd ntv;
    public NativeAd ntvfb;
    LinearLayout rateing, sharing, contating, privacy, newapps;
    TextView app_name, version_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityoption);
        initads();

        FL = findViewById(R.id.frame_ad);

        app_name = (TextView) findViewById(R.id.app_name);
        version_name = (TextView) findViewById(R.id.version_name);
        app_name.setText(getResources().getString(R.string.app_name));
        version_name.setText("Version : 2");
        rateing =  findViewById(R.id.about_rate);
        sharing =  findViewById(R.id.about_share);
        newapps = findViewById(R.id.about_moreapps);
        contating =  findViewById(R.id.about_contact);
        privacy =  findViewById(R.id.about_privacy);
        back = findViewById(R.id.back);
        rateing.setOnClickListener(this);
        sharing.setOnClickListener(this);
        newapps.setOnClickListener(this);
        contating.setOnClickListener(this);
        privacy.setOnClickListener(this);
        back.setOnClickListener(this);

        setNativeAd();

    }

   ToolsAds toolsAds_;
    private void initads(){
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.about_rate:
                rate();
                break;

            case R.id.about_share:
                shhare();
                break;
            case R.id.about_moreapps:
                 moreap();
                break;

            case R.id.about_contact:
                contact();
                break;

            case R.id.about_privacy:
                privacyurl();
                break;
            case R.id.back:
                if (toolsAds.selectnetwork.equals("unity")){
                   UnityBanners.destroy();
                }
               finish();
                break;
        }

    }


    private void contact(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{finalsapi.sendmetoemail});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Improvement");
        intent.setType("text/plain");
        final PackageManager pm = getPackageManager();
        final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
        ResolveInfo best = null;
        for(final ResolveInfo info : matches)
            if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail"))
                best = info;
        if (best != null)
            intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
            startActivity(intent);


    }

    private void rate(){
        try {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalsapi.playstoreapps +getPackageName())));
        }
    }

    public boolean doesavilable(Intent intent) {
        final PackageManager mgr = getPackageManager();
        List<ResolveInfo> list = mgr.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
    private void moreap(){

        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalsapi.PlayStoreDeveloper+ finalsapi.namedeveloperhere));
        if (doesavilable(mIntent)) {
            startActivity(mIntent);
        } else {
            Toast.makeText(getApplicationContext(), finalsapi.NotAvailableMessage, Toast.LENGTH_SHORT).show();
        }


    }

    private void shhare(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text) + " *** DOWNLOAD!!! from Here ==> " + "https://play.google.com/store/apps/details?id=" + getPackageName());
        sendIntent.setType("text/plain");
        if (doesavilable(sendIntent)) {
            startActivity(sendIntent);
        } else {
            Toast.makeText( Activityoption.this, "There is no app availalbe for this task", Toast.LENGTH_SHORT).show();
        }

    }
    private void privacyurl(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            intent.setData(Uri.parse(finalsapi.shieldprivacy));
            startActivity(intent);
        } catch (Exception e) {
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (toolsAds.selectnetwork.equals("unity")){
            UnityBanners.destroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.unityprepare(this, toolsAds.unity_bannerads);
        }
    }

    public void setNativeAd(){



        //loadNativeAdFB(frameLayout,"173186393601951_356298065290782");
        if (toolsAds.selectnetwork.equals(toolsAds.admobads)) {
            try {
                loadNativeAdmob(FL, toolsAds.nativeadsadmob);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if (toolsAds.selectnetwork.equals(toolsAds.fbads)) {
            try {
                loadNativeAdFB(FL, toolsAds.nativeadsfacebook);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void loadNativeAdmob(final FrameLayout frameLayout, String nativeAds) {

        AdLoader.Builder builder = new AdLoader.Builder(this, nativeAds);
        builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull com.google.android.gms.ads.nativead.NativeAd nativeAd) {
                ntv = nativeAd;
                com.google.android.gms.ads.nativead.NativeAdView adView = ( com.google.android.gms.ads.nativead.NativeAdView ) getLayoutInflater().inflate(R.layout.ad_unified, null);
                populateUnifiedNativeAdView(nativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
                if (BuildConfig.DEBUG){
                    Log.d("motya", "native adMob loaded");
                }

                //Toast.makeText ( context , "native loade" , Toast.LENGTH_SHORT ).show ();

            }
        });


        AdLoader adLoader = builder.withAdListener(new AdListener() {

        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());


    }
    public void loadNativeAdFB(final FrameLayout frameLayout , String nativeID) {

        ntvfb = new NativeAd(this, nativeID);

        ntvfb.loadAd((NativeAdBase.NativeLoadAdConfig) ntvfb.buildLoadAdConfig().withAdListener(new NativeAdListener() {

            @Override
            public void onMediaDownloaded(Ad ad) {}
            @Override
            public void onError(Ad ad, AdError adError) {
                if (BuildConfig.DEBUG){
                    Log.d("motya","Native Facebook Failed to load");
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (ntvfb == null || ntvfb != ad) {
                    return;
                }
                try {
                    inflateAdFacebook(frameLayout, ntvfb);
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
            public void onAdClicked(Ad ad) {}
            @Override
            public void onLoggingImpression(Ad ad) {}

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
        //  VideoController vc = nativeAd.getVideoController();


    }


}
