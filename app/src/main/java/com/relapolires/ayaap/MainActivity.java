package com.relapolires.ayaap;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.multidex.BuildConfig;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdView;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


public class MainActivity extends AppCompatActivity {


    CardView callme, vid, chat, contact, more, ratewe, shareit, counter;
    ImageView settingclick, stargiveme;
    AlertDialog mEuDialog;
    Tools_Ads toolsAds;
    public com.google.android.gms.ads.nativead.NativeAd ntv;
    public NativeAd ntvfb;
    public FrameLayout FL;
   // public RewardedAd rewardedAd;

    private CollapsingToolbarLayout collapsingLayout;
    Context context;

    public static final int MY_CAMERA_REQUEST_CODE = 100;
    ToolsAds toolsAds_;
    private RewardedAd mRewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        toolsAds_ =new ToolsAds(this);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
            }
        }
        Toolbar toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

           context=this;

            FL = findViewById(R.id.frame_ad);

          initads();



           initR();
       setNativeAd ();




        createAndLoadRewardedAd();




        //=======================================================================================

        //=========================================================================

    }



    /*
       //this for collapse title of the toolbar
        */
    private AppBarLayout.OnOffsetChangedListener onOffSetChangedListener = new AppBarLayout.OnOffsetChangedListener () {
        int scrollRang = -1;
        boolean isShowing = false;

        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout , int i) {
            if (scrollRang == -1)
                scrollRang = appBarLayout.getTotalScrollRange ();
            if (scrollRang + i == 0) {
               // collapsingLayout.setTitle ( getString ( R.string.app_name ) );

                isShowing = true;
            } else if (isShowing) {
                collapsingLayout.setTitle ( BuildConfig.FLAVOR );
                isShowing = false;
            }
        }
    };







