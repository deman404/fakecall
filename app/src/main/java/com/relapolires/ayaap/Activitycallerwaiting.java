package com.relapolires.ayaap;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.services.banners.UnityBanners;


public class Activitycallerwaiting extends AppCompatActivity {
    ImageView hero;
    TextView heroName, HeroNum;


    Tools_Ads toolsAds;
    public static String extraPosition = "ContactPosition";
    int getExtra ;
    MediaPlayer call_song;
    RelativeLayout accept_call,refuse_call;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitycallerwaiting);
        adsshow();
init();
        getExtra = getIntent().getIntExtra(extraPosition, 0);

        charcterui(getExtra);

        songringing();

        accept_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call_song.stop();
                call_song.release();
                call_song = null;
                AcceptPosition(getExtra);
                //startActivity(new Intent(getApplicationContext(), Accept_Call.class));

            }
        });


        refuse_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call_song.stop();
                call_song.release();
                call_song = null;
                ShowInterstitial();

                if (toolsAds.selectnetwork.equals("unity"))
                {
                    UnityBanners.destroy();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else {
                    finish();

                }

            }
        });


    }

public void init(){
    hero = findViewById(R.id.img_hero_1) ;
    heroName = findViewById(R.id.name_hero_1);
    HeroNum = findViewById(R.id.num_hero_1);
    accept_call = findViewById(R.id.accept_call);  refuse_call = findViewById(R.id.call_refus);

}


    private void setUi(int img, String name , String number){
        hero.setImageResource(img);
        heroName.setText(name);
        HeroNum.setText(number);
    }
    private String throwinfo(int info){
        return getString(info);
    }

    private void AcceptPosition(int position){
        if (toolsAds.selectnetwork.equals("unity"))
        {
            UnityBanners.destroy();
        }
        Intent intent = new Intent(getApplicationContext(), Activityacceptingcaller.class);
        intent.putExtra(Activityacceptingcaller.extra2Position , position);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (toolsAds.selectnetwork.equals("unity")){
            UnityBanners.destroy();
        }
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

        call_song.stop();
        call_song.release();
        call_song = null;
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.unityprepare(this, toolsAds.unity_bannerads);
        }
    }

   ToolsAds toolsAds_;
    private void adsshow(){
        toolsAds_ =new ToolsAds (this);
        toolsAds = (Tools_Ads)getApplicationContext();
        RelativeLayout view = findViewById(R.id.adView);
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.initunity(toolsAds.Unity_id_ads, toolsAds.unity_testads, toolsAds.unity_bannerads,this);
        }else {
            toolsAds.setBannerAd(view);

        }
    }
    public void ShowInterstitial(){
        toolsAds = (Tools_Ads)getApplicationContext();
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.interunity(this, toolsAds.unity_inter_ads);
        }else {

            toolsAds.showInterstitial(this);
        }

    }



    public void songringing(){

        call_song = MediaPlayer.create(getBaseContext(), R.raw.ringtune);
        call_song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                songringing();
            }
        });
        try {
            call_song.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }
        call_song.start();

    }


    private void charcterui(int position){
        switch (position){
            case 0 :
                setUi(R.drawable.img1, throwinfo(R.string.contact1), throwinfo(R.string.contactnum1));
                break;
            case 1 :
                setUi(R.drawable.img2, throwinfo(R.string.contact2), throwinfo(R.string.contactnum2));
                break;
            case 2 :
                setUi(R.drawable.img3, throwinfo(R.string.contact3), throwinfo(R.string.contactnum3));
                break;
            case 3 :
                setUi(R.drawable.img4, throwinfo(R.string.contact4), throwinfo(R.string.contactnum4));
                break;
            case 4 :
                setUi(R.drawable.img5, throwinfo(R.string.contact5), throwinfo(R.string.contactnum5));
                break;
            case 5 :
                setUi(R.drawable.img6, throwinfo(R.string.contact6), throwinfo(R.string.contactnum6));
                break;
        }

    }

}

