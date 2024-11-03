package com.relapolires.ayaap;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;


import com.unity3d.services.banners.UnityBanners;

import java.util.List;


public class Activityacceptingcaller extends AppCompatActivity {

    Chronometer second_cal ;
    public static String extra2Position = "Contact2Position";
    int get2Extra ;

   RelativeLayout closecall;

   Tools_Ads toolsAds;
    ImageView btnnewcall, throwapp, vibirate, ushsshhh;
    ImageView imageperson;
    TextView avatar2, num_hero2 ;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activityacceptingcaller);
       toolsAds = (Tools_Ads)getApplicationContext();
       get2Extra = getIntent().getIntExtra(extra2Position, 0);
       initADS();
       initR();
       second_cal.start();
       setVoiceHero(get2Extra);


       closecall = findViewById(R.id.finish_call);

       closecall.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                toolsAds.stopSound();
               second_cal.stop();

               if (toolsAds.selectnetwork.equals("unity"))
               {
                  UnityBanners.destroy();
                   startActivity(new Intent(getApplicationContext(), ActivityContacting.class));
               }else {
                   startActivity(new Intent(getApplicationContext(), ActivityContacting.class));
                   finish();

               }
               ShowInterstitial();


           }
       });


       btnnewcall.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (toolsAds.selectnetwork.equals("unity")){
                   UnityBanners.destroy();
               }
               startActivity(new Intent(getApplicationContext(), ActivityContacting.class));

               toolsAds.stopSound();
               second_cal.stop();
               finish();
           }
       });



       throwapp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent sendIntent = new Intent();
               sendIntent.setAction(Intent.ACTION_SEND);
               sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text) + " *** DOWNLOAD!!! from Here ==> " + "https://play.google.com/store/apps/details?id=" + getPackageName());
               sendIntent.setType("text/plain");
               if (isAvailable(sendIntent)) {
                   startActivity(sendIntent);
               } else {
                   Toast.makeText( Activityacceptingcaller.this, "There is no app available for this task", Toast.LENGTH_SHORT).show();
               }
           }
       });

   }
public void initR(){
   btnnewcall = findViewById(R.id.add_call);
   throwapp = findViewById(R.id.send_app);
   vibirate = findViewById(R.id.add_vib);
   ushsshhh = findViewById(R.id.muet);

   imageperson = findViewById(R.id.img_hero);
   avatar2 = findViewById(R.id.user_name);
   num_hero2 = findViewById(R.id.user_num);

   second_cal = findViewById(R.id.second_cal);
}
    @Override
    protected void onResume() {
        super.onResume();
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.unityprepare(this, toolsAds.unity_bannerads);
        }
    }

   ToolsAds toolsAds_;
   private void initADS(){
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
        if (toolsAds.selectnetwork.equals("unity")){
            toolsAds_.interunity(this, toolsAds.unity_inter_ads);
        }else {

            toolsAds = (Tools_Ads)getApplicationContext();
            toolsAds.showInterstitial(this);}

    }


    private void setVoiceHero(int position){
        switch (position){
            case 0 :
                setUi(R.drawable.img1, setInfo(R.string.contact1),setInfo(R.string.contactnum1),R.raw.sound1);
                break;
            case 1 :
                setUi(R.drawable.img2, setInfo(R.string.contact2),setInfo(R.string.contactnum2),R.raw.sound2);
                break;
            case 2 :
                setUi(R.drawable.img3, setInfo(R.string.contact3),setInfo(R.string.contactnum3),R.raw.sound3);
                break;
            case 3 :
                setUi(R.drawable.img4, setInfo(R.string.contact4),setInfo(R.string.contactnum4),R.raw.sound4);
                break;
            case 4 :
                setUi(R.drawable.img5, setInfo(R.string.contact5),setInfo(R.string.contactnum5),R.raw.sound5);
                break;
            case 5 :
                setUi(R.drawable.img6, setInfo(R.string.contact6),setInfo(R.string.contactnum6),R.raw.sound6);
                break;
        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        if (toolsAds.selectnetwork.equals("unity")){
            UnityBanners.destroy();
        }
        toolsAds.stopSound();
    }


    public void playVoice(@RawRes final  int mRaw){
        toolsAds.setsoundavatar(getApplicationContext() ,mRaw);

    }


    //check app for share in your social media app :
    public boolean isAvailable(Intent intent) {
        final PackageManager mgr = getPackageManager();
        List<ResolveInfo> list =
                mgr.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private void setUi(int img, String name, String number, int aud){
        imageperson.setImageResource(img);
        avatar2.setText(name);
        num_hero2.setText(number);
        playVoice(aud);

    }
    private String setInfo(int info){
        return getString(info);
    }






}