public void initR(){
    toolsAds = (Tools_Ads)getApplicationContext();

}
    public void lockedUI(View v1 , View v2, boolean islocked){
        if (islocked){
            setVisible(v1,true);
            setVisible(v2,false);
            return;
        }
        setVisible(v1,false);
        setVisible(v2,true);
    }

    public void setVisible(View view, boolean isVisible){
        if (isVisible){
            view.setVisibility(View.VISIBLE);
            return;
        }
        view.setVisibility(View.GONE);
    }


    public RewardedAd createAndLoadRewardedAd() {
        toolsAds = (Tools_Ads)getApplicationContext();

        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, toolsAds.rewardsvideo,
                adRequest, new RewardedAdLoadCallback(){
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.


                        //Toast.makeText ( toolsAds , "reward failed"+loadAdError , Toast.LENGTH_SHORT ).show ();
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                     //   Toast.makeText ( toolsAds , "reward loaded" , Toast.LENGTH_SHORT ).show ();


                    }
                });





        return mRewardedAd;
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



    public void initads(){
        toolsAds = (Tools_Ads)getApplicationContext();
        RelativeLayout view = findViewById(R.id.adView);
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.initunity(toolsAds.Unity_id_ads, toolsAds.unity_testads, toolsAds.unity_bannerads,this);
            toolsAds_.unitybanner(this);

        }else {
            toolsAds.setBannerAd(view);

        }
    }
    public void gdprshow() {
        ConsentInformation consentInformation = ConsentInformation.getInstance(this);
        String[] publisherIds = {toolsAds.idadmob}; // enter your admob pub-id
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                switch (consentStatus) {
                    case PERSONALIZED:
                        ConsentInformation.getInstance(getApplicationContext()).setConsentStatus(ConsentStatus.PERSONALIZED);
                        initads();
                        break;
                    case NON_PERSONALIZED:
                        break;
                    case UNKNOWN:
                        if(ConsentInformation.getInstance(getBaseContext()).isRequestLocationInEeaOrUnknown()){
                            showdialog();
                        }
                        else {

                          initads();

                        }
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
            }
        });

    }
    public void showdialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder( MainActivity.this, R.style.MyAlertDialogStyle);
        LayoutInflater inflater = getLayoutInflater();
        View eu_consent_dialog = inflater.inflate(R.layout.consent_gdpr, null);
        alertDialog.setView(eu_consent_dialog).setCancelable(false);
        mEuDialog = alertDialog.create();
        mEuDialog.show();
        Button btn_eu_consent_yes = (Button) eu_consent_dialog.findViewById(R.id.button_yes);
        btn_eu_consent_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEuDialog.cancel();
                ConsentInformation.getInstance(getApplicationContext()).setConsentStatus(ConsentStatus.PERSONALIZED);
                initads();
            }

        });

        TextView learn_more = eu_consent_dialog.findViewById(R.id.consent);
        learn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consent_Privacy_URL = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.privacy_policy) ));
                if (isAvailable(consent_Privacy_URL)) {
                    startActivity(consent_Privacy_URL);
                }
                else {
                    Toast.makeText(getApplicationContext(), "There is no app availalbe for this task", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public boolean isAvailable(Intent intent) {
        final PackageManager mgr = getPackageManager();
        List<ResolveInfo> list =
                mgr.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }





    @Override
    public void onBackPressed() {

        AlertDialog.Builder alert = new AlertDialog.Builder( MainActivity.this);
        alert.setTitle(getString(R.string.text_rate));
        alert.setPositiveButton((getString(R.string.button_rate)), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id="
                                    + getPackageName())));
                }

                dialog.dismiss();
                finish();
            }
        });

        alert.create();
        alert.show();

    }


    @Override
    public void onResume() {
        super.onResume();
        if (toolsAds.selectnetwork.equals("unity")){

            toolsAds_.unityprepare(this, toolsAds.unity_bannerads);
        }
        gdprshow();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Opps..Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater ().inflate ( R.menu.main , menu );
//        for (int i = 0; i < menu.size (); i++) {
//            Drawable drawable = menu.getItem ( i ).getIcon ();
//            if (drawable != null) {
//                drawable.mutate ();
//                drawable.setColorFilter ( getResources ().getColor ( R.color.white ) , PorterDuff.Mode.SRC_ATOP );
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId ();
//
//        if (item.getItemId () == R.id.more) {
//
//            startActivity(new Intent(getApplicationContext(), Activityoption.class));
//        }
//
//
//        return super.onOptionsItemSelected ( item );
//
//    }


    public void settings(View view) {
        startActivity(new Intent(getApplicationContext(), Activityoption.class));

    }

    public void supportviarewardAds(View view) {
        //put reward video here
    }

    public void ratemeinMain(View view) {

        try {
            Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName()));
            startActivity(localIntent);
        } catch (ActivityNotFoundException localActivityNotFoundException) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public void one(View view) {
        switch (view.getId()) {

            case R.id.one:
               startActivity(new Intent(this, Activityvideowaiting.class));
                break;
            case R.id.two:
               startActivity(new Intent(this, Activitycallerwaiting.class));
                break;
            case R.id.three:
               startActivity(new Intent(this, ActivityChat.class));
                break;
            case R.id.four:
                startActivity(new Intent(this, ActivityContacting.class));
                break;
            case R.id.five:
                startActivity(new Intent(this, Activitytime.class));
                break;
            case R.id.six:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, context.getResources ().getString(R.string.share_text) + " *** DOWNLOAD!!! from Here ==> " + "https://play.google.com/store/apps/details?id=" + context.getPackageName());
                sendIntent.setType("text/plain");
               startActivity(sendIntent);


                break;
            case R.id.seven:
                try {
                    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName()));
                   startActivity(localIntent);
                } catch (ActivityNotFoundException localActivityNotFoundException) {
                   startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                }
                break;
            case R.id.eight:
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalsapi.PlayStoreDeveloper+ finalsapi.namedeveloperhere));
                startActivity(mIntent);

                break;






        }
    }

    public void showReward(View view) {

        if (mRewardedAd != null) {
            Activity activityContext = MainActivity.this;
            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Toast.makeText ( toolsAds , "Thanks for support" , Toast.LENGTH_SHORT ).show ();

                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                }
            });
        } else {
            Toast.makeText ( toolsAds , "Ads not loaded, please try later" , Toast.LENGTH_SHORT ).show ();
        }


    }
}




















